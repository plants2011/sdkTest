# -*-coding:utf-8-*-

###
### Base test case class for webrtc sdk
### by Jian

import time

import src.test.appium_test_case as appium_test_case
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class WebrtcSdkTestCase(appium_test_case.AppiumTestCase):


  def setUp(self):

    appium_test_case.AppiumTestCase.setUp(self)

    time.sleep(2)
    self.hideKeyBoard()
    time.sleep(2)

  def gameScreenBackToMainUi(self, failedBack = True):

    for _ in range(3):

      self.back(sleepTime=0.3)
      self.back(sleepTime=1.5)

      if self.findElementByClassName("android.widget.Button", "开始云玩") \
          or self.findElementById("com.haima.me.saas_sdk.master:id/rbtnRtc") \
          or self.findElementById("com.haima.me.saas_sdk.master:id/etBid") \
          or self.findElementById("com.haima.me.saas_sdk.master:id/etProvince"):
        return

    if failedBack:
      self.fail("DemoUiBug: gameScreenBackToMainUi back failed")


  def randomInput(self, sleeTime = 1):

    if sleeTime > 0:
      time.sleep(sleeTime)

    try:
      width = self.driver().get_window_size()['width']
      height = self.driver().get_window_size()['height']

      self.driver().tap([(width * 0.5, height * 0.3)])
    except Exception as e:
      self.logger().warn("randomInput got Exception: {}", str(e))






