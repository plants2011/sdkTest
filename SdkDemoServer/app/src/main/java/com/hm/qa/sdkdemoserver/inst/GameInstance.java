package com.hm.qa.sdkdemoserver.inst;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;

import com.hm.pluginsdk.listeners.OnSendWsMessageListener;
import com.hm.qa.sdkdemoserver.inst.bean.GameProcessInfo;
import com.hm.qa.sdkdemoserver.inst.bean.GameResult;
import com.hm.pluginsdk.HmcpManager;
import com.hm.pluginsdk.HmcpVideoView;
import com.hm.qa.sdkdemoserver.inst.bean.GameBaseInfo;
import com.hm.qa.sdkdemoserver.inst.bean.UserBaseInfo;
import com.hm.qa.sdkdemoserver.inst.bean.WaitResult;

public class GameInstance {
    private static GameInstance instance = new GameInstance();
    private GameInstance(){}
    public static GameInstance inst(){
        return instance;
    }

    //---
    private Application application; //当前应用
    private Activity activity; //当前activity
    private Handler handler;


    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    //-------
    private HmcpVideoView hmcpVideoView;
    private HmcpManager hmcpManager;

    public HmcpManager getHmcpManager() {
        return hmcpManager;
    }

    public void setHmcpManager(HmcpManager hmcpManager) {
        this.hmcpManager = hmcpManager;
    }

    public HmcpVideoView getHmcpVideoView() {
        return hmcpVideoView;
    }

    public void setHmcpVideoView(HmcpVideoView hmcpVideoView) {
        this.hmcpVideoView = hmcpVideoView;
    }

    //--------
    private UserBaseInfo userInfo;
    private GameBaseInfo gameInfo;
    private GameProcessInfo gameProcessInfo;
    private GameResult gameResult;

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public GameProcessInfo getGameProcessInfo() {
        return gameProcessInfo;
    }

    public void setGameProcessInfo(GameProcessInfo gameProcessInfo) {
        this.gameProcessInfo = gameProcessInfo;
    }

    public UserBaseInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserBaseInfo userInfo) {
        this.userInfo = userInfo;
    }

    public GameBaseInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameBaseInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}

