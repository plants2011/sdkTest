package com.hm.pluginsdk.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/5/9.
 */

public class StatusCallbackUtil {
    public static String STATUS="status";
    public static String DATA="data";
    public static String PLAYERSTATE="playerState";
    public static String SERVICEFINISH="isServiceFinish";

    public static String getCallbackData(int status,String data){
        JSONObject obj=new JSONObject();
        try {
            obj.put(STATUS,status);
            obj.put(DATA,data);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
}
