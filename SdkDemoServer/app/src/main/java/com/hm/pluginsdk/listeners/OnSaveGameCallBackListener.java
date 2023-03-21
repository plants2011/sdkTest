package com.hm.pluginsdk.listeners;

/**
 * 实例存档回调
 */
public interface OnSaveGameCallBackListener {
    void success(boolean result);

    void fail(String msg);
}
