package com.hm.qa.demoapp;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.location.LocationManager;
import android.location.LocationListener;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class TestSenser extends AppCompatActivity {

    private TextView gpsView;
    private String TAG = getClass().getSimpleName();
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_senser);

        Button backHomeBtn = (Button) findViewById(R.id.backHome);
        backHomeBtn.setOnClickListener(new BackHomeListener());

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏显示

        gpsView = (TextView) findViewById(R.id.gpsData);
//        gpsView.setText("666");

        // 要申请的权限 数组 可以同时申请多个权限
        String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION};
        int check = ContextCompat.checkSelfPermission(this, permissions[0]);
        if (!(check == PackageManager.PERMISSION_GRANTED)) {
            //写入你需要权限才能使用的方法
            Toast.makeText(TestSenser.this, "开启定位权限和GPS后重启app", Toast.LENGTH_LONG).show();
            gpsView.setText("开启定位权限和GPS后重启app");
//                ActivityCompat.requestPermissions(TestSenser.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_CODE);
        }

        // 要申请的权限 数组 可以同时申请多个权限
//        String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION};
//        if (Build.VERSION.SDK_INT >= 23) {
//            //如果超过6.0才需要动态权限，否则不需要动态权限
//            //如果同时申请多个权限，可以for循环遍历
//            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
//            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
//            if (!(check == PackageManager.PERMISSION_GRANTED)) {
//                //写入你需要权限才能使用的方法
//                Toast.makeText(TestSenser.this, "开启定位权限和GPS后重启app", Toast.LENGTH_LONG).show();
//                ActivityCompat.requestPermissions(TestSenser.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_CODE);
//            }
//        }

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        //获得定位信息
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
//            Log.i(TAG, String.valueOf(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED));
            return ;
        }

        //获得定位信息
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,//指定GPS定位的提供者
                1000,//间隔时间
                1,//位置间隔1米
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                }
        );
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);//获取最新的定位信息
        locationUpdates(location);//将获取到的定位信息传递到Location当中
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE){
//            Log.i(TAG, String.valueOf(permissions));
            if (permissions[0].equals(Manifest.permission.DELETE_CACHE_FILES)
                    &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //用户同意使用write
                Log.i(TAG, "同意权限");

            }else{
                //用户不同意，自行处理即可
                Log.i(TAG, "不同意权限");
            }
        }
    }

    public void locationUpdates(Location location){
        Log.i(TAG, "locationUpdates");
        if(location!=null){
            StringBuilder stringBuilder = new StringBuilder();//创建一个字符串构建器，用于记录定位信息
            stringBuilder.append("当前的位置信息：\n");
            stringBuilder.append("经度：" + location.getLongitude() + "\n");
            stringBuilder.append("纬度：" + location.getLatitude() + "\n");
            stringBuilder.append("高度：" + location.getAltitude() + "\n");
            stringBuilder.append("速度：" + location.getSpeed() + "\n");
            stringBuilder.append("方向：" + location.getBearing() + "\n");
            stringBuilder.append("定位精度：" + location.getAccuracy() + "\n");
            gpsView.setText(stringBuilder.toString());//显示到页面上
//            gpsView.setText("44444444444");//显示到页面上
        }else{
            gpsView.setText("没有获取到gps信息");
        }
    }

}