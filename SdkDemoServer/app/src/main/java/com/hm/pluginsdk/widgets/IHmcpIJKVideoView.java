package com.hm.pluginsdk.widgets;

public interface IHmcpIJKVideoView {

    void release();

    void setHmcpIJKVideoViewListener(HmcpIJKVideoViewListener hmcpIJKVideoViewListener);

    void stopPlay();

    void startPlay(String videoPath, String audioPath);

    boolean isPlaying();

}
