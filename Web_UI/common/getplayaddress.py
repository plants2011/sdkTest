# -*- coding:utf-8 -*-
# @Time   : 2021.11.29 18:57
# @Author : yanxin

from config.gameConfig import demourl,demourl_k8s,gameinfo1,gameinfo2,gameinfo3,gameinfo4

class PlayStream():

    def create_url(self,chioce):
        websdk_url = ""

        #播流rel环境-旅行青蛙-webrtc
        if chioce == 1:
            websdk_url = demourl + gameinfo1
            print('旅行青蛙-播流地址：\n' + websdk_url)

        # 播流rel环境-光遇-webrtc
        if chioce == 2:
            websdk_url = demourl + gameinfo2
            print('光遇-播流地址：\n' + websdk_url)

        #播流rel环境-旅行青蛙-rtmp
        if chioce == 3:
            websdk_url = demourl + gameinfo1 + '&streamtype=0'
            print('rtmp-播流地址：\n' + websdk_url)

        #播流staging环境-庆余年-测试排队
        if chioce == 4:
            websdk_url = demourl + gameinfo3
            print('测试排队-播流地址：\n' + websdk_url)

        #测试游戏时间到，游戏结束
        if chioce == 5:
            websdk_url = demourl + gameinfo1 + '&playingtime=60000'
            print('测试游戏时间到-播流地址：\n' + websdk_url)

        #测试全面屏
        if chioce == 6:
            websdk_url = demourl + gameinfo2 + '&width=1920&height=880'
            print('测试全面屏-播流地址：\n' + websdk_url)

        if chioce == 7:
            websdk_url = demourl_k8s + gameinfo4
            print('测试长时间无操作-播流地址：\n' + websdk_url)

        if chioce == 8:
            websdk_url = 'chrome://settings/content/siteDetails?site=https://sdk-staging.haimawan.com'


        return websdk_url

ps = PlayStream()

if __name__ == '__main__':
    ps.create_url(1)




# class PlayStream:
#
#     # 1-初始化，获取初始参数 , 并启动驱动
#     def __init__(self, gamenum, streamtype, sdkversion):
#         self.streamtype = streamtype
#         self.gamenum = gamenum
#         self.sdkversion = sdkversion
#
#     # 定义拼接播流地址函数，支持横竖屏播流、以及重构非重构的webSDK版本
#     def getplayaddress(self,gameInfo):
#
#         #1-拼接播流地址
#         demourl = 'https://sdk-staging.haimawan.com/sdk/websdk5/game?'
#         saas_url = 'https://saas-rel.haimawan.com/s/rest/api'
#
#         #2-获取游戏参数
#         parame = gameInfo.get(str(self.gamenum))
#
#         packageName = parame.get('packageName')
#         accessKey = parame.get('accessKey')
#         appChannel = parame.get('appChannel')
#         bid = parame.get('bid')
#
#
#         #3-选择流类型, 1为webrtc，0为rtmp
#         if self.streamtype == "0":
#             streamtype = '0'
#         elif self.streamtype == "1":
#             streamtype = '1'
#         else:
#             exit() #中断函数运行
#
#
#         #4-定义SDK版本,0表示非重构，1为重构
#         if self.sdkversion == "0":
#             version = 'dist-rel-master'
#         elif self.sdkversion == "1":
#             version = 'dist-rel-master-cg'
#         elif self.sdkversion == "2":
#             version = input("本次测试sdk版本为：")
#         else:
#             exit() #中断函数运行
#
#         websdk_url = demourl + 'url='+saas_url+ '&bid='+ bid\
#                      + '&accessKey='+ accessKey \
#                      + '&packageName=' + packageName \
#                      + '&appChannel='+ appChannel \
#                      + '&streamtype=' + streamtype\
#                      + '&version=' + version
#
#
#         print('播流地址：\n' + websdk_url)
#
#         return websdk_url
#
# gamenum = int(input("请选择播流游戏：1-旅行青蛙，2-光遇，3-哈利波特："))
# streamtype = input("请选择播流类型：1-webrtc，0-rtmp：")
# sdkversion = input("请选择webSDK版本：0-非重构webSDK；1-重构webSDK;2-自定义版本，请输入2：")
#
# #1-初始化，播流
# ps = PlayStream(gamenum, streamtype, sdkversion)


# if __name__ == '__main__':
#     # gamenum = int(input("请选择播流游戏：1-旅行青蛙，2-哈利波特，3-光遇："))
#     # streamtype = input("请选择播流类型：1-webrtc，0-rtmp：")
#     # sdkversion = input("请选择webSDK版本：0-非重构webSDK；1-重构webSDK;2-自定义版本，请输入2：")
#     #
#     # t = PlayStream(gamenum, streamtype,sdkversion)
#
#     #2- 使用播流地址播流
#     ps.getplayaddress(gameInfo)