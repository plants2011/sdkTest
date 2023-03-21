import time
from src.test.webrtc_sdk_test_case import WebrtcSdkTestCase
from appium import webdriver


class TestInitSdk():
    Options = {
        "waitInitTime": 5,  # wait sdk init finish time
        "testFirstFrameCount": 2,  # test count
        "testFirstFrameMaxWaitTime": 60  # wait first frame max time
    }

    def TestInitpermission(self):

        permissionmd = self.findElementByClassName("android.widget.Button", "允许")
        if permissionmd:
            self.assertTrue(permissionmd)
            permissionmd.click()
        elif permissionmd== False:
            self.logger.error("权限1无需点击")

        time.sleep(1)

        permissionaudio = (self.findElementByClassName("android.widget.Button", "仅使用期间允许") or self.findElementByClassName("android.widget.Button", "允许"))
        if permissionaudio:
            self.assertTrue(permissionaudio)
            permissionaudio.click()
        elif permissionaudio== False:
            self.logger.error("权限2无需点击")


    def TestInitSetUuid(self):
        userinfobtn = self.findElementByXPath("//android.support.v7.app.ActionBar.Tab[@content-desc=\"用户信息设置\"]/android.widget.TextView")
        #userinfobtn = self.find_element(by=AppiumBy.CLASS_NAME, value="//android.support.v7.app.ActionBar.Tab[@content-desc=\"用户信息设置\"]/android.widget.TextView")

        #userinfobtn = self.findElementByClassName("//android.support.v7.app.ActionBar.Tab[@content-desc=\"用户信息设置\"]/android.widget.TextView")
        #self.asserTrue(userinfobtn)
        userinfobtn.click()

        time.sleep(1)

        self.findElementById('com.haima.me.saas_sdk.master:id/et_user_id').send_keys("lei123")

    def TestInitRtmp(self):
        gameinfobtn = self.findElementByXPath(
            "//android.support.v7.app.ActionBar.Tab[@content-desc=\"游戏设置\"]/android.widget.TextView")
        gameinfobtn.click()
        time.sleep(1)

        rtmpRadio = self.scrollDownToFindElementById("com.haima.me.saas_sdk.master:id/rbtnRtmp")
        self.assertTrue(rtmpRadio)
        rtmpRadio.click()

    def TestInitRTC(self):
        gameinfobtn = self.findElementByXPath(
            "//android.support.v7.app.ActionBar.Tab[@content-desc=\"游戏设置\"]/android.widget.TextView")
        gameinfobtn.click()
        time.sleep(1)

        rtcRadio = self.scrollDownToFindElementById("com.haima.me.saas_sdk.master:id/rbtnRtc")
        self.assertTrue(rtcRadio)
        rtcRadio.click()







    def Testinitpluginsdk(self):

        # permissionmd = self.findElementByClassName("android.widget.Button", "允许")
        # if permissionmd:
        #     self.assertTrue(permissionmd)
        #     permissionmd.click()
        # elif permissionmd == False:
        #     logger.error("权限1无需点击")
        #
        # time.sleep(1)
        #
        # permissionaudio = self.findElementByClassName("android.widget.Button", "仅使用期间允许")
        # if permissionaudio:
        #     self.assertTrue(permissionaudio)
        #     permissionaudio.click()
        # elif permissionaudio == False:
        #     logger.error("权限2无需点击")



        PluginstallJar = self.findElementByClassName("android.widget.Button", "安装自带插件")
        if PluginstallJar:
            self.assertTrue(PluginstallJar)
            self.logger().info("安装自带插件")
            PluginstallJar.click()

        time.sleep(1)

        PluginIiitJar = self.findElementByClassName("android.widget.Button","加载Plugin")
        if PluginstallJar:
            self.assertTrue(PluginIiitJar)
            PluginIiitJar.click()


    def TestPlaySDK(self):
        time.sleep(1)
        initBtn = self.scrollDownToFindElementByClassName("android.widget.Button", "初始化")
        #initBtn = self.findElemnetByClassName("android.widget.Button", "初始化")
        self.assertTrue(initBtn)
        initBtn.click()

        time.sleep(TestInitSdk.Options["waitInitTime"])

        startBtn = self.scrollDownToFindElementByClassName("android.widget.Button", "开始云玩")
        #startBtn = self.findElemnetByClassName("android.widget.Button", "开始云玩")
        self.assertTrue(startBtn)
        startBtn.click()

    def TestPluginPlaySDK(self):
        time.sleep(1)
        #initBtn = self.scrollDownToFindElementByClassName("android.widget.Button", "初始化SDK")
        initBtn = self.findElementByClassName("android.widget.Button", "初始化SDK")
        self.assertTrue(initBtn)
        initBtn.click()

        time.sleep(TestInitSdk.Options["waitInitTime"])

        #startBtn = self.scrollDownToFindElementByClassName("android.widget.Button", "开始云玩")
        startBtn = self.findElementByClassName("android.widget.Button", "开始云玩")
        self.assertTrue(startBtn)

        startBtn.click()

    def TestPluginSelectGame(self):

        SelectGamebtn = self.findElementById('com.example.hmcpdemo:id/switch_saas')
        #self.assertTrue(SelectGamebtn)
        SelectGamebtn.click()

    def TestPluginSelcetTestMenu(self):
        GetcTestBtn = self.findElementById('com.example.hmcpdemo:id/show_test_menu_btn')
        self.assertTrue(GetcTestBtn)
        GetcTestBtn.click()

    def TestPluginGetcid(self):
        GetcGetcidBtn = self.findElementById('com.example.hmcpdemo:id/get_cid')
        #self.assertTrue(GetcGetcidBtn)
        GetcGetcidBtn.click()

        # print(self.getElementTextByXPath('//*[@class="android.widget.Toast"]'))


        Getcidtext = self.getElementTextByXPath('//*[@class="android.widget.Toast"]')
        print(Getcidtext)
        self.logger().debug('getcid:',Getcidtext)
        Cidstring = Getcidtext[13:]
        #Cidstring = Getcidtext.partition("getCloudId : /")[2]
        print(Cidstring)
        self.logger().info('cid:',Cidstring)
        return  Cidstring









