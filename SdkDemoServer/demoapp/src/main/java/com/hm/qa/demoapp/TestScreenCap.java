package com.hm.qa.demoapp;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.hm.qa.demoapp.MainActivity;

import com.hm.qa.demoapp.common.Utils;
import com.hm.qa.demoapp.common.ScreenRecorderService;



public class TestScreenCap extends AppCompatActivity {

    private String TAG = "SCREEN_CAP";
    private TextView mTvTime;
    private MediaProjectionManager mMediaProjectionManager;
    private static final int request_code_permission = 110;
    private static final int request_code_media_projection = 120;
    private ScreenRecorderService.ScreenRecorderBinder mRecorderBinder;

    private String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_screen_cap);

        Button backHomeBtn = (Button) findViewById(R.id.backHome);

        Button startRecordeBtn = (Button) findViewById(R.id.startRecorde);
        Button stopRecordeBtn = (Button) findViewById(R.id.stopRecorde);

        // 动态权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i(TAG, "动态权限: ");
            requestPermissions(permissions, request_code_permission);
        }

        //开始录屏
        startRecordeBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v){
                Log.i(TAG, "点击开始录屏: ");
                startRecord();
            }
        });

        //结束录屏
        stopRecordeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.i(TAG, "点击结束录屏: ");
                stopRecord();
            }
        });

        // 子线程时间计数器
        mTvTime = findViewById(R.id.tv_time);
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> {
                    mTvTime.setText(Utils.getDateString());
                });
            }
        }).start();

        backHomeBtn.setOnClickListener(new BackHomeListener());
        this.onBtnInit();
    }
    /**
     * 截取全屏
     * @return
     */
    public Bitmap captureScreenWindow() {
        getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = getWindow().getDecorView().getDrawingCache();
        return bmp;
    }

    public void saveBitmapForSdCard(Activity context, String bitName, Bitmap mBitmap) {
        String local_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Sky";
        File appDir = new File(local_path);
        //判断不存在就创建
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        //创建file对象
        File f = new File(local_path +"/" + bitName + ".png");
        Log.d(TAG,"start create file: "+local_path+"/"+bitName+".png");
        try {
            //创建
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR: "+e.getMessage());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR: "+e.getMessage());
        }
        //原封不动的保存在内存卡上
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR: "+e.getMessage());
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR: "+e.getMessage());
        }
        //
        Toast.makeText(context, "截屏成功，保存在: "+f.getAbsolutePath(), Toast.LENGTH_LONG).show();
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    f.getAbsolutePath(), bitName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR: "+e.getMessage());
        }
        // 最后通知图库更新
        String path = Environment.getExternalStorageDirectory().getPath();
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        Toast.makeText(context, "截屏成功，请在相册中查看！", Toast.LENGTH_LONG).show();
        Log.i(TAG, "screen cap SUCCESS: ");
    }

    private void onBtnInit(){
        Activity thisView = this;
        Button screenCapBtn = (Button) findViewById(R.id.screenCapBtn);
        screenCapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bmp = captureScreenWindow();
                saveBitmapForSdCard(thisView, "test_img", bmp);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startRecord() {
        Log.i(TAG, "进入startRecord函数");
        if (Utils.data != null) {
            Log.i(TAG, "Utils.data != null；进入startScreenRecordService");
            startScreenRecordService(Utils.data);
            return;
        }

        mMediaProjectionManager = (MediaProjectionManager) getApplicationContext().getSystemService(MEDIA_PROJECTION_SERVICE);
        startActivityForResult(mMediaProjectionManager.createScreenCaptureIntent(), request_code_media_projection);



//        Toast.makeText(getApplicationContext(), "开始录屏", Toast.LENGTH_LONG).show();

    }



    private void stopRecord() {
        if (mRecorderBinder != null) {
            Log.i(TAG, "mRecorderBinder != null；进入stopRecord");
            mRecorderBinder.stopRecord();
        }
        Button startRecordeBtn = (Button) findViewById(R.id.startRecorde);
        startRecordeBtn.setBackgroundColor(Color.rgb(98, 0, 238));

        Toast.makeText(getApplicationContext(), "结束录屏", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_code_media_projection) {
            if (resultCode == RESULT_OK) {
                Log.i(TAG, "onActivityResult -- startScreenRecordService");
                startScreenRecordService(data);
            }
        }
    }

    private void startScreenRecordService(Intent data) {
        Log.i(TAG, "startScreenRecordService");
        // 申请通过就保存在内存里，避免每次都申请，Intent只能内存序列化，不能持久化 todo
        Utils.data = data;

        Intent intent = new Intent(TestScreenCap.this, ScreenRecorderService.class);
        intent.putExtra("data", data);
        //绑定服务
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected");
                mRecorderBinder = (ScreenRecorderService.ScreenRecorderBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected");
                mRecorderBinder = null;
            }
        }, Context.BIND_AUTO_CREATE);


        // 不仅bindService，还要startService，避免Activity退出引起Service退出
        // 适配 8.0+ 的前台服务
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.i(TAG, ">=");
            startForegroundService(intent);
        } else {
            startService(intent);
            Log.i(TAG, "else");
        }

        Button startRecordeBtn = (Button) findViewById(R.id.startRecorde);
        startRecordeBtn.setBackgroundColor(Color.RED);
    }
}