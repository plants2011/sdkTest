# -*- coding:utf-8 -*-
# @Time   : 2021.11.29 18:57
# @Author : yanxin


import os
import time

from common.driverBase import base
from page.webPage import webpage

import pytest
import allure





class TestBasicAndLiving:

    @pytest.mark.basic
    @allure.feature('基本功能')
    @allure.title('case1：播流webrtc并校验播流结果')
    def test_playwebrtc(self):
        print('case1：播流webrtc')

        #1-播流旅行青蛙
        webpage.test_playsdk(1)

        #2-获取vibf信息
        vibf = webpage.checkplay()
        print('vibf提示: ' + vibf)
        time.sleep(1)

        #3-断言与结果判断
        container = '触发vibf场景回调'

        #4-断言
        assert   vibf == container, 'webSDK播流失败！'

        if vibf.startswith(container):
            print('webSDK播流成功！')
        else:
            print('webSDK播流失败！')

        #5-返回结果
        return vibf



    @pytest.mark.basic
    @allure.feature('基本功能')
    @allure.title('case2：toast提示信息')
    def test_gettoast(self):
        print('\ncase2：toast提示信息')

        # 1-获取toast提示消息
        toast = webpage.get_toast()
        time.sleep(1)

        # 2-打印toast提示内容
        print('toast提示：' +  toast)

        # 3-断言
        assert  toast != '','toast消息获取失败'

        # 4-返回toast提示内容
        return 'toast提示：' +  toast




    @pytest.mark.basic
    @allure.feature('基本功能')
    @allure.title('case3：切换清晰度')
    #3-切换分辨率，并检查切换后播流是否正常
    def test_switchResulotion(self):
        print('\ncase3：切换清晰度' )
        #1- 调用切换清晰度接口
        webpage.switchresoulution()
        time.sleep(3)

        #2- 获取alert弹窗结果
        resolutioninfo = webpage.get_alert()
        # print(resolutioninfo)
        result = resolutioninfo.split(',')[0].split(":")[1]
        cur_rate = resolutioninfo.split(',')[1].split(":")[1]

        #3- 关闭alert弹窗
        webpage.quit_alert()

        #4- 断言
        assert result ==  '1', '切换清晰度失败'

        #5- 判断是否获得有效cid
        if result ==  '1':
            print("切换清晰度成功,当前清晰度id:" + cur_rate)
        else:
            print('切换清晰度失败!')

        return resolutioninfo



    @pytest.mark.basic
    @allure.feature('基本功能')
    @allure.title('case4：刷新页面')
    # 4-刷新页面，并检查刷新后播流是否正常
    def test_refresh(self):
        print('\ncase4：刷新页面')
        #1- 获取刷新前页面分辨率
        beforerefresh = webpage.get_resolution()

        #2- 刷新页面
        base.refresh()
        time.sleep(5)
        print('刷新页面成功！')

        #3- 获取刷新后清晰度
        afterrefresh = webpage.get_resolution()

        #4- 断言
        assert afterrefresh == beforerefresh, '刷新成功后，清晰度改变！'

        #5- 检查刷新后清晰度
        if afterrefresh == beforerefresh:
            print('刷新后，清晰度不变！')
        else:
            print('刷新后，清晰度改变！')



    @pytest.mark.basic
    @allure.feature('基本功能')
    @allure.title('case5：网页全屏功能')
    #6- 网页全屏切换
    def test_webFullscreen(self):
        print('\ncase5：网页全屏功能测试' )

        #1- 打开网页全屏
        webpage.webFullscreen()
        time.sleep(5)

        #2- 获取网页全屏title
        title = webpage.get_webfull_title()

        #3-断言
        assert title == '退出网页全屏',"进入网页全屏模式失败！"

        if title == '退出网页全屏':
            print("已进入网页全屏模式!")
        else:
            print('进入网页全屏模式失败！')

        #4-  退出网页全屏
        time.sleep(5)
        webpage.webFullscreen()
        print("已退出网页全屏")


    @pytest.mark.basic
    @allure.feature('基本功能')
    @allure.title('case6：全屏功能')
    #6- 全屏与取消全屏
    def test_fullscreen(self):
        print('\ncase6：全屏功能测试:')

        # 1- 打开全屏
        webpage.fullscreen()
        time.sleep(5)

        # 2- 获取网页全屏title
        title = webpage.get_fullscreen_title()

        # 3-断言
        assert title == '退出全屏',"进入全屏模式失败！"

        if title == '退出全屏':
            print("已进入全屏模式!")
        else:
            print('进入全屏模式失败！')

        # 4-退出全屏
        time.sleep(5)
        webpage.quit_fullscreen()
        print("已退出全屏")
        time.sleep(5)



    @pytest.mark.basic
    @allure.feature('基本功能')
    @allure.title('case7：键鼠开关')
    # 7-键鼠开关切换
    def test_Keyboard(self):
        print('\ncase7：键鼠开关功能测试')

        #1- 打开键鼠配置功能区
        webpage.keyboard()
        print("打开键鼠配置功能区")
        time.sleep(2)

        #2- 获取键鼠展示
        guide = webpage.keyboard_guide()
        close = webpage.keyboard_close()
        save = webpage.keyboard_save()

        #3-断言
        assert (guide == '操作引导') and (close == '关闭') and (save == '保存'), "打开键鼠操作功能区失败！"

        if (guide == '操作引导') and (close == '关闭') and (save == '保存'):
            print("打开键鼠操作功能区成功！")
        else:
            print('打开键鼠操作功能区失败！')



        #4- 关闭键鼠配置功能区
        time.sleep(2)
        webpage.keyboard()
        print("已关闭键鼠配置功能区")





    @pytest.mark.quit
    @allure.feature('基本功能')
    @allure.title('case8：结束游戏功能测试')
    #case: 结束游戏
    def test_gameover(self):
        print('\ncase8：结束游戏功能测试')

        #1-调用结束游戏接口
        webpage.gameover()
        time.sleep(2)

        #2-获取stop场景回调
        wait_reason = webpage.get_alert()
        webpage.quit_alert()

        #3-获取游戏界面展示
        word = webpage.gameover1()
        time.sleep(2)

        #4-断言
        assert (wait_reason == "normal") and ('游戏结束' in word), '游戏未结束！'

        if '游戏结束' in word:
            print('游戏结束成功！')
        else:
            print('游戏未结束！')


# if __name__ == '__main__':
#     t = TestBasicAndLiving()
#     t.test_checkplayResult()
#     t.test_upload()
#     t.test_gameover()



if __name__ == '__main__':
    # 执行pytest单元测试，生成 Allure 报告需要的数据存在 /tmp 目录
    pytest.main(['test_BasicAndLiving.py','-s','--alluredir', '../report/tmp'])
    #pytest.main(['test_BasicAndLiving.py','-s','-m','quit','--alluredir', '../report/tmp'])
    # 执行命令 allure generate ./tmp -o ./report --clean ，生成测试报告
    os.system('allure generate  ../report/tmp -o ../report/report --clean')



