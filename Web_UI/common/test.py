# -*- coding:utf-8 -*-
# @Time   : 19:16
# @Author : yanxin
import time

from selenium import webdriver

from common.driver_common import CommDriver

from selenium.webdriver.common.by import  By

driver = CommDriver().get_driver()
driver.get('http://www.baidu.com')
t = driver.find_element('id','kw')
t.send_keys('测试')

time.sleep(10)

s=driver.find_element('id','su')
s.click()
time.sleep(10)

driver.quit()