package com.hm.pluginsdk.beans;

/**
 * 分辨率数据实体类
 */

public class ResolutionInfo {
    public String id;
    public String name;
    public String resolution;
    public String peakBitRate;
    public String bitRate;
    public int frameRate;
    public String defaultChoice;
    public String close;

    @Override
    public String toString() {
        return "ResolutionInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", resolution='" + resolution + '\'' +
                ", peakBitRate='" + peakBitRate + '\'' +
                ", frameRate='" + frameRate + '\'' +
                ", bitRate=' " + bitRate + '\'' +
                ", defaultChoice='" + defaultChoice + '\'' +
                ", close='" + close + '\'' +
                '}';
    }
}
