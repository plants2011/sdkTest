package com.hm.pluginsdk.beans;

import android.text.TextUtils;

/**
 * Demo中GPS数据参数配置实体类
 */
public class GPSData {

    public Float longitude; //经度
    public Float latitude; //纬度
    public Float altitude; //海拔，可选
    public Float speed; //速度，可选
    public Float course; // 航向，大于等于0，小于360浮点数

    public static boolean isValid(GPSData gpsData) {
        if (gpsData == null) {
            return false;
        }

        if (!checkItude(String.valueOf(gpsData.longitude),
                String.valueOf(gpsData.latitude))) {
            return false;
        }

        if (gpsData.course != null) {
            if (gpsData.course < 0 || gpsData.course >= 360) {
                return false;
            }
        }

        return true;
    }

    /**
     * 验证经纬度是否有效
     *
     * @param longitude -180~180 支持小数点后15位
     * @param latitude  -90~90
     * @return
     */
    public static boolean checkItude(String longitude, String latitude) {
        if (TextUtils.isEmpty(longitude) || TextUtils.isEmpty(latitude)) {
            return false;
        }

        String reglo = "[\\-+]?(0?\\d{1,2}|0?\\d{1,2}\\.\\d{1,15}|1[0-7]?\\d|1[0-7]?\\d\\.\\d{1,15}|180|180\\.0{1,15})";
        String regla = "[\\-+]?([0-8]?\\d|[0-8]?\\d\\.\\d{1,15}|90|90\\.0{1,15})";

        longitude = longitude.trim();
        latitude = latitude.trim();

        return longitude.matches(reglo) && latitude.matches(regla);
    }
}
