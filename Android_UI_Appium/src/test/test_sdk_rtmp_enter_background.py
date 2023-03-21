# -*-coding:utf-8-*-

###
### Test case for enter backgroud
### by Jian

import time
import random
from src.test.webrtc_sdk_test_case import WebrtcSdkTestCase
from src.test.test_initsdk import TestInitSdk

class TestRtmpEnterBackground(WebrtcSdkTestCase):

  Options = {
    "waitInitTime": 8,        # wait sdk init finish time
    "testFirstFrameCount": 2, # test count
    "testFirstFrameMaxWaitTime": 60 # wait first frame max time
  }

  def testFirstFrame(self):

    self.logger().info("testFirstFrame start, options: {}", TestRtmpEnterBackground.Options)


    TestInitSdk.TestInitpermission(self)
    time.sleep(1)
    TestInitSdk.TestInitRtmp(self)
    TestInitSdk.TestInitSetUuid(self)

    TestInitSdk.TestPlaySDK(self)


    def checkFun():

      self.assertFalse(
        self.isElementVisibleById("com.haima.me.saas_sdk.master:id/tvPrompt") and self.getElementTextById(
          "com.haima.me.saas_sdk.master:id/tvPrompt"))

      if self.checkLog("Haima debug: first frame arrived", ignorePreLog=True):
        time.sleep(1)
        return True

      return False

    self.assertTrue(
      self.waitAndCheck(TestRtmpEnterBackground.Options["testFirstFrameMaxWaitTime"], checkFun)
    )

    time.sleep(20)

    self.logger().info("testFirstFrame enter game screen done")

    for i in range(TestRtmpEnterBackground.Options["testFirstFrameCount"]):

      backgroundTime = random.random() * 10 + 10

      self.logger().info("testFirstFrame start test time: {} and ENTER background: {}", i + 1, backgroundTime)

      self.ignorePreLog()

      self.enterBackground(backgroundTime)

      self.assertTrue(
        self.waitAndCheck(TestRtmpEnterBackground.Options["testFirstFrameMaxWaitTime"], checkFun)
      )

      self.randomInput(sleeTime=3)

      time.sleep(5)

      self.logger().info("testFirstFrame test time: {} SUCCEED, background time: {}", i + 1, backgroundTime)

    self.gameScreenBackToMainUi(failedBack=False)
