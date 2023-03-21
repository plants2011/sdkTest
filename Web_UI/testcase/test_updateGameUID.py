# -*- coding:utf-8 -*-
# @Time   : 17:44
# @Author : yanxin

import os
import time

from page.webPage import webpage

import pytest
import allure




class TestupdateGameUID:

    @pytest.mark.updata
    @allure.feature('基本功能')
    @allure.title('case20：更新uid与游戏时长')
    def test_updateGameUID(self):

        print('\ncase20：更新uid与游戏时长')

        #1-播流旅行青蛙
        webpage.test_playsdk(1)

        #2-调用更新接口
        webpage.updateUID()
        time.sleep(2)

        #3-获取接口调用响应
        code = webpage.get_alert()
        webpage.quit_alert()

        #4-获取totaltime场景回调
        toast = webpage.get_toasts()

        #5-断言
        assert (code == '0' ) and ('214接口' in toast), '更新uid与playingtime失败！'

        if (code == '0' ) and ('214接口' in toast):
            print('更新uid与playingtime成功！')
        else:
            print('更新uid与playingtime失败！')

        #6-结束游戏
        webpage.gameover()
        webpage.quit_alert()
        time.sleep(5)



if __name__ == '__main__':
    # 执行pytest单元测试，生成 Allure 报告需要的数据存在 /tmp 目录
    pytest.main(['test_updateGameUID.py','-s','--alluredir', '../report/tmp'])
    os.system('allure generate  ../report/tmp -o ../report/report --clean')

