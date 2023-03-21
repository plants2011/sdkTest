package com.hm.pluginsdk.enums;

/**
 * 键盘状态枚举
 */
public enum CloudPlayerKeyboardStatus {

    CLOUD_PLAYER_KEYBOARD_STATUS_NONE(-1),
    CLOUD_PLAYER_KEYBOARD_STATUS_SHOW(0),
    CLOUD_PLAYER_KEYBOARD_STATUS_HIDE(1);

    //本地键盘显示与否状态状态
    private int status;

    public int getStatus() {
        return status;
    }

    CloudPlayerKeyboardStatus(int status) {
        this.status = status;
    }
}
