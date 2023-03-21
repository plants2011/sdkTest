# -*- coding:utf-8 -*-
# @Time   : 16:05
# @Author : yanxin

"""
selenium基类
本文件存放了selenium基类的封装方法
"""
import time
from common.driver_common import CommDriver


class driverBase():

    # 获取驱动
    def __init__(self):
        self.driver = CommDriver().get_driver()

    def only_driver(self):
        return self.driver

    # 驱动打开页面
    def open_url(self, url):
        self.driver.get(url)

    # @staticmethod
    # def element_locator(func,locater):
    #     # locator: 元素定位器
    #     如(By.ID, 'username')['id', 'username']
    #     name,value = locater
    #     return func(cm.LOCAL_MODE[locater],value)

    # 元素定位
    def get_element(self, locator):
        """
        异常保护,如果定位不到就可以截图、日志
        :return: 返回元素
        """
        return self.driver.find_element(*locator)

    # 多元素定位
    def get_elements(self, locator):
        return self.driver.find_elements(*locator)


    #元素点击
    def click_element(self, locator):
        """
        点击元素
        :param locator:  定位器
        :return:  None
        """
        return self.get_element(locator).click()

    #多元素点击
    def click_elements(self, locator, num):
        """
        点击元素
        :param locator:  定位器
        :return:  None
        """
        return self.get_elements(locator)[num].click()

    # 获取元素的文本
    def get_element_text(self, locator):
        """
        获取元素的文本
        :param locator: 定位器
        :return:
        """
        return self.get_element(locator).text
    def get_elements_text(self, locator, num):
        """
        获取元素的文本
        :param locator: 定位器
        :return:
        """
        return self.get_elements(locator)[num].text



    #alert弹窗，获取弹窗内文本
    def get_alert_text(self):
        return self.driver.switch_to.alert.text

    #弹窗内输入
    def alert_input(self,file):
        return self.driver.switch_to.alert.send_keys(file)

    # alert弹窗，关闭弹窗
    def quit_alert(self):
        return self.driver.switch_to.alert.accept()

    #刷新
    def refresh(self):
        return  self.driver.refresh()

    #打开新窗口
    def open_newtab(self,js):
        return self.driver.execute_script(js)

    #退出
    def driver_quit(self):
        return  self.driver.quit()




base = driverBase()

if __name__ == '__main__':

    base.open_url('http://www.baidu.com')
    time.sleep(5)
    locator = base.get_element(('id','kw'))
    locator.send_keys('松勤网')
    time.sleep(5)

    locator1 = base.click_element(('id','su'))
    time.sleep(5)


    locatoe2 = base.get_element_text(('id','1'))
    print(locatoe2)
    time.sleep(5)

    base.driver_quit()




