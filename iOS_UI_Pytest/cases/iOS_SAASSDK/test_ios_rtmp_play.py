__author__ = "leixin"

#测试rtmp播流

import wda
import time
from util import demo
from util.common_steps import  iOS_SaasSDK
from util.common_steps import iOSSdk_setting
from util.common_steps import iOS_common



def test_init():
    demo.start_demo("iOS_Arm")
    d = iOS_SaasSDK.s
    iOSSdk_setting.setUserid(d,"uiautotest123")
    iOSSdk_setting.setStreamtype(d,"rtmp")
    iOSSdk_setting.ios_init(d)
    iOSSdk_setting.ios_play(d)
    time.sleep(5)
    iOS_common.exit_game(d)

    demo.stop_demo("iOS_Arm")





