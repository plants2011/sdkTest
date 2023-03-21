# -*- coding:utf-8 -*-
# @Time   : 2021.11.29 18:57
# @Author : yanxin


import os
import time
import pytest
import allure

from common.driverBase import base
from page.webPage import webpage

from selenium.webdriver.common.action_chains import ActionChains
from selenium.common.exceptions import  NoAlertPresentException
from pywinauto.keyboard import send_keys


class TestUpAndDown:

    @pytest.mark.upload
    @allure.feature('上传功能')
    @allure.title('case16：图片上传功能测试')
    def test_upload(self):

        print('\ncase16：图片上传功能测试:')
        filepath = r"D:\pictures\2.jpg"

        # 1-播流光遇
        time.sleep(5)
        webpage.test_playsdk(2)


        try:
            # 2- 元素定位
            fileupload = webpage.fileupload()
            # print(type(fileupload))
            time.sleep(5)
            # 直接click报错，解决方法：将click操作修改为鼠标事件即可
            # fileupload.click()

            # 3- 调用ActionChains方法，生成一个动作
            action = ActionChains(base.only_driver())
            time.sleep(5)

            # 4- 给动作添加方法
            action.click(fileupload)
            time.sleep(5)

            # 5- 调用perform()方法执行链中的所有动作
            action.perform()

            # 6-在元素位置松开鼠标左键
            action.release()

            # 一定要等待系统弹窗就绪,不然就会报错，所以设置等待时长长一点!!!!!
            print('等待系统弹窗打开！')
            time.sleep(10)

            # 7- 选择图片
            send_keys(filepath)
            # 一定要等待足够长时间,等待元素正常处理，不然就会报错，所以设置等待时长长一点
            time.sleep(10)

            # 8- 回车确定
            send_keys("{VK_RETURN}")
            # 一定要等待足够时长
            time.sleep(5)

            # 9-校验上传结果
            uploadresult = webpage.get_alert()
            # print(uploadresult)

            # 10- 关闭alert弹窗
            time.sleep(2)
            webpage.quit_alert()

            # 11-断言
            container = "2.jpg"
            assert container in uploadresult, '上传失败！'

            if uploadresult.startswith('上传成功'):
                print(uploadresult)
            else:
                print(uploadresult)

        except:
            NoAlertPresentException, NoAlertPresentException



    @pytest.mark.download
    @allure.feature('下载功能')
    @allure.title('case17：获取图片列表并下载单张图片')
    # case:获取图片列表
    def test_getFileAndDownLoad(self):
        print('\ncase17：获取图片列表功能测试')

        #此功能需要 光遇游戏登录成功后，进入游戏界面才可以测试,因此需要等待完成登录操作
        time.sleep(90)

        print('开始获取图片列表：')

        #1- 点击获取图片列表按钮
        webpage.getfilelist()
        time.sleep(2)

        #2- 获取图片列表
        filelist = webpage.get_alert()

        #3- 断言
        assert  filelist, '未截图成功，或调用接口失败。'

        #4- 关闭alert弹窗
        webpage.quit_alert()
        time.sleep(2)

        print('获取图片列表：' + filelist)

        print('\n开始下载图片：')

        #1- 点击下载按钮
        webpage.download()

        #2-获取下载文件名称
        if ',' in filelist:
            filename = filelist.split(',')[0]
        elif filelist:
            filename = filelist
        else:
            print('未获取截图名称！')


        #3-弹窗内输入截图文件名
        webpage.input(filename)
        time.sleep(2)

        #4-关闭弹窗
        webpage.quit_alert()

        #5-获取弹窗内容，检查上传结果
        time.sleep(5)
        download_result = webpage.get_alert()

        print('截图下载结果：' + download_result )

        #6-结果判断与断言
        if download_result == 'true':
            print("截图下载成功!")
        else:
            print('截图下载失败！')

        assert  download_result == 'true', '截图下载失败，或调用接口失败。'

        #7-关闭弹窗
        webpage.quit_alert()
        time.sleep(5)


        #8-结束游戏
        webpage.gameover()
        webpage.quit_alert()
        time.sleep(5)

        return '截图下载结果：' + download_result


if __name__ == '__main__':
    # 执行pytest单元测试，生成 Allure 报告需要的数据存在 /tmp 目录
    pytest.main(['test_UpAndDown.py','-s','--alluredir', '../report/tmp'])
    # 执行命令 allure generate ./tmp -o ./report --clean ，生成测试报告
    os.system('allure generate  ../report/tmp -o ../report/report --clean')

