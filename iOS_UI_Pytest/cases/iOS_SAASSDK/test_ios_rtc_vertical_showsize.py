__author__ = "leixin"

#测试竖屏游戏播流

import wda
import time
from util import demo
from util.common_steps import  iOS_SaasSDK
from util.common_steps import iOSSdk_setting
from util.common_steps import iOS_common



def test_init():
    demo.start_demo("iOS_Arm")
    d = iOS_SaasSDK.s
    iOSSdk_setting.setGame(d,"OpenCamera")
    time.sleep(2)
    iOSSdk_setting.setUserid(d,"uiautotest123")
    iOSSdk_setting.setShowSize(d,"390x814")
    iOSSdk_setting.setShowPoint(d,"0x30")
    iOSSdk_setting.setResetPlayerFrame(d,"1")
    iOSSdk_setting.ios_init(d)
    iOSSdk_setting.ios_play(d)
    time.sleep(4)

    iOS_common.exit_game(d)

    #iOS_SaasSDK.teardown_function()

    demo.stop_demo("iOS_Arm")





