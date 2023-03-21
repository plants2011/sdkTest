package com.hm.qa.demoapp.common;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


import java.io.IOException;


public class ScreenRecorderService extends Service {
    private static final String TAG = ScreenRecorderService.class.getSimpleName();

    private MediaProjectionManager mMediaProjectionManager;
    private MediaProjection mMediaProjection;
    private MediaRecorder mMediaRecorder;
    private VirtualDisplay mVirtualDisplay;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ScreenRecorderBinder();
    }

    // 非静态内部类
    public class ScreenRecorderBinder extends Binder {
        public void stopRecord() {
            Log.i(TAG, "ScreenRecorderBinder-stopRecord");
            mMediaRecorder.stop();
//            showToast("stopRecord");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand1");
//        Log.d(TAG, "onStartCommand: intent=" + intent + " flags=" + flags + " startId=" + startId);

//        createNotificationChannel();

        initMediaRecorder();

        createVirtualDisplay(intent.getParcelableExtra("data"));

        return super.onStartCommand(intent, flags, startId);
    }

    // 用MediaRecorder的缓存Surface进行录制
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createVirtualDisplay(Intent intent) {
        DisplayMetrics outMetrics = Utils.getDisplayMetrics(getApplicationContext());

        mMediaProjectionManager = (MediaProjectionManager) getApplicationContext().getSystemService(MEDIA_PROJECTION_SERVICE);
        mMediaProjection = mMediaProjectionManager.getMediaProjection(Activity.RESULT_OK, intent);
        mVirtualDisplay = mMediaProjection.createVirtualDisplay(
                "ScreenRecorder", outMetrics.widthPixels, outMetrics.heightPixels, outMetrics.densityDpi,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR, mMediaRecorder.getSurface(), new VirtualDisplay.Callback() {
                    @Override
                    public void onPaused() {
                        Log.d(TAG, "onPaused: ");
                    }

                    @Override
                    public void onResumed() {
                        Log.d(TAG, "onResumed: ");
                    }

                    @Override
                    public void onStopped() {
                        Log.d(TAG, "onStopped: ");
                    }
                }, null);
    }

//    private void createNotificationChannel() {
//        Notification.Builder builder = new Notification.Builder(getApplicationContext())
//                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, TestScreenCap.class), 0)) // 设置PendingIntent
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("ScreenRecorder")
//                .setContentText("recording...")
//                .setAutoCancel(true)
//                .setWhen(System.currentTimeMillis());
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            builder.setChannelId("channelId");
//        }
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel("channelId", "notification_name", NotificationManager.IMPORTANCE_LOW);
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        Notification notification = builder.build();
//        notification.defaults = Notification.DEFAULT_SOUND;
//        startForeground(110, notification);
//    }

    public void initMediaRecorder() {
        Log.i(TAG, "initMediaRecorder: ");
        DisplayMetrics displayMetrics = Utils.getDisplayMetrics(getApplicationContext());
//        String mp4FilePath = Utils.getMP4FilePath(getApplicationContext());
        String mp4FilePath = "/storage/emulated/0/Android/data/com.hm.qa.demoapp/cache/record_video.mp4";
        Log.i(TAG, "mp4FilePath = " + mp4FilePath);
        showLongToast("文件路径=" + mp4FilePath);

        mMediaRecorder = new MediaRecorder();

        mMediaRecorder.setOnErrorListener((mr, what, extra) -> Log.e(TAG, "onError: what=" + what + " extra=" + extra));

        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 外置麦克风
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mMediaRecorder.setVideoSize(displayMetrics.widthPixels, displayMetrics.heightPixels);
        mMediaRecorder.setVideoEncodingBitRate(1024 * 1024); // 编码比特率
        mMediaRecorder.setVideoFrameRate(18); // 帧率，录制每秒捕获多少帧
        mMediaRecorder.setMaxDuration(30 * 1000);
        mMediaRecorder.setOrientationHint(0);

        // 设置路径、准备、开始
        mMediaRecorder.setOutputFile(mp4FilePath);
        try {
            mMediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaRecorder.start();
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void showLongToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}
