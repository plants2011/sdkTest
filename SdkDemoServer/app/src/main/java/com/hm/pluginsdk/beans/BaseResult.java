package com.hm.pluginsdk.beans;

/**
 * 服务器端返回结果基类
 */
public abstract class BaseResult {
    public int code;
    public String msg;
    public String errorCode;
    public String errorMsg;
    public int retryRequestCount;

    @Override
    public String toString() {
        return "code = " + code + ":msg = " + msg + ":errorCode = " + errorCode + ":errorMsg" + errorMsg;
    }
}
