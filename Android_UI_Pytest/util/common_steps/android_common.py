__author__ = "leixin"

import os

from util.conf.instance import Instance
import logging
import time
from util.demo import wait_for_toast




def d_back():
    d = Instance.get_device()
    d.press("back")
    d.press("back")

#切换分辨率
def switch_resolution():
    d = Instance.get_device()
    d.xpath(
        '//*[@resource-id="com.haima.me.saas_sdk.master:id/parent_view"]/android.widget.FrameLayout[1]/android.widget.ImageView[1]').click()
    #d.xpath('//android.widget.ImageView').click()
    d(resourceId="com.haima.me.saas_sdk.master:id/rbtSecond").click()
    if (d(resourceId="com.haima.me.saas_sdk.master:id/tvTitle").exists(timeout=0.2)):
        logging.info("点击第二个清晰度")
        d(resourceId="com.haima.me.saas_sdk.master:id/rbtFirst").click()
    resmsg=d(resourceId="com.haima.me.saas_sdk.master:id/tvPrompt").get_text()
    logging.info(resmsg)
    #wait_for_toast(greaterequal="您已切换至")
    assert(str(resmsg) >= "您已切换至" or str(resmsg) !="正在为您切换清晰度，请稍候")

#点击按钮退出游戏
def exit_game():
    d = Instance.get_device()
    d.xpath('//android.widget.ImageView').click()
    d(resourceId="com.haima.me.saas_sdk.master:id/btnExit").click()


#检查是否播流，根据现显示显示信息判断
def check_play():
    d = Instance.get_device()
    if d(resourceId="com.haima.me.saas_sdk.master:id/showDelay"):
        text =d(resourceId="com.haima.me.saas_sdk.master:id/showDelay").get_text()
        if text =='Loading':
            logging.info(text)
            assert 0
        elif text != None:
            assert 1
    else:
        assert 0

#检查状态码
def check_Status(statuscode):
    d = Instance.get_device()
    statuscodestr = "Status "+str(statuscode)
    if (d(resourceId="com.haima.me.saas_sdk.master:id/tvPrompt").exists()):
        statustext=d(resourceId="com.haima.me.saas_sdk.master:id/tvPrompt").get_text()
        assert(str(statustext) >= statuscodestr)

def check_StatusTips(statustips):
    statustip = ""
    d = Instance.get_device()
    if statustips =="overtime":
        statustip="游戏结束，感谢使用"
    elif statustips == "noinput":
        statustip = "{\"errorCode\":\"\",\"errorCodeWithoutCid\":\"\",\"status\":11}"
    logging.info(statustip)
    if (d(resourceId="com.haima.me.saas_sdk.master:id/tvPrompt").exists(timeout=40)):
        statustext=d(resourceId="com.haima.me.saas_sdk.master:id/tvPrompt").get_text()
        logging.info(statustext)
        assert(str(statustext) >= statustip)

def check_prompt_exists():
    d = Instance.get_device()
    if (d(resourceId="com.haima.me.saas_sdk.master:id/tvPrompt").wait_gone(timeout=4)):
        assert 1




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




#开启无线网络
def enablewifi():
    d = Instance.get_device()
    d.session("com.android.settings")
    if(d(text="Wi-Fi").exists(timeout=0.2)):
        d(text="Wi-Fi").click()
        if (d(className="android.widget.Switch").get_text() == "关"):
            d(className="android.widget.Switch").click()
        d.app_stop("com.android.settings")
    elif (d(text="WLAN").exists(timeout=0.2)):
        d(text="WLAN").click()
        if (d(className="android.widget.Switch").get_text() == "关" or d(
                className="android.widget.Switch").get_text() == "close"):
            d(className="android.widget.Switch").click()
        d.app_stop("com.android.settings")
    elif (d(text="无线网络").exists(timeout=0.2)):
        d(text="无线网络").click()
        if (d(resourceId="com.meizu.wifiadmin:id/sw_right").exists(timeout=0.2)):
            if ( d(resourceId="com.meizu.wifiadmin:id/sw_right").get_text() == "关闭"):
                d(resourceId="com.meizu.wifiadmin:id/sw_right").click()
        elif (d(className="android.widget.Switch").exists(timeout=0.2)):
            if (d(className="android.widget.Switch").get_text() == "关" or d(
                    className="android.widget.Switch").get_text() == "close"):
                d(className="android.widget.Switch").click()
        d.app_stop("com.android.settings")

    #d(text="Wi-Fi").right(className="android.widget.Switch").click()

