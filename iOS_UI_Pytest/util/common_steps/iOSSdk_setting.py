__author__ = "leixin"

import  wda
import time
import logging
from util.common_steps import iOS_common

from util.conf.instance import Instance
from util.common_steps import  iOS_SaasSDK


#初始化
def ios_init(d):
    d.xpath('//Button/StaticText[1]').click()
    iOS_common.check_alertnetwork(d)
    d(label="初始化").click()

#初始化语言
def ios_initlanguage(d):
    d.xpath('//Button/StaticText[1]').click()
    d(label="初始化且切换成英文").click()

#开始游戏
def ios_play(d):
    d.xpath('//Button/StaticText[1]').click()
    iOS_common.check_alertnetwork(d)
    d(label="开始游戏").click()
    time.sleep(20)

#获取控制权
def ios_getauth(d):
    d.xpath('//Button/StaticText[1]').click()
    d(label="获取控制权").click()

#测速
def ios_testspeed(d):
    d.xpath('//Button/StaticText[1]').click()
    d(label="测速").click()

#IDC查询
def ios_checkidc(d):
    d.xpath('//Button/StaticText[1]').click()
    d(label="IDC查询").click()

#获取未释放实例
def ios_getsrviceinstance(d):
    d.xpath('//Button/StaticText[1]').click()
    d(label="获取未释放实例").click()

#释放驻留实例
def ios_releaseinstance(d):
    d.xpath('//Button/StaticText[1]').click()
    d(label="释放驻留实例").click()

#查询存档进度
def ios_checkarchivestatus(d):
    d.xpath('//Button/StaticText[1]').click()
    d(label="查询存档进度").click()

#查询是否有存档
def ios_getarchives(d):
    d.xpath('//Button/StaticText[1]').click()
    d(label="查询是否有存档").click()

#查询游戏配置
def ios_getgameinfo(d):
    d.xpath('//Button/StaticText[1]').click()
    d(label="查询游戏配置").click()




def switch_game(d,cgame,egame):
    d(label=cgame).click()
    time.sleep(0.5)
    d(label=egame).click()

#设置游戏
def setGame(d,gamename):
    d.click(0.901, 0.446)
    time.sleep(0.5)
    logging.info(gamename)
    d(label=gamename).click()


#设置流类型
def setStreamtype(d,type):
    if (type == "rtmp"):
        d.swipe_up()
        e = d.xpath('//Table/Cell[23]/TextField[1]')
        d.xpath('//Table/Cell[23]/TextField[1]').click()
        e.clear_text()
        e.set_text("0")
        d(name='Return').click()
    elif (type =="rtc"):
        d.swipe_up()
        e = d.xpath('//Table/Cell[23]/TextField[1]')
        d.xpath('//Table/Cell[23]/TextField[1]').click()
        e.clear_text()
        e.set_text("1")
        d(name='Return').click()

#设置userid
def setUserid(d,userid):
    e = d.xpath('//Table/Cell[3]/TextField[1]')
    d.xpath('//Table/Cell[3]/TextField[1]').click()
    e.clear_text()
    e.set_text(userid)
    d(name='Return').click()


#设置用户类型
def setUsertype(d,usertype):
    e = d.xpath('//Table/Cell[5]/TextField[1]')
    d.xpath('//Table/Cell[5]/TextField[1]').click()
    e.clear_text()
    e.set_text(usertype)
    d(name='Return').click()


#设置游云游戏加载方式 0 ｜1
def setGameldtype(d,ldtype):
    e = d.xpath('//Table/Cell[9]/TextField[1]')
    d.xpath('//Table/Cell[9]/TextField[1]').click()
    e.clear_text()
    e.set_text(ldtype)
    d(name='Return').click()


#设置游戏云玩时长
def setPlayingtime(d,ptime):
    e = d.xpath('//Table/Cell[10]/TextField[1]')
    d.xpath('//Table/Cell[10]/TextField[1]').click()
    e.clear_text()
    e.set_text(ptime)
    d(name='Return').click()


