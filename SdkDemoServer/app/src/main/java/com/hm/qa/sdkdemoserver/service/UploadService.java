package com.hm.qa.sdkdemoserver.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.hm.qa.sdkdemoserver.Constant;
import com.hm.qa.sdkdemoserver.utils.JacocoUtil;

public class UploadService extends IntentService {
    private String TAG = "UploadService";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public UploadService(String name) {
        super(name);
    }

    public UploadService() {
        super("sup");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String result =
                JacocoUtil.execUpload(Constant.JACOCO_REPORT_ADDRESS, Constant.JACOCO_REPORT_PORT);
        Log.d("UploadService", "result:" + result + ":time=" + System.currentTimeMillis());
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationChannel channel = null;
//            channel = new NotificationChannel(Constant.mChannelID, "JacocoUpload", NotificationManager.IMPORTANCE_HIGH);
//            notificationManager.createNotificationChannel(channel);
//            Notification notification = new NotificationCompat.
//                    Builder(getApplicationContext(), TAG).
//                    setContentText("jacoco").setContentTitle("jacocoRunning")
//                    .build();
//            startForeground(222, notification);
//        }
    }
}