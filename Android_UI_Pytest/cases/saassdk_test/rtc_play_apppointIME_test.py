__author__ = "xiaomao"

import  logging
import time
from util import demo
from util.common_steps import saassdk_demo
from util.common_steps import android_common
from util.common_steps import android_demo_setting


def test_setup():

    demo.start_demo("AndroidArm")
    android_demo_setting.gameinfo_choosegame("QQ-mao1")
    android_demo_setting.gameinfo_choosebid("staging")

    #app指定输入法  本地输入法传1，云端输入法传0
    android_demo_setting.gaminfo_apppointIME("1")

    saassdk_demo.init_sdk()
    saassdk_demo.play_sdk()
    time.sleep(15)
    android_common.check_play()
    android_common.d_back()

    demo.stop_demo("AndroidArm")

