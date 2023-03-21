__author__ = "leixin"

#测试锁屏后解锁

import wda
import time
from util import demo
from util.common_steps import  iOS_SaasSDK
from util.common_steps import iOSSdk_setting
from util.common_steps import iOS_common



def test_init():
    demo.start_demo("iOS_Arm")
    d = iOS_SaasSDK.s
    iOSSdk_setting.setGame(d,"Msg_1.2")
    time.sleep(2)
    iOSSdk_setting.setUserid(d,"uiautotest123")
    iOSSdk_setting.ios_init(d)
    iOSSdk_setting.ios_play(d)
    time.sleep(4)
    d.lock()
    time.sleep(2)
    d.unlock()


    time.sleep(3)

    #iOS_SaasSDK.teardown_function()
    iOS_common.exit_game(d)


    demo.stop_demo("iOS_Arm")





