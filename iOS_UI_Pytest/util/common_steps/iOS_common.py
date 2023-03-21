__author__ = "leixin"

import logging
import time


#切换清晰度，高清
def switch_resolution(d,expect):
    d.xpath('//Window[1]/Other[3]').click()
    time.sleep(0.5)
    d(label=expect).click()
    #d.xpath('//Window[1]/Other[3]/Button[2]/StaticText[1]').click()

#退出游戏
def exit_game(d):
    d.xpath('//Window[1]/Other[3]').click()
    time.sleep(0.5)
    #d.xpath('//Window[1]/Other[3]/Button[4]/StaticText[1]').click()
    if (d(label="关闭").exists):
        d(label="关闭").click()
    elif (d(label="退出").exists):
        d(label="退出").click()

#显示/隐藏日志
def op_displaylog(d):
    d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    #d.xpath('//Window[1]/Other[3]/Button[4]/StaticText[1]').click()
    d(label="显示/隐藏日志").click()

def op_getdisplaylog(d):
    #aa = d.xpath('//Window[1]/Other[2]/').value
    aa =d.xpath('//TextView').value
    logging.info(aa)



#旋转
def op_rotate(d):
    d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    #d.xpath('//Window[1]/Other[3]/Button[4]/StaticText[1]').click()
    d(label="旋转").click()


# 获取图片列表
def op_getimagelist(d):
    d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    # d.xpath('//Window[1]/Other[3]/Button[4]/StaticText[1]').click()
    d(label="获取图片列表").click()
    if (d(label="获取图片列表").exists):
        d(label="确定").click()
        if (d.xpath('//Window[1]/Other[2]/Table[1]/Cell[1]/Image[1]').exists):
            d.xpath('//Window[1]/Other[2]/Table[1]/Cell[1]/Image[1]').click()
            #开始下载
            d.xpath('//Window[1]/Other[2]/Button[1]/StaticText[1]').click()
            #取消下载
            #d.xpath('//Window[1]/Other[2]/Button[2]/StaticText[1]').click()
            #关闭下载页面
            #d.xpath('//Window[1]/Other[2]/Button[3]/StaticText[1]').click()
    else:
        pass






