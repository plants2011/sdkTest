package com.hm.pluginsdk.listeners;

/**
 * 对应用层开放的获取授权码回调接口
 */
public interface OnContronListener {

    void pinCodeResult(boolean success, String cid, String pinCode, String msg);

    void contronResult(boolean success, String msg);

    void contronLost();

}
