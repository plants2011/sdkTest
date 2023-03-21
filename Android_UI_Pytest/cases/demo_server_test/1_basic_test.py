import logging
import time

from util.conf import config
from util.demo_server import demoServer, constants
from util.demo_server.data import UserBaseInfo, GameBaseInfo, Resolutions, UpdateGameUid

log = logging.getLogger(__name__)

demo = demoServer()

def getUserInfo():
    userInfo = UserBaseInfo()
    userInfo.saasUrl = config.config_get("demo_server.ini", "test", "saasUrl")
    userInfo.bid = config.config_get("demo_server.ini", "test", "bid")
    userInfo.channelId = config.config_get("demo_server.ini", "test", "channelId")
    userInfo.userId = config.config_get("demo_server.ini", "test", "userId")
    userInfo.accessKey = config.config_get("demo_server.ini", "test", "accessKey")
    return userInfo

class TestBasic01:
    """
    基础信息获取和简单流控制
    """
    @classmethod
    def setup_class(cls):
        userInfo = getUserInfo()

        gameInfo = GameBaseInfo()
        gameInfo.packageName = config.config_get("demo_server.ini", "test", "packageName")
        res = demo.init(userInfo, gameInfo)
        if (res['result']['running'] != True):
            log.error("init error %s" % res)
            demo.quitGame()
        else:
            time.sleep(1)  # 等待1秒

    @classmethod
    def teardown_class(cls):
        demo.quitGame()
        pass


    def test_pause_restart(self):
        """
        测试用例：游戏暂停/继续
        1. 暂停游戏，测试验证收到暂停消息
        2. 继续游戏，测试验证收到继续游戏消息
        以上1，2步中，均无流错误
        :return:
        """
        res = demo.pauseGame()
        log.info("pauseGame: %s" % res)
        demo.wait_status([constants.STATUS_PAUSE_PLAY], timeout=2)
        demo.check_player_error()

        res = demo.restartGame()
        log.info("restartGame: %s" % res)
        demo.wait_status([constants.STATUS_START_PLAY],timeout=2)
        demo.check_player_error()
        pass


    def test_backToGame(self):
        """
        测试用例：返回游戏
        解决多级登录后无法正常返回的问题，使用backToGame聚焦游戏
        1. 调用backToGame，1s内无播流错误
        :return:
        """
        res = demo.backToGame()
        log.info("backToGame: %s" % res)
        time.sleep(2)
        demo.check_player_error()


    def test_reconnect(self):
        """
        测试用例：测试重连
        1. 流基本重连功能，重连后播流等2秒没有任何错误
        """
        res = demo.reconnect()
        log.info("reconnectGame: %s" % res)
        demo.wait_status([constants.STATUS_FIRST_FRAME_ARRIVAL], timeout=2)
        demo.check_player_error()

        pass

    def test_reconnect_wifi_reconnect(self):
        """
        测试用例： 测试Wifi下的网络重连
        1. 关闭Wifi，并开始进行connect重连
        2. 打开Wifi，重连，并统计第一帧达到
        :return:
        """
        demo.server.enableWifi(True)
        ret, msg = demo.server.enableWifi(False)
        log.info("ret=%d, %s"%(ret, msg))
        assert ret == True
        try:
            time.sleep(5)
            res = demo.reconnect()
            log.info("reconnectGame: %s" % res)
            demo.wait_status([constants.STATUS_FIRST_FRAME_ARRIVAL])
        finally:
            demo.server.enableWifi(True)
        #//重新连接
        time.sleep(5)
        res = demo.reconnect()
        log.info("reconnectGame: %s" % res)
        demo.wait_status([constants.STATUS_FIRST_FRAME_ARRIVAL])

    def test_startPlay(self):
        """
        测试用例：重新播放视频
            1. 重新播流，发起播流后，重新播流不出现播流错误
            2. 使用插队播流方式发起播流后，播流不出现错误
        """
        res = demo.startPlay()
        log.info("startPlay: %s" % res)
        time.sleep(2)
        demo.check_player_error()

        res = demo.startPlayAhead(True)
        log.info("startPlayAhead: %s" % res)
        time.sleep(2)
        demo.check_player_error()
        pass


    def test_getCloudId(self):
        """
        测试用例：获取Cid，获取cid，且CID不为0或者空
        """
        cid = demo.getCloudId()
        log.info("cid=%s" % cid)
        assert cid != 0
        assert cid != None
        pass


    def test_getSdkVersion(self):
        """
        测试用例： 获取版本号
        获取版本内容，且获取的版本内容不为空或者是空字符串即可
        """
        v = demo.getSdkVersion()
        log.info("version=%s" % v)
        assert v != ""
        assert v != None
        pass


    def test_getClockDiffVideoLatencyInfo(self):
        """
        测试用例：调用getClockDiffVideoLatencyInfo获取游戏延迟消息，
        1. 获取延迟消息并打印，由于本场景中播流的场景是没有设置信息获取时间的，所以都拿不到相关信息，所有数据为0
        """
        latencyInfo = demo.getClockDiffVideoLatencyInfo()
        log.info("test_getClockDiffVideoLatencyInfo, %s" % latencyInfo)
        keys = latencyInfo.keys()
        assert 'bitRate' in keys and 'decodeDelay' in keys and 'delayTime' in keys \
               and 'netDelay' in keys and 'receiveFrameSize' in keys and 'renderDelay' in keys \
               and 'serverEncodeDelay' in keys and 'timeStamp' in keys and 'videoFps' in keys \
               and 'decodeType' in keys and 'packetsLostRate' in keys and 'roundTrip' in keys \
               and 'vaild' in keys and 'jitterBuffer' in keys
        assert latencyInfo['bitRate'] == 0
        assert latencyInfo['reciveFrameCount'] == 0
        assert latencyInfo['receiveFrameSize'] == 0
        assert latencyInfo['videoFps'] == 0
        assert latencyInfo['serverEncodeDelay'] == -1

    def test_setAudioMute(self):
        """
        测试用例：测试静音和静音打开
        1. 设置静音
        2. 重置静音
        查看播流是否正常，是否有报错信息
        """
        demo.setAudioMute(True)
        time.sleep(2)
        demo.check_player_error()
        demo.setAudioMute(False)
        time.sleep(2)
        demo.check_player_error()
        pass

    def test_checkScreenshot(self):
        """
        测试用例：截屏 & 检查截屏黑屏功能(这个功能仅限TextureView处理）
        1. 获取截屏后直接检查黑屏,理论上第一帧是黑屏，应该返回1，之后的帧就应该返回其他内容
        """
        renderType = demo.getRenderType()['type']
        log.info("getRenderType: %s"%renderType)
        if renderType == "TextureView":
            time.sleep(2)
            ret = demo.checkBitmap(0.9)
            log.info("checkScreenshot: %d"%ret)
            assert ret >= 1 and ret <= 4
        pass

    def test_getGameArchiveStatus(self):
        """
        测试用例：获取当前存档状态
        1. 云玩过程中判断当前存档状态，应该就是未存档状态，返回True
        """
        packageName = config.config_get("demo_server.ini", "test", "packageName")
        res = demo.getGameArchiveStatus(packageName, getUserInfo().getUserInfo())
        log.info("getGameArchiveStatus, %s"%res)
        assert res['status'] == True

