package com.hm.pluginsdk.listeners;


/**
 * 发送WsMessage消息回调
 */
public interface OnSendWsMessageListener {
    void sendWsMessageSuccess();

    void sendWsMessageFail(String msg);
}