def disablewWLAN():
    d = Instance.get_device()
    os.system('adb shell svc wifi disable')

def enalewWLAN():
    d = Instance.get_device()
    os.system('adb shell svc wifi enable')



#切换应用程序到后台，并设置切换时间
def changed_backgroud(bkgtime):
    d = Instance.get_device()
    #d.app_start("com.android.contacts")
    d.app_start("com.android.settings")
    time.sleep(bkgtime)
    d.app_stop("com.android.settings")

#点击触屏
def touchclick(x,y):
    d = Instance.get_device()
    #logging.info(x)
    #logging.info(y)
    d.click(x,y)

#获取截图
def getscreenshot(screenshotname):
    d = Instance.get_device()
    d.screenshot(screenshotname)

# 匹配待查找的图片，立刻返回一个结果
def getmatchimg(imdata):
    d = Instance.get_device()
    logging.info(imdata)
    #d.image.match(imdata)
    d.image.match("opencamera.png")


#20s内匹配截图
def getclickmatchimg(imdata):
    d = Instance.get_device()
    d.image.click(imdata, timeout=20.0)

#发送消息
def sendmessage(message):
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="发送消息").exists(timeout=0.2)
    d(text="发送消息").click()
    d(resourceId="com.haima.me.saas_sdk.master:id/l_send_msg_et_msg_content").click()
    d.send_keys(message, clear=True)
    d(resourceId="com.haima.me.saas_sdk.master:id/send_msg_link").click()
    wait_for_toast(equal="receive send message result:true")

#发送GPS消息
def sendGPS():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="发送消息").exists(timeout=0.2)
    d.swipe_ext("up", scale=0.9)
    d.swipe_ext("up", scale=0.9)
    d(text="GPS/剪贴板").exists(timeout=0.2)
    d(text="GPS/剪贴板").click()
    d.swipe_ext("up", scale=0.9)
    d(scrollable=True).scroll.to(text="发送GPS数据")
    d(text="发送GPS数据").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/tv_gps_submit").click()
    #wait_for_toast(equal="send wsMessage success")
    wait_for_toast(equal="true")


#更新token
def updatetoken():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="发送消息").exists(timeout=0.2)
    d.swipe_ext("up", scale=0.9)
    d(text="刷新Token").exists(timeout=0.2)
    d(text="刷新Token").click()

#更新云玩
def updateplaytime(updatetime):
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="发送消息").exists(timeout=0.2)
    d.swipe_ext("up", scale=0.9)
    d(text="更新游戏时长").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/update_play_time").click()
    #d(resourceId="com.haima.me.saas_sdk.master:id/et_uid").click()
    #d.send_keys("cpd203505461", clear=True)
    d(resourceId="com.haima.me.saas_sdk.master:id/et_play_time").click()
    d.send_keys(updatetime, clear=True)
    d(resourceId="com.haima.me.saas_sdk.master:id/tv_ok").click()
    wait_for_toast(equal="更新成功",timeout=2)


def getplaystatuscode():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="发送消息").exists(timeout=0.2)
    d.swipe_ext("up", scale=0.1)
    d(text="获取状态码").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/getStatusCode").click()
    # codemsg = d(resourceId="com.haima.me.saas_sdk.master:id/tvPrompt").get_text()
    # logging.info(codemsg)
    # assert (str(codemsg) >= "getCloudPlayStatusCode:" )
    #codemsg = wait_for_toast(timeout=2).get_text()
    #logging.info(codemsg)

    #wait_for_toast(equal ="getCloudPlayStatusCode", timeout=2)

