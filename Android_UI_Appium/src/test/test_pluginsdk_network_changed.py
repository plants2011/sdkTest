# -*-coding:utf-8-*-

###
### Test case for network changed
### by Jian

import time
from src.test.test_initsdk import TestInitSdk
from src.test.webrtc_sdk_test_case import WebrtcSdkTestCase

class TestNetworkChanged(WebrtcSdkTestCase):

  Options = {
    "waitInitTime": 8,              # wait sdk init finish time
    "testFirstFrameCount": 2,       # test count
    "testFirstFrameMaxWaitTime": 60 # wait first frame max time
  }

  def testFirstFrame(self):

    self.logger().info("testFirstFrame start, options: {}", TestNetworkChanged.Options)

    TestInitSdk.TestInitpermission(self)

    time.sleep(1)
    TestInitSdk.TestPluginSelectGame(self)

    TestInitSdk.Testinitpluginsdk(self)



    for i in range(TestNetworkChanged.Options["testFirstFrameCount"]):

      self.logger().info("testFirstFrame start test time: {}", i + 1)

      #self.enableWifi()

      TestInitSdk.TestPluginPlaySDK(self)

      #self.ignorePreLog()

      def checkFun():

        self.assertFalse(self.isElementVisibleById("com.example.hmcpdemo:id/show_test_menu_btn") and self.getElementTextById("com.example.hmcpdemo:id/show_test_menu_btn"))

        if self.checkLog("Haima debug: first frame arrived", ignorePreLog=True):
          time.sleep(1)
          return True

        return False

      self.assertTrue(
        self.waitAndCheck(TestNetworkChanged.Options["testFirstFrameMaxWaitTime"], checkFun)
       )

      self.logger().info("testFirstFrame normal test time: {} done and disable wifi", i + 1)

      self.ignorePreLog()
      # time.sleep(5)


      self.disableWifi()
      time.sleep(2)
      self.enableWifi()

      time.sleep(5)



      self.assertTrue(
        self.waitAndCheck(TestNetworkChanged.Options["testFirstFrameMaxWaitTime"], checkFun)
      )

      self.logger().info("testFirstFrame test time: {} SUCCEED", i + 1)

      self.gameScreenBackToMainUi()

