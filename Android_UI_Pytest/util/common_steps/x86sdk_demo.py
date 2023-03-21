import logging

from util.conf.instance import Instance
from util.demo import wait_for_toast

#log = logging.getLogger(__name__)

demo_apk = "com.haima.me.saas_sdk.master"
startup_activity = "com.haima.me.saas_sdk.FlashActivity"

#必须包含，启动包指令
def start_pkg():
    d = Instance.get_device()
    d.app_start(package_name=demo_apk, activity=startup_activity)



##必须包含，停止包指令
def stop_pkg():
    d = Instance.get_device()
    d.press("back")
    d.app_stop(package_name=demo_apk)

def d_back():
    d = Instance.get_device()
    d.press("back")


def init_sdk():
    d = Instance.get_device()
    #d(text="初始化").click()
    d(scrollable=True).scroll.to(text="初始化")
    d(text="初始化").click()
    wait_for_toast(equal="获取元数据成功")

def play_sdk():
    d = Instance.get_device()
    d(scrollable=True).scroll.to(text="开始云玩")
    d(text="开始云玩").click()



def start_connect():
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


# def change_game(game_name):
#     d = Instance.get_device()
#     d(resourceId="com.example.hmcpdemo:id/spinner").click()
#     d(resourceId="android:id/text1", text=game_name).click()
#     pass


def check_play():
    d = Instance.get_device()
    if d(resourceId="com.haima.me.saas_sdk.master:id/showDelay"):
        text =d(resourceId="com.haima.me.saas_sdk.master:id/showDelay").get_text()
        if text =='Loading':
            assert 0
        elif text != None:
            assert 1

# def exit_game():
#     d = Instance.get_device()
#     d.xpath('//*[@resource-id="com.haima.me.saas_sdk.master:id/parent_view"]/android.widget.FrameLayout[1]/android.widget.ImageView[1]').click()d/btnExit").click()
#     d(resourceId="com.haima.me.saas_sdk.master:id/btnExit").click()

#退出游戏
def quit_game():
    d = Instance.get_device()
    d.xpath(
        '//*[@resource-id="com.haima.me.saas_sdk.master:id/parent_view"]/android.widget.FrameLayout[1]/android.widget.ImageView[1]').click()
    d(resourceId="com.haima.me.saas_sdk.master:id/btnExit").click()

#wifi开关，断网
def disablewifi():
    d = Instance.get_device()
    d.session("com.android.settings")

    if(d(text="Wi-Fi").exists(timeout=0.2)):
        d(text="Wi-Fi").click()
        if (d(className="android.widget.Switch").get_text() == "开" or d(
                className="android.widget.Switch").get_text() == "open"):
            d(className="android.widget.Switch").click()
            d.app_stop("com.android.settings")
    elif (d(text="WLAN").exists(timeout=0.2)):
        d(text="WLAN").click()
        if (d(className="android.widget.Switch").get_text() == "开" or d(
                className="android.widget.Switch").get_text() == "open"):
            d(className="android.widget.Switch").click()
            d.app_stop("com.android.settings")
    elif (d(text="无线网络").exists(timeout=0.2)):
        d(text="无线网络").click()
        if (d(resourceId="com.meizu.wifiadmin:id/sw_right").exists(timeout=0.2)):
            if ( d(resourceId="com.meizu.wifiadmin:id/sw_right").get_text() == "开启"):
                d(resourceId="com.meizu.wifiadmin:id/sw_right").click()
                d.app_stop("com.android.settings")
        elif (d(className="android.widget.Switch").exists(timeout=0.2)):
            if (d(className="android.widget.Switch").get_text() == "开" or d(
                    className="android.widget.Switch").get_text() == "open"):
                d(className="android.widget.Switch").click()
                d.app_stop("com.android.settings")

#配置无操作超时时间
def gaminfo_noinputtime(inputtime):
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/etNoInputTime").click()
    d.send_keys(inputtime, clear=True)

#获取pincode
def getcontrollercode():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    # d.swipe_ext("up", scale=0.9)
    d.swipe(0.7, 0.75, 0.7, 0.3)
    d.swipe(0.7, 0.75, 0.7, 0.3)
    d.swipe(0.7, 0.75, 0.7, 0.3)
    d.swipe(0.7, 0.75, 0.7, 0.3)
    d.swipe(0.7, 0.75, 0.7, 0.3)
    d.swipe(0.7, 0.75, 0.7, 0.3)
    d(text="直播+控制权转移").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/show_living_contron_panel").click()
    # d.swipe_ext("up", scale=0.5)
    d.swipe(0.7, 0.75, 0.7, 0.32)
    d(text="获取授权码").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/pin_code_part").click()
    # d.swipe_ext("up", scale=0.5)
    controllercode = d(resourceId="com.haima.me.saas_sdk.master:id/res_pin_code").get_text()
    logging.info(controllercode)
    #需要继续调用sdk获取控制权

#打开菜单
def open_menu():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()

#关闭菜单
def close_menu():
    d = Instance.get_device()
    d.xpath('//*[@resource-id="com.haima.me.saas_sdk.master:id/panel_close"]/android.widget.ImageView[1]').click()

#打开全键盘
def open_keyboard():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/send_content").click()
    if (d(resourceId="com.haima.me.saas_sdk.master:id/fullKeyboardLayout").exists(timeout=0.2)):
        d(text="u").click()
        d(text="1").click()
        d(text="s").click()
        d(text="1").click()
    d.click(0.762, 0.121)

#检查是否显示鼠标
# def check_mouse():
#     d = Instance.get_device()
#     d.touch.move(15, 15)
#     if (d(className="android.widget.ImageView ").exists(timeout=0.2)):
#         logging.info("有鼠标")

#设置userid
def set_userid(userid):
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/etUserID").click()
    d.send_keys(userid)

#结束进程
def end_process():
    d = Instance.get_device()
    d.press("recent")
    d(resourceId="com.android.systemui:id/recent_igmbutton_clear_all").click()


