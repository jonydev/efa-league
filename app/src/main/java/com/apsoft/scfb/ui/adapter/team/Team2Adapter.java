package com.apsoft.scfb.ui.adapter.team;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.Tean2StartEntry;
import com.apsoft.scfb.utils.ImageLoader1;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public class Team2Adapter extends BaseAdapter {
    private List<Tean2StartEntry.CompetitionZoneBean.DataBean> data;
    private LayoutInflater inflater;
    private Context context;

    public Team2Adapter(Context context, List<Tean2StartEntry.CompetitionZoneBean.DataBean> data) {
        this.data = data;
        this.context=context;
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
            view=inflater.inflate(R.layout.item_team2_lv,null);
            holder.img= (ImageView) view.findViewById(R.id.iv_item_team2);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        ImageLoader1.getInstance().displayImage(context,data.get(i).getZone(),holder.img);
        return view;
    }

    public static class ViewHolder{
        ImageView img;
    }
}
