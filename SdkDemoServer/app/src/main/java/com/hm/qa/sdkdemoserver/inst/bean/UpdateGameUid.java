package com.hm.qa.sdkdemoserver.inst.bean;

public class UpdateGameUid {
    private Long playTime;
    private String userId;
    private String protoData = "";
    private String tip = "更新成功";
    private String accessKey;

    public Long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Long playTime) {
        this.playTime = playTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProtoData() {
        return protoData;
    }

    public void setProtoData(String protoData) {
        this.protoData = protoData;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
