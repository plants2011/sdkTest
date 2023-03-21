package com.hm.qa.demoapp.common;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class AudioRecorder {
    private String TAG="AudioCapturer";
    private String fileName = "audio_record.amr";
    private File recordFile = null;
    private MediaRecorder recorder = null;

    public AudioRecorder(){
    }

    public void startRecord(){
        Log.d(TAG, "start record");
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try{
            recordFile = new File(Environment.getExternalStorageDirectory(), fileName);
            recorder.setOutputFile(recordFile.getAbsolutePath());
            recorder.prepare();
            recorder.start();
            Log.d(TAG,"recording at "+recordFile.getAbsolutePath());
        } catch (IOException e) {
            Log.e(TAG, "Exception: "+e.toString());
        }
    }
    public void stopRecord(){
        if(recorder!=null){
            Log.d(TAG, "stop record");
            recorder.stop();
            recorder.release();
            Log.d(TAG, "stop completed");
            recorder=null;
        }
    }
    public void play(Context context){
        if(recordFile == null){
            return;
        }
        Log.d(TAG, "start play"+recordFile.getAbsolutePath());
        if(recordFile.exists() == false){
            Log.d(TAG, recordFile.getAbsolutePath() + "is not exist");
            return;
        }
        MediaPlayer mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(recordFile.getAbsolutePath());
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.i(TAG, "playVoice: "+e.toString());
        }
    }
}