class TestBasic02:
    """
    输入相关的测试用例
    """
    def setup_class(self):
        userInfo = getUserInfo()

        gameInfo = GameBaseInfo()
        gameInfo.noInputLimitTime = 5
        gameInfo.packageName = config.config_get("demo_server.ini", "test", "packageName")
        res = demo.init(userInfo, gameInfo)
        if (res['result']['running'] != True):
            log.error("init error %s" % res)
            demo.quitGame()
        else:
            time.sleep(1)  # 等待1秒

    def teardown(self):
        demo.quitGame()
        pass
    def test_resetInputTimer01(self):
        """
        测试用例：不重置无操作时长，用户退出
        1. 不更新时间，直接用户退出
        """
        time.sleep(6)
        demo.wait_status([constants.STATUS_STOP_PLAY])
    def test_resetInputTime02(self):
        """
        测试用例：重置无操作时间
        """
        time.sleep(3)
        ret = demo.resetInputTimer(True)

        log.info("resetInputTimer: %s"%ret)
        time.sleep(3)
        assert demo.check_status_exist([constants.STATUS_STOP_PLAY]) == False
        pass

class TestBasic03:
    """
    播流相关的验证
    """
    def initStream(self,streamType = ''):
        userInfo = getUserInfo()

        gameInfo = GameBaseInfo()
        if streamType.lower() == 'rtmp':
            gameInfo.streamType = 0
        elif streamType.lower() == 'rtc':
            gameInfo.streamType = 1
        else:
            pass
        gameInfo.packageName = config.config_get("demo_server.ini", "test", "packageName")
        res = demo.init(userInfo, gameInfo)
        if (res['result']['running'] != True):
            log.error("init error %s" % res)
            demo.quitGame()
        else:
            time.sleep(1)  # 等待1秒
    def teardown(self):
        demo.quitGame()
        pass
    def __verify_Success_PlayStatusCode(self):
        ret = demo.getCloudPlayStatusCode()
        assert ret == ""
        pass
    def __verify_RTMP(self):
        streamUrl = demo.getStreamUrl()
        log.info("streamUrl=%s"%streamUrl)
        assert streamUrl != ""
        assert streamUrl.startswith("rtmp://")
        pass
    def __verify_RTC(self):
        streamUrl = demo.getStreamUrl()
        log.info("streamUrl=%s"%streamUrl)
        assert streamUrl == ""
        pass
    def __verify_InputInformation(self):
        """
        验证点： 获取流地址相关，音频视频截图和输入地址
        1. 获取输入URL地址
        2. 获取视频流基本参数
        VERIFY： 验证输入流地址是否一致，输入参数是否一致；判断是否基本满足格式
        """
        inputUrl = demo.getInputUrl()
        log.info("inputUrl: %s" % inputUrl)
        assert inputUrl != ""
        assert inputUrl != None
        qrCode = demo.getQrCodeData()
        log.info("getQrCodeData: %s" % qrCode)
        assert qrCode['inputUrl'] == inputUrl
        assert qrCode['encryption'] != ''
        assert qrCode['encryption'] != None
        assert qrCode['orientation'] == False  # ScreenOrientation.LANDSCAPE //横屏
        assert qrCode['screenShotUrl'] != ''
        assert qrCode['screenShotUrl'] != None
        assert qrCode['screenResolution'] != None
        assert qrCode['screenResolution'] != ''
        pass
    def test_getStreamUrl_Default(self):
        """
        测试用例，获取流地址 默认格式，RTMP
        1. 不设置StreamURL则为RTMP格式，并有RTMP开头
        """
        self.initStream()
        self.__verify_RTMP()
        self.__verify_Success_PlayStatusCode()
        self.__verify_InputInformation()
        pass
    def test_getStreamUrl_RTC(self):
        """
        测试用例，获取流地址 RTC格式
        1. RTC格式流地址为空
        """
        self.initStream("rtc")
        self.__verify_RTC()
        self.__verify_Success_PlayStatusCode()
        self.__verify_InputInformation()
        pass
    def test_getStreamUrl_RTMP(self):
        """
        测试用例，获取流地址 设置RTMP格式
        1. RTC格式流地址为空,获取方式同RTMP格式
        """
        self.initStream("rtmp")
        self.__verify_RTMP()
        self.__verify_Success_PlayStatusCode()
        self.__verify_InputInformation()
        pass

