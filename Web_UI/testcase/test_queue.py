# -*- coding:utf-8 -*-
# @Time   : 10:11
# @Author : yanxin


import os
import time

from page.webPage import webpage

import pytest
import allure


class TestQueue:

    @pytest.mark.queue
    @allure.feature('排队功能测试')
    @allure.title('case10：排队功能测试')
    # case:排队功能测试
    def test_queue(self):
        print('\ncase10：排队功能测试')

        # 1-播流排队游戏4
        webpage.test_playsdk(4)

        #2- 获取alert弹窗结果
        wait_reason = webpage.get_alert()
        print(wait_reason )

        #3- 关闭alert弹窗
        webpage.quit_alert()

        #4 断言与打印
        assert   wait_reason == 'whetherToQueue', '当前游戏不支持排队！'

        if wait_reason == 'whetherToQueue':
            print('进入确认排队弹窗页面！')
        else:
            print('当前游戏不支持排队！')

        #5- 手动点击 继续按钮
        print('等待选择')
        time.sleep(5)
        webpage.enter_queue()

        #6-1  获取alert弹窗结果
        wait_reason = webpage.get_alert()
        print(wait_reason )
        #6-2 关闭alert弹窗
        webpage.quit_alert()

        #6-3 断言与打印
        assert   wait_reason == "enterQueue", '排队失败！'

        if wait_reason == "enterQueue":
            print('进入排队页面！')
        else:
            print('排队失败！')

        #7-结束游戏
        webpage.gameover()
        webpage.quit_alert()
        time.sleep(5)




if __name__ == '__main__':
    # 执行pytest单元测试，生成 Allure 报告需要的数据存在 /tmp 目录
    pytest.main(['test_queue.py','-s','--alluredir', '../report/tmp'])
    os.system('allure generate  ../report/tmp -o ../report/report --clean')
