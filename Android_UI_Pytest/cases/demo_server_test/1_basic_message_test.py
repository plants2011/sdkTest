import logging
import time

from util.conf import config
from util.demo_server import demoServer, constants
from util.demo_server.data import UserBaseInfo, GameBaseInfo, GpsData

log = logging.getLogger(__name__)

demo = demoServer()
def getUserInfo():
    userInfo = UserBaseInfo()
    userInfo.saasUrl = config.config_get("demo_server.ini", "test", "stage_saasUrl")
    userInfo.bid = config.config_get("demo_server.ini", "test", "stage_bid")
    userInfo.channelId = config.config_get("demo_server.ini", "test", "stage_channelId")
    userInfo.userId = config.config_get("demo_server.ini", "test", "stage_userId")
    userInfo.accessKey = config.config_get("demo_server.ini", "test", "stage_accessKey")
    return userInfo

class TestMessage01:
    @classmethod
    def setup_class(cls):
        userInfo = getUserInfo()

        gameInfo = GameBaseInfo()
        gameInfo.packageName = config.config_get("demo_server.ini", "test", "stage_packageName")
        res = demo.init(userInfo, gameInfo)
        if (res['result']['running'] != True):
            log.error("init error %s" % res)
            demo.quitGame()
        else:
            time.sleep(1)  # 等待1秒
        pass
    @classmethod
    def teardown_class(cls):
        demo.quitGame()
        pass
    def __verifyMessageSendResult(self, ret):
        assert ret['mid'] != 0
        pass
    def test_sendMessage_PAY(self):
        ret = demo.sendMessage("test", constants.MESSAGE_TYPE_PAY)
        self.__verifyMessageSendResult(ret)
        pass
    # def test_sendMessage_OTHER(self):
    #     ret = demo.sendMessage("test", constants.MESSAGE_TYPE_OTHER)
    #     self.__verifyMessageSendResult(ret)
    #     pass
    # def test_sendMessage_SYSTEM(self):
    #     ret = demo.sendMessage("test", constants.MESSAGE_TYPE_SYSTEM)
    #     self.__verifyMessageSendResult(ret)
    #     pass
    # def test_sendMessage_TRANSFER(self):
    #     ret = demo.sendMessage("test", constants.MESSAGE_TYPE_TRANSFER)
    #     self.__verifyMessageSendResult(ret)
    #     pass
class TestWsMessage01:
    @classmethod
    def setup_class(cls):
        userInfo = getUserInfo()

        gameInfo = GameBaseInfo()
        gameInfo.packageName = config.config_get("demo_server.ini", "test", "stage_packageName")
        res = demo.init(userInfo, gameInfo)
        if (res['result']['running'] != True):
            log.error("init error %s" % res)
            demo.quitGame()
        else:
            time.sleep(3)  # 等待1秒
        pass
    @classmethod
    def teardown_class(cls):
        demo.quitGame()
        pass
    def test_sendWsMessage_Gps(self):
        gps = GpsData()
        demo.sendGps(gps)
        demo.check_player_error()
        pass
    def test_sendWsMessage_ClipBoard(self):
        demo.sendClipboard("test")
        res = demo.getQrCodeData()
        log.info("getQrCodeData: %s"%res)
        demo.check_player_error()
        pass
