package com.hm.qa.sdkdemoserver.inst.bean;

import com.hm.pluginsdk.enums.WsMessageType;

public class WsMessageData {
    String payload;
    WsMessageType msgType;

    public WsMessageData(WsMessageType msgType, String payload){
        this.payload = payload;
        this.msgType = msgType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public WsMessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(WsMessageType msgType) {
        this.msgType = msgType;
    }
}
