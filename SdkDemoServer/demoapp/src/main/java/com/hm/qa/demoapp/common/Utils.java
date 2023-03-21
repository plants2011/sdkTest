package com.hm.qa.demoapp.common;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utils {

    public static Intent data;

    // 时间字符串
    private static SimpleDateFormat sSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // 文件名格式的时间字符串
    private static SimpleDateFormat sSimpleDateFormat2 = new SimpleDateFormat("yyyyMMdd_HHmmss");

    public static String getDateString() {
        return sSimpleDateFormat.format(new Date());
    }

    public static String getDateString2() {
        return sSimpleDateFormat2.format(new Date());
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        return outMetrics;
    }

    public static String getMP4FilePath(Context context) {
        // 内部私有目录
        // File file = new File(context.getCacheDir(), getDateString() + ".mp4");
        // 外部专属目录
        File file = new File(context.getExternalCacheDir(), getDateString2() + ".mp4");
        // 外部共有目录
        // Android 10 /storage/emulated/0/DCIM存在，但不可读、不可写
        // File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), getDateString() + ".mp4");
        if (file != null) {
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        return file.getAbsolutePath();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
