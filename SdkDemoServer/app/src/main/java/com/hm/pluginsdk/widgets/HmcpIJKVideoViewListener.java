package com.hm.pluginsdk.widgets;


public interface HmcpIJKVideoViewListener {

    void onFirstFrameArrival();

    void onError();

    void onPlayerRelease();

    void onPlayerStop();
}
