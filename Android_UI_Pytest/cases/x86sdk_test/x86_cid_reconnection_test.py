import  time

from util import demo
from util.common_steps import x86sdk_demo

def test_setup():
    demo.start_demo("Androidx86")
    #
    # #x86sdk_demo.start_pkg()
    x86sdk_demo.set_userid("112233")
    x86sdk_demo.init_sdk()
    x86sdk_demo.play_sdk()

    time.sleep(30)
    x86sdk_demo.check_play()
    time.sleep(2)
    x86sdk_demo.getcontrollercode()
    time.sleep(5)
    x86sdk_demo.end_process()
    time.sleep(10)
    demo.start_demo("Androidx86")
    # x86sdk_demo.start_pkg()
    x86sdk_demo.set_userid("112233")
    x86sdk_demo.init_sdk()
    x86sdk_demo.play_sdk()

    time.sleep(20)
    x86sdk_demo.check_play()
    x86sdk_demo.getcontrollercode()
    x86sdk_demo.close_menu()
    x86sdk_demo.quit_game()
