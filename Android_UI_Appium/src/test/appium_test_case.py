# -*-coding:utf-8-*-

###
### Base test case class for appium
### by Jian

import os, sys
sys.path.append(sys.path[0][:(sys.path[0].rindex((os.sep + "src")))])

import unittest
import time
from io import open

from appium import webdriver

import src.utils.util as util
import src.utils.logger


class AppiumTestCase(unittest.TestCase):
  """Base test case class for appium"""

  Name = None

  PlatformName = None
  PlatformVersion = None
  DeviceName = None
  AppPackage = None
  AppActivity = None

  Host = "localhost"
  Port = 4723

  LogDir = None

  MaxLogCacheSize = 10 * 1024

  # helper app for background feature
  HelperApp = None
  HelperAppActivity = None

  NeedPermmit = False

  def setUp(self):

    self.assertTrue(AppiumTestCase.Name)

    self.assertTrue(AppiumTestCase.LogDir)

    self.assertTrue(AppiumTestCase.PlatformName)
    self.assertTrue(AppiumTestCase.PlatformVersion)
    self.assertTrue(AppiumTestCase.DeviceName)
    self.assertTrue(AppiumTestCase.AppPackage)
    self.assertTrue(AppiumTestCase.AppActivity)


    self.assertTrue(AppiumTestCase.Host)

    self._logger = src.utils.logger.getLogger("%s::%s" % (AppiumTestCase.Name, self.__class__.__name__))

    self._logCache = ""

    logcatFileName = os.path.join(AppiumTestCase.LogDir, ("%s.log" % (self.__class__.__name__,)))
    self._logFile = open(logcatFileName, encoding="utf-8", mode="w+")

    self.assertTrue(self._logFile)

    self._logger.info("create logcat file: {}, cache size: {}", logcatFileName, AppiumTestCase.MaxLogCacheSize)

    caps = {}
    caps['platformName'] = AppiumTestCase.PlatformName
    caps['platformVersion'] = AppiumTestCase.PlatformVersion
    caps['deviceName'] = AppiumTestCase.DeviceName
    caps['appPackage'] = AppiumTestCase.AppPackage
    caps['appActivity'] = AppiumTestCase.AppActivity

    version = int(AppiumTestCase.PlatformVersion.split(".")[0])
    if version < 5:
      caps["automationName"] = "UiAutomator1"
      self._logger.warn("use UiAutomator1 as driver for 4.X android devices")

    url = 'http://%s:%s/wd/hub' % (AppiumTestCase.Host, AppiumTestCase.Port)

    self._logger.info("start connect to test server: {}, AND device: {}", url, AppiumTestCase.DeviceName)

    self._driver = webdriver.Remote(url, caps)

    self._logcatLogs = []

    self._logger.info("connect to test server: {}, AND device: {} SUCCEED", url, AppiumTestCase.DeviceName)

    if AppiumTestCase.NeedPermmit:
      self._autoClickPermissionDlg()

  def tearDown(self):

    self._suckLogcat()
    self.ignorePreLog()
    self._logFile.close()

    if self._isTestSucceed():
      self._logger.info("tearDown start, test is {} =======================", "SUCCEED")
    else:
      self._logger.error("tearDown start, test is {} =======================", "FAILED")

    self._driver.quit()

    self._logger.info("tearDown disconnect from test server SUCCEED")

  def _listToReason(self, excList):
    if excList and excList[-1][0] is self:
      return excList[-1][1]

  def _isTestSucceed(self):
    if hasattr(self, '_outcome'):  # Python 3.4+
      result = self.defaultTestResult()  # these 2 methods have no side effects
      self._feedErrorsToResult(result, self._outcome.errors)
    else:  # Python 3.2 - 3.3 or 3.0 - 3.1 and 2.7
      result = getattr(self, '_outcomeForDoCleanups', self._resultForDoCleanups)
    error = self._listToReason(result.errors)
    failure = self._listToReason(result.failures)
    return (not error and not failure)

  def driver(self):
    return self._driver

  def logger(self):
    return self._logger

  def enterBackground(self, seconds):

    if (not AppiumTestCase.HelperApp) or (not AppiumTestCase.HelperAppActivity):
      currentActivity = self._driver.current_activity
      self._driver.background_app(seconds)
      self._logger.info("enterBackground pre activity: {}, current activity: {}",
                      currentActivity, self._driver.current_activity)

    self._logger.info("enterBackground use helper to enter background, helper app: {}, {}",
                      AppiumTestCase.HelperApp, AppiumTestCase.HelperAppActivity)

    try:
      self._driver.start_activity(AppiumTestCase.HelperApp, AppiumTestCase.HelperAppActivity)
      time.sleep(seconds)
      self.assertTrue(self.findElementById("com.haima.appium_helper:id/checkExist"))
      self._driver.back()
      time.sleep(1)
    except Exception as e:
      self.fail("enterBackground use helper failed with Exception: " + str(e))

  def enableWifi(self, sleepTime=1):
    self._driver.set_network_connection(6)
    time.sleep(sleepTime)

  def disableWifi(self, sleepTime=1):
    self._driver.set_network_connection(4)
    time.sleep(sleepTime)

  def _autoClickPermissionDlg(self, maxCheckTime = 10):

    self._logger.info("_autoClickPermissionDlg is call")

    startTime = util.unixTime()

    allowBtnIdList = ["com.android.permissioncontroller:id/permission_allow_button"]
    denyBtnIdList = ["com.android.permissioncontroller:id/permission_deny_button"]

    while startTime + maxCheckTime >= util.unixTime():

      allowBtn = None
      denyBtn = None

      for allowBtnId in allowBtnIdList:
        allowBtn = self.findElementById(allowBtnId)
        if allowBtn:
          break

      for denyBtnId in denyBtnIdList:
        denyBtn = self.findElementById(denyBtnId)
        if denyBtn:
          break

      if allowBtn and denyBtn:
        allowBtn.click()
        time.sleep(2)
        self._logger.info("_autoClickPermissionDlg auto click one dialog")
        continue

      time.sleep(1)

  def isElementVisibleById(self, id):

    try:
      element = self.findElementById(id)
      if element:
        return element.is_displayed()
      return False
    except:
      return False

  def isElementChecked(self, element):

    try:
      return element and (element.get_attribute("checked") == "true")
    except:
      return False

  def isElementVisibleByXPath(self, xpath):

    try:
      element = self.findElementByXPath(id)
      if element:
        return element.is_displayed()
      return False
    except:
      return False

  def findElementById(self, id, maxTryTime = 1):

    for i in range(maxTryTime):
      try:
        return self._driver.find_element_by_id(id)
      except Exception as e:
        if str(e).find("An element could not be located on the page") == -1:
          self._logger.warn("findElementById got exception: {}", str(e))
        if i < (maxTryTime - 1):
          time.sleep(2)

    return None

  def findElementByXPath(self, xpath, maxTryTime = 1):

    for i in range(maxTryTime):
      try:
        return self._driver.find_element_by_xpath(xpath)
      except Exception as e:
        if str(e).find("An element could not be located on the page") == -1:
          self._logger.warn("findElementByXPath got exception: {}", str(e))
        if i < (maxTryTime - 1):
          time.sleep(2)

    return None

  def findElementByClassName(self, className, text = None, maxTryTime = 1):

    for i in range(maxTryTime):
      try:
        elementList = self._driver.find_elements_by_class_name(className)
        if (not elementList) or (len(elementList) == 0):
          return None

        if text:
          for element in elementList:
            if element.text == text:
              return element
          return None

        return elementList[0]
      except Exception as e:
        if str(e).find("An element could not be located on the page") == -1:
          self._logger.warn("findElementByClassName got exception: {}", str(e))
        if i < (maxTryTime - 1):
          time.sleep(2)

    return None

  def getElementTextById(self, id):

    try:
      element = self.findElementById(id)
      if element:
        return element.text
      return None
    except:
      return None

  def getElementTextByXPath(self, xpath):

    try:
      element = self.findElementByXPath(xpath)
      if element:
        return element.text
      return None
    except:
      return None

  def _scrollUp(self, margin = 0.45, durationInMs = 500):

    try:
      width = self._driver.get_window_size()['width']
      height = self._driver.get_window_size()['height']

      x1 = width * 0.5
      y1 = height * 0.25
      y2 = height * (0.25 + margin)
      self._driver.swipe(x1, y1, x1, y2, durationInMs)
    except Exception as e:
      self._logger.warn("_scrollDown got exception: {}", str(e))


  def _scrollDown(self, margin = 0.45, durationInMs = 500):

    try:
      width = self._driver.get_window_size()['width']
      height = self._driver.get_window_size()['height']

      x1 = width * 0.5
      y1 = height * (0.25 + margin)
      y2 = height * 0.25
      self._driver.swipe(x1, y1, x1, y2, durationInMs)
    except Exception as e:
      self._logger.warn("_scrollDown got exception: {}", str(e))

  def scrollUpToFindElementById(self, id, maxScrollCount = 5, margin = 0.45, sleepTime = 1):

    for _ in range(maxScrollCount):
      element = self.findElementById(id)
      if element:
        return element

      self._scrollUp(margin=margin)
      time.sleep(sleepTime)

  def scrollUpToFindElementByXPath(self, xpath, maxScrollCount = 5, margin = 0.45, sleepTime = 1):

    for _ in range(maxScrollCount):
      element = self.findElementByXPath(xpath)
      if element:
        return element

      self._scrollUp(margin=margin)
      time.sleep(sleepTime)

  def scrollUpToFindElementByClassName(self, className, text = None, maxScrollCount = 5, margin = 0.45, sleepTime = 1):

    for _ in range(maxScrollCount):
      element = self.findElementByClassName(className, text)
      if element:
        return element

      self._scrollUp(margin=margin)
      time.sleep(sleepTime)

    return None

  def scrollDownToFindElementById(self, id, maxScrollCount = 5, margin = 0.45, sleepTime = 1):

    for _ in range(maxScrollCount):
      element = self.findElementById(id)
      if element:
        return element

      self._scrollDown(margin=margin)
      time.sleep(sleepTime)

  def scrollDownToFindElementByXPath(self, xpath, maxScrollCount = 5, margin = 0.45, sleepTime = 1):

    for _ in range(maxScrollCount):
      element = self.findElementByXPath(xpath)
      if element:
        return element

      self._scrollDown(margin=margin)
      time.sleep(sleepTime)

    return None

  def scrollDownToFindElementByClassName(self, className, text = None, maxScrollCount = 5, margin = 0.45, sleepTime = 1):

    for _ in range(maxScrollCount):
      element = self.findElementByClassName(className, text)
      if element:
        return element

      self._scrollDown(margin=margin)
      time.sleep(sleepTime)

    return None

  def hideKeyBoard(self):
    self._driver.hide_keyboard()

  def back(self, sleepTime = 1):
    self._driver.back()
    time.sleep(sleepTime)

  def waitAndCheck(self, maxTime, fun, checkInterval = 1):

    startTime = util.unixTime()

    while startTime + maxTime >= util.unixTime():
      if fun():
        return True
      time.sleep(checkInterval)

    return False

  def _suckLogcat(self):

    try:
      logList = self._driver.get_log('logcat')
      logMessageList = list(map(lambda log: log['message'] if (log['message'].find("appium") == -1) else "", logList))

      self._logCache += "\n".join(logMessageList)
    except Exception as e:
      self._logger.error("_suckLogcat got exception: {}", str(e))


  def checkLog(self, logString, ignorePreLog=False):

    self._suckLogcat()

    ret = self._logCache.find(logString)

    if ignorePreLog or len(self._logCache) > AppiumTestCase.MaxLogCacheSize:
      self.ignorePreLog()

    return ret

  def ignorePreLog(self):

    if self._logCache:
      self._logFile.write(self._logCache)

    self._logCache = ""




def demo():
  pass

if __name__ == '__main__':
  demo()
