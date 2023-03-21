__author__ = "leixin"

#Android Demo中用户信息/环境信息/游戏信息设置


from util.conf.instance import Instance
import logging
import time
from util.demo import wait_for_toast

#用户信息设置
#设置userid
def userinfo_setuserid(userid):
    d = Instance.get_device()
    d(text="用户信息设置").click()
    if (d(resourceId="com.haima.me.saas_sdk.master:id/et_user_id").exists(timeout=0.2)):
        d(resourceId="com.haima.me.saas_sdk.master:id/et_user_id").click()
        d.send_keys(userid, clear=True)



#游戏信息设置
#选择游戏
def gameinfo_choosegame(gamename):
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/spinner_game_choose").click()
    time.sleep(0.5)
    d(resourceId="android:id/text1", text=gamename).click()

#选择BID
def gameinfo_choosebid(bidname):
    d = Instance.get_device()
    d(resourceId="com.haima.me.saas_sdk.master:id/saas_auths_spinner").click()
    d(resourceId="android:id/text1", text=bidname).click()

def gameinfo_Portrait():
    d = Instance.get_device()
    d(text="游戏设置").click()
    d(resourceId="com.haima.me.saas_sdk.master:id/rbtnPortrait").click()


#设置播流类型
def gameinfo_setstremtype(stremtype):
    d = Instance.get_device()
    d(text="游戏设置").click()
    d.swipe_ext("up", scale=0.8)
    d.swipe_ext("up", scale=0.8)
    if stremtype == 'rtc':
        d(resourceId="com.haima.me.saas_sdk.master:id/rbtnRtc").exists(timeout=0.2)
        d(resourceId="com.haima.me.saas_sdk.master:id/rbtnRtc").click()
    elif stremtype == 'rtmp':
        d(resourceId="com.haima.me.saas_sdk.master:id/rbtnRtmp").exists(timeout=0.2)
        d(resourceId="com.haima.me.saas_sdk.master:id/rbtnRtmp").click()


def gaminfo_playtime(playtime):
    d = Instance.get_device()
    d(text="游戏设置").click()
    d(resourceId="com.haima.me.saas_sdk.master:id/et_time").click()
    d.send_keys(playtime, clear=True)

def gaminfo_noinputtime(inputtime):
    d = Instance.get_device()
    d(text="游戏设置").click()
    d(resourceId="com.haima.me.saas_sdk.master:id/et_no_input_time").click()
    d.send_keys(inputtime, clear=True)

#app指定输入法
def gaminfo_apppointIME(IME):
    d = Instance.get_device()
    d(text="游戏设置").click()
    d.swipe_ext("up", scale=1)
    time.sleep(0.5)
    d(resourceId="com.haima.me.saas_sdk.master:id/client_ime_type_enable").click()
    d.swipe_ext("up", scale=1)
    time.sleep(0.5)
    d(resourceId="com.haima.me.saas_sdk.master:id/et_ime_type").click()
    d.send_keys(IME, clear=True)


#环境信息设置
#设置接入方式为xml或appcontext
def env_videoview(type):
    d = Instance.get_device()
    d(text="环境设置").click()
    d.swipe_ext("up", scale=0.8)
    d.swipe_ext("up", scale=0.8)
    logging.info(type)
    if type == "xml":
        logging.info("xml")
        d(resourceId="com.haima.me.saas_sdk.master:id/rbtn_view_activity_xml").click()

    elif type == "AppContext" :
        logging.info("AppContext")
        d(resourceId="com.haima.me.saas_sdk.master:id/rbtn_view_activity_xml").click()
