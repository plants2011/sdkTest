# -*-coding:utf-8-*-

###
### Test case for normal
### by Jian

import time
from src.test.test_initsdk import TestInitSdk
from src.test.webrtc_sdk_test_case import WebrtcSdkTestCase




class TestNormal(WebrtcSdkTestCase):

  Options = {
    "waitInitTime": 5,        # wait sdk init finish time
    "testFirstFrameCount": 2, # test count
    "testFirstFrameMaxWaitTime": 60 # wait first frame max time
  }

  def testFirstFrame(self):

    self.logger().info("testFirstFrame start, options: {}", TestNormal.Options)


    TestInitSdk.TestInitpermission(self)

    time.sleep(1)
    TestInitSdk.TestPluginSelectGame(self)

    TestInitSdk.Testinitpluginsdk(self)





    def checkFun():
      # if self.isElementVisibleById("com.example.hmcpdemo: id/ll_main"):

      if len(TestInitSdk.TestPluginGetcid(self)) != None:
        return True

      #   try:
      #     toast_el = WebDriverWait(self, 30, 0.00001). \
      #       until(lambda x: x.getElementTextByXPath("//*[contains(@text,'play onSuccess')]"))
      #     print(toast_el)
      #     return True
      #   except Exception as e:
      #     print(("Get Toast Error : ", e))
      #     return False




      # if self.isElementVisibleById("com.example.hmcpdemo:id/show_test_menu_btn"):
      #   # if promptText:
      #   #   self.assertEqual(promptText, "服务器繁忙，请稍后重试[200211006]")
      #   #   self.logger().warn("testFirstFrame got server busy, retry after 60 second")
      #   #   time.sleep(60)
      #   #   return True
      #
      #   try:
      #     toast_el = WebDriverWait(self, 30, 0.00001). \
      #       until(lambda x: x.getElementTextByXPath("//*[contains(@text,'play onSuccess')]"))
      #     self.logger().info("toast", toast_el)
      #
      #     self.assertTrue((toast_el == "play onSuccess") )
      #     return True
      #   except:
      #     return False

      # testtoase = self.gettoast_text( self,"play onSuccess")
      # self.logger().info("testtoase....",testtoase)
      # if  testtoase != None:
      #   self.assertTrue((testtoase == "True"))
      #   return True

      return False


    # def is_toast_exist(self,text,timeout=20,poll_frequency=0.00001):
    #   try:
    #     toast_loc = self.getElementTextByXPath(".//*[contains(@text,'%s')]"%text)
    #     WebDriverWait(self._driver,timeout,poll_frequency) .until(EC.presence_of_element_located(toast_loc))
    #     self.logger().debug("---toast---", WebDriverWait(self._driver,timeout,poll_frequency) .until(EC.presence_of_element_located(toast_loc)))
    #     return  True
    #   except:
    #     return  False
    #
    # def find_toast(driver, message, timeout=10, poll=0.01):
    #   try:
    #     message = '//*[@text=\'{}\']'.format(message)  # Toast内容
    #     element = WebDriverWait(driver, timeout, poll).until(EC.presence_of_element_located((AABy.XPATH, message)))
    #
    #     self.logger().debug("toast", element)
    #     return True
    #   except Exception as e:
    #     print(("Get Toast Error : ", e))
    #     return False
    #
    # def finds_toast(self, message):
    #   '''''判断toast信息'''
    #   try:
    #     element = WebDriverWait(self.driver, 10,0.0001).until(EC.presence_of_element_located((AABy.PARTIAL_LINK_TEXT, message)))
    #     self.logger().info("1231----",element)
    #     return True
    #   except:
    #     return False




    for i in range(TestNormal.Options["testFirstFrameCount"]):
      self.logger().info("testFirstFrame start test time: {}", i + 1)


      time.sleep(1)

      TestInitSdk.TestPluginPlaySDK(self)

      time.sleep(10)

      TestInitSdk.TestPluginSelcetTestMenu(self)
      time.sleep(1)
     # getcidaa = TestInitSdk.TestPluginGetcid(self)
     # print(getcidaa)


      self.assertTrue(
        self.waitAndCheck(TestNormal.Options["testFirstFrameMaxWaitTime"],checkFun)
      )

      self.logger().info("testFirstFrame test time: {} SUCCEED", i + 1)

      #self.gameScreenBackToMainUi()

