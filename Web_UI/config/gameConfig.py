# -*- coding:utf-8 -*-
# @Time   : 19:37
# @Author : yanxin


#使用k8s环境外网地址
sdkversion = '1662554606701'

demourl = "https://sdk-staging.haimawan.com/hmtools/sdk/cloudplay/1660533711223/" + sdkversion + "?"

demourl_k8s = "http://tools-manager.innerkubes.haimawan.com/hmtools/sdk/cloudplay/1660533711223/" + sdkversion + "?"

#rel-旅行青蛙
gameinfo1 = "env_name=rel&url=https://saas-rel.haimawan.com/s/rest/api&bid=1a305ed1dbc&accessKey=a4e8b7bfb8014061771b9b2c83b6ab01&packageName=jp.co.hit_point.tabikaeru&appChannel="

#rel-光遇
gameinfo2 = "env_name=rel&url=https://saas-rel.haimawan.com/s/rest/api&bid=fc7d0c914cd&accessKey=5cedb749a331d4cc00e8f05a3b2e6f45&packageName=com.netease.sky&appChannel=yuanshen"

#stag-庆余年-测试排队
gameinfo3 = "env_name=staging&url=https://saas-pre-staging.haimawan.com/s/rest/api&bid=29bb33deb84&accessKey=54a4c47b3dfae94298786713530f19af&packageName=com.qyn.rc.yy3733&appChannel=prj5181"

#sdktest-旅行青蛙-测试长时间无操作
gameinfo4= 'env_name=sdktest&bid=c37f6e2a033&appChannel=0912&packageName=jp.co.hit_point.tabikaeru&accessKey=269ef80b81ba285f612f4a2424f5d5cd&url=http://service-core-sdktest.innerkubes.haimawan.com/rest/api'

# #目前写死为rel环境
#
# #使用staging环境外网
# demourl_stag = "https://sdk-staging.haimawan.com/sdk/websdk5/game?"
#
# #rel-旅行青蛙
# gameinfo1_stag = "url=https://saas-rel.haimawan.com/s/rest/api&bid=1a305ed1dbc&accessKey=a4e8b7bfb8014061771b9b2c83b6ab01&packageName=jp.co.hit_point.tabikaeru&appChannel="
#
# #rel-光遇
# gameinfo2_stag = "url=https://saas-rel.haimawan.com/s/rest/api&bid=fc7d0c914cd&accessKey=5cedb749a331d4cc00e8f05a3b2e6f45&packageName=com.netease.sky&appChannel=yuanshen"
#
# #重构websdk
# sdkversion_stag = "dist-rel-master-cg"
#
# #流类型, 默认webrtc,不需引用
# streamtype_stag = '1'


#如果有需要，可以扩展添加其他环境游戏；同时在getplayaddress函数中增加一层判断即可
# gameInfo = {
#         '1':
#             {'gamename': '旅行青蛙',
#              'packageName': 'jp.co.hit_point.tabikaeru',
#              'accessKey': 'a4e8b7bfb8014061771b9b2c83b6ab01',
#              'appChannel': '',
#              'bid': '1a305ed1dbc',
#              },
#         '2':
#             {'gamename': '光遇',
#              'packageName': 'com.netease.sky',
#              'accessKey': '5cedb749a331d4cc00e8f05a3b2e6f45',
#              'appChannel': 'yuanshen',
#              'bid': 'fc7d0c914cd',
#              },
#         '3':
#             {'gamename': '哈利波特',
#              'packageName': 'com.netease.harrypotter',
#              'accessKey': 'a4e8b7bfb8014061771b9b2c83b6ab01',
#              'appChannel': 'gttest',
#              'bid': '1a305ed1dbc',
#              }
#
#     }

#print(gameInfo)

