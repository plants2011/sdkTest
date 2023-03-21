__author__ = "leixin"

#验证摄像头功能

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
    #iOSSdk_setting.switch_game(d,"qq","OpenCamera")
    time.sleep(2)
    iOSSdk_setting.setUserid(d,"uiautotest123")
    iOSSdk_setting.ios_init(d)
    iOSSdk_setting.ios_play(d)
    time.sleep(4)
    d.click(0.7, 0.964)
    iOS_common.check_cameraauth(d)
    time.sleep(4)
    iOS_common.exit_game(d)

    #iOS_SaasSDK.teardown_function()

    demo.stop_demo("iOS_Arm")