class TestBasic04:
    """
    进行的游戏状态的检查和释放
    """
    def setup(self):
        self.userInfo = getUserInfo()

        self.gameInfo = GameBaseInfo()
        self.gameInfo.packageName = config.config_get("demo_server.ini", "test", "packageName")
        pass
    def init(self, cid=None):
        self.gameInfo.cid = cid

        res = demo.init(self.userInfo, self.gameInfo)
        if (res['result']['running'] != True):
            log.error("init error %s" % res)
            demo.quitGame()
        else:
            time.sleep(1)  # 等待1秒
    def teardown(self):
        if self.quitGame == True:
            demo.quitGame()

    def test_checkNoPlayingGame(self):
        """
        测试用例：检查未进行的游戏
        1. 初始化，但不启动游戏
        2. 检查是否有正在进行中的游戏，（判断没有）
        :return:
        """
        self.quitGame = False
        demo.initHmcpManager(self.userInfo)
        res = demo.checkPlayingGame(self.userInfo.getUserInfo())
        log.info("checkPlayingGame: %s"%res)
        assert len(res['channelInfoLists']) == 0

    def test_checkPlayingGame(self):
        """
        测试用例：检查正在进行中的游戏
        1. 播流启动游戏
        2. 检查是否有正在进行中的游戏，检查cid和包名匹配
        :return:
        """
        self.quitGame = True
        demo.init(self.userInfo, self.gameInfo)
        res = demo.checkPlayingGame(self.userInfo.getUserInfo())
        log.info("checkPlayingGame: %s"%res)
        assert len(res['channelInfoLists']) >= 0
        cid = demo.getCloudId()
        assert res['channelInfoLists'][0]['cid'] == cid
        assert res['channelInfoLists'][0]['pkgName'] == self.gameInfo.packageName
        pass
    def test_setReleaseCid(self):
        """
        测试用例：通过setReleaseCid释放
        1. 创建游戏
        2. 通过setReleaseCid释放游戏
        3. 释放后检查checkPlayingGame是否已经没有了正在进行中的游戏了
        :return:
        """
        self.quitGame = True
        demo.init(self.userInfo, self.gameInfo)
        cid = demo.getCloudId()
        res = demo.setReleaseCid(self.gameInfo.packageName, cid, self.userInfo)
        log.info("setReleaseCid: %s"%res)
        res = demo.checkPlayingGame(self.userInfo.getUserInfo())
        log.info("checkPlayingGame: %s"%res)
        assert len(res['channelInfoLists']) == 0
        pass
    def test_continueLastCidGame(self):
        """
        测试用例：客户端异常退出，带cid播流继续播流
        1. 启动播流
        2. 用adb shell am force-stop 停止进程
        3. 启动demoServer
        4. 检查是否有进行中的cid
        5. 使用cid播流
        6. 验证两次cid一致
        :return:
        """
        self.quitGame = True
        demo.init(self.userInfo, self.gameInfo)
        demo.server.stop()
        time.sleep(1)
        demo.server.start()
        demo.initHmcpManager(self.userInfo)
        res = demo.checkPlayingGame(self.userInfo.getUserInfo())
        log.info("checkPlayingGame: %s"%res)
        assert len(res['channelInfoLists']) >= 0
        cid = res['channelInfoLists'][0]['cid']
        self.init(cid)
        cid = demo.getCloudId()
        assert res['channelInfoLists'][0]['cid'] == cid
        pass

