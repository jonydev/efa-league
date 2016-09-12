package com.apsoft.scfb.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.apsoft.scfb.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/7/25.
 */
public class ImageLoader1 {
    private static volatile ImageLoader1 mInstance;
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";
//    public static final String IMAGE_BASE_URL = "http://120.76.206.174:8080";
    public static final String IMAGE_BASE_URL = "http://obxgaesml.bkt.clouddn.com";
    public static String fullUrl(String url){
        if(url.startsWith("/")){
            return IMAGE_BASE_URL + url;
        }else{
            return IMAGE_BASE_URL + "/" + url;
        }
    }
    private ImageLoader1() {

    }

    public static ImageLoader1 getInstance() {
        ImageLoader1 instance = mInstance;
        if (instance == null) {
            synchronized (ImageLoader1.class) {
                if (instance == null) {
                    instance = new ImageLoader1();
                    mInstance = instance;
                }
            }
        }
        return instance;
    }


    //直接加载网络图片
    public void displayImage(Context context, String url, ImageView imageView) {
        if(url!=null && !url.startsWith("http://")){
            url = fullUrl(url);
        }
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }


    //加载SD卡图片
    public void displayImage(Context context, File file, ImageView imageView) {
        Glide
                .with(context)
                .load(file)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }

    //加载SD卡图片并设置大小
    public void displayImage(Context context, File file, ImageView imageView, int width, int height) {
        Glide
                .with(context)
                .load(file)
                .override(width, height)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }

    //加载网络图片并设置大小
    public void displayImage(Context context, String url, ImageView imageView, int width, int height) {
        if(url!=null && !url.startsWith("http://")){
            url = fullUrl(url);
        }
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .override(width, height)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    //加载drawable图片
    public void displayImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    //加载drawable图片显示为圆形图片
    public void displayCricleImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .crossFade()
                .bitmapTransform(new CropCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    //加载网络图片显示为圆形图片
    public void displayCricleImage(Context context, String url, ImageView imageView) {
        if(url!=null && !url.startsWith("http://")){
            url = fullUrl(url);
        }

        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(context))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    //加载SD卡图片显示为圆形图片
    public void displayCricleImage(Context context, File file, ImageView imageView) {
        Glide
                .with(context)
                .load(file)
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }

    //将资源ID转为Uri
    public Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
