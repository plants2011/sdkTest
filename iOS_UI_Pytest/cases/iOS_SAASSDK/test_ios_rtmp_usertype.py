__author__ = "leixin"


import wda
import time
from util import demo
from util.common_steps import  iOS_SaasSDK
from util.common_steps import iOSSdk_setting
from util.common_steps import iOS_common

#测试VIP用户类型

def test_init():
    demo.start_demo("iOS_Arm")
    d = iOS_SaasSDK.s
    iOSSdk_setting.setGame(d,"Msg_1.2")
    time.sleep(2)
    iOSSdk_setting.setUserid(d,"uiautotest123")
    iOSSdk_setting.setUsertype(d,"1")
    iOSSdk_setting.setStreamtype(d,"rtmp")
    iOSSdk_setting.ios_init(d)
    iOSSdk_setting.ios_play(d)
    time.sleep(5)

    #iOS_common.exit_game(d)

    #iOS_SaasSDK.teardown_function()

    demo.stop_demo("iOS_Arm")





