package com.hm.pluginsdk.beans;


import com.hm.pluginsdk.RefInvoke;

/**
 * Rtmp延迟数据JavaBean
 */
public class RtmpVideoDelayInfo extends VideoDelayInfo {
    /**
     * 真实的RtmpVideoDelayInfo
     */
    private Object relRtmpVideoDelayInfo;
    /**
     * 解码类型，1硬解，0软解，默认硬解
     */
    protected int decodeType = 1;

    public int getDecodeType() {
        return decodeType;
    }

    @Override
    public String getPacketsLostRate() {
        return (String) RefInvoke.invokeInstanceMethod(relRtmpVideoDelayInfo, "getPacketsLostRate");
    }

    @Override
    public String toString() {
        return (String) RefInvoke.invokeInstanceMethod(relRtmpVideoDelayInfo, "toString");
    }

    /**
     * @return 上报属性格式： 网络耗时|解码耗时|渲染耗时|感知延迟|帧大小|展示帧率|推流帧率|码率|乒乓耗时
     */
    @Override
    public String toReportString() {
        return (String) RefInvoke.invokeInstanceMethod(relRtmpVideoDelayInfo, "toReportString");
    }

    /**
     * 是否有效
     *
     * @return true 有效
     */
    @Override
    public boolean isVaild() {
        return (boolean) RefInvoke.invokeInstanceMethod(relRtmpVideoDelayInfo, "isVaild");

    }

    /**
     * rtmp不支持此方式，rtc支持
     *
     * @return
     */
    @Override
    public long getRoundTrip() {
        return (long) RefInvoke.invokeInstanceMethod(relRtmpVideoDelayInfo, "getRoundTrip");
    }

    /**
     * 获取服务端编码延迟，rtmp不支持此数据，返回-1
     *
     * @return rtmp返回-1
     */
    @Override
    public int getServerEncodeDelay() {
        return (int) RefInvoke.invokeInstanceMethod(relRtmpVideoDelayInfo, "getServerEncodeDelay");
    }
}
