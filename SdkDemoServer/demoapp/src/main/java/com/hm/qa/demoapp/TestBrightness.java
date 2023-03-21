package com.hm.qa.demoapp;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

public class TestBrightness extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

    private TextView tv_progress;
    private SeekBar seekbar;
    private int Max_Brightness = 255;//进度条的最大值
    private float fBrightness = 0.0f;//亮度值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_brightness);

        Button backHomeBtn = (Button) findViewById(R.id.backHome);
        backHomeBtn.setOnClickListener(new BackHomeListener());

        seekbar = findViewById(R.id.brightnessSeekBar);
        tv_progress = findViewById(R.id.textView3);
        seekbar.setMax(Max_Brightness);

        fBrightness = getBrightness();
        tv_progress.setText("brightness："+String.valueOf(Math.round(fBrightness)));
        seekbar.setOnSeekBarChangeListener(new SeekBarChangedListener());
        seekbar.setProgress(Math.round(fBrightness));
    }

    /**
     * 获得当前亮度
     */
    private int getBrightness() {
        int brightness = 0;
        ContentResolver resolver = TestBrightness.this.getContentResolver();

        try {
            brightness = Settings.System.getInt(resolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        return brightness;
    }

    /**
     * 停止自动亮度调节,改为手动
     */
    public void stopAutoBrightness() {
        Settings.System.putInt(TestBrightness.this.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    /**
     * 改变App当前Window亮度
     *
     * @param brightness
     */
    public void changeAppBrightness(int brightness) {
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (brightness == -1) {
            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        } else {
            lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
        }
        window.setAttributes(lp);
    }


    class SeekBarChangedListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            changeAppBrightness(Math.round(progress));
            tv_progress.setText(String.valueOf(progress));
//            Log.d(TAG, String.valueOf(fBrightness));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }
    }


}