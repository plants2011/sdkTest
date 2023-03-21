package com.hm.pluginsdk.beans;


import com.hm.pluginsdk.RefInvoke;

public class RtcVideoDelayInfo extends VideoDelayInfo {
    private Object relRtcVideoDelayInfo;

    private final int SERVER_ENCODE_DELAY_MIN = 18;
    private final int SERVER_ENCODE_DELAY_MAX = 22;

    private double netLevel;
    private int jankAndFreezeDuration;
    private int remoteToLocalClockTimeOffset;
    private long totalBitrate;
    private long pliSent;
    private int freezeCount;
    private int jankCount;
    private int decodeVariance;
    private int renderVariance;
    private long packetsLost;
    private long bitrateAudio;
    private long lastBitRateAudio;
    private int audioChannel = 2;
    private String audioEncodeMode = "opus";

    private String packetsLostRate;

    private String contentType;

    private long frameHeightReceived;

    private long frameWidthReceived;

    private long frameRateDecode;

    private long frameRateOutput;

    private long jitterBuffer;//单位 MS

    private long nacksSent;

    private long targetDelay;//单位 MS

    private long frameDelay;

    private long currentRoundTripTime;

    private long lastBitRate;

    private String codecName;

    private String codecImplementationName;

    private String boardType;

    /**
     * fecPackets*100/recvedPackets
     */
    private int fecPacketsPercent;

    /**
     * recoveredPackets*100/recvedFecPackets
     */
    private int fecRecoveredPercent;


    @Override
    public String getPacketsLostRate() {
        return this.packetsLostRate;
    }

    @Override
    public long getRoundTrip() {
        return this.currentRoundTripTime;
    }

    @Override
    public long getVideoFps() {
        return gameFps;
    }

    @Override
    public long getJitterBuffer() {
        return jitterBuffer;
    }

    public String getCodecName() {
        return codecName;
    }

    public String getCodecImplementationName() {
        return codecImplementationName;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    @Override
    public String toString() {
        return (String) RefInvoke.invokeInstanceMethod(relRtcVideoDelayInfo,"toString");
    }


    @Override
    public String toReportString() {
        return (String) RefInvoke.invokeInstanceMethod(relRtcVideoDelayInfo,"toReportString");
    }

    /**
     * 获取服务端编码延迟，rtmp不支持此数据，返回-1
     *
     * @return rtmp返回-1
     */
    @Override
    public int getServerEncodeDelay() {
        return (int) RefInvoke.invokeInstanceMethod(relRtcVideoDelayInfo,"getServerEncodeDelay");
    }

    /**
     * 是否有效
     *
     * @return true 有效
     */
    @Override
    public boolean isVaild() {
        return (boolean) RefInvoke.invokeInstanceMethod(relRtcVideoDelayInfo,"isVaild");
    }
}

