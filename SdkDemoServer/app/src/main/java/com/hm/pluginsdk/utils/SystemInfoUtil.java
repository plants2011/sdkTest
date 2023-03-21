package com.hm.pluginsdk.utils;

/**
 * author : suyong
 * date   : 2021/7/20 20:36
 * desc   :
 * version: 1.0
 */
public class SystemInfoUtil {
    /**
     *
     * [获取cpu类型和架构]
     *
     * @return
     * 三个参数类型的数组，第一个参数标识是不是ARM架构，第二个参数标识是V6还是V7架构，第三个参数标识是不是neon指令集
     */
    public static String getCpuArchitecture() {
        return android.os.Build.CPU_ABI;
    }
}
