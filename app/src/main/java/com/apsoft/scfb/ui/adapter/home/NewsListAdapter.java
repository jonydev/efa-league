package com.apsoft.scfb.ui.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.apsoft.scfb.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public class NewsListAdapter extends BaseAdapter {
    List    data;
    Context context;
    private LayoutInflater layoutInflater;
    public NewsListAdapter(Context context,List<?> objects){
        this.data=objects;
        this.context=context;
        this.layoutInflater= LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView != null){
            view = convertView;
        }else{
            view = layoutInflater.inflate(R.layout.listitem_home_news, parent);
        }

        return null;
    }
}
