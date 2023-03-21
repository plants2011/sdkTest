__author__ = "leixin"

import logging

from util.conf.instance import Instance
from util.demo import wait_for_toast

#log = logging.getLogger(__name__)

h5url="https://sdk-staging.haimawan.com/hmtools/sdk/cloudplay/1661424949684/1662462058409?env_name=rel&bid=1a305ed1dbc&appChannel=msg212&packageName=com.playcloud.msgsdkdemo&accessKey=a4e8b7bfb8014061771b9b2c83b6ab01&utoken=0&rotate=false&demoTest=false&priority=1&playingtime=70000&isArchive=false&isHideToolBar=false&isHotUpdate=false&channelId=100000&streamType=1&url=https%3A%2F%2Fsaas-rel.haimawan.com%2Fservice-core%2Frest%2Fapi"

browser_apk = "com.vivo.browser"
startup_activity = "com.haima.me.saas_sdk.activity.FlashActivity"

def start_browser(browsername=None):
    d = Instance.get_device()
    if (browsername == None):
        d.open_url(h5url)



#必须包含，启动包指令
def start_pkg():
    d = Instance.get_device()
    d.open_url(h5url)

    #d.app_start(package_name=browser_apk, activity=startup_activity)


#必须包含，停止包指令
def stop_pkg():
    d = Instance.get_device()
    d.press("back")
    d.app_stop(package_name=browser_apk)

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

def getcid():
    d = Instance.get_device()
    d(text="菜单").exists(timeout=10)
    d(text="菜单").click()
    #d.click(0.066, 0.148)
    d(text="获取cid").click()
    ttt=d(resourceId="com.android.browser:id/adc").get_text()
    d(resourceId="com.android.browser:id/a8k").click()
    #ttt=d(resourceId="com.vivo.browser:id/message").get_text()
    logging.info(ttt)
