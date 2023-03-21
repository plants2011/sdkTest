import  time

from util import demo
from util.common_steps import x86sdk_demo
from util.common_steps import android_common
def test_setup():
    demo.start_demo("Androidx86")

    # x86sdk_demo.start_pkg()
    x86sdk_demo.gaminfo_noinputtime("30")

    x86sdk_demo.init_sdk()
    x86sdk_demo.play_sdk()

    time.sleep(20)
    x86sdk_demo.check_play()
    time.sleep(30)
    android_common.check_StatusTips("noinput")
    x86sdk_demo.d_back()