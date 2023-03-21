# -*-coding:utf-8-*-

###
### Test case for normal
### by Jian

import time
from src.test.webrtc_sdk_test_case import WebrtcSdkTestCase

class TestNormal(WebrtcSdkTestCase):

  Options = {
    "waitInitTime": 5,        # wait sdk init finish time
    "testFirstFrameCount": 2, # test count
    "testFirstFrameMaxWaitTime": 60 # wait first frame max time
  }

  def testFirstFrame(self):

    self.logger().info("testFirstFrame start, options: {}", TestNormal.Options)

    #rtcRadio = self.scrollDownToFindElementById("com.haima.me.saas_sdk.master:id/rbtnRtc")
    #self.assertTrue(rtcRadio)
    #rtcRadio.click()

    #time.sleep(1)
    #rtcRadio = self.driver().find_element_by_id("com.haima.me.saas_sdk.master:id/rbtnRtc")
    #self.assertTrue(self.isElementChecked(rtcRadio))
    permissionmd = self.findElementByClassName("android.widget.Button", "允许")
    self.assertTrue(permissionmd)
    permissionmd.click()

    time.sleep(1)
    permissionaudio = self.findElementByClassName("android.widget.Button", "仅使用期间允许")
    self.assertTrue(permissionaudio)
    permissionaudio.click()

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

    for i in range(TestNormal.Options["testFirstFrameCount"]):
      self.logger().info("testFirstFrame start test time: {}", i + 1)

      initBtn = self.scrollDownToFindElementByClassName("android.widget.Button", "初始化")
      self.assertTrue(initBtn)
      initBtn.click()

      time.sleep(TestNormal.Options["waitInitTime"])

      startBtn = self.scrollDownToFindElementByClassName("android.widget.Button", "开始云玩")
      self.assertTrue(startBtn)

      startBtn.click()

      self.assertTrue(
        self.waitAndCheck(TestNormal.Options["testFirstFrameMaxWaitTime"], checkFun)
      )

      self.logger().info("testFirstFrame test time: {} SUCCEED", i + 1)

      self.gameScreenBackToMainUi()