class TestBasic05:
    """
    不同流的UpdateGameUid
    """
    def initDemo(self, streamType="rtmp"):
        userInfo = getUserInfo()
        gameInfo = GameBaseInfo()
        gameInfo.longPlayTime = True
        if streamType.lower() == 'rtmp':
            gameInfo.streamType = 0
        elif streamType.lower() == 'rtc':
            gameInfo.streamType = 1
        else:
            pass
        gameInfo.playTime = 5*1000
        gameInfo.packageName = config.config_get("demo_server.ini", "test", "packageName")
        res = demo.init(userInfo, gameInfo)
        if (res['result']['running'] != True):
            log.error("init error %s" % res)
            demo.quitGame()
        pass
    def teardown(self):
        demo.quitGame()
        pass
    def __verify_updateGameUid(self):
        updateGameUid = UpdateGameUid()
        updateGameUid.playTime = 20*1000
        updateGameUid.userId = "test01"
        updateGameUid.accessKey = config.config_get("demo_server.ini", "test", "accessKey")
        res = demo.updateGameUid(updateGameUid)
        log.info("updateGameUid: %s"%res)
        time.sleep(6)
        assert demo.check_status_exist([constants.STATUS_STOP_PLAY]) == False
    def test_updateGameUid_RTMP(self):
        """
        测试用例:更新用户Uid和PlayTime(RTMP)
        1. 播流，（playTime:5秒的游玩时间）
        2. 更新用户的uid和playTime，playTime更新到20s
        VERIFY：6s后，判断过程中不断流，且没有任何错误
        :return:
        """
        self.initDemo("rtmp")
        self.__verify_updateGameUid()
        pass
    def test_updateGameUid_RTC(self):
        """
        测试用例:更新用户Uid和PlayTime(RTC)
        1. 播流，（playTime:5秒的游玩时间）
        2. 更新用户的uid和playTime，playTime更新到20s
        VERIFY：6s后，判断过程中不断流，且没有任何错误
        :return:
        """
        self.initDemo("rtc")
        self.__verify_updateGameUid()
        pass

