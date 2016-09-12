package com.apsoft.scfb.ui.adapter.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.NoticeEntry;
import com.apsoft.scfb.utils.ImageLoader1;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class NoticeAdapter extends BaseAdapter {
    private List<NoticeEntry.NoticedataBean>data;
    private LayoutInflater inflater;
    Context mContext;

    public NoticeAdapter(Context context,List<NoticeEntry.NoticedataBean> data) {
        this.data = data;
        mContext = context;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data!=null?data.get(i):null;
    }



    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_notice_lv,null);
            holder.tv= (TextView) view.findViewById(R.id.tvContent_itemNotice);
            holder.iv= (ImageView) view.findViewById(R.id.iv_itemNotice);
            holder.dtView = (TextView)view.findViewById(R.id.tv_time_notice);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tv.setText(data.get(i).getContent());
        String time = data.get(i).getDate().substring(0, 10);
        holder.dtView.setText(time);
        ImageLoader1.getInstance().displayImage(mContext, data.get(i).getImage(), holder.iv);
        return view;
    }

    public static class ViewHolder{
        TextView tv;
        TextView dtView;
        ImageView iv;
    }
}
