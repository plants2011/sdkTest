__author__ = "leixin"

#释放实例

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
    iOSSdk_setting.ios_releaseinstance(d)
    time.sleep(1)

    demo.stop_demo("iOS_Arm")





