package com.hm.pluginsdk;

/**
 * author : suyong
 * date   : 2021/7/14 10:44
 * desc   :
 * version: 1.0
 */
public enum PluginInitResult {
    PLUGIN_FILE_INIT_SUCCESS(0, "plugin init success"),
    PLUGIN_FILE_NOTEXIT(-1, "plugin file not exist"),
    PLUGIN_FILE_UNZIPFAIL(-2, "plugin file unzip error"),
    PLUGIN_VERSION_NOTMATCH(-3, "plugin verison not match current sdk version");

    private final int code;
    private final String msg;

    PluginInitResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "PluginInitResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

}
