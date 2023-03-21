package com.hm.pluginsdk.listeners;

/**
 * 发送消息回调
 */
public interface OnSendMessageListener {
    void result(boolean success, String mid);
}
