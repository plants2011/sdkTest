package com.hm.qa.sdkdemoserver;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;
import androidx.annotation.RequiresApi;

import com.hm.qa.sdkdemoserver.inst.GameInstance;
import com.hm.pluginsdk.HmcpManager;

public class HmApplication extends Application {
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化云玩sdk
        final HmcpManager manager = HmcpManager.getInstance();
        GameInstance.inst().setHmcpManager(manager);
        GameInstance.inst().setApplication(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
