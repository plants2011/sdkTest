package com.hm.qa.sdkdemoserver;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.hm.pluginsdk.beans.CloudFile;
import com.hm.pluginsdk.beans.Message;
import com.hm.pluginsdk.enums.CloudOperation;
import com.hm.pluginsdk.enums.WsMessageType;
import com.hm.pluginsdk.listeners.CloudOperationListener;
import com.hm.pluginsdk.listeners.OnSendWsMessageListener;
import com.hm.qa.sdkdemoserver.inst.GameInstance;
import com.hm.qa.sdkdemoserver.inst.WaitingInstance;
import com.hm.qa.sdkdemoserver.inst.bean.GameResult;
import com.hm.qa.sdkdemoserver.inst.bean.PlayerError;
import com.hm.qa.sdkdemoserver.inst.bean.PlayerStatus;
import com.hm.pluginsdk.Constants;
import com.hm.pluginsdk.HmcpVideoView;
import com.hm.pluginsdk.beans.UserInfo;
import com.hm.pluginsdk.enums.CloudPlayerKeyboardStatus;
import com.hm.pluginsdk.enums.NetWorkState;
import com.hm.pluginsdk.enums.ScreenOrientation;
import com.hm.pluginsdk.listeners.HmcpPlayerListener;
import com.hm.pluginsdk.utils.CryptoUtils;
import com.hm.pluginsdk.utils.StatusCallbackUtil;
import com.hm.qa.sdkdemoserver.inst.bean.WaitResult;
import com.hm.qa.sdkdemoserver.inst.bean.WsMessageData;
import com.hm.qa.sdkdemoserver.inst.enums.MethodMessageEnum;
import com.hm.qa.sdkdemoserver.stub.Log;
import com.hm.qa.sdkdemoserver.utils.JacocoUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class PlayerActivity extends Activity implements HmcpPlayerListener, OnSendWsMessageListener, CloudOperationListener {
    private HmcpVideoView hmcpVideoView;

    private OnSendWsMessageListener wsMessageListener = null;
    private Handler handler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_player);

        hmcpVideoView = new HmcpVideoView(this);
        View view = hmcpVideoView.getRealView();
        if(view != null){
            hmcpVideoView.setCloudOperationListener(this);
        }
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull android.os.Message msg) {
                Log.i("handleMessage: " + msg.what);
                try{
                    switch(msg.what){
                        case MethodMessageEnum.WsMessage:
                            onWsMessageCall((WsMessageData) msg.obj);
                            break;
                    }
                }catch (InterruptedException e){
                    Log.e("handleMessage error: "+e.getMessage());
                }
                return false;
            }
        });
        GameInstance.inst().setActivity(this);
        GameInstance.inst().setHandler(handler);
        GameInstance.inst().setHmcpVideoView(hmcpVideoView);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        FrameLayout layout = (FrameLayout) findViewById(R.id.fl_container);
        if(hmcpVideoView.getRealView() != null){
            layout.addView(hmcpVideoView.getRealView(), layoutParams);
        }
        updateScreenOrientation();
        UserInfo userInfo = new UserInfo();
        userInfo.userType = 0;
        userInfo.userLevel = 0;
        userInfo.userId = GameInstance.inst().getUserInfo().getUserId();
        userInfo.userToken = GameInstance.inst().getUserInfo().getUserId();
        hmcpVideoView.setUserInfo(userInfo);
        hmcpVideoView.setConfigInfo("");
        startPlayer();
    }

    private void onWsMessageCall(WsMessageData message) throws InterruptedException {
        String payload = message.getPayload();
        WsMessageType msgType = message.getMsgType();

        WaitingInstance.setWaitResult(null);
        Log.i(msgType +":"+ payload);
        hmcpVideoView.sendWsMessage(payload, msgType, this);
    }

    protected  void updateScreenOrientation(){
        if(GameInstance.inst().getGameInfo().getPortrait() == true){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    protected void setFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        if (Build.VERSION.SDK_INT > 28){
            // 延伸显示区域到刘海
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        //getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(this);
    }

    protected void startPlayer(){
        UserInfo mUserInfo = new UserInfo();
        mUserInfo.userId = GameInstance.inst().getUserInfo().getUserId();
        mUserInfo.userToken = GameInstance.inst().getUserInfo().getUserId();
        mUserInfo.userType = 0;

        hmcpVideoView.setUserInfo(mUserInfo);

        boolean isPortraitOrientation = GameInstance.inst().getGameInfo().getPortrait(); // 是否竖屏
        ScreenOrientation orientaion = isPortraitOrientation ? ScreenOrientation.PORTRAIT :
                ScreenOrientation.LANDSCAPE;
        Long playTime = GameInstance.inst().getGameInfo().getPlayTime() * 1000;
        int priority = GameInstance.inst().getGameInfo().getPriority();
        String packageName = GameInstance.inst().getGameInfo().getPackageName();
        String appChannel = GameInstance.inst().getUserInfo().getChannelId();
        String accessKeyID = GameInstance.inst().getUserInfo().getBid();

        String raw = mUserInfo.userId + mUserInfo.userToken + packageName + accessKeyID + appChannel;
        String accessKey = GameInstance.inst().getUserInfo().getAccessKey();
        String cToken = CryptoUtils.generateCToken(raw, accessKey);

        // 传⼊参数
        Bundle gbundle = new Bundle();
        gbundle.putSerializable(HmcpVideoView.ORIENTATION, orientaion);
        if(GameInstance.inst().getGameInfo().getLongPlayTime() == true){
            gbundle.putLong(HmcpVideoView.PLAY_TIME, playTime);
        }else{
            gbundle.putInt(HmcpVideoView.PLAY_TIME, playTime.intValue());
        }
        gbundle.putInt(HmcpVideoView.PRIORITY, priority);
        gbundle.putString(HmcpVideoView.APP_NAME, packageName);
        gbundle.putString(HmcpVideoView.APP_CHANNEL, appChannel);
        gbundle.putString(HmcpVideoView.C_TOKEN, cToken);
        gbundle.putString(HmcpVideoView.EXTRA_ID, "extraIDFromAndroidDemo");
        gbundle.putString(HmcpVideoView.PAY_PROTO_DATA, "");

        // 采集fps周期及上报周期，单位为s
        gbundle.putInt(HmcpVideoView.FPS_PERIOD, 1);
        // 采集带宽的周期及上报周期，单位为s
        gbundle.putInt(HmcpVideoView.BAND_WIDTH_PERIOD, 5);
        // 采集带宽周期内的最大的几个值
        gbundle.putInt(HmcpVideoView.BAND_WIDTH_PEAK, 3);
        // 采集解码时间的周期及上报周期，单位为秒s
        gbundle.putInt(HmcpVideoView.DECODE_TIME_PERIOD, 1);
        if(GameInstance.inst().getGameInfo().getArchive() != null){
            gbundle.putBoolean(HmcpVideoView.ARCHIVED, GameInstance.inst().getGameInfo().getArchive());
        }
        if(GameInstance.inst().getGameInfo().getProtoData() != null){
            gbundle.putString(HmcpVideoView.PAY_PROTO_DATA, GameInstance.inst().getGameInfo().getProtoData());
        }
        if(GameInstance.inst().getGameInfo().getFpsPeriod() != null){
            gbundle.putInt(HmcpVideoView.FPS_PERIOD, GameInstance.inst().getGameInfo().getFpsPeriod());
        }
        if(GameInstance.inst().getGameInfo().getBandWidthPeriod() != null){
            gbundle.putInt(hmcpVideoView.BAND_WIDTH_PERIOD, GameInstance.inst().getGameInfo().getBandWidthPeriod());
        }
        if(GameInstance.inst().getGameInfo().getBandWidthPeak() != null){
            gbundle.putInt(hmcpVideoView.BAND_WIDTH_PEAK, GameInstance.inst().getGameInfo().getBandWidthPeak());
        }
        if(GameInstance.inst().getGameInfo().getDecodeTimePeriod() != null){
            gbundle.putInt(hmcpVideoView.DECODE_TIME_PERIOD, GameInstance.inst().getGameInfo().getDecodeTimePeriod());
        }
        if(GameInstance.inst().getGameInfo().getWidth() != null){
            gbundle.putInt(HmcpVideoView.VIEW_RESOLUTION_WIDTH, GameInstance.inst().getGameInfo().getWidth());
        }
        if(GameInstance.inst().getGameInfo().getHeight() != null){
            gbundle.putInt(HmcpVideoView.VIEW_RESOLUTION_HEIGHT, GameInstance.inst().getGameInfo().getHeight());
        }
        if(GameInstance.inst().getGameInfo().getStreamType() != null){
            gbundle.putInt(HmcpVideoView.STREAM_TYPE, GameInstance.inst().getGameInfo().getStreamType());
        }
        if(GameInstance.inst().getGameInfo().getDecodeType() != null){
            gbundle.putInt(HmcpVideoView.DECODE_TYPE, GameInstance.inst().getGameInfo().getDecodeType());
        }
        if(GameInstance.inst().getGameInfo().getSpeed() != null){
            gbundle.putInt(hmcpVideoView.INTERNET_SPEED, GameInstance.inst().getGameInfo().getSpeed());
        }
        if(GameInstance.inst().getGameInfo().getInterfaceId() != null){
            gbundle.putInt(HmcpVideoView.DEMO_TEST_INSTANCE_ID, GameInstance.inst().getGameInfo().getInterfaceId());
        }
        if(GameInstance.inst().getGameInfo().getInstanceId() != null){
            gbundle.putInt(HmcpVideoView.DEMO_TEST_INSTANCE_ID, GameInstance.inst().getGameInfo().getInstanceId());
        }
        if(GameInstance.inst().getGameInfo().getCid() != null){
            gbundle.putString(HmcpVideoView.C_ID, GameInstance.inst().getGameInfo().getCid());
        }
        if(GameInstance.inst().getGameInfo().getNoInputLimitTime() != null){
            gbundle.putInt(HmcpVideoView.NO_INPUT_LIMIT_TIME, GameInstance.inst().getGameInfo().getNoInputLimitTime());
        }
        if(GameInstance.inst().getGameInfo().getCidCacheInterval() != null){
            gbundle.putLong(HmcpVideoView.CID_CACHE_INTERVAL, GameInstance.inst().getGameInfo().getCidCacheInterval());
        }
        if(GameInstance.inst().getGameInfo().getClientISP() != null){
            gbundle.putString(HmcpVideoView.CLIENT_ISP, GameInstance.inst().getGameInfo().getClientISP());
        }
        if(GameInstance.inst().getGameInfo().getClientProvince() != null){
            gbundle.putString(HmcpVideoView.CLIENT_PROVINCE, GameInstance.inst().getGameInfo().getClientProvince());
        }
        if(GameInstance.inst().getGameInfo().getClientCity() != null){
            gbundle.putString(HmcpVideoView.CLIENT_CITY, GameInstance.inst().getGameInfo().getClientCity());
        }
        if(GameInstance.inst().getGameInfo().getIpv6() != null){
            gbundle.putBoolean(HmcpVideoView.ALLOW_COMPATIBLE_IPV6, GameInstance.inst().getGameInfo().getIpv6());
        }
        Log.i("play: "+gbundle.toString());
        hmcpVideoView.setHmcpPlayerListener(this);
        hmcpVideoView.play(gbundle);
    }
    @Override
    protected void onStart(){
        super.onStart();
        hmcpVideoView.onStart();
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        hmcpVideoView.onRestart(-1);
    }
    @Override
    protected  void onResume(){
        super.onResume();
        hmcpVideoView.onResume();
        GameInstance.inst().setHandler(handler);
        GameInstance.inst().setActivity(this);
        GameInstance.inst().setHmcpVideoView(hmcpVideoView);
    }
    @Override
    protected void onPause(){
        super.onPause();
        hmcpVideoView.onPause();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (hmcpVideoView!=null){
            hmcpVideoView.onDestroy();
            hmcpVideoView = null;
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        hmcpVideoView.onStop();
    }
    @Override
    public void onError(int var1, String var2) {
        Log.i(String.format("onError: %d, %s", var1, var2));
        GameResult gameResult = GameInstance.inst().getGameResult();
        gameResult.setResult(false);
        gameResult.setRunning(false);
        gameResult.setCode(var1);
        gameResult.setMessage(var2);
    }

    @Override
    public void onSuccess() {
        hmcpVideoView.setAttachContext(this);
    }

    @Override
    public void onExitQueue() {
        this.finish();
    }


    @Override
    public void onMessage(Message var1) {
        Log.d(String.format("onMessage: %s", var1));
        GameInstance.inst().getGameProcessInfo().addMessages(var1);
    }

    @Override
    public void onSceneChanged(String var1) {
        Log.d(String.format("onSceneChanged: %s", var1));
        GameInstance.inst().getGameProcessInfo().addSceneChanges(var1);
    }

    @Override
    public void onNetworkChanged(NetWorkState var1) {
        Log.d(String.format("onNetworkChanged: %s", var1));
        GameInstance.inst().getGameProcessInfo().addNetworkStat(var1);
    }

    @Override
    public void onPlayStatus(int var1, long var2, String var4) {
    }

    @Override
    public void HmcpPlayerStatusCallback(String callback) {
        try {
            JSONObject jsonObject = new JSONObject(callback);
            Integer statusCode = jsonObject.getInt(StatusCallbackUtil.STATUS);
            Log.d("HmcpPlayerStatusCallback,"+jsonObject.toString());

            PlayerStatus status = new PlayerStatus();
            status.setStatus(statusCode);
            status.setData(jsonObject.getString(StatusCallbackUtil.DATA));
            GameInstance.inst().getGameProcessInfo().addPlayerStatuses(status);
            switch (statusCode) {
                case Constants.STATUS_PLAY_INTERNAL:
                    hmcpVideoView.play();
                    break;
                case Constants.STATUS_FIRST_FRAME_ARRIVAL:
                    GameResult gameResult = GameInstance.inst().getGameResult();
                    gameResult.setRunning(true);
                    gameResult.setResult(true);
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPlayerError(String var1, String var2) {
        GameInstance.inst().getGameProcessInfo().setPlayerError(new PlayerError(var1, var2));
    }

    @Override
    public void onInputMessage(String var1) {
        Log.i("onInputMessage: "+var1 );
    }

    @Override
    public void onInputDevice(int var1, int var2) {
        Log.i("onInputDevice: "+var1 +","+ var2);
    }

    @Override
    public void onPermissionNotGranted(String var1) {
        Log.d("onPermissionNotGranted: "+var1);
        GameInstance.inst().getGameProcessInfo().addPermission(var1);
    }

    @Override
    public void onCloudDeviceStatus(String status) {
        Log.d("onCloudDeviceStatus: "+status);
    }

    @Override
    public void onInterceptIntent(String intentData) {
        Log.d("onInterceptIntent: "+intentData);
        GameInstance.inst().getGameProcessInfo().addIntent(intentData);
    }

    @Override
    public void onCloudPlayerKeyboardStatusChanged(CloudPlayerKeyboardStatus status) {

    }

    @Override
    public void sendWsMessageSuccess() {
        Log.i("sendWsMessageSuccess");
        WaitResult waitResult = new WaitResult();
        waitResult.setResult(true);
        waitResult.setMessage("");
        WaitingInstance.setWaitResult(waitResult);
    }

    @Override
    public void sendWsMessageFail(String msg) {
        Log.i("sendWsMessageFail:"+msg);
        WaitResult waitResult = new WaitResult();
        waitResult.setResult(false);
        waitResult.setMessage(msg);
        WaitingInstance.setWaitResult(waitResult);
    }

    @Override
    public void onSuccess(CloudOperation operation, CloudFile file) {
    }

    @Override
    public void onFinish(CloudOperation operation) {

    }

    @Override
    public void onStop(CloudOperation operation, String message) {

    }

    @Override
    public void onError(CloudOperation operation, String message) {

    }

    @Override
    public void onCancel(CloudOperation operation) {

    }
}