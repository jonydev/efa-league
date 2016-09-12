package com.apsoft.scfb.ui;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.anywhere.appbase.BackCloseActivity;
import com.anywhere.tools.SysUtil;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppAccountTool;
import com.apsoft.scfb.app.AppGlobalTool;
import com.apsoft.scfb.app.SCFBApplication;
import com.apsoft.scfb.bean.TestEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.http.NetSetting;
import com.apsoft.scfb.http.NetStandandLogin;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.MineFragment;
import com.apsoft.scfb.utils.ImageLoader1;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import java.util.List;


public class LogoActivity extends BackCloseActivity {
	ImageSwitcher mImageSwitcher;
	
	//int[] arrayImage = {R.drawable.startimage1 , R.drawable.startimage2 , R.drawable.startimage3 , R.drawable.startimage4}; 
	
	Handler mPostHandler = new Handler();
	int	deltaMS = 2500;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysUtil.GainViewSize(this);

//		String aaa= "2edb48841fbc2b517119a8eec9beb5299f12a020a681684d104f223e";
//		boolean f =validatePassword("admin", aaa);
//		System.out.println(f);

		//showNotify(1,1,this);
		setContentView(R.layout.activity_start_logo);
		mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
		mImageSwitcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				ImageView imageView = (ImageView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.image_switcher_view, mImageSwitcher, false);

				return imageView;
			}
		});
		
		mImageSwitcher.setImageResource(R.drawable.start_default);
		backgroundLogin();
//		mPostHandler.postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				Intent intent = new Intent(LogoActivity.this, MainActivity.class);//LoginActivity.class);
//				startActivity(intent);
//				finish();
//			}
//		}, 2000);
	}
	
	public void backgroundLogin(){
		if(!AppAccountTool.getInstance().getAutoLogin()){
			mPostHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent it = new Intent(LogoActivity.this, MainActivity.class);
					startActivity(it);
					finish();
				}
			}, 2000);
			return;
		}
		AppAccountTool.getInstance().doLogin(AppAccountTool.getInstance().getUserName(), AppAccountTool.getInstance().getPassword(), new AppAccountTool.ILoginInterface() {
			@Override
			public void onLoginSuccess(Object obj) {
				mPostHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent it = new Intent(LogoActivity.this, MainActivity.class);
						startActivity(it);
						finish();
					}
				}, 2000);
			}

			@Override
			public void onLoginFailed(Object obj) {
				mPostHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent it = new Intent(LogoActivity.this, MainActivity.class);
						startActivity(it);
						finish();
					}
				}, 1500);
			}
		});
	}
	
	public void onLoginFailed(){
		mPostHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent it = new Intent(LogoActivity.this, LoginActivity.class);
				startActivity(it);
				finish();
			}
		}, 500);
	}

}
