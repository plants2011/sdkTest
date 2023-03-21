package com.hm.qa.sdkdemoserver.service;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.RequiresApi;

import com.alibaba.fastjson.JSON;
import com.hm.pluginsdk.beans.ClipBoardData;
import com.hm.pluginsdk.beans.ClipBoardItemData;
import com.hm.pluginsdk.beans.GPSData;
import com.hm.pluginsdk.beans.RtcVideoDelayInfo;
import com.hm.pluginsdk.beans.RtmpVideoDelayInfo;
import com.hm.pluginsdk.enums.MessageType;
import com.hm.pluginsdk.enums.WsMessageType;
import com.hm.pluginsdk.listeners.OnContronListener;
import com.hm.pluginsdk.listeners.OnSendMessageListener;
import com.hm.pluginsdk.listeners.OnSendWsMessageListener;
import com.hm.qa.sdkdemoserver.Constant;
import com.hm.qa.sdkdemoserver.PlayerActivity;
import com.hm.qa.sdkdemoserver.inst.GameInstance;
import com.hm.qa.sdkdemoserver.inst.WaitingInstance;
import com.hm.qa.sdkdemoserver.inst.bean.GameProcessInfo;
import com.hm.qa.sdkdemoserver.inst.bean.GameResult;
import com.hm.qa.sdkdemoserver.inst.bean.PlayerStatus;
import com.hm.qa.sdkdemoserver.inst.bean.UpdateGameUid;
import com.hm.pluginsdk.HmcpManager;
import com.hm.pluginsdk.HmcpVideoView;
import com.hm.pluginsdk.PluginManager;
import com.hm.pluginsdk.RefInvoke;
import com.hm.pluginsdk.beans.ChannelInfo;
import com.hm.pluginsdk.beans.ResolutionInfo;
import com.hm.pluginsdk.beans.UserInfo;
import com.hm.pluginsdk.beans.VideoDelayInfo;
import com.hm.pluginsdk.listeners.OnGameIsAliveListener;
import com.hm.pluginsdk.listeners.OnGetResolutionsCallBackListener;
import com.hm.pluginsdk.listeners.OnInitCallBackListener;
import com.hm.qa.sdkdemoserver.inst.bean.GameBaseInfo;
import com.hm.qa.sdkdemoserver.inst.bean.UserBaseInfo;
import com.hm.pluginsdk.listeners.OnLivingListener;
import com.hm.pluginsdk.listeners.OnSaveGameCallBackListener;
import com.hm.pluginsdk.listeners.OnUpdataGameUIDListener;
import com.hm.pluginsdk.utils.CryptoUtils;
import com.hm.qa.sdkdemoserver.inst.bean.WaitResult;
import com.hm.qa.sdkdemoserver.inst.bean.WsMessageData;
import com.hm.qa.sdkdemoserver.inst.enums.MethodMessageEnum;
import com.hm.qa.sdkdemoserver.stub.Log;
import com.hm.qa.sdkdemoserver.utils.JacocoUtil;
import com.hm.qa.sdkdemoserver.utils.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SdkServiceImpl {
    private String initResult = null;
    private Integer checkTimeout = 3*1000;
    public String ping(){
        Log.i("ping here");
        return "pong";
    }

    private WaitResult callPlayActivityMessage(int methodMessage, Object params) throws InterruptedException {
        android.os.Message message = GameInstance.inst().getHandler().obtainMessage();
        message.what = methodMessage;
        message.obj = params;
        WaitingInstance.setWaitResult(null);
        Log.i("CLASS: " + GameInstance.inst().getActivity().getClass().getSimpleName());
        message.sendToTarget();
        return WaitingInstance.waitForWaitResultCompleted();
    }
    private String getCToken(String packageName, String userId, String accessKeyID, String appChannel, String accessKey){
        String raw = userId + userId + packageName + accessKeyID + appChannel;
        String cToken = CryptoUtils.generateCToken(raw, accessKey);
        return cToken;
    }

    private Map getResult(){
        return getResult(true);
    }
    private Map getResult(Object result){
        Map<String, Object> ret = new HashMap<>();
        ret.put("process", GameInstance.inst().getGameProcessInfo());
        ret.put("result", result);
        return ret;
    }

    private Boolean checkProcessStatusExist(List<Integer> statusLst){
        Long timeout = System.currentTimeMillis() + checkTimeout;
        while(System.currentTimeMillis() < timeout){
            Integer foundCount = 0;
            for(Integer status : statusLst){
                for(PlayerStatus ps : GameInstance.inst().getGameProcessInfo().getPlayerStatuses()){
                    if (ps.getStatus().equals(status)) {
                        foundCount ++;
                    }
                }
            }
            if(foundCount == statusLst.size()){
                return true;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        return false;
    }
    private Boolean checkProcessStatusExist(Integer status)  {
        List<Integer> statusLst = new ArrayList<>();
        statusLst.add(status);
        return checkProcessStatusExist(statusLst);
    }
    public Map getGameProcess(){
        return getResult();
    }
    public String uploadJacocoReport(){
        String result = JacocoUtil.execUpload(Constant.JACOCO_REPORT_ADDRESS, Constant.JACOCO_REPORT_PORT);
        Log.d("result:" + result + ":time=" + System.currentTimeMillis());
        return result;
    }

    public Boolean initHmcpManager(UserBaseInfo userBaseInfo) throws InterruptedException {
        Bundle bundle = new Bundle();
        bundle.putString(HmcpManager.ACCESS_KEY_ID, userBaseInfo.getBid());
        bundle.putString(HmcpManager.CHANNEL_ID, userBaseInfo.getChannelId());
        if(userBaseInfo.getSaasUrl() != null){
            bundle.putString(HmcpManager.BUNDLE_HMCP_SAAS_AUTH_URL, userBaseInfo.getSaasUrl());
            HmcpManager.getInstance().setServiceUrl(bundle);
        }
        GameInstance.inst().setUserInfo(userBaseInfo);

        initResult = null;
        GameInstance.inst().getHmcpManager().init(bundle, GameInstance.inst().getApplication(), new OnInitCallBackListener() {
            @Override
            public void success() {
                Log.i("init success");
                initResult = "success";
            }

            @Override
            public void fail(String msg) {
                Log.e("init failed: " +msg);
                initResult = "failed, "+msg;
            }
        });
        Long now = System.currentTimeMillis();
        while(initResult == null && (System.currentTimeMillis() - now) < WaitingInstance.waitTimeout){
            Thread.sleep(200);
        }
        return true;
    }
    public Map init(UserBaseInfo userBaseInfo, GameBaseInfo gameBaseInfo) throws InterruptedException{
        initHmcpManager(userBaseInfo);

        GameInstance.inst().setGameInfo(gameBaseInfo);
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        GameInstance.inst().setGameResult(new GameResult());

        Intent intent = new Intent();
        intent.setClass(GameInstance.inst().getActivity(), PlayerActivity.class);
        GameInstance.inst().getActivity().startActivity(intent);

        Long now = System.currentTimeMillis();
        while(GameInstance.inst().getGameResult().getRunning() == false){
            if(System.currentTimeMillis() - now > WaitingInstance.waitTimeout){
                break;
            }
            if(GameInstance.inst().getGameResult().getCode() != 0){
                break;
            }
            Thread.sleep(200);
        }
        return getResult(GameInstance.inst().getGameResult());
    }

    public Map pauseGame(){
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        GameInstance.inst().getHmcpVideoView().pauseGame();
        return getResult();
    }
    public Map reconnect(){
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        GameInstance.inst().getHmcpVideoView().reconnection();
        return getResult();
    }
    public Map restartGame(Long playTime) throws InterruptedException {
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        if (playTime ==  null){
            playTime = GameInstance.inst().getGameInfo().getPlayTime();
        }
        GameInstance.inst().getHmcpVideoView().restartGame(playTime.intValue());
        return getResult();
    }
    public Map startPlay(){
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        GameInstance.inst().getHmcpVideoView().startPlay();
        return getResult();
    }
    public Map startPlayAhead(Boolean isAhead){
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        GameInstance.inst().getHmcpVideoView().startPlay(isAhead);
        return getResult();
    }
    public Map quitGame(){
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        if (GameInstance.inst().getHmcpVideoView() != null){
            GameInstance.inst().getHmcpVideoView().onPause();
            GameInstance.inst().getHmcpVideoView().onDestroy();
            GameInstance.inst().getActivity().finish();
        }
        return getResult();
    }
    public Map getResolutionDatas(){
        List<ResolutionInfo> lst = GameInstance.inst().getHmcpManager().getResolutionDatas();
        Map ret = new HashMap();
        ret.put("result", true);
        ret.put("resolutions", lst);
        return ret;
    }
    public Map onSwitchResolution(Integer level, ResolutionInfo resolutionInfo, Integer rate){
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        GameInstance.inst().getHmcpVideoView().onSwitchResolution(level, resolutionInfo, rate);
        return getResult();
    }
    public Map entryQueue(){
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        GameInstance.inst().getHmcpVideoView().entryQueue();
        return getResult();
    }
    public Map exitQueue(){
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        GameInstance.inst().getHmcpVideoView().exitQueue();
        return getResult();
    }
    public int getVideoLatency(){
        return GameInstance.inst().getHmcpVideoView().getVideoLatency();
    }
    public Map updateGameUid(UpdateGameUid updateData) throws InterruptedException {
        Bundle bundle = new Bundle();
        bundle.putLong(HmcpVideoView.PLAY_TIME, updateData.getPlayTime());
        bundle.putString(HmcpVideoView.USER_ID, updateData.getUserId());
        bundle.putString(HmcpVideoView.PAY_PROTO_DATA, updateData.getProtoData());


        String userId = updateData.getUserId();
        String packageName = GameInstance.inst().getGameInfo().getPackageName();
        String accessKeyID = GameInstance.inst().getUserInfo().getBid();
        String appChannel = GameInstance.inst().getUserInfo().getChannelId();

        //设置用户
        UserInfo userInfo = new UserInfo();
        userInfo.userToken = userId;
        userInfo.userId = userId;
        userInfo.userType = 0;
        GameInstance.inst().getHmcpVideoView().setUserInfo(userInfo);

        //设置CTOKEN
        String cToken = getCToken(packageName, userId, accessKeyID, appChannel, updateData.getAccessKey());
        bundle.putString(HmcpVideoView.C_TOKEN, cToken);

        //准备发送
        Map ret = WaitingInstance.getWaitTimeoutError();
        WaitingInstance.setWaiting(false);
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo()); //开始记录信息
        GameInstance.inst().getHmcpVideoView().updateGameUID(bundle, new OnUpdataGameUIDListener() {
            @Override
            public void success(boolean success) {
                ret.put("result", success);
                ret.put("message", "");
                if(success == true){
                    //如果成功，就开始更新
                    GameInstance.inst().getUserInfo().setUserId(updateData.getUserId());
                    GameInstance.inst().getUserInfo().setAccessKey(updateData.getAccessKey());
                    GameInstance.inst().getGameInfo().setPlayTime(updateData.getPlayTime());
                }
                WaitingInstance.setWaiting(true);
            }

            @Override
            public void fail(String msg) {
                ret.put("result", false);
                ret.put("message", msg);
                WaitingInstance.setWaiting(true);
            }
        });
        WaitingInstance.waitForCompleted();
        ret.put("process", GameInstance.inst().getGameProcessInfo());
        return ret;
    }
    public String getInputUrl(){
        return GameInstance.inst().getHmcpVideoView().getInputUrl();
    }
    public String getSdkVersion(){
        return GameInstance.inst().getHmcpManager().getSDKVersion();
    }
    public Map getResolutionInfos(String packageName, String channelId) throws InterruptedException {
        WaitingInstance.setWaiting(false);
        Map ret = WaitingInstance.getWaitTimeoutError();
        GameInstance.inst().getHmcpManager().getResolutionInfos(packageName, channelId, new OnGetResolutionsCallBackListener() {
            @Override
            public void success(List<ResolutionInfo> mResolutionList) {
                ret.put("resolutions", mResolutionList);
                ret.put("result", true);
                ret.put("message", "");
                WaitingInstance.setWaiting(true);
            }

            @Override
            public void fail(String msg) {
                ret.put("result", false);
                ret.put("message", msg);
                WaitingInstance.setWaiting(true);
            }
        });
        WaitingInstance.waitForCompleted();
        return ret;
    }
    public Map getQrCodeData() throws JSONException {
        String qrCodeData = GameInstance.inst().getHmcpVideoView().getQRCodeData();
        JSONObject jsonObject =  new JSONObject(qrCodeData);
        Map ret = new HashMap();
        ret.put("screenShotUrl", jsonObject.getString("screenShotUrl"));
        ret.put("screenResolution", jsonObject.getString("screenResolution"));
        ret.put("inputUrl", jsonObject.getString("inputUrl"));
        ret.put("orientation", jsonObject.getInt("orientation"));
        ret.put("encryption", jsonObject.getString("encryption"));
        return ret;
    }
    public Map backToGame(){
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        GameInstance.inst().getHmcpVideoView().backToGame();
        return getResult();
    }
    public Object getClockDiffVideoLatencyInfo() {
        RtcVideoDelayInfo rtcVideoDelayInfo ;
        RtmpVideoDelayInfo rtmpVideoDelayInfo ;
        Object o ;
        o = GameInstance.inst().getHmcpVideoView().getClockDiffVideoLatencyInfo();
        if (o instanceof RtmpVideoDelayInfo) {
            rtmpVideoDelayInfo = (RtmpVideoDelayInfo) o;
            Log.i("rtmpVideoDelayInfo:" + rtmpVideoDelayInfo.toString());
            Log.i("rtmpVideoDelayInfo:" + rtmpVideoDelayInfo.getPacketsLostRate());
            Log.i("rtmpVideoDelayInfo:" + rtmpVideoDelayInfo.toReportString());
            Log.i("rtmpVideoDelayInfo:" + rtmpVideoDelayInfo.getRoundTrip());
            Log.i("rtmpVideoDelayInfo:" + rtmpVideoDelayInfo.getServerEncodeDelay());
        }
        if (o instanceof RtcVideoDelayInfo) {
            rtcVideoDelayInfo = (RtcVideoDelayInfo) o;
            Log.i("rtcVideoDelayInfo:" + rtcVideoDelayInfo.getCodecName());
            Log.i("rtcVideoDelayInfo:" + rtcVideoDelayInfo.toString());
            Log.i("rtcVideoDelayInfo:" + rtcVideoDelayInfo.toReportString());
        }
        return o;
    }
    public Map release(){
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        GameInstance.inst().getHmcpVideoView().release();
        return getResult();
    }
    public Long getLastUserOperationTimestamp(){
        return GameInstance.inst().getHmcpVideoView().getLastUserOperationTimestamp();
    }
    public int resetInputTimer(Boolean needUpdate){
        return GameInstance.inst().getHmcpVideoView().resetInputTimer(needUpdate);
    }
    public int checkBitmap(Float percent){
        Bitmap bmp = GameInstance.inst().getHmcpVideoView().getShortcut();
        if(bmp != null){
            return GameInstance.inst().getHmcpVideoView().checkBitmap(bmp, percent);
        }
        return -1;
    }
    public Map setAudioMute(Boolean isMute){
        GameInstance.inst().setGameProcessInfo(new GameProcessInfo());
        GameInstance.inst().getHmcpVideoView().setAudioMute(isMute);
        return getResult();
    }
    public String getStreamUrl(){
        return GameInstance.inst().getHmcpVideoView().getStreamUrl();
    }
    public String getCloudId(){
        return GameInstance.inst().getHmcpManager().getCloudId();
    }
    public Map getGameArchiveStatus(String packageName, UserInfo userInfo) throws InterruptedException {
        Map ret = WaitingInstance.getWaitTimeoutError();
        WaitingInstance.setWaiting(false);
        GameInstance.inst().getHmcpManager().getGameArchiveStatus(packageName, userInfo, new OnSaveGameCallBackListener() {
            @Override
            public void success(boolean result) {
                ret.put("result", true);
                ret.put("status", result);
                ret.put("message", "");
                WaitingInstance.setWaiting(true);
            }

            @Override
            public void fail(String msg) {
                ret.put("result", false);
                ret.put("status", false);
                ret.put("message", msg);
                WaitingInstance.setWaiting(true);
            }
        });
        WaitingInstance.waitForCompleted();
        return ret;
    }
    public Map gameArchived(String packageName, UserInfo userInfo) throws InterruptedException {
        Map ret = WaitingInstance.getWaitTimeoutError();
        WaitingInstance.setWaiting(false);
        GameInstance.inst().getHmcpManager().gameArchived(packageName, userInfo, new OnSaveGameCallBackListener() {
            @Override
            public void success(boolean result) {
                ret.put("result", result);
                ret.put("message", "");
                WaitingInstance.setWaiting(true);
            }

            @Override
            public void fail(String msg) {
                ret.put("result", false);
                ret.put("message", msg);
                WaitingInstance.setWaiting(true);
            }
        });
        WaitingInstance.waitForCompleted();
        return ret;
    }
    public Map stopAndDismiss(Integer minArchiveSecond){
        Map ret = new HashMap();
        GameInstance.inst().setGameInfo(new GameBaseInfo());
        Activity activity = GameInstance.inst().getActivity();
        if(activity == null){
            return getResult();
        }
        GameInstance.inst().getHmcpVideoView().stopAndDismiss(activity, minArchiveSecond);
        return getResult();
    }
    public Map checkPlayingGame(UserInfo userInfo) throws InterruptedException {
        Map ret = WaitingInstance.getWaitTimeoutError();
        WaitingInstance.setWaiting(false);
        GameInstance.inst().getHmcpManager().checkPlayingGame(userInfo, new OnGameIsAliveListener() {
            @Override
            public void success(List<ChannelInfo> channelInfoLists) {
                ret.put("result", true);
                ret.put("message", "");
                if (channelInfoLists != null && channelInfoLists.size() > 0) {
                    ret.put("channelInfoLists", channelInfoLists);
                }else{
                    ret.put("channelInfoLists", new ArrayList<>());
                }
                WaitingInstance.setWaiting(true);
            }

            @Override
            public void fail(String msg) {
                ret.put("result", false);
                ret.put("message", msg);
                WaitingInstance.setWaiting(true);
            }
        });
        WaitingInstance.waitForCompleted();
        return ret;
    }
    public String getCloudPlayStatusCode(){
        return GameInstance.inst().getHmcpVideoView().getCloudPlayStatusCode();
    }
    public Map startLiving(String livingId, String livingUrl) throws InterruptedException {
        Map ret = WaitingInstance.getWaitTimeoutError();
        WaitingInstance.setWaiting(false);
        GameInstance.inst().getHmcpVideoView().startLiving(livingId, livingUrl, new OnLivingListener() {
            @Override
            public void start(boolean success, String msg) {
                ret.put("method", "start");
                ret.put("result", success);
                ret.put("msg", msg);
                WaitingInstance.setWaiting(true);
            }

            @Override
            public void stop(boolean success, String msg) {
                ret.put("method", "stop");
                ret.put("result", success);
                ret.put("msg", msg);
                WaitingInstance.setWaiting(true);
            }
        });
        WaitingInstance.waitForCompleted();
        return ret;
    }
    public Map stopLiving(String livingId) throws InterruptedException {
        Map ret = WaitingInstance.getWaitTimeoutError();
        WaitingInstance.setWaiting(false);
        GameInstance.inst().getHmcpVideoView().stopLiving(livingId, new OnLivingListener() {
            @Override
            public void start(boolean success, String msg) {
                ret.put("method", "start");
                ret.put("result", success);
                ret.put("msg", msg);
                WaitingInstance.setWaiting(true);
            }

            @Override
            public void stop(boolean success, String msg) {
                ret.put("method", "stop");
                ret.put("result", success);
                ret.put("msg", msg);
                WaitingInstance.setWaiting(true);
            }
        });
        WaitingInstance.waitForCompleted();
        return ret;
    }

    public boolean inputText(String payload){
        return GameInstance.inst().getHmcpVideoView().inputText(payload);
    }
    public Map getPinCode() throws InterruptedException {
        String pinCode = null;
        String cid = null;
        WaitingInstance.setWaiting(false);
        Map ret = WaitingInstance.getWaitTimeoutError();
        GameInstance.inst().getHmcpVideoView().getPinCode(new OnContronListener() {
            @Override
            public void pinCodeResult(boolean success, String cid, String pinCode, String msg) {
                ret.put("cid", cid);
                ret.put("pinCode", pinCode);
                ret.put("result", true);
                ret.put("msg", msg);
                WaitingInstance.setWaiting(true);
            }

            @Override
            public void contronResult(boolean success, String msg) {
                if(success == false){
                    ret.put("result", false);
                    ret.put("msg", msg);
                }
                WaitingInstance.setWaiting(true);
            }

            @Override
            public void contronLost() {
                ret.put("result", false);
                ret.put("msg", "contronLost");
                WaitingInstance.setWaiting(true);
            }
        });
        WaitingInstance.waitForCompleted();
        return ret;
    }

    public Map getRenderType(){
        Map ret = new HashMap();
        Class clz = PluginManager.getInstance().loadClass("com.haima.hmcp.HmcpManager");
        if(clz == null){
            ret.put("result", false);
            ret.put("message", "类加载错误");
            return ret;
        }
        Object objInstance = RefInvoke.invokeStaticMethod(clz, "getInstance");
        Integer type = (Integer)RefInvoke.invokeInstanceMethod(objInstance, "getVideoViewType");
        if(type == 1){
            ret.put("result", true);
            ret.put("message", "");
            ret.put("type", "SurfaceView");
            return ret;
        }else if(type == 2){
            ret.put("result", true);
            ret.put("message", "");
            ret.put("type", "TextureView");
            return ret;
        }else{
            ret.put("result", false);
            ret.put("message", "未知类型");
            return ret;
        }
    }
    public Map setReleaseCid(String packageName, String cid, UserBaseInfo user) throws InterruptedException {
        String userToken = getCToken(packageName, user.getUserId(), user.getBid(), user.getChannelId(), user.getAccessKey());
        UserInfo userInfo = new UserInfo();
        userInfo.userId = user.getUserId();
        userInfo.userToken = user.getUserId();
        userInfo.userLevel = 0;
        userInfo.userType = 0;
        Map ret = WaitingInstance.getWaitTimeoutError();
        WaitingInstance.setWaiting(false);
        GameInstance.inst().getHmcpManager().setReleaseCid(packageName, cid, userToken, user.getChannelId(), userInfo, new OnSaveGameCallBackListener() {
            @Override
            public void success(boolean result) {
                ret.put("result", result);
                ret.put("message", "");
                WaitingInstance.setWaiting(true);
            }

            @Override
            public void fail(String msg) {
                ret.put("result", false);
                ret.put("message", msg);
                WaitingInstance.setWaiting(true);
            }
        });
        WaitingInstance.waitForCompleted();
        return ret;
    }
    public Map sendMessage(String payload, Integer type) throws InterruptedException {
        Map ret = WaitingInstance.getWaitTimeoutError();
        MessageType msgType = null;
        if(type == 0){
            msgType = MessageType.OTHER_TYPE;
        }else if(type == 1){
            msgType = MessageType.PAY_TYPE;
        }else if(type == 2){
            msgType = MessageType.SYSTEM_TYPE;
        }else if(type == 3){
            msgType = MessageType.TRANSFER;
        }else{
            ret.put("result", false);
            ret.put("message", "no support message type");
            return ret;
        }
        WaitingInstance.setWaiting(false);
        GameInstance.inst().getHmcpVideoView().sendMessage(payload, msgType, new OnSendMessageListener() {
            @Override
            public void result(boolean success, String mid) {
                ret.put("result", success);
                ret.put("message", "");
                ret.put("mid", mid);
                WaitingInstance.setWaiting(true);
            }
        });
        WaitingInstance.waitForCompleted();
        return ret;
    }
    private String getGpsData(String payload) throws JSONException {
        JSONObject gpsObj = new JSONObject(payload);
        GPSData gpsData = new GPSData();
        gpsData.longitude = new Float(gpsObj.getDouble("longitude"));
        gpsData.latitude = new Float(gpsObj.getDouble("latitude"));
        gpsData.altitude = new Float(gpsObj.getDouble("altitude"));
        gpsData.course = new Float(gpsObj.getDouble("course"));
        gpsData.speed = new Float(gpsObj.getDouble("speed"));

        return JsonUtil.toJsonString(gpsData);
    }
    private String getClipBoardPlainTextData(String payload){
        List<ClipBoardItemData> data = new ArrayList<>();
        ClipBoardItemData clipBoardItemData = new ClipBoardItemData();
        clipBoardItemData.itemType = ClipBoardItemData.TYPE_TEXT_PLAIN;
        clipBoardItemData.itemData = payload;
        data.add(clipBoardItemData);

        ClipBoardData clipBoardData = new ClipBoardData();
        clipBoardData.data = data;
        clipBoardData.dataType = ClipBoardData.CLIPBOARD_DATA_TYPE_PLAIN;
        clipBoardData.code = ClipBoardData.CLIPBOARD_DATA_CODE;
        return JsonUtil.toJsonString(clipBoardData);
    }
    public Map sendWsMessage(String payload, Integer type) throws InterruptedException, JSONException {
        Map ret = WaitingInstance.getWaitTimeoutError();
        WsMessageType msgType = null;
        if(type == 0){
            msgType = WsMessageType.GPS_TYPE;
            payload = getGpsData(payload);
        }else if(type == 2){
            msgType = WsMessageType.CLIPBOARD_TYPE;
            payload = getClipBoardPlainTextData(payload);
        }else if(type == 1){
            msgType = WsMessageType.INTENT_TYPE;
        }else{
            ret.put("result", false);
            ret.put("message", "no support message type");
            return ret;
        }
        Log.i("sendWsMessage: "+msgType+", "+payload);

        WsMessageData param = new WsMessageData(msgType, payload);
        WaitResult waitResult = callPlayActivityMessage(MethodMessageEnum.WsMessage, param);

        ret.put("result", waitResult.getResult());
        ret.put("message", waitResult.getMessage());
        return ret;
    }
}
