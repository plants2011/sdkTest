# -*- coding:utf-8 -*-
# @Time   : 18:20
# @Author : yanxin
import os

def clear():
    for one in os.listdir('../report/tmp'):
        if "json" in one:
            os.remove(f"../report/tmp/{one}")
        if "txt" in one:
            os.remove(f"../report/tmp/{one}")



