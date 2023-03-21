package com.hm.pluginsdk.beans;

/**
 * 视频流播放相关耗时信息
 */
public class VideoDelayInfo extends AbsVideoDelayInfo {
    /**
     * 采集时间
     */
    protected long timeStamp;
    /**
     * 网络耗时
     */
    protected int netDelay;
    /**
     * 解码耗时
     */
    protected int decodeDelay;

    /**
     * 渲染耗时
     */
    protected int renderDelay;

    /**
     * 最后一帧耗时
     */
    protected long delayTime = 0;

    /**
     * 最后一帧距离采样时的耗时包含时钟差
     */
    protected long nowDelayTime = 0;

    /**
     * 帧大小单位byte
     */
    protected int frameSize;

    /**
     * 显示帧率
     */
    protected long videoFps;

    /**
     * 码率
     */
    protected long bitRate;

    /**
     * 接收到的总帧数
     */
    protected int reciveFrameCount = 0;

    /**
     * 接收到的所有帧大小
     */
    protected long receiveFrameSize = 0;

    /**
     * 推流帧率
     */
    protected int gameFps = 0;

    protected int frameRateEglRender = 0;

    protected long clockDiffUse;

    /**
     * 服务端编码延迟，只支持rtc
     */
    protected int serverEncodeDelay = -1;

    public int getRenderDelay() {
        return renderDelay;
    }

    public int getNetDelay() {
        return netDelay;
    }

    public int getDecodeDelay() {
        return decodeDelay;
    }

    private int getFrameSize() {
        return frameSize;
    }

    public long getVideoFps() {
        return videoFps;
    }

    public long getBitRate() {
        return bitRate;
    }



    public int getReciveFrameCount() {
        return reciveFrameCount;
    }

    public long getReceiveFrameSize() {
        return receiveFrameSize;
    }

    public String getPacketsLostRate() {
        return "";
    }

    public long getTimeStamp() {
        return timeStamp;
    }


    public long getDelayTime() {
        return delayTime;
    }


    public long getJitterBuffer() {
        //rtmp不支持此属性，返回-1
        return -1L;
    }

    /**
     * rtmp不支持此方式，rtc支持
     * @return
     */
    public long getRoundTrip() {
        return 0;
    }

    /**
     * 获取服务端编码延迟，rtmp不支持此数据，返回-1
     * @return rtmp返回-1
     */
    public int getServerEncodeDelay() {
        return serverEncodeDelay;
    }



    /**
     * @return 上报属性格式： 网络耗时|解码耗时|渲染耗时|感知延迟|帧大小|展示帧率|推流帧率|码率|乒乓耗时
     */
    @Override
    public String toReportString() {
        return "";
    }

    /**
     * 是否有效
     *
     * @return true 有效
     */
    public boolean isVaild() {
        return false;
    }
}
