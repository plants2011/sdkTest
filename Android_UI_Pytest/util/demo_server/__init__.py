import hashlib
import json
import logging
import os
import socket
import subprocess
import threading
import time

import urllib3
from uiautomator import Adb

from util.demo_server import constants

try:
    import urllib2
except ImportError:
    import urllib.request as urllib2
try:
    from httplib import HTTPException
except:
    from http.client import HTTPException

log = logging.getLogger(__name__)

DEFAULT_SERVER_PORT=9001
LOCAL_PORT = 19001

_init_local_port = LOCAL_PORT - 1

def next_local_port(adbHost=None):
    def is_port_listening(port):
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        result = s.connect_ex((str(adbHost) if adbHost else '127.0.0.1', port))
        s.close()
        return result == 0
    global _init_local_port
    _init_local_port = _init_local_port + 1 if _init_local_port < 32764 else LOCAL_PORT
    while is_port_listening(_init_local_port):
        _init_local_port += 1
    return _init_local_port

class JsonRPCError(Exception):

    def __init__(self, code, message):
        self.code = int(code)
        self.message = message

    def __str__(self):
        return "JsonRPC Error code: %d, Message: %s" % (self.code, self.message)


class JsonRPCMethod(object):

    if os.name == 'nt':
        try:
            pool = urllib3.PoolManager()
        except:
            pass

    def __init__(self, url, method, timeout=30):
        self.url, self.method, self.timeout = url, method, timeout

    def __call__(self, *args, **kwargs):
        if args and kwargs:
            raise SyntaxError("Could not accept both *args and **kwargs as JSONRPC parameters.")
        data = {"jsonrpc": "2.0", "method": self.method, "id": self.id()}
        if args:
            data["params"] = args
        elif kwargs:
            data["params"] = kwargs
        #打印过程日志
        if (self.method == "getGameProcess"):
            log.debug("send requests: [%s] %s"%(self.method, json.dumps(data)))
        else:
            log.info("send requests: [%s] %s"%(self.method, json.dumps(data)))
        jsonresult = {"result": ""}
        if os.name == "nt":
            res = self.pool.urlopen("POST",
                                    self.url,
                                    headers={"Content-Type": "application/json"},
                                    body=json.dumps(data).encode("utf-8"),
                                    timeout=self.timeout)
            jsonresult = json.loads(res.data.decode("utf-8"))
        else:
            result = None
            try:
                req = urllib2.Request(self.url,
                                      json.dumps(data).encode("utf-8"),
                                      {"Content-type": "application/json"})
                result = urllib2.urlopen(req, timeout=self.timeout)
                jsonresult = json.loads(result.read().decode("utf-8"))
            finally:
                if result is not None:
                    result.close()
        if "error" in jsonresult and jsonresult["error"]:
            if "data" in jsonresult["error"]:
                errMsg = "%s: %s" % (jsonresult["error"]["data"]["exceptionTypeName"], jsonresult["error"]["message"])
            else:
                errMsg = "%s: %s" % (self.method, jsonresult["error"]["message"])

            raise JsonRPCError(
                jsonresult["error"]["code"], errMsg
            )
        return jsonresult["result"]

    def id(self):
        m = hashlib.md5()
        m.update(("%s at %f" % (self.method, time.time())).encode("utf-8"))
        return m.hexdigest()


class JsonRPCClient(object):

    def __init__(self, url, timeout=30, method_class=JsonRPCMethod):
        self.url = url
        self.timeout = timeout
        self.method_class = method_class

    def __getattr__(self, method):
        return self.method_class(self.url, method, timeout=self.timeout)

