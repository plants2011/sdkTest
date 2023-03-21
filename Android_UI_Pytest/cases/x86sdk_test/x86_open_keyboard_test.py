import  time

from util import demo
from util.common_steps import x86sdk_demo

def test_setup():
    demo.start_demo("Androidx86")

    #x86sdk_demo.start_pkg()
    x86sdk_demo.init_sdk()
    x86sdk_demo.play_sdk()

    time.sleep(120)
    x86sdk_demo.check_play()
    x86sdk_demo.open_menu()
    x86sdk_demo.open_keyboard()
    x86sdk_demo.quit_game()