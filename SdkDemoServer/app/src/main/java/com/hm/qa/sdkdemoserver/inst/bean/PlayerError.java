package com.hm.qa.sdkdemoserver.inst.bean;

public class PlayerError {
    public PlayerError(String code, String msg){
        errCode = code;
        errMsg = msg;
    }

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
