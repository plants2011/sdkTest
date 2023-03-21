# -*-coding:utf-8-*-

###
### Test case for normal
### by Jian

import time
from src.test.test_initsdk import TestInitSdk
from src.test.webrtc_sdk_test_case import WebrtcSdkTestCase

class TestRtmpNormal(WebrtcSdkTestCase):

  Options = {
    "waitInitTime": 5,        # wait sdk init finish time
    "testFirstFrameCount": 2, # test count
    "testFirstFrameMaxWaitTime": 60 # wait first frame max time
  }

  def testFirstFrame(self):

    self.logger().info("testFirstFrame start, options: {}", TestRtmpNormal.Options)


    TestInitSdk.TestInitpermission(self)
    time.sleep(1)
    TestInitSdk.TestInitRtmp(self)
    TestInitSdk.TestInitSetUuid(self)


    def checkFun():

      if self.isElementVisibleById("com.haima.me.saas_sdk.master:id/tvPrompt"):
        promptText = self.getElementTextById("com.haima.me.saas_sdk.master:id/tvPrompt")
        if promptText:
          self.assertEqual(promptText, "服务器繁忙，请稍后重试[200211006]")
          self.logger().warn("testFirstFrame got server busy, retry after 60 second")
          time.sleep(60)
          return True

      element = self.findElementById("com.haima.me.saas_sdk.master:id/showDelay")

      if element and element.text and element.text != "Loading":
        return True

      return False

    for i in range(TestRtmpNormal.Options["testFirstFrameCount"]):
      self.logger().info("testFirstFrame start test time: {}", i + 1)

      TestInitSdk.TestPlaySDK(self)

      self.assertTrue(
        self.waitAndCheck(TestRtmpNormal.Options["testFirstFrameMaxWaitTime"], checkFun)
      )

      self.logger().info("testFirstFrame test time: {} SUCCEED", i + 1)

      self.gameScreenBackToMainUi()

