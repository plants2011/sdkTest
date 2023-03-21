package com.hm.pluginsdk.listeners;

/**
 * 更新游戏UID监听
 */
public interface OnUpdataGameUIDListener {
    void success(boolean success);

    void fail(String msg);
}
