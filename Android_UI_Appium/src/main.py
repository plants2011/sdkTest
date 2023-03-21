# -*-coding:utf-8-*-

###
### main entry for auto test tool
### by Jian

import os, sys
sys.path.append(sys.path[0][:(sys.path[0].rindex((os.sep + "src")))])

import unittest
import time

import src.utils.util as util
from src.test.appium_test_case import AppiumTestCase

import src.utils.logger
logger = src.utils.logger.getLogger("AutoTest")

DEFAULT_LOGCAT_DIR = "./logcat"

class Loader:

  def __init__(self, cfgFileName):

    logger.info("Loader init with cfgFileName: {}", cfgFileName)

    self._cfgData = util.loadJsonFromFile(cfgFileName, byteify=False)

    logger.info("Loader read cfg data: {}", util.toJsonString(self._cfgData))

  def start(self):

    AppiumTestCase.Name = self._cfgData["name"]

    AppiumTestCase.PlatformName = self._cfgData["platformName"]
    AppiumTestCase.PlatformVersion = self._cfgData["platformVersion"]
    AppiumTestCase.DeviceName = self._cfgData["deviceName"]
    AppiumTestCase.AppPackage = self._cfgData["appPackage"]
    AppiumTestCase.AppActivity = self._cfgData["appActivity"]

    if "host" in self._cfgData:
      AppiumTestCase.Host = self._cfgData["host"]

    if "port" in self._cfgData:
      AppiumTestCase.Port = self._cfgData["port"]

    if "maxLogCacheSize" in self._cfgData:
      AppiumTestCase.MaxLogCacheSize = self._cfgData["maxLogCacheSize"]

    if "helperApp" in self._cfgData:
      AppiumTestCase.HelperApp = self._cfgData["helperApp"]

    if "helperAppActivity" in self._cfgData:
      AppiumTestCase.HelperAppActivity = self._cfgData["helperAppActivity"]

    if "needPermmit" in self._cfgData:
      AppiumTestCase.NeedPermmit = self._cfgData["needPermmit"]

    pLogDir = DEFAULT_LOGCAT_DIR
    if "logcatDir" in self._cfgData:
      pLogDir = self._cfgData["logcatDir"]

    logDir = os.path.join(pLogDir, "%s_%s" % (AppiumTestCase.Name, time.strftime('%Y-%m-%d_%H-%M-%S',time.localtime(time.time()))))

    util.createDir(logDir)

    AppiumTestCase.LogDir = logDir

    logger.info("Loader init logcat dir: {}", logDir)

    suite = unittest.TestSuite()

    for testcaseCfg in self._cfgData["testcases"]:

      if ("run" in testcaseCfg) and (not testcaseCfg["run"]):
        logger.info("Loader IGNORE test case: {} -------------------", testcaseCfg["className"])
        continue

      cls = util.strToClass(testcaseCfg["className"])
      suite.addTests(unittest.TestLoader().loadTestsFromTestCase(cls))

      if "options" in testcaseCfg:
        for k in testcaseCfg["options"]:
          cls.Options[k] = testcaseCfg["options"][k]

    runner = unittest.TextTestRunner(verbosity=1)
    runner.run(suite)

def main():

  if len(sys.argv) != 2:
    logger.error("invalid argv: {}", sys.argv)
    return

  Loader(sys.argv[1]).start()

if __name__ == '__main__':
    main()

