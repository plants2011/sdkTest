# -*- coding:utf-8 -*-
# @Time   : 10:28
# @Author : yanxin


import os
import time

from page.webPage import webpage

import pytest
import allure



class Testfullscreen:

    @pytest.mark.fullscreen
    @allure.feature('基本功能')
    @allure.title('case21:全面屏功能测试')
    def test_full_screen(self):

        print('\ncase21:全面屏功能测试')

        #1-全面屏游戏地址播流
        webpage.test_playsdk(6)

        #2-获取游戏界面比例
        scale = webpage.getscale()

        #3-断言
        standard_scale = 1920/880

        assert (standard_scale - scale) < abs(0.01), '全面屏播流失败！'

        if (standard_scale - scale) < abs(0.01):
            print('全面屏播流成功！')
        else:
            print('全面屏播流失败！')

        #4-结束游戏
        webpage.gameover()
        webpage.quit_alert()
        time.sleep(5)


if __name__ == '__main__':
    # 执行pytest单元测试，生成 Allure 报告需要的数据存在 /tmp 目录
    pytest.main(['test_fullscreen.py','-s','--alluredir', '../report/tmp'])
    os.system('allure generate  ../report/tmp -o ../report/report --clean')

