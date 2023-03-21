import json


class DictData:
    def keys(self):
        keyLst = []
        for key in self.__dict__.keys():
            if getattr(self, key) != None:
                keyLst.append(key)

        return keyLst

class UserInfo(DictData):
    def __init__(self):
        self.userId = ""
        self.userToken = ""
        self.userLevel = 0
        self.userType = 0
        pass
    def __getitem__(self, item):
        return getattr(self, item)
    pass
class UserBaseInfo(DictData):
    def __init__(self):
        self.saasUrl = None
        self.bid = ""
        self.channelId = ""
        self.userId = ""
        self.accessKey = ""

    def __getitem__(self, item):
        return getattr(self, item)

    def getUserInfo(self):
        userInfo = UserInfo()
        userInfo.userId = self.userId
        userInfo.userToken = self.userId
        userInfo.userLevel = 0
        userInfo.userType = 0
        return userInfo


class GameBaseInfo(DictData):
    def __init__(self):
        self.packageName = ""
        self.priority = 0
        self.playTime = 600*1000
        self.showTime = False
        self.portrait = False
        self.longPlayTime = None #Long类型PlayTime参数

        self.archive = None #是否存档
        self.protoData = None #app业务餐宿，需要base64编码

        self.fpsPeriod = None #采集FPS并设置上报周期
        self.bandWidthPeriod = None #采集裁断和上报周期
        self.bandWidthPeak = None #带宽中期内的最大几个值
        self.decodeTimePeriod = None #采集解码时间和上报周期，单位为s

        self.openCameraPermissionCheck = None
        self.width = None
        self.height = None
        self.streamType = None #0 RTMP, 1, RTC
        self.decodeType = None #0 软解 1硬解
        self.speed = None #是否固定码率，码率单位kB/s

        self.interfaceId = None
        self.instanceId = None
        self.cid = None #是否使用之前的cid连接
        self.noInputLimitTime = None #无操作超时时间
        self.cidCacheInterval = None #默认2小时，0永不超时，超时重连时长
        self.clientISP = None #运营商名称;
        self.clientProvince = None #省份
        self.clientCity = None #定位城市
        self.isIpv6 = None #是否开启IPV6，默认不开
        pass

    def __getitem__(self, item):
        return getattr(self, item)

class ResolutionInfo(DictData):
    def __init__(self):
        self.id = ""
        self.name = ""
        self.resolution = ""
        self.peakBitRate = ""
        self.bitRate = ""
        self.frameRate = 0
        self.defaultChoice = ""
        self.close = ""
        pass
    def __getitem__(self, item):
        return getattr(self, item)
    def set(self,resolution):
        for key in resolution.keys():
            setattr(self, key, resolution[key])
        return self

class Resolutions:
    def __init__(self, resolutions):
        self.resolutionMap = {}
        for resolution in resolutions:
            info = ResolutionInfo().set(resolution)
            self.resolutionMap[info.name] = info
        pass
    @property
    def names(self):
        return list(self.resolutionMap.keys())
    def get(self, name):
        return self.resolutionMap[name]

class UpdateGameUid(DictData):
    def __init__(self):
        self.playTime = None
        self.userId = None
        self.protoData = None
        self.tip = "更新成功"
        self.accessKey = ""
        pass
    def __getitem__(self, item):
        return getattr(self, item)

class GpsData(DictData):
    def __init__(self):
        self.longitude = 0.0
        self.latitude = 0.0
        self.altitude = 0.0
        self.course = 0.0
        self.speed = 0.0
        pass
    def __getitem__(self, item):
        return getattr(self, item)
    def __str__(self):
        return json.dumps(dict(self))
