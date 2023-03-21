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

    android_demo_setting.gameinfo_choosegame("光·遇")
    android_demo_setting.gameinfo_choosebid("netrel")
    android_demo_setting.userinfo_setuserid("lei1234")


    saassdk_demo.init_sdk()
    saassdk_demo.play_sdk()
    time.sleep(10)
    android_common.touchclick(0.622, 0.825)
    android_common.touchclick(0.582, 0.807)
    time.sleep(20)
    #android_common.check_play()
    android_common.touchclick(0.676, 0.473)
    android_common.touchclick(0.63, 0.469)
    time.sleep(30)
    android_common.touchclick(0.962, 0.946)
    time.sleep(0.5)
    android_common.touchclick(0.962, 0.946)
    time.sleep(0.5)
    android_common.touchclick(0.902, 0.914)
    time.sleep(0.5)
    android_common.touchclick(0.902, 0.914)
    time.sleep(2)
    android_common.test_img_download()


    #android_common.exit_game()
    demo.stop_demo("AndroidArm")



