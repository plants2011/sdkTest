package com.hm.pluginsdk.listeners;

/**
 * 对应用层开放的游戏直播接口
 */
public interface OnLivingListener {

    void start(boolean success, String msg);

    void stop(boolean success, String msg);

}
