# -*- coding:utf-8 -*-
# @Time   : 2021.12.23 17:49
# @Author : yanxin
#
from selenium import webdriver
from config.driverConfig import Def_Browser_Type, Headless_Flag , IMPLICITLY_WAIT_TIME


class CommDriver():
    driver = None  # 用来控制产生一个浏览器的

    def get_driver(self,browser_type=Def_Browser_Type,headless_flag=Headless_Flag):
        """
        :param browser_type: 浏览器类型，默认是DEF_BROWSER_TYPE
        :param headless_flag: 是否有头，TRUE是无头，FALSE有头
        :return: 返回浏览器实例
        """
        if self.driver is None:  #配合上面，如果产生过就有了，不是None
            if not headless_flag:
                if browser_type=="chrome":
                    self.driver = webdriver.Chrome()
                elif browser_type=="firefox":
                    self.driver = webdriver.Firefox()
                elif browser_type=="IE":
                    self.driver = webdriver.Ie(executable_path="D:\Program Files\Python\Python38\IEDriverServer.exe")
                else:
                    raise Exception(f'{browser_type}暂不支持' )
            else:
                if browser_type=="chrome":
                    self.option = webdriver.ChromeOptions()
                    self.option.add_argument('--headless')
                    # 此处的参数可以是--headless或者-headless，如果是headless，chrome是可以的
                    self.driver = webdriver.Chrome(options=self.option)
                elif browser_type=="firefox":
                    self.option = webdriver.FirefoxOptions()
                    self.option.add_argument('--headless')
                    self.driver = webdriver.Firefox(options=self.option)
                elif browser_type=="IE":
                    self.driver = webdriver.Ie()
                    self.option.add_argument('--headless')
                    self.driver = webdriver.Ie(options=self.option)
                else:
                    raise Exception(f'{browser_type}暂不支持' )
                # TODO 支持 IE EDGE等
            #self.driver.maximize_window()
            self.driver.implicitly_wait(IMPLICITLY_WAIT_TIME)
        return self.driver




# 调试脚本，暂时无用
nexttodo = '''最近发现了一个工具Grid,
搭建起来后，可以同时支持多个不同版本、不同类型的浏览器，
用来做自动化的兼容性测试很合适，后续研究一下'''


#调试：IE浏览器驱动
# from selenium import webdriver
# import time
# driver = webdriver.Ie(executable_path="D:/Program Files/Python/Python37/IEDriverServer.exe")
#
# driver.get("http://www.baidu.com")
# time.sleep(3)
# assert "百度一下" in driver.title
#
# elem=driver.find_element_by_id("kw")
# elem.send_keys("selenium")
# driver.find_element_by_id("su").click()
# time.sleep(3)
# assert "selenium" in driver.title
#
# driver.close()

#第一个报错：
# Message: Unexpected error launching Internet Explorer. Browser zoom level was set to 91%. It should be set to 100%
#解决办法，修改IE浏览器缩放比为100%

#第二个报错：
# Message: Unable to get browser
#参考文档：https://blog.csdn.net/weixin_34128501/article/details/85837819



if __name__ == '__main__':
    # driver1 = CommDriver().get_driver()
    # driver2 = CommDriver().get_driver(browser_type="firefox")
    driver3 = CommDriver().get_driver(browser_type="IE")
    #driver4 = CommDriver().get_driver(headless_flag=True)