#设置横竖屏 0是横屏，1是竖屏
def setPortrait(d,portrait):
    e = d.xpath('//Table/Cell[11]/TextField[1]')
    d.xpath('//Table/Cell[11]/TextField[1]').click()
    e.clear_text()
    e.set_text(portrait)
    d(name='Return').click()


#设置Is Rotating(0|1)
def setIsRotating(d):
    e12 = d.xpath('//Table/Cell[12]/TextField[1]')
    d.xpath('//Table/Cell[12]/TextField[1]').click()
    e12.clear_text()
    e12.set_text("0")
    d(name='Return').click()



#设置设备信息
def setDeviceinfo(d,devinfo):
    e = d.xpath('//Table/Cell[13]/TextField[1]')
    d.xpath('//Table/Cell[13]/TextField[1]').click()
    e.clear_text()
    e.set_text(devinfo)
    d(name='Return').click()


#设置显示位置 0x30 或 30x0
def setShowPoint(d,showPoint):
    e = d.xpath('//Table/Cell[14]/TextField[1]')
    d.xpath('//Table/Cell[14]/TextField[1]').click()
    e.clear_text()
    e.set_text(showPoint)
    d(name='Return').click()


#设置显示位置 0x30 或 30x0
def setShowSize(d,showSize):
    e = d.xpath('//Table/Cell[15]/TextField[1]')
    d.xpath('//Table/Cell[15]/TextField[1]').click()
    e.clear_text()
    e.set_text(showSize)
    d(name='Return').click()


#设置ResetPlayerFrame 0或者1
def setResetPlayerFrame(d,plframe):
    e = d.xpath('//Table/Cell[16]/TextField[1]')
    d.xpath('//Table/Cell[16]/TextField[1]').click()
    e.clear_text()
    e.set_text(plframe)
    d(name='Return').click()


#缩小图后Y纵坐标 300
def setResetPlayerFrame(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[17]/TextField[1]')
    d.xpath('//Table/Cell[17]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#Archive(0|1)存档
def setArchive(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[18]/TextField[1]')
    d.xpath('//Table/Cell[18]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#设置bid
def setAccessKeyID(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[19]/TextField[1]')
    d.xpath('//Table/Cell[19]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#bid的AccessKey
def setAccessKey(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[20]/TextField[1]')
    d.xpath('//Table/Cell[20]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#设置控制权的主cid MasterCid
def setMasterCid(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[21]/TextField[1]')
    d.xpath('//Table/Cell[21]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#设置控制权的authcode
def setAuthCode(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[22]/TextField[1]')
    d.xpath('//Table/Cell[22]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#设置VideoRender Callback (0|1)
def setVideoRender(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[24]/TextField[1]')
    d.xpath('//Table/Cell[24]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#设置省份
def setProvince(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[25]/TextField[1]')
    d.xpath('//Table/Cell[25]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#设置控制权的authcode
def setCity(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[26]/TextField[1]')
    d.xpath('//Table/Cell[26]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(label="return" or "换行").click()


#设置Connection Ip Changed notify (0|1)
def setIPchangenotify(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[27]/TextField[1]')
    d.xpath('//Table/Cell[27]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#设置是否是测试Demo申请 0｜1
def setDemoapply(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[28]/TextField[1]')
    d.xpath('//Table/Cell[28]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#设置机房id
def setIntface(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[29]/TextField[1]')
    d.xpath('//Table/Cell[29]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#设置实例id
def setInstance(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[30]/TextField[1]')
    d.xpath('//Table/Cell[30]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()


#设置openCameraPermissionCheck 0｜1
def setOpenCameraPermissionCheck(d,y):
    d.swipe_up()
    e = d.xpath('//Table/Cell[31]/TextField[1]')
    d.xpath('//Table/Cell[31]/TextField[1]').click()
    e.clear_text()
    e.set_text(y)
    d(name='Return').click()






