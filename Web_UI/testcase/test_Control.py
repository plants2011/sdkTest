# -*- coding:utf-8 -*-
# @Time   : 2021.11.29 18:57
# @Author : yanxin


import os
import time

from common.driverBase import base
from page.webPage import webpage
from common.getplayaddress import ps

import pytest
import allure


class TestControl:

    @pytest.mark.control
    @allure.feature('获取授权码功能')
    @allure.title('case14：获取授权码')
    # case:获取授权码
    def test_getPincode(self):
        print('\ncase14：获取授权码')

        #1-播流旅行青蛙
        webpage.test_playsdk(1)

        # 2- 调用获取授权码接口
        webpage.getpincode()
        time.sleep(3)

        # 3- 获取接口返回接口
        pincodeinfo = webpage.get_alert()
        print(pincodeinfo)

        # 4- 关闭alert弹窗
        webpage.quit_alert()

        # 5- 获取接口返回的cid与pincode
        if pincodeinfo:
            mastercid = pincodeinfo.split('，')[0].split("：")[1]
            pincode = pincodeinfo.split('，')[1].split("：")[1]
            result = pincodeinfo.split('，')[2].split("：")[1]
        else:
            print("获取授权码失败！")

        # 6-1 获取本次播流cid
        webpage.getcid()
        time.sleep(2)

        #6-2 获取alert弹窗结果
        cid = webpage.get_alert()
        time.sleep(2)

        #6-3  关闭alert弹窗
        webpage.quit_alert()

        #6-断言
        assert  (result == '1') and (mastercid == cid) , '未获得授权码'

        if (result == '1') and (mastercid == cid):
            print('获取授权码成功！')
        else:
            print('获取授权码失败！')

        return mastercid,pincode


    @pytest.mark.getcontrol
    @allure.feature('控制权转移功能')
    @allure.title('case15：控制权转移')
    # case:获取控制权
    def test_getControl(self):
        print('\ncase15：控制权转移')

        #1-获得控制权播流地址
        webpage.getpincode()
        time.sleep(3)

        # 2- 获取alert弹窗结果
        pincodeinfo = webpage.get_alert()
        webpage.quit_alert()

        # 3- 获取cid与pincode
        mastercid = pincodeinfo.split('，')[0].split("：")[1]
        pincode = pincodeinfo.split('，')[1].split("：")[1]

        controller_url = ps.create_url(1) + '&cid=' + mastercid + '&pincode='  + pincode
        print('获取控制权播流地址：\n' + controller_url)

        # 4-打开新窗口
        webpage.open_newtab()
        time.sleep(3)

        # 5-获取控制权
        base.open_url(controller_url)
        time.sleep(15)


        # 6-获取控制权信息
        controllinfo = webpage.get_alert()
        #print(controllinfo)
        result =  controllinfo.split('，')[0].split("：")[1]
        #print(result)

        # 7- 关闭alert弹窗
        webpage.quit_alert()
        time.sleep(2)

        # 8- 断言与结果判断
        assert  result=="1" , '未获得授权码'

        if result == '1':
            print('\n获取控制权成功！' )
        else:
            print('\n获取控制权失败！' )

        time.sleep(5)

        # 9-结束游戏
        webpage.gameover()
        webpage.quit_alert()
        time.sleep(5)

        return '获取控制权结果：' +  controllinfo




if __name__ == '__main__':
    # 执行pytest单元测试，生成 Allure 报告需要的数据存在 /tmp 目录
    pytest.main(['test_Control.py','-s','--alluredir', '../report/tmp'])
    #pytest.main(['test_Control.py','-s','-m','quit','--alluredir', '../report/tmp'])
    # 执行命令 allure generate ./tmp -o ./report --clean ，生成测试报告
    os.system('allure generate  ../report/tmp -o ../report/report --clean')



