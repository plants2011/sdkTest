import time
from util import demo
from util.common_steps import saassdk_demo
from util.common_steps import android_common
from util.common_steps import android_demo_setting

def test_setup():
    demo.start_demo("AndroidArm")
    android_demo_setting.userinfo_setuserid("xiaomao")
    android_demo_setting.gameinfo_setstremtype("rtmp")

    saassdk_demo.init_sdk()
    saassdk_demo.play_sdk()
    time.sleep(15)
    android_common.check_play()
    time.sleep(1)
    android_common.switchime()
    time.sleep(2)
    android_common.d_back()

    demo.stop_demo("AndroidArm")


