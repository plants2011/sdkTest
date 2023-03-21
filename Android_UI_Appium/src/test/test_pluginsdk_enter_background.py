# -*-coding:utf-8-*-

###
### Test case for enter backgroud
### by Jian

import time
import random
from src.test.webrtc_sdk_test_case import WebrtcSdkTestCase
from src.test.test_initsdk import TestInitSdk

class TestEnterBackground(WebrtcSdkTestCase):

  Options = {
    "waitInitTime": 8,        # wait sdk init finish time
    "testFirstFrameCount": 2, # test count
    "testFirstFrameMaxWaitTime": 60 # wait first frame max time
  }

  def testFirstFrame(self):

    self.logger().info("testFirstFrame start, options: {}", TestEnterBackground.Options)


    TestInitSdk.TestInitpermission(self)
    time.sleep(1)
    TestInitSdk.TestPluginSelectGame(self)

    TestInitSdk.TestPluginPlaySDK(self)


    def checkFun():

      self.assertFalse(
        self.isElementVisibleById("com.example.hmcpdemo:id/show_test_menu_btn") and self.getElementTextById(
          "com.example.hmcpdemo:id/show_test_menu_btn"))

      if self.checkLog("Haima debug: first frame arrived", ignorePreLog=True):
        time.sleep(1)
        return True

      return False

    self.assertTrue(
      self.waitAndCheck(TestEnterBackground.Options["testFirstFrameMaxWaitTime"], checkFun)
    )

    time.sleep(20)

    self.logger().info("testFirstFrame enter game screen done")

    for i in range(TestEnterBackground.Options["testFirstFrameCount"]):

      backgroundTime = random.random() * 10 + 10

      self.logger().info("testFirstFrame start test time: {} and ENTER background: {}", i + 1, backgroundTime)

      self.ignorePreLog()

      self.enterBackground(backgroundTime)

      self.assertTrue(
        self.waitAndCheck(TestEnterBackground.Options["testFirstFrameMaxWaitTime"], checkFun)
      )

      self.randomInput(sleeTime=3)

      time.sleep(5)

      self.logger().info("testFirstFrame test time: {} SUCCEED, background time: {}", i + 1, backgroundTime)

    self.gameScreenBackToMainUi(failedBack=False)
