__author__ = "leixin"

#播流后旋转游戏

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
    time.sleep(3)
    iOS_common.op_displaylog(d)
    iOS_common.op_sendtext(d,"1")
    time.sleep(1)
    iOS_common.op_getdisplaylog(d)
    time.sleep(3)
    iOS_common.exit_game(d)


    demo.stop_demo("iOS_Arm")





