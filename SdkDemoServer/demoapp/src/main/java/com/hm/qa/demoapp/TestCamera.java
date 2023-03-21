package com.hm.qa.demoapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.File;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileNotFoundException;
import java.io.IOException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.content.FileProvider;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import android.widget.VideoView;
import android.widget.MediaController;


public class TestCamera extends AppCompatActivity {
    public static final int TAKE_PHOTO = 1;
    public static final int TAKE_VIDEO = 2;
    private ImageView picture;
    private Uri imageUri;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_camera);

        Button photoBtn = (Button) findViewById(R.id.photo);
        Button videoBtn = (Button) findViewById(R.id.video);

        picture = findViewById(R.id.picture);
        videoView = findViewById(R.id.videoView );

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                //判断图片是否存在，存在则删除在创建，不存在则直接创建
                try
                {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //判断运行设备的系统版本是否低于Android7.0
                if (Build.VERSION.SDK_INT >= 24)
                {
                    imageUri = FileProvider.getUriForFile(TestCamera.this,
                            "com.example.cameraalbumtest.fileprovider", outputImage);

                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                //使用隐示的Intent，调用摄像头，并把它存储
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                File outputImage = new File(getExternalCacheDir(), "output_video.mp4");
                //判断是否存在，存在则删除在创建，不存在则直接创建
                try
                {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //判断运行设备的系统版本是否低于Android7.0
                if (Build.VERSION.SDK_INT >= 24)
                {
                    imageUri = FileProvider.getUriForFile(TestCamera.this,
                            "com.example.cameraalbumtest.fileprovider", outputImage);

                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                //使用隐示的Intent，调用摄像头，并把它存储
                Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_VIDEO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //拍照完成后返回调用
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
                //
//                picture.setVisibility(4);
                //将拍摄的照片显示出来
                if(resultCode == RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            case TAKE_VIDEO:
                //
//                picture.setVisibility(4);

                //显示视频
                play_mp4();
            default:
                break;
        }
    }

    private void play_mp4(){
        String videoUrl1 = "/storage/emulated/0/Android/data/com.hm.qa.demoapp/cache/output_video.mp4";   // 或者 file:///storage/emulated/0/test.mp4
        Uri uri = Uri.parse( videoUrl1 );

        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.start();
    }
}