class DemoAppJsonRpcServer(object):
    def __init__(self, serial=None):
        self.uiautomator_process = None
        self.adb = Adb(serial=serial)
        self.server_port = DEFAULT_SERVER_PORT
        try:  # first we will try to use the local port already adb forwarded
            for s, lp, rp in self.adb.forward_list():
                if s == self.adb.device_serial() and rp == 'tcp:%d' % self.server_port:
                    self.local_port = int(lp[4:])
                    break
            else:
                self.local_port = next_local_port()
        except:
            self.local_port = next_local_port()
        self.start()

    def adb_shell(self, cmd):
        outputs = self.adb.cmd("shell", cmd).communicate()
        output_lines = []
        for output in outputs:
            output_lines.append(output.decode("UTF-8"))
        return "\n".join(output_lines)
    def enableWifi(self, enabled=True):
        res = None
        if enabled == True:
            res = self.adb_shell("svc wifi enable")
        else:
            res = self.adb_shell("svc wifi disable")
        if res.strip() != "":
            return False, res
        return True, ""

    def getDemoPid(self):
        pid = self.adb_shell("ps -ef | grep com.hm.qa.sdkdemoserver | awk '{print $2}'")
        return pid.strip()

    def _logcatWatching(self):
        while self.proc is not None:
            if self.proc == None:
                break
            next_line = self.proc.stdout.readline()
            return_line = next_line.decode("utf-8", "ignore")
            if return_line == "" and self.proc.poll() != None:
                break
            if self.lines == None:
                self.lines = []
            self.lines.append(return_line)

    def startLogcatWatching(self, grepCmd = None):
        self.adb.cmd("shell", "logcat -c").wait()
        if grepCmd != None:
            grepCmd = "| %s"%grepCmd
        else:
            grepCmd = ""
        log.info('adb shell logcat --pid %s %s'% (self.getDemoPid(), grepCmd))
        self.proc = self.adb.cmd('shell', 'logcat --pid %s %s'% (self.getDemoPid(), grepCmd))
        self.lines = []
        threading.Thread(target=self._logcatWatching, daemon=True).start()
        pass

    def getLogcatWatchLines(self):
        self.proc.kill()
        self.proc = None
        return self.lines


    def start(self, timeout=5):
        cmd = ["shell", "am", "start", "-n", "com.hm.qa.sdkdemoserver/.MainActivity"]
        log.info(" ".join(cmd))
        self.uiautomator_process = self.adb.cmd(*cmd).wait()
        log.info("adb forward %d %d"%(self.local_port, self.server_port))
        self.adb.forward(self.local_port, self.server_port)
        while not self.alive and timeout > 0:
            time.sleep(0.2)
            timeout -= 0.1
        if not self.alive:
            raise IOError("RPC server not started!")
        pass
    def stop(self):
        self.adb.cmd(*["shell", "am", "force-stop", "com.hm.qa.sdkdemoserver"]).wait()
        pass

    def __jsonrpc(self):
        return JsonRPCClient(self.rpc_uri, timeout=int(os.environ.get("JSONRPC_TIMEOUT", 30)))

    def ping(self):
        try:
            return self.__jsonrpc().ping()
        except:
            return None

    @property
    def alive(self):
        '''Check if the rpc server is alive.'''
        return self.ping() == "pong"
    @property
    def rpc_uri(self):
        return "http://%s:%d/jsonrpc/hm_sdk" % (self.adb.adb_server_host, self.local_port)

    @property
    def jsonrpc(self):
        return self.jsonrpc_wrap(timeout=int(os.environ.get("jsonrpc_timeout", 30)))

    def jsonrpc_wrap(self, timeout):
        server = self
        ERROR_CODE_BASE = -32000

        def _JsonRPCMethod(url, method, timeout, restart=True):
            _method_obj = JsonRPCMethod(url, method, timeout)

            def wrapper(*args, **kwargs):
                URLError = urllib3.exceptions.HTTPError if os.name == "nt" else urllib2.URLError
                try:
                    return _method_obj(*args, **kwargs)
                except (URLError, socket.error, HTTPException) as e:
                    if restart:
                        server.stop()
                        server.start(timeout=30)
                        return _JsonRPCMethod(url, method, timeout, False)(*args, **kwargs)
                    else:
                        raise
                except JsonRPCError as e:
                    if e.code >= ERROR_CODE_BASE - 1:
                        server.stop()
                        server.start()
                        return _method_obj(*args, **kwargs)
                    elif e.code == ERROR_CODE_BASE - 2 and self.handlers['on']:  # Not Found
                        try:
                            self.handlers['on'] = False
                            # any handler returns True will break the left handlers
                            any(handler(self.handlers.get('device', None)) for handler in self.handlers['handlers'])
                        finally:
                            self.handlers['on'] = True
                        return _method_obj(*args, **kwargs)
                    raise
            return wrapper

        return JsonRPCClient(self.rpc_uri,
                             timeout=timeout,
                             method_class=_JsonRPCMethod)

