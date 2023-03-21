package com.hm.pluginsdk.listeners;


import com.hm.pluginsdk.beans.RtcSeiData;

import java.util.List;

public interface ISeiListener {

    void onData(List<RtcSeiData> data);
}
