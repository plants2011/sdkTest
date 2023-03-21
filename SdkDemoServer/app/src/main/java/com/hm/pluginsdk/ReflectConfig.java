package com.hm.pluginsdk;


public enum ReflectConfig {
    /**
     * 对应SaasSDK中的通用类
     */
    HmcpManager(HmcpManager.class, "com.haima.hmcp.HmcpManager"),
    HmcpVideoView(HmcpVideoView.class, "com.haima.hmcp.widgets.HmcpVideoView"),
    ResourceManager(null, "com.haima.hmcp.business.ResourceManager"),

    /**
     * 对应SaasSDK中的JavaBean类
     */
    AbsVideoDelayInfo(com.hm.pluginsdk.beans.AbsVideoDelayInfo.class, "com.haima.hmcp.beans.AbsVideoDelayInfo"),
    BaseResult(com.hm.pluginsdk.beans.BaseResult.class, "com.haima.hmcp.beans.BaseResult"),
    CheckCloudServiceResult(com.hm.pluginsdk.beans.CheckCloudServiceResult.class, "com.haima.hmcp.beans.CheckCloudServiceResult"),
    ClipBoardData(com.hm.pluginsdk.beans.ClipBoardData.class, "com.haima.hmcp.beans.ClipBoardData"),
    ClipBoardItemData(com.hm.pluginsdk.beans.ClipBoardItemData.class, "com.haima.hmcp.beans.ClipBoardItemData"),
    GPSData(com.hm.pluginsdk.beans.GPSData.class, "com.haima.hmcp.beans.GPSData"),
    VideoDelayInfo(com.hm.pluginsdk.beans.VideoDelayInfo.class, "com.haima.hmcp.beans.VideoDelayInfo"),
    UserInfo(com.hm.pluginsdk.beans.UserInfo.class, "com.haima.hmcp.beans.UserInfo"),
    UserInfo2(com.hm.pluginsdk.beans.UserInfo.class, "com.haima.hmcp.beans.UserInfo2"),
    ResolutionInfo(com.hm.pluginsdk.beans.ResolutionInfo.class, "com.haima.hmcp.beans.ResolutionInfo"),
    Control(com.hm.pluginsdk.beans.Control.class, "com.haima.hmcp.beans.Control"),
    CloudFile(com.hm.pluginsdk.beans.CloudFile.class, "com.haima.hmcp.beans.CloudFile"),
    FPoint(com.hm.pluginsdk.beans.FPoint.class, "com.haima.hmcp.beans.FPoint"),
    RtcVideoDelayInfo(com.hm.pluginsdk.beans.RtcVideoDelayInfo.class, "com.haima.hmcp.rtc.widgets.beans.RtcVideoDelayInfo"),
    RtmpVideoDelayInfo(com.hm.pluginsdk.beans.RtmpVideoDelayInfo.class, "com.haima.hmcp.rtmp.widgets.beans.RtmpVideoDelayInfo"),

    /**
     * 对应SaasSDK中的枚举类型
     */
    MessageType(com.hm.pluginsdk.enums.MessageType.class, "com.haima.hmcp.enums.MessageType"),
    CloudOperation(com.hm.pluginsdk.enums.CloudOperation.class, "com.haima.hmcp.enums.CloudOperation"),
    ScreenOrientation(com.hm.pluginsdk.enums.ScreenOrientation.class, "com.haima.hmcp.enums.ScreenOrientation"),
    CloudPlayerKeyboardStatus(com.hm.pluginsdk.enums.CloudPlayerKeyboardStatus.class, "com.haima.hmcp.enums.CloudPlayerKeyboardStatus"),
    ELivingCapabilityStatus(com.hm.pluginsdk.enums.ELivingCapabilityStatus.class, "com.haima.hmcp.enums.ELivingCapabilityStatus"),
    NetWorkState(com.hm.pluginsdk.enums.NetWorkState.class, "com.haima.hmcp.enums.NetWorkState"),
    WsMessageType(com.hm.pluginsdk.enums.WsMessageType.class, "com.haima.hmcp.enums.WsMessageType"),
    KeyType(com.hm.pluginsdk.enums.KeyType.class, "com.haima.hmcp.enums.KeyType"),

