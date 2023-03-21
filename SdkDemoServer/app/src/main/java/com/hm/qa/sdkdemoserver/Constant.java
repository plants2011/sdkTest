package com.hm.qa.sdkdemoserver;

public interface Constant {
    public static String mChannelID = "com.haima.pluginsdk";
    String JACOCO_REPORT_GIT_COMMIT_ID = "943dc53b07524b971b75ea9f4aeec4069532da79"; // 构建的commit_id
    int JACOCO_REPORT_JENKINS_BUILD = 55; // 构建的任务号


    /**
     * Jacoco上报数据，常量定义
     */
    String JACOCO_REPORT_ADDRESS = "114.251.97.18"; //上报地址
    int JACOCO_REPORT_PORT = 19995; //端口
    long JACOCO_REPORT_INTERVAL = 60000; //毫秒
    int JACOCO_REPORT_DEFAULT = 0; // 自动上报(1自动，0关闭)
    String JACOCO_REPORT_JIRA_ID = "automation"; // 这个可能是jiraID 也有可能是commitID
    String JACOCO_REPORT_REMARK = "default"; // 预留一个数据
    String JACOCO_REPORT_CHANNEL = "haima"; // 构建的渠道
    String JACOCO_REPORT_BUILD_TYPE = "haima"; // 构建的类型，可能是私有云构建的
    int START_FOREGROUND_NOTIFICATION_ID = 0x001;

    boolean IS_JACOCO_REPORT = JACOCO_REPORT_DEFAULT == 1;
}
