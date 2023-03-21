package com.hm.qa.sdkdemoserver.inst.bean;

public class ReportInfo {
    String jiraID; //jenkins构建时，填入的构建id
    String app; // app的名字
    String buildVersion; //构建时的jira-id
    String buildType; //构建类型
    String channel; //渠道
    int jenkinsTask; //jenkins任务号
    String remark; //其他备注

    public String getJiraID() {
        return jiraID;
    }

    public void setJiraID(String jiraID) {
        this.jiraID = jiraID;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public int getJenkinsTask() {
        return jenkinsTask;
    }

    public void setJenkinsTask(int jenkinsTask) {
        this.jenkinsTask = jenkinsTask;
    }

    public String getRemark() {
        return remark;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
