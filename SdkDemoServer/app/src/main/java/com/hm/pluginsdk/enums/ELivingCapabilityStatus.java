package com.hm.pluginsdk.enums;

/**
 * 是否支持直播状态
 */
public enum ELivingCapabilityStatus {
    //切换清晰度/断网重连/刷新SToken操作时，设为Unknown
    UNKNOWN,
    //收到流地址信息时，根据SAAS返回字段，赋值为Supported.
    SUPPORTED,
    //收到流地址信息时，根据SAAS返回字段，赋值为Unsupported.
    UNSUPPORTED
}
