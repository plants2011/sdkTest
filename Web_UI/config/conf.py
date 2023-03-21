# -*- coding:utf-8 -*-
# @Time   : 16:47
# @Author : yanxin

from selenium.webdriver.common.by import  By

class ConfigManager():

    #元素定位类型
    LOCALE_MODE={
        'id': By.ID,
        'class': By.CLASS_NAME,
        'css': By.CSS_SELECTOR
    }

cm = ConfigManager()

#print(cm.LOCALE_MODE['class'])