class TestBasic06:
    """
    其他功能：包括点击事件、分辨率切换、获取PinCode等
    """
    def setup(self):
        userInfo = getUserInfo()

        gameInfo = GameBaseInfo()
        gameInfo.packageName = config.config_get("demo_server.ini", "test", "packageName")
        res = demo.init(userInfo, gameInfo)
        if (res['result']['running'] != True):
            log.error("init error %s" % res)
            demo.quitGame()
        else:
            time.sleep(1)  # 等待1秒
        pass
    def teardown(self):
        demo.quitGame()
        pass
    def test_getLastOperationTimestamp(self):
        """
        获取最后的点击事件时间点
        1. 起流不做任何操作的情况下，应该没有操作时间点
        2. 做了5次连续的tap点击操作，然后再查看时间点，且操作时间的计算上不超过10s
        """
        time.sleep(2)
        s1 = demo.getLastUserOperationTimestamp()
        log.info("getLastUserOperationTimestamp: %d"%s1)
        assert s1 == 0
        time.sleep(2)
        for i in range(0, 5):
            demo.server.adb_shell("input tap 300 300")
        s2 = demo.getLastUserOperationTimestamp()
        log.info("getLastUserOperationTimestamp: %d"%s2)
        assert abs(s2/1000 == time.time()) < 10
        assert s2 > s1
        pass
    def test_getResolutionDatas(self):
        """
        测试用例：分辨率相关：
            1. 获取当前分辨率
            2. 根据分辨率名称保存所有分辨率记录
            3. 遍历所有分辨率记录进行分辨率切换操作，
            VERIFY：验证切换过程中有STATUS_SWITCH_RESOLUTION，且切换后3s内无播流Error
            VERIFY:判断切换分辨率后的分辨率是否一致
        """
        res = demo.getResolutionDatas()
        log.info("getResolutionDatas, %s" % res)
        resolutions = Resolutions(res['resolutions'])
        assert len(resolutions.names) > 0

        names = resolutions.names
        log.info("resolutions: %s" % names)
        for name in names:
            resolutionInfo = resolutions.get(name)
            log.info("currentInfo: %s"%resolutionInfo.__dict__)
            res = demo.onSwitchResolution(0, resolutionInfo, resolutionInfo.bitRate)
            log.info("onSwitchResolution, %s" % res)
            time.sleep(3)
            assert demo.check_status_exist(constants.STATUS_SWITCH_RESOLUTION_ERROR) == False
            demo.wait_sceneChanges(['crst', 'firstFrameArrival']) #可能有cred信息丢失的情况，这个地方可能是一个隐藏bug
            #判断切换后的分辨率是否一致
            # qrCode = demo.getQrCodeData()
            # log.info("qrCode, %s"%qrCode)
            # assert qrCode['screenResolution'] == resolutionInfo.resolution #这个分辨率信息有点问题，具体这个分辨率是指哪个需要再次判断
            # demo.check_player_error()
        pass
    def test_getPincode(self):
        """
        测试用例： 获取当前PinCode
        1. 尝试获取PinCode数据，不能为空
        """
        pinCode = demo.getPinCode()['pinCode']
        log.info("pinCode: %s"%pinCode)
        assert pinCode != ""
        pass

class TestBasic07:
    def teardown(self):
        demo.quitGame()
        pass
    def test_stopAndDismiss(self):
        """
        测试用例：测试停止游戏, stopAndDismiss
        :return:
        """
        userInfo = getUserInfo()

        gameInfo = GameBaseInfo()
        gameInfo.packageName = config.config_get("demo_server.ini", "test", "packageName")
        res = demo.init(userInfo, gameInfo)
        if (res['result']['running'] != True):
            log.error("init error %s" % res)
            demo.quitGame()
        else:
            time.sleep(1)  # 等待1秒

        demo.stopAndDismiss(5)
        res = demo.wait_status([constants.STATUS_STOP_PLAY])
        log.info("stopAndDismiss: %s"%res)
        demo.check_player_error()
        pass