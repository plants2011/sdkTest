# -*- coding:utf-8 -*-
# @Time   : 17:40
# @Author : yanxin

#定义一个函数，用于直播推流
# import subprocess
#
# def pushStreamer(pushorder):
#
#     print('运行cmd指令：{}'.format(pushorder))
#     subprocess.Popen(pushorder,shell=True, stdout=None, stderr=None).wait()

def pushStreamer(pushorder):
    import os
    a = os.system(pushorder)
    print(a)

if __name__ == '__main__':
    pushStreamer("pip list")
