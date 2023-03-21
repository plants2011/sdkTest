package com.hm.pluginsdk.listeners;

/**
 * 测速回调Listener
 */

public interface OnSpeedTestCallBackListener {

    /**
     * 测速完成
     *
     * @param success        true成功
     * @param speed          测速值，单位KB
     * @param speedThreshold 测速阈值
     */
    void onComplete(boolean success, int speed, int speedThreshold);
}
