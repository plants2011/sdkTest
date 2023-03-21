package com.hm.qa.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.hm.qa.demoapp.*;
import com.hm.qa.demoapp.bean.DataBean;

import java.util.List;


public class MyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Context mContext;
    private List<DataBean> mlist;

    public MyAdapter(Context mContext, List<DataBean> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
    }


    /**
     * ListView 的条目个数，也就是我们需要展示的数据个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return mlist == null ? 0 : mlist.size();
    }

    /**
     * item(条目) 所对应的数据
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 给item 填充数据，
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //在填充数据之前，我们需要一个布局，就是item的布局，以微信的联系人列表为例，写一个布局文件   item_list_layout.xml
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_layout, null);
            myViewHolder.tvName = convertView.findViewById(R.id.tv_item_name);
            myViewHolder.tvDescription = convertView.findViewById(R.id.tv_item_description);
            convertView.setTag(myViewHolder);

        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        myViewHolder.tvName.setText(mlist.get(position).getName());
        myViewHolder.tvDescription.setText(mlist.get(position).getDescription()+"");


        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mlist.get(position).getName().equals("点击测试") == true){

        }else if (mlist.get(position).getName().equals("输入测试") == true) {
            this.mContext.startActivity(new Intent(this.mContext, TestInput.class));
        }else if (mlist.get(position).getName().equals("视频测试") == true){
            this.mContext.startActivity(new Intent(this.mContext, TestCamera.class));
        }else if(mlist.get(position).getName().equals("录音测试") == true){
            this.mContext.startActivity(new Intent(this.mContext, TestAudioRecord.class));
        }else if(mlist.get(position).getName().equals("截屏/录屏测试") == true){
            this.mContext.startActivity(new Intent(this.mContext, TestScreenCap.class));
        }else if(mlist.get(position).getName().equals("打开图库") == true){
            Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            mContext.startActivity(picture);
        }else if(mlist.get(position).getName().equals("摄像头测试") == true){
            this.mContext.startActivity(new Intent(this.mContext, TestCamera.class));
        }else if(mlist.get(position).getName().equals("横屏测试") == true){
            this.mContext.startActivity(new Intent(this.mContext, TestLandscape.class));
        }
        else if(mlist.get(position).getName().equals("亮度测试") == true){
            this.mContext.startActivity(new Intent(this.mContext, TestBrightness.class));
        }else if(mlist.get(position).getName().equals("传感器测试") == true){
            this.mContext.startActivity(new Intent(this.mContext, TestSenser.class));
        }
    }

    public static class MyViewHolder {
        TextView tvName, tvDescription;
    }
}
