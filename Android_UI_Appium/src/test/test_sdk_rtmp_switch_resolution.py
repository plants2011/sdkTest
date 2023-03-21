# -*-coding:utf-8-*-

###
### Test case for switch resolution
### by Jian

import time
from src.test.test_initsdk import TestInitSdk
from src.test.webrtc_sdk_test_case import WebrtcSdkTestCase

class TestRtmpSwitchResolution(WebrtcSdkTestCase):

  Options = {
    "waitInitTime": 8,              # wait sdk init finish time
    "testFirstFrameCount": 2,       # test count
    "testFirstFrameMaxWaitTime": 60 # wait first frame max time
  }

  def testFirstFrame(self):

    self.logger().info("testFirstFrame start, options: {}", TestRtmpSwitchResolution.Options)

    #rtcRadio = self.scrollDownToFindElementById("com.haima.me.saas_sdk.master:id/rbtnRtc")
    #self.assertTrue(rtcRadio)
    #rtcRadio.click()

    #time.sleep(1)
    #rtcRadio = self.driver().find_element_by_id("com.haima.me.saas_sdk.master:id/rbtnRtc")
    #self.assertTrue(self.isElementChecked(rtcRadio))

    # permissionmd = self.findElementByClassName("android.widget.Button", "允许")
    # self.assertTrue(permissionmd)
    # permissionmd.click()
    #
    # time.sleep(1)
    # permissionaudio = self.findElementByClassName("android.widget.Button", "仅使用期间允许")
    # self.assertTrue(permissionaudio)
    # permissionaudio.click()
    #
    # initBtn = self.scrollDownToFindElementByClassName("android.widget.Button", "初始化")
    # self.assertTrue(initBtn)
    # initBtn.click()
    #
    # time.sleep(TestSwitchResolution.Options["waitInitTime"])
    #
    # self.ignorePreLog()
    #
    # startBtn = self.scrollDownToFindElementByClassName("android.widget.Button", "开始云玩")
    # self.assertTrue(startBtn)
    #
    # startBtn.click()
    TestInitSdk.TestInitpermission(self)
    time.sleep(1)
    TestInitSdk.TestInitRtmp(self)
    TestInitSdk.TestInitSetUuid(self)

    TestInitSdk.TestPlaySDK(self)

    def checkFun():
      if self.isElementVisibleById("com.haima.me.saas_sdk.master:id/tvPrompt"):
        promptText = self.getElementTextById("com.haima.me.saas_sdk.master:id/tvPrompt")
        if promptText:
          self.assertTrue((promptText == "正在为您切换清晰度，请稍候") or (promptText.find("您已切换至") != -1))
          return False

      if self.checkLog("Haima debug: first frame arrived", ignorePreLog=True):
        time.sleep(1)
        return True

      return False

    self.assertTrue(
      self.waitAndCheck(TestRtmpSwitchResolution.Options["testFirstFrameMaxWaitTime"], checkFun)
    )

    time.sleep(20)

    self.logger().info("testFirstFrame enter game screen done")

    btn1Id = "com.haima.me.saas_sdk.master:id/rbtFirst"
    btn2Id = "com.haima.me.saas_sdk.master:id/rbtSecond"

    for i in range(TestRtmpSwitchResolution.Options["testFirstFrameCount"]):

      self.logger().info("testFirstFrame start test time: {}", i + 1)

      self.ignorePreLog()

      switchResBtn = self.findElementByClassName("android.widget.ImageView")

      self.assertIsNotNone(switchResBtn)

      clickPosX = switchResBtn.location["x"] + switchResBtn.size["width"] / 2
      clickPosY = switchResBtn.location["y"] + switchResBtn.size["height"] / 2

      self.driver().tap([(clickPosX, clickPosY)])

      self.logger().info("testFirstFrame test time: {} click done: {}, {}", i + 1, clickPosX, clickPosY)

      btn1 = self.findElementById(btn1Id, maxTryTime=5)
      btn2 = self.findElementById(btn2Id, maxTryTime=5)

      self.assertTrue(btn1)

      if not btn2:
        self.logger().info("testFirstFrame not START for only one resolution exist")
        return

      if self.isElementChecked(btn1):
        btn2.click()
      else:
        btn1.click()

      self.assertTrue(
        self.waitAndCheck(TestRtmpSwitchResolution.Options["testFirstFrameMaxWaitTime"], checkFun)
      )

      self.randomInput()

      time.sleep(3)

      self.logger().info("testFirstFrame test time: {} SUCCEED", i + 1)

    self.gameScreenBackToMainUi(failedBack=False)
