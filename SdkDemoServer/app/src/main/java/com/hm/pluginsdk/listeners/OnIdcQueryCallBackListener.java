package com.hm.pluginsdk.listeners;

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2022/5/24
 * 描述: OnIdcQueryCallBackListener.java
 * 历史: history<br/>
 * ================================================
 */
public interface OnIdcQueryCallBackListener {
    /**
     * 查询成功回调
     * @param url 测速地址,一个大文件的下载地址,通过下载该文件，判断网速
     * @param ipAddress ip地址
     */
    void success(String url, String ipAddress);

    /**
     * 查询失败
     * @param msg 失败原因
     */
    void fail(String msg);
}
