# -*- coding:utf-8 -*-
# @Time   : 21:47
# @Author : yanxin
import time

import pytest
from common.driverBase import base
# from config.gameConfig import gameInfo
from common.getplayaddress import ps
from utils import tmpClear

@pytest.fixture(scope='session', autouse = True)
def drivers(request): #这个运行该包下，任何一个test文件，都会一开始就执行的操作

    print("\n---------清除上次测试报告记录-----------")
    tmpClear.clear()

    print("\n---------开始执行自动化测试-----------")





    #数据清除操作：websdk播流，无数据清除，就是退出driver
    def fin():
        base.driver_quit()
        print("\n------自动化测试结束--------")
    request.addfinalizer(fin)
