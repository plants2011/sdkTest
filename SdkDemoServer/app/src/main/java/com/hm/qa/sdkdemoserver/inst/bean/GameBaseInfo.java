package com.hm.qa.sdkdemoserver.inst.bean;

public class GameBaseInfo {
    private String packageName; //<必填>游戏包名
    private Integer priority; //<必填>游戏优先级
    private Long playTime; //<必填>游戏时间，单位ms
    private Boolean isShowTime; //显示用户剩余游戏时间
    private Boolean isPortrait; //是否竖屏
    private Boolean isLongPlayTime = false; //是否Long类型

    //以下选填
    //private Boolean openCameraPermissionCheck; //每次申请摄像头想App申请权限，默认关闭
    private Boolean archive; //是否存档
    private String protoData; //App业务参数，需要base64编码

    private Integer fpsPeriod; //采集FPS并设置上报周期
    private Integer bandWidthPeriod; //采集裁断和上报周期
    private Integer bandWidthPeak; //带宽中期内的最大几个值
    private Integer decodeTimePeriod; //采集解码时间和上报周期，单位为s

    private Integer width;//自定义分辨率宽
    private Integer height; //自定义分辨率高
    private Integer streamType; //0 RTMP,默认, 1 RTC;
    private Integer decodeType; //0 软解，1 硬解默认
    private Integer speed; //是否固定码率,单位kB/s

    private Integer interfaceId; //固定播流的interfaceID
    private Integer instanceId; //固定播流的interfaceID
    private String cid; //是否使用之前的cid连接
    private Integer noInputLimitTime; //无操作超时时间
    private Long cidCacheInterval; //默认2小时，0永不超时，超时重连时长
    private String clientISP; //运营商名称;
    private String clientProvince; //省份
    private String clientCity; //定位城市
    private Boolean isIpv6; //是否开启IPV6，默认不开


    public Integer getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }

    public Integer getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Integer instanceId) {
        this.instanceId = instanceId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Long playTime) {
        this.playTime = playTime;
    }

    public Boolean getShowTime() {
        return isShowTime;
    }

    public void setShowTime(Boolean showTime) {
        isShowTime = showTime;
    }

    public Boolean getPortrait() {
        return isPortrait;
    }

    public void setPortrait(Boolean portrait) {
        isPortrait = portrait;
    }

//    public Boolean getOpenCameraPermissionCheck() {
//        return openCameraPermissionCheck;
//    }
//
//    public void setOpenCameraPermissionCheck(Boolean openCameraPermissionCheck) {
//        this.openCameraPermissionCheck = openCameraPermissionCheck;
//    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public String getProtoData() {
        return protoData;
    }

    public void setProtoData(String protoData) {
        this.protoData = protoData;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getStreamType() {
        return streamType;
    }

    public void setStreamType(Integer streamType) {
        this.streamType = streamType;
    }

    public Integer getDecodeType() {
        return decodeType;
    }

    public void setDecodeType(Integer decodeType) {
        this.decodeType = decodeType;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Long getCidCacheInterval() {
        return cidCacheInterval;
    }

    public void setCidCacheInterval(Long cidCacheInterval) {
        this.cidCacheInterval = cidCacheInterval;
    }

    public String getClientISP() {
        return clientISP;
    }

    public void setClientISP(String clientISP) {
        this.clientISP = clientISP;
    }

    public String getClientProvince() {
        return clientProvince;
    }

    public void setClientProvince(String clientProvince) {
        this.clientProvince = clientProvince;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public Boolean getIpv6() {
        return isIpv6;
    }

    public void setIpv6(Boolean ipv6) {
        isIpv6 = ipv6;
    }

    public Integer getFpsPeriod() {
        return fpsPeriod;
    }

    public void setFpsPeriod(Integer fpsPeriod) {
        this.fpsPeriod = fpsPeriod;
    }

    public Integer getBandWidthPeriod() {
        return bandWidthPeriod;
    }

    public void setBandWidthPeriod(Integer bandWidthPeriod) {
        this.bandWidthPeriod = bandWidthPeriod;
    }

    public Integer getBandWidthPeak() {
        return bandWidthPeak;
    }

    public void setBandWidthPeak(Integer bandWidthPeak) {
        this.bandWidthPeak = bandWidthPeak;
    }

    public Integer getDecodeTimePeriod() {
        return decodeTimePeriod;
    }

    public void setDecodeTimePeriod(Integer decodeTimePeriod) {
        this.decodeTimePeriod = decodeTimePeriod;
    }

    public Integer getNoInputLimitTime() {
        return noInputLimitTime;
    }

    public void setNoInputLimitTime(Integer noInputLimitTime) {
        this.noInputLimitTime = noInputLimitTime;
    }

    public Boolean getLongPlayTime() {
        return isLongPlayTime;
    }

    public void setLongPlayTime(Boolean longPlayTime) {
        isLongPlayTime = longPlayTime;
    }
}
