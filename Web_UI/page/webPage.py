# -*- coding:utf-8 -*-
# @Time   : 19:30
# @Author : yanxin

#使用selenium版本4.1以上，语法变更较大
from selenium.webdriver import Keys

from common.driverBase import base
from selenium.webdriver.common.by import  By
from common.getplayaddress import ps
import time



class webPage():

    def test_playsdk(self,chocie):
        # 1-获取播流地址
        websdk_url = ps.create_url(chocie)
        # 2-调用驱动，播流
        base.open_url(websdk_url)
        # assert websdk_url, '拼接播流地址'
        # 3-等待20s，等待播流成功
        time.sleep(20)



    #1-播流校验
    def checkplay(self):
        return base.get_element_text(('id',"vibf"))

    #2-获取cid
    def getcid(self):
        return  base.click_element(('id','getcid'))

    #3-获取弹窗内容
    def get_alert(self):
        return base.get_alert_text()

    #4-关闭弹窗
    def quit_alert(self):
        return  base.quit_alert()

    #5-切换分辨率
    def switchresoulution(self):
        return base.click_element(('id', 'switchResolution'))

    #6-当前分辨率
    def get_resolution(self):
        return base.get_element_text((By.CSS_SELECTOR,'div.resolution_btns > a'))

    #7-1 网页全屏按钮
    def webFullscreen(self):
        return  base.click_element(('id','webPageFullScreenFn'))

    # 7-2 网页全屏title
    def get_webfull_title(self):
        return base.get_element((By.XPATH,"//*[@id='example']/div/div[5]/a[3]")).get_attribute("title")

    #8-1 全屏
    def fullscreen(self):
        return base.click_element(('id','fullScreenFn'))

    #8-2 全屏title
    def get_fullscreen_title(self):
        return base.get_element((By.XPATH,"//*[@id='example']/div/div[5]/a[2]")).get_attribute("title")

    #9-退出全屏
    def quit_fullscreen(self):
        return  base.click_element((By.CLASS_NAME,'fullscreen_btn'))

    #10-键鼠功能区开关
    def keyboard(self):
        return  base.click_element(('id',"Keyboard"))

    #11-是否支持直播
    def issupportLiving(self):
        return base.click_element(('id',"isSupportLiving"))

    #12-开启直播
    def startliving(self):
        return base.click_element(('id',"startLiving"))

    #12-停止直播
    def stopliving(self):
        return base.click_element(('id',"stopLiving"))

    #12-获取授权码
    def getpincode(self):
        return base.click_element(('id',"getpincode"))

    #13-上传图片
    def fileupload(self):
        return base.get_element(('id','file'))

    #14-获取图片列表
    def getfilelist(self):
        return base.click_element(('id', "getfilelist"))

    #15-结束游戏
    def gameover(self):
        return base.click_element(('id',"gameover"))


    #16-结束游戏
    def gameover1(self):
        overinfo1 = base.get_element_text((By.CSS_SELECTOR,'div.toastMessage > div.wordInfo'))
        return overinfo1

    #17-打开新标签页
    def open_newtab(self):
        return base.get_element((By.TAG_NAME,'body')).send_keys(Keys.COMMAND + 't')

    #18-toast提示
    def get_toast(self):
        return base.get_element_text(('id',"totalTime"))

    #18-1 获取多个toast提示：
    def get_toasts(self):
        return base.get_elements_text(('id',"totalTime"),1)

    #19-截图下载
    def download(self):
        return base.click_element(('id', "downLoadFile"))

    #20-弹窗输入
    def input(self,file):
        return base.alert_input(file)


    #21-调用更新uid与游戏时长接口
    def updateUID(self):
        return base.click_element(('id',"update1"))

    #21-键鼠操作
    def keyboard_guide(self):
        return base.get_element((By.XPATH, '//*[@id="example"]/div/div[3]/div[1]/div[1]/div[3]/input')).get_attribute("value")

    def keyboard_close(self):
        return base.get_element((By.XPATH, '//*[@id="example"]/div/div[3]/div[1]/div[1]/div[4]/input[1]')).get_attribute(
            "value")

    def keyboard_save(self):
        return base.get_element((By.XPATH, '//*[@id="example"]/div/div[3]/div[1]/div[1]/div[4]/input[2]')).get_attribute(
            "value")

    #21-获取游戏界面比例
    def getscale(self):
        fullscreen = base.get_element((By.CLASS_NAME,'KeyPositionBox'))
        size = fullscreen.size
        wight = size.get('width')
        hight = size.get('height')
        scale = wight/hight

        return scale

    #22-确认排队
    def enter_queue(self):
        return base.click_element((By.CLASS_NAME,'queue_confirm'))

    #23-启动flash
    def start_flash(self):
        print("打开网页设置页面")
        url = ps.create_url(8)
        base.open_url(url)
        time.sleep(5)

        #2-启动flash
        print("启动flash")
        a = base.get_elements((By.XPATH,'//*[@id="permission"]'))
        time.sleep(2)
        b = base.get_elements((By.XPATH,'//*[@id="allow"]'))
        time.sleep(2)


webpage = webPage()




