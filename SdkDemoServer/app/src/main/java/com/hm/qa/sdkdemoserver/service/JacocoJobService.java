package com.hm.qa.sdkdemoserver.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.hm.qa.sdkdemoserver.Constant;
import com.hm.qa.sdkdemoserver.stub.Log;

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2022/5/18
 * 描述: JacocoJobService 是为了定期触发Jacoco上报任务的
 * 历史: history<br/>
 * ================================================
 */
public class JacocoJobService extends Service {
    public final String TAG = this.getClass().getSimpleName();
    private final int MSG_WHAT_UPLOAD = 0x01;
    private Intent uploadIntent ;

    @SuppressLint("HandlerLeak")
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_WHAT_UPLOAD) {
                ComponentName result = null;
                result = startService(uploadIntent);
                Log.i("UploadService : result = " + result);
                mHandle.sendEmptyMessageDelayed(MSG_WHAT_UPLOAD, Constant.JACOCO_REPORT_INTERVAL);
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        mHandle.removeCallbacksAndMessages(null);
        mHandle = null;
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("JacocoJobService onCreate");
        uploadIntent = new Intent(getApplicationContext(), UploadService.class);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationChannel channel = null;
//            channel = new NotificationChannel(Constant.mChannelID, "JacocoUpload" , NotificationManager.IMPORTANCE_HIGH);
//            notificationManager.createNotificationChannel(channel);
//            Notification notification = new Notification.Builder(getApplicationContext(), TAG).build();
//            startForeground(Constant.START_FOREGROUND_NOTIFICATION_ID, notification);
//        }
        mHandle.sendEmptyMessage(MSG_WHAT_UPLOAD);
    }

}