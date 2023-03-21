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

    android_demo_setting.gameinfo_choosebid("haimarel")
    android_demo_setting.gameinfo_choosegame('OpenCamera')
    android_demo_setting.gameinfo_Portrait()
    #android_demo_setting.userinfo_setuserid("lei1234")


    saassdk_demo.init_sdk()
    saassdk_demo.play_sdk()
    time.sleep(20)
    android_common.check_play()
    time.sleep(1)
    android_common.touchclick(0.964, 0.295)
    android_common.touchclick(0.706, 0.963)
    time.sleep(20)
    #android_common.getscreenshot()
    #android_common.getclickmatchimg("util/common_steps/check_img/opencamera.jpg")
    #android_common.getmatchimg("opencamera.png")


    #android_common.touchclick(0.068, 0.06)
    #time.sleep(40)
    #android_common.check_play()

    android_common.d_back()

    demo.stop_demo("AndroidArm")

