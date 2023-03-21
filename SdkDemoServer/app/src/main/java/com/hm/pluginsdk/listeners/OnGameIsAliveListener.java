package com.hm.pluginsdk.listeners;

import com.hm.pluginsdk.beans.ChannelInfo;

import java.util.List;

/**
 * 查询用户是否有正在进行，实例未被释放的游戏回调
 */

public interface OnGameIsAliveListener {
    void success(List<ChannelInfo> channelInfoLists);

    void fail(String msg);
}
