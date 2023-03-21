package com.hm.pluginsdk.listeners;

import com.hm.pluginsdk.beans.Message;
import com.hm.pluginsdk.enums.CloudPlayerKeyboardStatus;
import com.hm.pluginsdk.enums.NetWorkState;

/**
 * 播放器核心回调接口HmcpPlayerListener
 */
public interface HmcpPlayerListener {
    void onError(int var1, String var2);

    void onSuccess();

    void onExitQueue();

    void onMessage(Message var1);

    void onSceneChanged(String var1);

    void onNetworkChanged(NetWorkState var1);

    void onPlayStatus(int var1, long var2, String var4);

    void HmcpPlayerStatusCallback(String var1);

    void onPlayerError(String var1, String var2);

    void onInputMessage(String var1);

    void onInputDevice(int var1, int var2);

    void onPermissionNotGranted(String var1);

    void onCloudDeviceStatus(String status);

    void onInterceptIntent(String intentData);

    void onCloudPlayerKeyboardStatusChanged(CloudPlayerKeyboardStatus status);
}
