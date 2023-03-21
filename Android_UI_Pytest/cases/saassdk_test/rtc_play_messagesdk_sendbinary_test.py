__author__ = "leixin"

import  logging
import time

from util import demo
from util.common_steps import saassdk_demo
from util.common_steps import android_common
from util.common_steps import android_demo_setting


#log = logging.getLogger(__name__)

def test_setup():
    demo.start_demo("AndroidArm")
    android_demo_setting.gameinfo_choosegame("MsgSdk")
    android_demo_setting.userinfo_setuserid("lei1234")


    saassdk_demo.init_sdk()
    saassdk_demo.play_sdk()
    time.sleep(15)
    android_common.check_play()
    time.sleep(1)
    android_common.switchdelay("close")
    time.sleep(1)
    #初始化
    android_common.touchclick(0.06, 0.067)
    #发送二进制
    time.sleep(1)
    android_common.touchclick(0.21, 0.341)
    time.sleep(1)
    android_common.d_back()

    demo.stop_demo("AndroidArm")
