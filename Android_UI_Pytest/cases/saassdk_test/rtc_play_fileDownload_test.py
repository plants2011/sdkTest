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

    android_demo_setting.gameinfo_choosegame("光遇")
    android_demo_setting.gameinfo_choosebid("staging")
    android_demo_setting.userinfo_setuserid("lei1234")


    saassdk_demo.init_sdk()
    saassdk_demo.play_sdk()
    time.sleep(20)
    android_common.check_play()
    # 点击游戏中的开始
    android_common.touchclick(0.666, 0.476)
    time.sleep(20)
    # 点击右下角，显示截图和录屏入口
    android_common.touchclick(0.957, 0.941)
    time.sleep(5)
    # 开始录屏
    android_common.touchclick(0.963, 0.834)
    time.sleep(30)
    # 结束录屏
    android_common.touchclick(0.942, 0.332)
    time.sleep(5)
    android_common.test_file_download()
    time.sleep(40)

    android_common.d_back()
    demo.stop_demo("AndroidArm")