def startliving():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    #d(text="发送消息").exists(timeout=0.2)
    #d.swipe_ext("up", scale=0.9)
    d(text="直播+控制权转移").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/show_living_contron_panel").click()
    d(resourceId="com.haima.me.saas_sdk.master:id/et_living_url").click()
    #d.send_keys("rtmp://publish2-ws.migufun.com/live/", clear=True)
    d(resourceId="com.haima.me.saas_sdk.master:id/et_living_id").click()
    #d.send_keys("2611053270", clear=True)
    d(resourceId="com.haima.me.saas_sdk.master:id/start_living").click()
    d(resourceId="com.haima.me.saas_sdk.master:id/res_start_living").exists(timeout=0.2)
    livingstatus = d(resourceId="com.haima.me.saas_sdk.master:id/res_start_living").get_text()
    logging.info(livingstatus)


    #需要增加直播的判断






def getsupportlivingstatus(expectstatus):
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    # d(text="发送消息").exists(timeout=0.2)
    # d.swipe_ext("up", scale=0.9)
    d(text="直播+控制权转移").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/show_living_contron_panel").click()
    d(scrollable=True).scroll.to(text="是否支持直播")
    d(text="是否支持直播").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/supprot_living").click()
    d.swipe_ext("up", scale=0.5)
    livingstatus = d(resourceId="com.haima.me.saas_sdk.master:id/supprot_living_result").get_text()
    #livingstatus:UNSUPPORTED or SUPPORTED
    assert (str(livingstatus) == expectstatus )


def getcontrollercode():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    #d(text="发送消息").exists(timeout=0.2)
    #d.swipe_ext("up", scale=0.9)
    d(text="直播+控制权转移").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/show_living_contron_panel").click()
    d.swipe_ext("up", scale=0.5)
    d(text="获取授权码").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/pin_code_part").click()
    d.swipe_ext("up", scale=0.5)
    controllercode = d(resourceId="com.haima.me.saas_sdk.master:id/res_pin_code").get_text()
    logging.info(controllercode)
    #需要继续调用sdk获取控制权




def sendinputmessage(inputmessage):
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="发送消息").exists(timeout=0.2)
    d.swipe_ext("up", scale=0.9)
    d.swipe_ext("up", scale=0.9)
    d(text="GPS/剪贴板").exists(timeout=0.2)
    d(text="GPS/剪贴板").click()
    d.swipe_ext("up", scale=0.9)
    d.swipe_ext("up", scale=0.9)
    d(resourceId="com.haima.me.saas_sdk.master:id/et_clipboard").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/et_clipboard").click()
    d.send_keys(inputmessage, clear=True)
    d(resourceId="com.haima.me.saas_sdk.master:id/tv_input_submit").click()


def sendclipboard():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="发送消息").exists(timeout=0.2)
    d.swipe_ext("up", scale=0.9)
    d.swipe_ext("up", scale=0.9)
    d(text="GPS/剪贴板").exists(timeout=0.2)
    d(text="GPS/剪贴板").click()
    d.swipe_ext("up", scale=0.99)
    d(scrollable=True).scroll.to(text="发送粘贴板数据")
    d(text="发送粘贴板数据").exists(timeout=0.2)
    #d(resourceId="com.haima.me.saas_sdk.master:id/tv_clipboard_submit").exists(timeout=0.2)
    d(resourceId="com.haima.me.saas_sdk.master:id/tv_clipboard_submit").click()
    #wait_for_toast(equal="true",timeout=2)
    wait_for_toast(equal="send wsMessage success",timeout=2)

def switchstreamertype():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="发送消息").exists(timeout=0.2)
    d.swipe_ext("up", scale=0.8)
    d.swipe_ext("up", scale=0.8)
    d(text="切换流").exists(timeout=0.2)
    d(text="切换流").click()
    time.sleep(1)
    #d(resourceId="com.haima.me.saas_sdk.master:id/test_menu_panel_close").click()

