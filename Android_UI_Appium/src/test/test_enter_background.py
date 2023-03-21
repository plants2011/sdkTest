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

   # rtcRadio = self.scrollDownToFindElementById("com.haima.me.saas_sdk.master:id/rbtnRtc")
   # self.assertTrue(rtcRadio)
   # rtcRadio.click()

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

    TestInitSdk.TestInitpermission(self)
    time.sleep(1)
    # TestInitSdk.Testinitpluginsdk(self)
    TestInitSdk.TestInitSetUuid(self)

    TestInitSdk.TestPlaySDK(self)

    # time.sleep(TestEnterBackground.Options["waitInitTime"])
    #
    # self.ignorePreLog()
    #
    # startBtn = self.scrollDownToFindElementByClassName("android.widget.Button", "开始云玩")
    # self.assertTrue(startBtn)

    #startBtn.click()

    def checkFun():

      self.assertFalse(
        self.isElementVisibleById("com.haima.me.saas_sdk.master:id/tvPrompt") and self.getElementTextById(
          "com.haima.me.saas_sdk.master:id/tvPrompt"))

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
