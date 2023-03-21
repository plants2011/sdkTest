package com.hm.pluginsdk.listeners;

/**
 * 获取云端图片列表Listener
 */
public interface OnCloudImageListListener {

    void onSuccess(String payload);

    void onError(String error);

}
