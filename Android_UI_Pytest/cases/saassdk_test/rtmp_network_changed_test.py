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

    android_demo_setting.userinfo_setuserid("lei1234")
    android_demo_setting.gameinfo_setstremtype("rtmp")

    saassdk_demo.init_sdk()
    saassdk_demo.play_sdk()
    time.sleep(15)
    android_common.check_play()
    android_common.disablewifi()
    android_common.check_Status(6)
    time.sleep(3)
    android_common.enablewifi()
    time.sleep(3)
    android_common.check_prompt_exists()
    android_common.check_play()
    android_common.d_back()

    demo.stop_demo("AndroidArm")

