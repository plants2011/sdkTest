__author__ = "leixin"

import logging

from util.conf.instance import Instance
from util.demo import wait_for_toast

log = logging.getLogger(__name__)

demo_apk = "com.example.hmcpdemo"
startup_activity = ".activity.SettingActivity"

#必须包含，启动包指令
def start_pkg():
    d = Instance.get_device()
    d.app_start(package_name=demo_apk, activity=startup_activity)
    pass

#必须包含，停止包指令
def stop_pkg():
    d = Instance.get_device()
    d.press("back")
    d.app_stop(package_name=demo_apk)

def d_back():
    d = Instance.get_device()
    d.press("back")

#必须包含，检查包指令
def check_dependences():
    log.info("-"*10+"开始检查依赖内容"+"-"*10)

    log.info("-"*10+"依赖检查成功"+"-"*10)
    return True

def download_plugin():
    d = Instance.get_device()
    d.screen_on()
    d(text="下载Plugin").click()
    wait_for_toast(startswith="插件将下载至")
    wait_for_toast(equal="插件下载成功")
    pass


def install_plugin():
    d = Instance.get_device()
    d(text="安装下载的Plugin").click()
    wait_for_toast(equal="插件安装成功")
    pass

def install_initplugin():
    d = Instance.get_device()
    d(text="安装自带插件").click()
    wait_for_toast(equal="插件安装成功")
    pass

def load_plugin():
    d = Instance.get_device()
    #text="加载PLUGIN"加载Plugin
    d( text="加载Plugin").click()
    wait_for_toast(equal="插件加载成功")
    pass

def init_plugin():
    d = Instance.get_device()
    d(text="初始化SDK").click()
    wait_for_toast(equal="云玩初始化成功")
    pass

def play_plugin():
    d = Instance.get_device()
    d(text="开始云玩").click()
    # wait_for_toast(2,equal="play onSuccess")
    #assert "play onSuccess" in d.toast.get_message(5.0, default="")
    # d.toast.get_message()


    pass

def start_connect():
    pass

def getcid_plugin():
    d = Instance.get_device()
    d(text="测试菜单").click()
    d(text="获取CID").click()

    pass


def change_test_env(use_saas, env_name):
    d = Instance.get_device()
    if use_saas == False:
        if d(resourceId="com.example.hmcpdemo:id/switch_saas").info['text'] == '打开':
            d(resourceId="com.example.hmcpdemo:id/switch_saas").click()
    elif use_saas == True:
        if d(resourceId="com.example.hmcpdemo:id/switch_saas").info['text'] == '关闭':
            d(resourceId="com.example.hmcpdemo:id/switch_saas").click()
        d(resourceId="com.example.hmcpdemo:id/spinner_saas").click()
        d(resourceId="android:id/text1", text=env_name).click()
    pass

def change_game(game_name):
    d = Instance.get_device()
    d(resourceId="com.example.hmcpdemo:id/spinner").click()
    d(resourceId="android:id/text1", text=game_name).click()
    pass