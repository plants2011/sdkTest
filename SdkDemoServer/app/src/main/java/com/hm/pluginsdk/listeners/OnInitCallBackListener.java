package com.hm.pluginsdk.listeners;

/**
 * 插件化初始化回调
 */

public interface OnInitCallBackListener {
    public void success();
    public void fail(String msg);
}
