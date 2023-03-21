# -*-coding:utf-8-*-

###
### Test case for network changed
### by Jian

import time
import random
from src.test.test_initsdk import TestInitSdk
from src.test.webrtc_sdk_test_case import WebrtcSdkTestCase

class TestRtmpNetworkChanged(WebrtcSdkTestCase):

  Options = {
    "waitInitTime": 8,              # wait sdk init finish time
    "testFirstFrameCount": 2,       # test count
    "testFirstFrameMaxWaitTime": 60 # wait first frame max time
  }

  def testFirstFrame(self):

    self.logger().info("testFirstFrame start, options: {}", TestRtmpNetworkChanged.Options)

    #rtcRadio = self.scrollDownToFindElementById("com.haima.me.saas_sdk.master:id/rbtnRtc")
    #self.assertTrue(rtcRadio)
    #rtcRadio.click()

    # permissionmd = self.findElementByClassName("android.widget.Button", "允许")
    # self.assertTrue(permissionmd)
    # permissionmd.click()
    #
    # time.sleep(1)
    # permissionaudio = self.findElementByClassName("android.widget.Button", "仅使用期间允许")
    # self.assertTrue(permissionaudio)
    # permissionaudio.click()

    TestInitSdk.TestInitpermission(self)
    time.sleep(1)
    TestInitSdk.TestInitRtmp(self)
    TestInitSdk.TestInitSetUuid(self)


    for i in range(TestRtmpNetworkChanged.Options["testFirstFrameCount"]):

      self.logger().info("testFirstFrame start test time: {}", i + 1)


      TestInitSdk.TestPlaySDK(self)


      def checkFun():

        self.assertFalse(self.isElementVisibleById("com.haima.me.saas_sdk.master:id/tvPrompt") and self.getElementTextById("com.haima.me.saas_sdk.master:id/tvPrompt"))

        if self.checkLog("Haima debug: first frame arrived", ignorePreLog=True):
          time.sleep(1)
          return True

        return False

      self.assertTrue(
        self.waitAndCheck(TestRtmpNetworkChanged.Options["testFirstFrameMaxWaitTime"], checkFun)
       )

      self.logger().info("testFirstFrame normal test time: {} done and disable wifi", i + 1)

      self.ignorePreLog()
      # time.sleep(5)


      for i in range(1,6):
        self.disableWifi()
        time.sleep(2)
        self.enableWifi()

        time.sleep(5)


      self.assertTrue(
        self.waitAndCheck(TestRtmpNetworkChanged.Options["testFirstFrameMaxWaitTime"], checkFun)
      )

      self.logger().info("testFirstFrame test time: {} SUCCEED", i + 1)

      self.gameScreenBackToMainUi()

