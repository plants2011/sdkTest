# -*-coding:utf-8-*-

###
### Test case for switch resolution
### by Jian

import time
from src.test.test_initsdk import TestInitSdk
from src.test.webrtc_sdk_test_case import WebrtcSdkTestCase

class TestRtmpSwitchResolutionNetwork(WebrtcSdkTestCase):

  Options = {
    "waitInitTime": 8,              # wait sdk init finish time
    "testFirstFrameCount": 2,       # test count
    "testFirstFrameMaxWaitTime": 60 # wait first frame max time
  }

  def testFirstFrame(self):

    self.logger().info("testFirstFrame start, options: {}", TestRtmpSwitchResolutionNetwork.Options)


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
      self.waitAndCheck(TestRtmpSwitchResolutionNetwork.Options["testFirstFrameMaxWaitTime"], checkFun)
    )

    time.sleep(20)

    self.logger().info("testFirstFrame enter game screen done")

    btn1Id = "com.haima.me.saas_sdk.master:id/rbtFirst"
    btn2Id = "com.haima.me.saas_sdk.master:id/rbtSecond"

    for i in range(TestRtmpSwitchResolutionNetwork.Options["testFirstFrameCount"]):

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

      for i in range(1, 6):
        self.disableWifi()
        time.sleep(2)
        self.enableWifi()

        time.sleep(5)

      self.assertTrue(
        self.waitAndCheck(TestRtmpSwitchResolutionNetwork.Options["testFirstFrameMaxWaitTime"], checkFun)
      )

      self.randomInput()

      time.sleep(3)

      self.logger().info("testFirstFrame test time: {} SUCCEED", i + 1)

    self.gameScreenBackToMainUi(failedBack=False)
