# -*- coding:utf-8 -*-
# @Time   : 16:31
# @Author : yanxin

import os
import time

from page.webPage import webpage

import pytest
import allure



class TestBasicAndLiving:

    @pytest.mark.over
    @allure.feature('基本功能')
    @allure.title('case18：游戏时长到，游戏结束')
    def test_timeout(self):

        print('\ncase18：游戏时长到，游戏结束')

        #1-播流时长60s的游戏
        webpage.test_playsdk(5)

        #2-等待60s
        print('开始等待60s')
        time.sleep(60)

        #3-获取stop场景回调
        wait_reason = webpage.get_alert()
        webpage.quit_alert()

        #4-获取游戏界面展示
        word = webpage.gameover1()
        time.sleep(3)

        #5-断言
        assert (wait_reason == "time_limit") and ('游戏结束' in word), '游戏未结束！'

        if (wait_reason == "time_limit") and ('游戏结束' in word):
            print('游戏结束成功！')
        else:
            print('游戏未结束！')



    @pytest.mark.over
    @allure.feature('基本功能')
    @allure.title('case19：长时间无操作，游戏结束')
    def test_nooperation(self):

        print('\ncase19：长时间无操作，游戏结束')

        #1-播流长时间无操作1分钟的游戏
        webpage.test_playsdk(7)

        #2-等待60s
        print('开始等待60s')
        time.sleep(60)

        #3-获取stop场景回调
        wait_reason = webpage.get_alert()
        webpage.quit_alert()

        #4-获取游戏界面展示
        word = webpage.gameover1()
        time.sleep(3)

        #5-断言
        assert (wait_reason == "no_operation") and ('您已长时间无操作' in word), '长时间无操作，游戏结束失败！'

        if (wait_reason == "no_operation") and ('您已长时间无操作' in word):
            print('长时间无操作，游戏结束成功！')
        else:
            print('长时间无操作，游戏结束失败！')


if __name__ == '__main__':
    # 执行pytest单元测试，生成 Allure 报告需要的数据存在 /tmp 目录
    pytest.main(['test_endGame.py','-s','--alluredir', '../report/tmp'])
    os.system('allure generate  ../report/tmp -o ../report/report --clean')

