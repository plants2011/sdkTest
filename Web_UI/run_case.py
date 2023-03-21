# -*- coding:utf-8 -*-
# @Time   : 12:45
# @Author : yanxin

import os

import pytest
import allure

#pytest.main(['testcase', '-s','--alluredir', '../report/tmp'])
pytest.main(['testcase', '-s','--alluredir=report/tmp'])
os.system('allure generate  ./report/tmp -o ./report/report --clean')



