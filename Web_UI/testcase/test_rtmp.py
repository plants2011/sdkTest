# -*- coding:utf-8 -*-
# @Time   : 16:14
# @Author : yanxin


import os
import time

from page.webPage import webpage
from common.driverBase import base
from common.getplayaddress import ps
from selenium.webdriver.common.by import  By
import pytest
import allure





class TestPlayRTMP:

    @pytest.mark.basic
    @allure.feature('基本功能')
    @allure.title('case9：播流RTMP')
    def test_playrtmp(self):
        print('\ncase9：播流rtmp')

        # #0-打开网页设置页面
        # webpage.start_flash()

        #1-1 等待20s，手动开启flash
        #time.sleep(10)

        #1-播流旅行青蛙，流类型为rtmp
        webpage.test_playsdk(3)

        #2-获取vibf信息
        vibf = webpage.checkplay()
        print('vibf提示: ' + vibf)
        time.sleep(3)

        #3-断言与结果判断
        container = '触发vibf场景回调'

        #4-断言
        assert   vibf == container, 'webSDK播流失败！'

        if vibf.startswith(container):
            print('webSDK播流成功！')
        else:
            print('webSDK播流失败！')

        #5-结束游戏
        webpage.gameover()
        webpage.quit_alert()
        time.sleep(5)


        #6-返回结果
        return vibf



if __name__ == '__main__':
    # 执行pytest单元测试，生成 Allure 报告需要的数据存在 /tmp 目录
    pytest.main(['test_rtmp.py','-s','--alluredir', '../report/tmp'])
    os.system('allure generate  ../report/tmp -o ../report/report --clean')