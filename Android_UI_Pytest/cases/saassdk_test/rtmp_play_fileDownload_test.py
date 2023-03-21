import  logging
import time

from util import demo
from util.common_steps import saassdk_demo
from util.common_steps import android_common
from util.common_steps import android_demo_setting


def test_setup():
    demo.start_demo("AndroidArm")

    android_demo_setting.gameinfo_choosegame("光遇")
    android_demo_setting.gameinfo_choosebid("staging")
    android_demo_setting.gameinfo_setstremtype("rtmp")
    android_demo_setting.userinfo_setuserid("ceshi")

    saassdk_demo.init_sdk()
    saassdk_demo.play_sdk()
    time.sleep(20)
    android_common.check_play()
    time.sleep(1)
    # 点击游戏中接受
    android_common.touchclick(0.61, 0.823)
    time.sleep(25)
    # 点击游戏中的开始
    android_common.touchclick(0.67, 0.462)
    time.sleep(20)
    # 点击右下角，显示截图和录屏入口
    android_common.touchclick(0.908, 0.932)
    # android_common.touchclick(0.957, 0.941)
    time.sleep(1)
    # 开始录屏
    android_common.touchclick(0.898, 0.807)
    # android_common.touchclick(0.96, 0.823)
    time.sleep(10)
    # 结束录屏
    android_common.touchclick(0.868, 0.338)
    # android_common.touchclick(0.942, 0.332)
    time.sleep(5)

    android_common.test_file_download()
    time.sleep(5)

    android_common.d_back()
    demo.stop_demo("AndroidArm")