    /**
     * 对应SaasSDK中的Listener
     */
    HmcpPlayerListener(com.hm.pluginsdk.listeners.HmcpPlayerListener.class, "com.haima.hmcp.listeners.HmcpPlayerListener"),
    CloudOperationListener(com.hm.pluginsdk.listeners.CloudOperationListener.class, "com.haima.hmcp.listeners.CloudOperationListener"),
    OnCloudImageListListener(com.hm.pluginsdk.listeners.OnCloudImageListListener.class, "com.haima.hmcp.listeners.OnCloudImageListListener"),
    OnContronListener(com.hm.pluginsdk.listeners.OnContronListener.class, "com.haima.hmcp.listeners.OnContronListener"),
    OnGameIsAliveListener(com.hm.pluginsdk.listeners.OnGameIsAliveListener.class, "com.haima.hmcp.listeners.OnGameIsAliveListener"),
    OnGetResolutionsCallBackListener(com.hm.pluginsdk.listeners.OnGetResolutionsCallBackListener.class, "com.haima.hmcp.listeners.OnGetResolutionsCallBackListener"),
    OnInitCallBackListener(com.hm.pluginsdk.listeners.OnInitCallBackListener.class, "com.haima.hmcp.listeners.OnInitCallBackListener"),
    OnLivingListener(com.hm.pluginsdk.listeners.OnLivingListener.class, "com.haima.hmcp.listeners.OnLivingListener"),
    OnSaveGameCallBackListener(com.hm.pluginsdk.listeners.OnSaveGameCallBackListener.class, "com.haima.hmcp.listeners.OnSaveGameCallBackListener"),
    OnSendMessageListener(com.hm.pluginsdk.listeners.OnSendMessageListener.class, "com.haima.hmcp.listeners.OnSendMessageListener"),
    OnSendWsMessageListener(com.hm.pluginsdk.listeners.OnSendWsMessageListener.class, "com.haima.hmcp.listeners.OnSendWsMessageListener"),
    OnSpeedTestCallBackListener(com.hm.pluginsdk.listeners.OnSpeedTestCallBackListener.class, "com.haima.hmcp.listeners.OnSpeedTestCallBackListener"),
    OnUpdataGameUIDListener(com.hm.pluginsdk.listeners.OnUpdataGameUIDListener.class, "com.haima.hmcp.listeners.OnUpdataGameUIDListener"),
    ISeiListener(com.hm.pluginsdk.listeners.ISeiListener.class, "com.haima.hmcp.listeners.ISeiListener"),
    TransferHelperCallback(com.hm.pluginsdk.business.TransferHelper.Callback.class, "com.haima.hmcp.business.TransferHelper$Callback"),
    HmFrameCallback(com.hm.pluginsdk.listeners.HmFrameCallback.class, "com.haima.hmcp.listeners.HmFrameCallback"),
    HmStreamerIPCallback(com.hm.pluginsdk.listeners.HmStreamerIPCallback.class, "com.haima.hmcp.listeners.HmStreamerIPCallback"),
    IHmcpIJKVideoView(com.hm.pluginsdk.widgets.IHmcpIJKVideoView.class, "com.haima.hmcp.rtmp.widgets.IHmcpIJKVideoView"),
    OnIdcQueryCallBackListener(com.hm.pluginsdk.listeners.OnIdcQueryCallBackListener.class, "com.haima.hmcp.listeners.OnIdcQueryCallBackListener");

    /**
     * Plugin插件中的类
     */
    private Class<?> type;

    /**
     * 对应的SaasSDK中的类
     */
    private String className;

    /**
     * 枚举构造函数
     *
     * @param type
     * @param reflectClassName
     */
    private ReflectConfig(Class<?> type, String reflectClassName) {
        this.type = type;
        this.className = reflectClassName;
    }

    /**
     * 获取枚举值的映射的SaasSDK中的Class
     *
     * @return
     */
    public Class<?> getReflectClass() {
        return PluginManager.getInstance().loadClass(this.className);
    }

    /**
     * 获取Plugin SDK中的Class
     *
     * @return
     */
    public Class<?> getType() {
        return this.type;
    }

    /**
     * 返回反射的类名
     *
     * @return
     */
    public String getReflectClassName() {
        return this.className;
    }
}
