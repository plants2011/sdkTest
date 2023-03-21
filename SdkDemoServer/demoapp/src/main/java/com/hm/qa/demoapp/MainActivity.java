package com.hm.qa.demoapp;

import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.hm.qa.demoapp.adapter.MyAdapter;
import com.hm.qa.demoapp.bean.DataBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //声明找到listview
        ListView listView = findViewById(R.id.list_view);

        //然后我们需要一个数据源   来添加10条数据
        List<DataBean> mlist = new ArrayList<>();
        mlist.add(new DataBean("点击测试","测试位置点击"));
        mlist.add(new DataBean("输入测试", "测试输入框输入，弹出或者隐藏键盘或者本地键盘"));
        mlist.add(new DataBean("录音测试", "测试录音和播放录音"));
        mlist.add(new DataBean("截屏/录屏测试", "测试截屏功能和截屏同步到本地的功能"));
        mlist.add(new DataBean("打开图库","打开系统图库并查看图片"));
        mlist.add(new DataBean("摄像头测试","打开摄像头检测视频"));
        mlist.add(new DataBean("横屏测试","横屏显示activity"));
        mlist.add(new DataBean("亮度测试","通过滑块设置屏幕亮度"));
        mlist.add(new DataBean("传感器测试","查看传感器数据"));

        //有了数据之后我们需要给ListView 一个适配器，就是用来适配数据的
        MyAdapter myAdapter =  new MyAdapter(MainActivity.this,mlist);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(myAdapter);
    }
}