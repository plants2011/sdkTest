package com.hm.qa.sdkdemoserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.hm.qa.sdkdemoserver.inst.GameInstance;
import com.hm.pluginsdk.PluginInitCallback;
import com.hm.pluginsdk.PluginInitResult;
import com.hm.pluginsdk.PluginManager;
import com.hm.pluginsdk.utils.FileUtils;
import com.hm.qa.sdkdemoserver.stub.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private void requestMyPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||  ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {
            Log.d("requestMyPermissions: 有SD权限");
            loadAndInstallPlugin();
        }

    }
    @Override
    protected void onResume() {
        GameInstance.inst().setActivity(this);
        super.onResume();
    }
//    private void startJacocoReportService(){
//        Log.i("startJacocoReport");
//        Intent intent = new Intent(MainActivity.this, JacocoJobService.class);
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
//            ComponentName result = startForegroundService(intent);
//            Log.i("result="+result);
//        }else{
//            startService(intent);
//        }
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameInstance.inst().setActivity(this);
        this.requestMyPermissions();

        //启动后端服务
        final Intent intentHttp = new Intent(MainActivity.this, HttpServerService.class);
        startService(intentHttp);

//        //启动Jacoco覆盖率
//        startJacocoReportService();
//storage/emulated/0/plugin.apk
        ///sdcard/plugin.apk

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length>0){
            for (int grantResult : grantResults) {
                if (grantResult!=PackageManager.PERMISSION_GRANTED){
                    Log.e("onRequestPermissionsResult: NO Permissions");
                    break;
                }
            }
            Log.e("onRequestPermissionsResult: has Permissions");
            loadAndInstallPlugin();

        }else{
            Log.e("onRequestPermissionsResult: NO Permissions");
        }

    }

    private void loadAndInstallPlugin() {
        File srcFile = new File("/sdcard/plugin.apk");
        File apkFile = new File(this.getCacheDir()+File.separator+"/plugin.apk");

        FileUtils.copyFiles(this.getApplicationContext(), srcFile, apkFile);
        PluginManager.getInstance().install(getApplicationContext(), apkFile, new PluginInitCallback() {
            @Override
            public void onInit(PluginInitResult result) {
                Toast.makeText(getApplicationContext(), result.getMsg(), Toast.LENGTH_SHORT).show();
                if(result.getCode() == PluginInitResult.PLUGIN_FILE_INIT_SUCCESS.getCode()){
                    PluginManager.getInstance().loadPlugin(getApplicationContext(), apkFile);
                }
            }
        });
    }
}
