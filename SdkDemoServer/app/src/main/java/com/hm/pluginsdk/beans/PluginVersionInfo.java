package com.hm.pluginsdk.beans;

/**
 * Plugin插件包版本信息
 */
public class PluginVersionInfo {

    /**
     * 插件apk包VersionName
     */
    public String pluginVersionName;

    /**
     * 插件apk包VersionCode
     */
    public int pluginVersionCode;

    /**
     * 插件apk包支持的pluginSDK版本下限
     */
    public int supportMinSDKVersion;

    /**
     * 插件apk包支持的pluginSDK版本上限
     */
    public int supportMaxSDKVersion;

    /**
     * 插件apk包不支持的pluginSDK版本号
     */
    public String[] unSupportSDKVersionCodes;

}
