import  time

from util import demo
from util.common_steps import x86sdk_demo
from util.common_steps import android_common
def test_setup():
    demo.start_demo("Androidx86")

    #x86sdk_demo.start_pkg()
    x86sdk_demo.init_sdk()
    x86sdk_demo.play_sdk()

    time.sleep(20)
    x86sdk_demo.check_play()
    android_common.disablewifi()
    android_common.check_Status(6)
    time.sleep(3)
    android_common.enablewifi()
    time.sleep(3)
    android_common.check_prompt_exists()
    time.sleep(2)
    android_common.check_play()
    x86sdk_demo.quit_game()