def switchdelay(action):
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(resourceId="com.haima.me.saas_sdk.master:id/delay_swich").exists(timeout=0.2)
    delaystatus = d(resourceId="com.haima.me.saas_sdk.master:id/delay_swich").get_text()
    if (action == "close"):
        if(delaystatus =="隐藏延迟数据"):
            d(resourceId="com.haima.me.saas_sdk.master:id/delay_swich").click()
    elif (action == "open"):
        if (delaystatus == "显示延迟数据"):
            d(resourceId="com.haima.me.saas_sdk.master:id/delay_swich").click()
    #
    # d(resourceId="com.haima.me.saas_sdk.master:id/delay_swich").exists(timeout=0.2)
    # d(resourceId="com.haima.me.saas_sdk.master:id/delay_swich").click()


def test_img_download():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="发送消息").exists(timeout=0.2)
    d.swipe_ext("up", scale=0.9)
    d.swipe_ext("up", scale=0.9)
    d(resourceId="com.haima.me.saas_sdk.master:id/upload_download_switch").click()
    d.swipe_ext("up", scale=0.8)
    d(resourceId="com.haima.me.saas_sdk.master:id/requestImageList").click()
    d.swipe_ext("up", scale=1)
    d(resourceId="android:id/text1").exists(timeout=0.5)
    d(resourceId="android:id/text1").click()
    d(resourceId="com.haima.me.saas_sdk.master:id/start_download").click()
    wait_for_toast(equal="下载完成", timeout=60)

def test_img_upload():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="发送消息").exists(timeout=0.2)
    d.swipe_ext("up", scale=0.99)
    d.swipe_ext("up", scale=0.99)
    d(scrollable=True).scroll.to(resourceId="com.haima.me.saas_sdk.master:id/upload_download_switch")
    #d(scrollable=True).scroll.to(text="文件上传、下载 OFF")
    d(resourceId="com.haima.me.saas_sdk.master:id/upload_download_switch").click()
    #d(scrollable=True).scroll.to(resourceId="com.haima.me.saas_sdk.master:id/upload_download_switch")
    time.sleep(1)
    d.swipe_ext("up", scale=1)
    d(scrollable=True).scroll.to(text="打开文件浏览")
    d(resourceId="com.haima.me.saas_sdk.master:id/to_file_browser").click()
    d(scrollable=True).scroll.to(text="DCIM")
    d(resourceId="com.haima.me.saas_sdk.master:id/tv", text="DCIM").click()
    d(resourceId="com.haima.me.saas_sdk.master:id/tv", text="Camera").click()
    d.xpath(
        '//*[@resource-id="com.haima.me.saas_sdk.master:id/recycler_view"]/android.widget.RelativeLayout[1]/android.widget.CheckBox[1]').click()
    d(resourceId="com.haima.me.saas_sdk.master:id/select_confirm").click()
    time.sleep(1)
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(resourceId="com.haima.me.saas_sdk.master:id/start_upload").click()
    wait_for_toast(equal="上传完成",timeout=60)

#切换输入法
def switchime():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="切换键盘").exists(timeout=0.5)
    d(text="切换键盘").click()

#截图
def shot():
    d = Instance.get_device()
    # 点击游戏中接受
    d.click(0.61, 0.823)
    time.sleep(25)
    # 点击游戏中的开始
    d.click(0.67, 0.462)
    time.sleep(20)
    # 点击右下角，显示截图和录屏入口
    d.click(0.908, 0.932)
    time.sleep(1)
    # 点击截图
    d.click(0.962, 0.934)
    time.sleep(2)

#大文件下载
def test_file_download():
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/show_test_menu_btn").click()
    d(text="视频下载").exists(timeout=3)
    d(text="网易下载").exists(timeout=3)
    #d(text="查询实例文件").exists(timeout=3)
    d(resourceId="com.haima.me.saas_sdk.master:id/tv_query_downloading_list").click()
    time.sleep(2)
    d(resourceId="com.haima.me.saas_sdk.master:id/tv_file_op").click()




