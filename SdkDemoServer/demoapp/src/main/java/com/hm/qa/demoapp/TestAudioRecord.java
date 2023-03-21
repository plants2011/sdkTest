package com.hm.qa.demoapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.hm.qa.demoapp.common.AudioRecorder;

public class TestAudioRecord extends AppCompatActivity {

    private Button recordBtn, playBtn;
    private AudioRecorder audioRecorder = new AudioRecorder();

    private static final int MY_PERMISSION_REQUEST_CODE = 10000;
    private static final String[] allPermissions = new String[] {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false;
            }
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_audio_record);

        boolean isAllGranted = checkPermissionAllGranted(allPermissions);
        if(!isAllGranted){
            ActivityCompat.requestPermissions(this, allPermissions, MY_PERMISSION_REQUEST_CODE);
        }

        recordBtn = (Button) findViewById(R.id.audioRecord);
        playBtn = (Button) findViewById(R.id.audioPlay);

        this.onButtonsListener();

        Button backHomeBtn = (Button) findViewById(R.id.backHome);
        backHomeBtn.setOnClickListener(new BackHomeListener());

    }
    private void onButtonsListener(){
        Context thisView = this;
        recordBtn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    //按下
                    audioRecorder.startRecord();
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    //抬起
                    audioRecorder.stopRecord();
                }
                return false;
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                audioRecorder.play(thisView);
            }
        });
    }
}