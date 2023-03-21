__author__ = "leixin"

import logging

from util.conf.instance import Instance
from util.demo import wait_for_toast

#log = logging.getLogger(__name__)

demo_apk = "com.haima.me.saas_sdk.master"
startup_activity = "com.haima.me.saas_sdk.activity.FlashActivity"

#必须包含，启动包指令
def start_pkg():
    d = Instance.get_device()
    d.app_start(package_name=demo_apk, activity=startup_activity)


#必须包含，停止包指令
def stop_pkg():
    d = Instance.get_device()
    d.press("back")
    d.app_stop(package_name=demo_apk)

def d_back():
    d = Instance.get_device()
    d.press("back")



def init_sdk():
    d = Instance.get_device()
    d(text="初始化").click()
    wait_for_toast(equal="获取元数据成功")

def play_sdk():
    d = Instance.get_device()
    d(text="开始云玩").click()
    # wait_for_toast(2,equal="play onSuccess")
    #assert "play onSuccess" in d.toast.get_message(5.0, default="")
    # d.toast.get_message()


def start_connect():
    pass





def change_game(game_name):
    d = Instance.get_device()
    d(resourceId="com.example.hmcpdemo:id/spinner").click()
    d(resourceId="android:id/text1", text=game_name).click()
    pass


def check_play():
    d = Instance.get_device()
    if d(resourceId="com.haima.me.saas_sdk.master:id/showDelay"):
        text =d(resourceId="com.haima.me.saas_sdk.master:id/showDelay").get_text()
        if text =='Loading':
            assert 0
        elif text != None:
            assert 1
    else:
        assert 0

