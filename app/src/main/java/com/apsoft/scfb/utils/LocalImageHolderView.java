package com.apsoft.scfb.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by Administrator on 2016/7/25.
 */
public class LocalImageHolderView implements Holder<String > {
    private ImageView imageView;
    ImageView.ScaleType scaleType = null;
    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        if(scaleType != null){
            imageView.setScaleType(scaleType);
        }else {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        return imageView;
    }

    public void setScaleType(ImageView.ScaleType sc){
        scaleType = sc;
    }

    public ImageView getImageView(){
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        System.out.println("-----data:"+data);
        ImageLoader1.getInstance().displayImage(context,data,imageView);
    }


}
