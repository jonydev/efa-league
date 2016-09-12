package com.apsoft.scfb.ui.adapter.team;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.ui.adapter.AbsAdapter;

import java.util.List;

/**
 * Created by admin on 2016/8/16.
 */
public class TeamClothChoiseAdapter extends AbsAdapter<Bitmap> {
    private List<Bitmap>data;
    private LayoutInflater inflater;
    private int position;
    public TeamClothChoiseAdapter(Context context, List<Bitmap> data) {
        super(context, data);
        this.inflater=LayoutInflater.from(context);
        this.data=data;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_gv,null);
            holder.imageView= (ImageView) view.findViewById(R.id.iv);
            holder.imgSelected= (ImageView) view.findViewById(R.id.iv_judge_selected);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
            if (i==position){
                holder.imgSelected.setVisibility(View.VISIBLE);
            }else {
                holder.imgSelected.setVisibility(View.INVISIBLE);
            }
        }
        holder.imageView.setImageBitmap(data.get(i));
        return view;
    }

    public void setIsSelected(int index){
        this.position=index;
    }

    public static class ViewHolder{
        ImageView imageView;
        ImageView imgSelected;
    }

}