WAIT_DEFAULT_TIMEOUT= 10

class SDKAutomatorServer(object):
    def __init__(self, serial=None):
        self.server = DemoAppJsonRpcServer(serial)
        pass
    def __wait_for_sceneChange(self, sceneChanges, timeout=WAIT_DEFAULT_TIMEOUT):
        timeout = time.time() + timeout
        allStatuses = []
        while time.time() < timeout:
            allStatuses = []
            proc = self.server.jsonrpc.getGameProcess()
            if 'process' in proc.keys() and proc['process']['playerError'] != None:
                raise Exception("发现错误: %s, %s"%(proc['process']['playerError']['errCode'], proc['process']['playerError']['errMsg']))
            if proc["result"] == True:
                statuses = proc['process']['sceneChanges']
                for status in statuses:
                    statusObj = json.loads(status)
                    allStatuses.append(statusObj['sceneId'])
            foundCount = 0
            for change in sceneChanges:
                if change in allStatuses:
                    foundCount += 1
            if foundCount >= len(sceneChanges):
                return proc
            time.sleep(1)
        log.error("sceneChanges status not found: expect %s, actual %s", sceneChanges, allStatuses)
        assert "播流等待场景变化超时失败" == ""
        return {"result": False, "process": proc['process']}
    def __wait_for_status(self, statusLst, timeout = WAIT_DEFAULT_TIMEOUT):
        timeout = time.time() + timeout
        allStatuses = []
        while time.time() < timeout:
            allStatuses = []
            proc = self.server.jsonrpc.getGameProcess()
            if 'process' in proc.keys() and proc['process']['playerError'] != None:
                raise Exception("发现错误: %s, %s"%(proc['process']['playerError']['errCode'], proc['process']['playerError']['errMsg']))
            if proc["result"] == True:
                statuses = proc['process']["playerStatuses"]
                for status in statuses:
                    allStatuses.append(status['status'])
            foundCount = 0
            for status in statusLst:
                if status in allStatuses:
                    foundCount += 1
            if foundCount >= len(statusLst):
                return proc
            time.sleep(1)

        log.error("process status not found: expect %s, actual %s", statusLst, allStatuses)
        assert "播流等待超时失败" == ""
        return {"result": False, "process": proc["process"]}

    #检查是否有运行错误
    def check_player_error(self):
        proc = self.server.jsonrpc.getGameProcess()
        if 'process' in proc.keys() and proc['process']['playerError'] != None:
            raise Exception("发现错误: %s, %s"%(proc['process']['playerError']['errCode'], proc['process']['playerError']['errMsg']))
    #等待某个状态，1s检查一次，直到等到
    def wait_status(self, statusLst, timeout=WAIT_DEFAULT_TIMEOUT):
        return self.__wait_for_status(statusLst, timeout)
    #等待某个场景变化，1s检查一次，直到等到
    def wait_sceneChanges(self, sceneChanges, timeout=WAIT_DEFAULT_TIMEOUT):
        return self.__wait_for_sceneChange(sceneChanges, timeout)
    #检查某个状态是否存在
    def check_status_exist(self, checkStatus):
        allStatuses = []
        proc = self.server.jsonrpc.getGameProcess()
        if 'process' in proc.keys() and proc['process']['playerError'] != None:
            raise Exception("发现错误: %s, %s"%(proc['process']['playerError']['errCode'], proc['process']['playerError']['errMsg']))
        if proc["result"] == True:
            statuses = proc['process']["playerStatuses"]
            for status in statuses:
                allStatuses.append(status['status'])

        if checkStatus in allStatuses:
            return True
        else:
            return False
    def __checkResult(self, resp):
        log.info("CHECK_RESULT: %s"%resp)
        assert(resp["result"] is True)
        return resp
    #---------------------
    #以下是所有SDK侧提供的接口封装，见SDK的客户文档，以plugIn插件为例做的实现，
    #如有未实现接口，请联系陈雷&申苏鲁实现接口并做添加 @chenlei @shensulu
    def initHmcpManager(self, userBaseInfo):
        if type(userBaseInfo).__name__ != "UserBaseInfo":
            raise Exception("param format error")
        return self.server.jsonrpc.initHmcpManager(dict(userBaseInfo))
    def init(self, userBaseInfo, gameBaseInfo):
        if type(userBaseInfo).__name__ != "UserBaseInfo":
            raise Exception("param format error")
        if type(gameBaseInfo).__name__ != "GameBaseInfo":
            raise Exception("param format error")
        return self.server.jsonrpc.init(dict(userBaseInfo), dict(gameBaseInfo))
    def pauseGame(self):
        self.__checkResult(self.server.jsonrpc.pauseGame())
        return self.__wait_for_status([constants.STATUS_PAUSE_PLAY])
    def reconnect(self):
        self.__checkResult(self.server.jsonrpc.reconnect())
        return self.__wait_for_status([constants.STATUS_START_PLAY])
    def restartGame(self, playTime=None):
        self.__checkResult(self.server.jsonrpc.restartGame(playTime))
        return self.__wait_for_status([constants.STATUS_START_PLAY])
    def startPlay(self):
        self.__checkResult(self.server.jsonrpc.startPlay())
        return self.__wait_for_status([constants.STATUS_START_PLAY])
    def startPlayAhead(self, isAhead=False):
        self.__checkResult(self.server.jsonrpc.startPlayAhead(isAhead))
        return self.__wait_for_status([constants.STATUS_START_PLAY])
    def quitGame(self):
        return self.__checkResult(self.server.jsonrpc.quitGame())
    def stopAndDismiss(self, minArchiveSecond=10):
        self.__checkResult(self.server.jsonrpc.stopAndDismiss(minArchiveSecond))
        return self.__wait_for_status([constants.STATUS_STOP_PLAY])
    def getResolutionDatas(self):
        return self.__checkResult(self.server.jsonrpc.getResolutionDatas())
    def onSwitchResolution(self, level, resolutionInfo, rate):
        if type(resolutionInfo).__name__ != "ResolutionInfo":
            raise Exception("param format error")
        self.__checkResult(self.server.jsonrpc.onSwitchResolution(level, dict(resolutionInfo), rate))
        return self.__wait_for_status([constants.STATUS_SWITCH_RESOLUTION])
    def entryQueue(self):
        return self.__checkResult(self.server.jsonrpc.entryQueue())
    def exitQueue(self):
        return self.__checkResult(self.server.jsonrpc.exitQueue())
    def getVideoLatency(self):
        return self.server.jsonrpc.getVideoLatency()
    def updateGameUid(self, updateGameUid):
        if type(updateGameUid).__name__ != "UpdateGameUid":
            raise Exception("param format error")
        return self.__checkResult(self.server.jsonrpc.updateGameUid(dict(updateGameUid)))
    def getInputUrl(self):
        return self.server.jsonrpc.getInputUrl()
    def getSdkVersion(self):
        return self.server.jsonrpc.getSdkVersion()
    def getResolutionInfos(self, packageName, channelId):
        return self.__checkResult(self.server.jsonrpc.getResolutionInfos(packageName, channelId))
    def getQrCodeData(self):
        return self.server.jsonrpc.getQrCodeData()
    def backToGame(self):
        return self.__checkResult(self.server.jsonrpc.backToGame())
    def getClockDiffVideoLatencyInfo(self):
        return self.server.jsonrpc.getClockDiffVideoLatencyInfo()
    def release(self):
        return self.__checkResult(self.server.jsonrpc.release())
    def getLastUserOperationTimestamp(self):
        return self.server.jsonrpc.getLastUserOperationTimestamp()
    def resetInputTimer(self, needUpdate):
        return self.server.jsonrpc.resetInputTimer(needUpdate)
    def checkBitmap(self, percent=0.9):
        return self.server.jsonrpc.checkBitmap(percent)
    def setAudioMute(self, isMute):
        return self.server.jsonrpc.setAudioMute(isMute)
    def getStreamUrl(self):
        return self.server.jsonrpc.getStreamUrl()
    def getCloudId(self):
        return self.server.jsonrpc.getCloudId()
    def getGameArchiveStatus(self, packageName, userInfo):
        if type(userInfo).__name__ != "UserInfo":
            raise Exception("param format error")
        return self.__checkResult(self.server.jsonrpc.getGameArchiveStatus(packageName, dict(userInfo)))
    def gameArchived(self, packageName, userBaseInfo):
        if type(userBaseInfo).__name__ != "UserBaseInfo":
            raise Exception("param format error")
        return self.__checkResult(self.server.jsonrpc.gameArchived(packageName, dict(userBaseInfo)))
    def checkPlayingGame(self, userInfo):
        if type(userInfo).__name__ != "UserInfo":
            raise Exception("param format error")
        return self.__checkResult(self.server.jsonrpc.checkPlayingGame(dict(userInfo)))
    def getCloudPlayStatusCode(self):
        return self.server.jsonrpc.getCloudPlayStatusCode()
    def startLiving(self, livingId, livingUrl):
        return self.__checkResult(self.server.jsonrpc.startLiving(livingId, livingUrl))
    def stopLiving(self, livingId):
        return self.__checkResult(self.server.jsonrpc.stopLiving(livingId))
    def inputText(self, text):
        return self.server.jsonrpc.inputText(text)
    def getPinCode(self):
        return self.__checkResult(self.server.jsonrpc.getPinCode())
    def getRenderType(self):
        return self.__checkResult(self.server.jsonrpc.getRenderType())
    def setReleaseCid(self, packageName, cid, userBaseInfo):
        if type(userBaseInfo).__name__ != "UserBaseInfo":
            raise Exception("param format error")
        return self.__checkResult(self.server.jsonrpc.setReleaseCid(packageName, cid, dict(userBaseInfo)))
    def sendMessage(self,payload,messageType):
        return self.__checkResult(self.server.jsonrpc.sendMessage(payload, messageType))
    def sendGps(self, gpsData):
        if type(gpsData).__name__ != "GpsData":
            raise Exception("param format error")
        return self.sendWsMessage(str(gpsData), constants.WS_MESSAGE_TYPE_GPS)
    def sendClipboard(self, text):
        return self.sendWsMessage(text, constants.WS_MESSAGE_TYPE_CLIPBOARD)
    def sendWsMessage(self,payload, messageType):
        return self.__checkResult(self.server.jsonrpc.sendWsMessage(payload, messageType))
    #其他扩展接口也可以通过这个__getattr__临时使用，但是不保证接口入参出参的状态检查
    def __getattr__(self, method):
        return getattr(self.server.jsonrpc, method)

demoServer = SDKAutomatorServer
