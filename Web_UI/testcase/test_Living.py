# -*- coding:utf-8 -*-
# @Time   : 20:31
# @Author : yanxin

import os
import time

from page.webPage import webpage

import pytest
import allure



class TestLiving:

    @pytest.mark.living
    @allure.feature('直播功能')
    @allure.title('case11：是否支持直播')
    # case：是否支持直播
    def test_isSupportLiving(self):
        print('\ncase11：是否支持直播')

        # 1-播流旅行青蛙
        webpage.test_playsdk(1)

        # 2- 调用是否支持直播接口
        webpage.issupportLiving()
        time.sleep(1)

        # 3- 获取调用结果
        isSupport = webpage.get_alert()
        print("是否支持直播接口，查询结果：" + isSupport)

        # 4- 关闭alert弹窗
        webpage.quit_alert()

        # 5- 断言
        assert isSupport in ('true', 'false'), '是否支持直播接口：未获得接口响应值！'




    @pytest.mark.living
    @allure.feature('直播功能')
    @allure.title('case12：开启直播')
    # case:-开启直播
    def test_startLiving(self):
        print('\ncase12：开启直播')
        # 1- 开启直播
        webpage.startliving()
        time.sleep(2)

        # 2- 获取alert弹窗结果
        startresult = webpage.get_alert()
        time.sleep(2)

        # 3- 关闭alert弹窗
        webpage.quit_alert()

        # 4- 直播推流
        if startresult == "true":
            print(
                '开启直播成功！' + '\n推流命令：ffplay -i rtmp://live-test.haimacloud.com/live/livetest003 -x 800 -y 800' + "\n备注：直播依赖工具ffmpeg")
            time.sleep(2)
        else:
            print("开启直播失败！")

        # 5- 断言
        assert startresult  == 'true',  '开启直播接口：调用未获得响应。'




    @pytest.mark.living
    @allure.feature('直播功能')
    @allure.title('case13：停止直播')
    # case:停止直播
    def test_stopLiving(self):
        print('\ncase13：停止直播')
        # 1- 停止直播
        webpage.stopliving()
        time.sleep(2)

        # 2- 获取alert弹窗结果
        stopresult = webpage.get_alert()
        # print("停止直播结果：" +stopresult)
        time.sleep(2)

        # 3- 关闭alert弹窗
        webpage.quit_alert()

        # 4- 停止直播结果判断
        if stopresult == "true":
            print('停止直播成功！')
            time.sleep(1)
        else:
            print("停止直播失败！")

        # 5- 断言
        assert stopresult == 'true',  '停止直播接口：调用未获得响应。'


        # 6-结束游戏
        webpage.gameover()
        webpage.quit_alert()
        time.sleep(5)


if __name__ == '__main__':
    # 执行pytest单元测试，生成 Allure 报告需要的数据存在 /tmp 目录
    pytest.main(['test_Living.py','-s','--alluredir', '../report/tmp'])
    #pytest.main(['test_Control.py','-s','-m','quit','--alluredir', '../report/tmp'])
    # 执行命令 allure generate ./tmp -o ./report --clean ，生成测试报告
    os.system('allure generate  ../report/tmp -o ../report/report --clean')

