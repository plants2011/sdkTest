package com.hm.pluginsdk.beans;

public class RtcSeiData {

    private String cid = "";//cid
    private String uid = "";//uid

    private int frameId;
    private long preEncoderTimestamp;
    private long encodedTimestamp;
    private long preDecoderTimestamp;
    private long decodedTimestamp;


    public String getCid() {
        return cid;
    }

    public String getUid() {
        return uid;
    }

    public int getFrameId() {
        return this.frameId;
    }

    public long getPreEncoderTimestamp() {
        return this.preEncoderTimestamp;
    }

    public long getEncodedTimestamp() {
        return encodedTimestamp;
    }

    public long getPreDecoderTimestamp() {
        return preDecoderTimestamp;
    }

    public long getDecodedTimestamp() {
        return decodedTimestamp;
    }

    @Override
    public String toString() {
        return "cid=" + this.cid + ";" +
                "uid=" + this.uid + ";" +
                "frameId=" + this.frameId + ";" +
                "preEncoderTimestamp=" + this.preEncoderTimestamp + ";" +
                "encodedTimestamp=" + this.encodedTimestamp + ";" +
                "preDecoderTimestamp=" + this.preDecoderTimestamp + ";" +
                "decodedTimestamp=" + this.decodedTimestamp + ";" +
                "";
    }
}
