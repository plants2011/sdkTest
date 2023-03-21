package com.hm.qa.sdkdemoserver.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JsonUtil {
    private JsonUtil() {
    }

    public static JSONObject getJsonObj(Map<String, Object> jsonMap) {
        JSONObject json = new JSONObject();
        if (jsonMap == null || jsonMap.isEmpty()) {
            return json;
        }
        try {
            Set<Map.Entry<String, Object>> set = jsonMap.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                String key = entry.getKey();
                Object value = entry.getValue();
                if (!TextUtils.isEmpty(key)) {
                    json.put(entry.getKey(), value != null ? value : "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return json;
        }
    }

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public static Object parse(String text) {
        return JSON.parse(text);
    }

    public static <T> T fromJson(String json, Class<T> target) {
        return JSON.parseObject(json, target);
    }

    /**
     * 字符串是否为JSONObject格式
     *
     * @param json json
     * @return result. true:is json;false:not json
     */
    public static boolean isJsonObject(String json) {
        if (TextUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    /**
     * 字符串是否为JSONArray格式
     *
     * @param json json
     * @return result.true:is json;false:not json
     */
    public static boolean isJsonArray(String json) {
        if (TextUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JSONArray(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    /**
     * 字符串是否为JSON(JSONObject/JSONArray)格式
     *
     * @param json json
     * @return result.true:is json;false:not json
     */
    public static boolean isJSONValid(String json) {
        if (TextUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JSONObject(json);
        } catch (JSONException ex) {
            try {
                new JSONArray(json);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
