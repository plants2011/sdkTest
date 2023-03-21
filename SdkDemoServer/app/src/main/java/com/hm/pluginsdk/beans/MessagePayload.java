package com.hm.pluginsdk.beans;


/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2022/5/24
 * 描述: MessagePayload plugin-sdk中没有调用，勿删！！！接入商在onMessage中的Payload转换使用
 * 历史: history<br/>
 * ================================================
 */
public class MessagePayload {
    public static final int CODE_EXIT = 100;
    public static final int CODE_INVALID_UTOKEN = 101;
    public int code;
    public String uid;
    public String description;

    @Override
    public String toString() {
        return "MessagePayload{" +
                "code='" + code + '\'' +
                ", uid='" + uid + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
