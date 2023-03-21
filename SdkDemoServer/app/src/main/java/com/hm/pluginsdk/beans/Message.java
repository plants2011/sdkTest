package com.hm.pluginsdk.beans;

import java.io.Serializable;

/**
 * 消息实体类
 */
public class Message implements Serializable {

    public static final int ACK_CLIENT_SEND = 0;

    public static final int TYPE_PAY_MESSAGE = 1;
    public static final int TYPE_SYSTEM_MESSAGE = 2;
    public static final int TYPE_AD_MESSAGE = 3;
    public static final int TYPE_INTERACTIVE_MESSAGE = 4;

    public String to; //目标接收者CID，全部推送"ALL"

    public String mid; //消息ID

    public String payload; //消息内容

    public String from;  //发送者  系统消息="System"

    public int type; // 消息类型 1=支付，2=系统消息

    public int ack; //客户端发送默认为0；服务端收到回复为1

    public String uid; //可选参数，目标uid

    @Override
    public String toString() {
        return "Message{" +
                "to='" + to + '\'' +
                ", mid='" + mid + '\'' +
                ", payload='" + payload + '\'' +
                ", from='" + from + '\'' +
                ", type=" + type +
                ", ack=" + ack +
                ", uid='" + uid + '\'' +
                '}';
    }
}
