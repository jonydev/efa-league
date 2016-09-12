package com.apsoft.scfb.ui.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apsoft.scfb.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class NoticeDetailAdapter extends BaseAdapter {
    private List<String >data;
    private LayoutInflater inflater;

    public NoticeDetailAdapter(Context context, List<String> data) {
        this.data = data;
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
        System.out.println("2222222"+"222222");
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_notice_detail_lv,null);
            holder.tv= (TextView) view.findViewById(R.id.tv_item_notice_detail);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tv.setText(data.get(i));
        System.out.println("2222222:   "+data);
        return view;
    }

    public static class ViewHolder{
        TextView tv;
    }
}
