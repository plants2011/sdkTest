package com.hm.qa.sdkdemoserver.inst.bean;

public class UserBaseInfo {
    private String saasUrl;
    private String bid;
    private String channelId;
    private String userId;
    private String accessKey;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSaasUrl() {
        return saasUrl;
    }

    public void setSaasUrl(String saasUrl) {
        this.saasUrl = saasUrl;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