# 暂停游戏
def op_pausegame(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    # d.xpath('//Window[1]/Other[3]/Button[4]/StaticText[1]').click()
    d(label="暂停游戏").click()


# 恢复游戏
def op_resumegame(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    # d.xpath('//Window[1]/Other[3]/Button[4]/StaticText[1]').click()
    d(label="恢复游戏").tap()
    d(label="恢复游戏").click()


# 当前音频模式
def op_getaudiotype(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    # d.xpath('//Window[1]/Other[3]/Button[4]/StaticText[1]').click()
    d(label="当前音频模式").click()


# 发送Gps数据
def op_sendgpsinfo(d):
    d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    #d.swipe_up()
    #d.scroll('up')
    #d(label="发送Gps数据").click()
    d(label="发送Gps数据").get().tap()
    #d.xpath('//Window[1]/Other[2]/Other[1]/Table[1]/Cell[14]').click()
    d(label="发送Gps数据").click()
    time.sleep(1)
    if (d(label="Close").exists):
        #设置longitude
        e1 = d.xpath('//Window[1]/Other[3]/TextField[1]')
        d.xpath('//Window[1]/Other[3]/TextField[1]').click()
        e1.clear_text()
        e1.set_text("123.4")
        d(name='Return').click()

        # 设置latitude
        e2 = d.xpath('//Window[1]/Other[3]/TextField[2]')
        d.xpath('//Window[1]/Other[3]/TextField[2]').click()
        e2.clear_text()
        e2.set_text("125.4")
        d(name='Return').click()

        # 设置altitude
        e3 = d.xpath('//Window[1]/Other[3]/TextField[3]')
        d.xpath('//Window[1]/Other[3]/TextField[3]').click()
        e3.clear_text()
        e3.set_text("123")
        d(name='Return').click()

        # # 设置speed
        # e4 = d.xpath('//Window[1]/Other[3]/TextField[4]')
        # d.xpath('//Window[1]/Other[3]/TextField[4]').click()
        # e4.clear_text()
        # e4.set_text("45")
        # d(name='Return').click()
        #
        # # 设置course
        # e5 = d.xpath('//Window[1]/Other[3]/TextField[5]')
        # d.xpath('//Window[1]/Other[3]/TextField[5]').click()
        # e5.clear_text()
        # e5.set_text("12")
        # d(name='Return').click()

        d.xpath('//Window[1]/Other[3]/Button[2]').click()




# 发送Message
def op_sendmessageinfo(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    # d.xpath('//Window[1]/Other[3]/Button[4]/StaticText[1]').click()
    d(label="发送Message").tap()
    d(label="发送Message").click()


# 体感发送文本
def op_sendsomatosensorytext(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    # d.xpath('//Window[1]/Other[3]/Button[4]/StaticText[1]').click()
    d(label="体感发送文本").tap()
    d(label="体感发送文本").click()


# 体感发送二进制
def op_sendsomatosensorybinary(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    # d.xpath('//Window[1]/Other[3]/Button[4]/StaticText[1]').click()
    d(label="体感发送二进制").tap()
    d(label="体感发送二进制").click()


# 发送到剪贴板
def op_sendclipboard(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    # d.xpath('//Window[1]/Other[3]/Button[4]/StaticText[1]').click()
    d(label="发送到剪贴板").tap()
    d(label="发送到剪贴板").click()


# 发送文本
def op_sendtext(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    d(label="发送文本").tap()
    d(label="发送文本").click()


#获取授权码
def op_getauthcode(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    d(label="获取授权码").tap()
    d(label="获取授权码").click()

# 获取状态码
def op_getstatuscode(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    d(label="获取状态码").tap()
    d(label="获取状态码").click()

# 获取CID
def op_getcid(d,y=2):
    logging.info(y)
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif(y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    d(label="获取CID").tap()
    d(label="获取CID").click()

#启/停直播
def op_living(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
        #time.sleep(0.5)
        #d(label="启/停直播").tap()
        #d.xpath('//Window[1]/Other[2]/Other[1]/Table[1]/Cell[23]/Other[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
        #time.sleep(0.5)
        #d(label="启/停直播").tap()
        #d.xpath('//Window[1]/Other[3]/Other[1]/Table[1]/Cell[23]/Other[1]').click()
    time.sleep(0.5)
    d(label="启/停直播").tap()
    d(label="启/停直播").click()

def op_stopliving(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
        time.sleep(0.5)
        d(label="启/停直播").tap()
        d.xpath('//Window[1]/Other[2]/Other[1]/Table[1]/Cell[23]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
        time.sleep(0.5)
        #d(label="启/停直播").tap()
        d.click(0.164, 0.463)
        #d.xpath('//Window[1]/Other[3]/Other[1]/Table[1]/Cell[23]/StaticText[1]').click()
    # time.sleep(0.5)
    # d(label="启/停直播").tap()
    # d(label="启/停直播").click()

# 长连接心跳
def op_inputstatus(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    d(label="长连接心跳").tap()
    d(label="长连接心跳").click()

# 显示/隐藏键盘
def op_keyboard(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    d(label="显示/隐藏键盘").tap()
    d(label="显示/隐藏键盘").click()


#收起游戏内部本地键盘
def op_gamekeyboard(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    d(label="收起游戏内部本地键盘").tap()
    d(label="收起游戏内部本地键盘").click()


#弹出悬浮层
def op_pop(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    d(label="弹出悬浮层").tap()
    d(label="弹出悬浮层").click()

#更新UID
def op_updateuid(d,y=2):
    if (y == "2"):
        d.xpath('//Window[1]/Other[2]/Other[1]/Button[1]/StaticText[1]').click()
    elif (y == "1"):
        d.xpath('//Window[1]/Other[3]/Other[1]/Button[1]/StaticText[1]').click()
    time.sleep(0.5)
    d(label="更新UID").tap()
    d(label="更新UID").click()


def  check_rotate(d):
    if (d(label="未设置游戏中屏幕旋转").exists):
        d(label="好的").click()
        pass
    else:
        0
        #False

def  check_gameover(d):
    if (d(label="游戏结束，感谢使用").exists):
        logging.info("游戏结束，感谢使用")
        pass
    else:
        0

def  check_noinput(d):
    if (d(label="您已长时间无操作，请重新连接游戏").exists):
        logging.info("您已长时间无操作，请重新连接游戏")
        pass
    else:
        0



#You have switched to HD mode
#//Window[1]/Other[3]/Button[1]
#您已切换至 高清 模式

def  check_exitlanguage(d):
    d.xpath('//Window[1]/Other[3]').click()
    time.sleep(0.5)
    if (d(label="Select resolution").exists):
        #logging.info("您已长时间无操作，请重新连接游戏")
        pass
    else:
        0

def  check_alertnetwork(d):
    if (d.xpath('//Alert').exists):
        d(label="好").click()
    else:
        pass

def  check_cameraauth(d):
    time.sleep(0.1)
    d.click(0.164, 0.463)
    if (d.xpath('//Alert').exists):
        logging.info("有提示")
        d(label="好").click()
    elif (d.xpath('//Alert').exists):
        d(label="好的").click()
    else:
        pass

def check_queuestatus(d):
    time.sleep(0.1)
    if(d(label="排队").exists):
        logging.info("出现排队，执行失败")
        0
    else:
        pass





#获取图片列表
#确定
#//Window[1]/Other[2]/Table[1]/Cell[2]/Image[1]


