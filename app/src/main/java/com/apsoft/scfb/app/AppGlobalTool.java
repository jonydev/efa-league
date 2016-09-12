package com.apsoft.scfb.app;

import android.content.Context;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.anywhere.appbase.AppBaseApplication.IImageLoadCallback;
import com.anywhere.utils.ToastUtils;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.TestEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.ykcloud.sdk.openapi.YKAPIFactory;
import com.youku.player.base.YoukuPlayerInit;

import java.util.ArrayList;
import java.util.List;

public class AppGlobalTool {
	static double	mLastLon;
	static double 	mLastLat;
//	static LocationProvider locationProvider = null;

	static TestEntry	testEntry;

	///全局数据，球衣图片
	static List<Integer> mCloseResourceArray = new ArrayList<Integer>();
	static List<Integer> mPathResourceArray = new ArrayList<Integer>();

	static Context getAppContext(){
		return SCFBApplication.getApplication();
	}
	
	public static void execNetRequest(Runnable rable){
		SCFBApplication.getApplication().execNetRequest(rable);
	}

	public static void setTestEntry(TestEntry entry){
		testEntry = entry;
	}

	public static TestEntry getTestEntry() {
		return testEntry;
	}

	public static void execImageRequest(Runnable ra){
		SCFBApplication.getApplication().execImageRequest(ra);
	}
	
	public static void execLoadImg(final String url, final IImageLoadCallback il){
		SCFBApplication.getApplication().execLoadImg(url, il);
	}
	
	public static void execLoadThumbImg(final String url, final ImageView iv){
		SCFBApplication.getApplication().execLoadThumbImg(url, iv);
	}
	
	public static void execLoadImgRemote(final String url, final IImageLoadCallback il){
		SCFBApplication.getApplication().execLoadImgRemote(url, il);
	}
	
	public static void execLoadBigImg(final String url, final IImageLoadCallback il){
		SCFBApplication.getApplication().execLoadBigImg(url, il);
	}
	
	public static double getLastLon(){
		return mLastLon;
	}
	
	public static double getLastLat(){
		return mLastLat;
	}
	
	public static void setLastLonLat(final double lon, final double lat){
		mLastLon = lon;
		mLastLat = lat;
	}

	public static void initClothData() {
		mCloseResourceArray.add( R.drawable.cloth1 );
		mCloseResourceArray.add( R.drawable.cloth2 );
		mCloseResourceArray.add( R.drawable.cloth3 );
		mCloseResourceArray.add( R.drawable.cloth4 );
		mCloseResourceArray.add( R.drawable.cloth5 );
		mCloseResourceArray.add( R.drawable.cloth6 );
		mCloseResourceArray.add( R.drawable.cloth7 );
		mCloseResourceArray.add( R.drawable.cloth8 );
		mCloseResourceArray.add( R.drawable.cloth9 );
		mCloseResourceArray.add( R.drawable.cloth10 );
		mCloseResourceArray.add( R.drawable.cloth11 );
		mCloseResourceArray.add( R.drawable.cloth12 );
		mCloseResourceArray.add( R.drawable.cloth13 );
		mCloseResourceArray.add( R.drawable.cloth14 );
		mCloseResourceArray.add( R.drawable.cloth15 );
		mCloseResourceArray.add( R.drawable.cloth16 );

		mPathResourceArray.add(R.drawable.path1 );
		mPathResourceArray.add(R.drawable.path2 );
		mPathResourceArray.add(R.drawable.path3 );
		mPathResourceArray.add(R.drawable.path4 );
		mPathResourceArray.add(R.drawable.path5 );
		mPathResourceArray.add(R.drawable.path6 );
		mPathResourceArray.add(R.drawable.path7 );
		mPathResourceArray.add(R.drawable.path8 );
		mPathResourceArray.add(R.drawable.path9 );
		mPathResourceArray.add(R.drawable.path10 );
		mPathResourceArray.add(R.drawable.path11 );
		mPathResourceArray.add(R.drawable.ath12 );
		mPathResourceArray.add(R.drawable.ath13 );
		mPathResourceArray.add(R.drawable.ath14 );
		mPathResourceArray.add(R.drawable.ath15 );
		mPathResourceArray.add(R.drawable.ath16 );
	}

	public static int getCloseDrawableId(int pos){
		if(pos>=0 && pos<mCloseResourceArray.size())
			return mCloseResourceArray.get(pos);
		return mCloseResourceArray.get(0);
	}

	public static int getCloseDrawableId(String pos){
		int ipos = 0;
		if(pos!=null && pos.length()>0 )
			ipos = Integer.parseInt(pos);
		return getCloseDrawableId(ipos);
	}

	public static int getPathDrawableId(int pos){
		if(pos>=0 && pos<mPathResourceArray.size())
			return mPathResourceArray.get(pos);
		return mPathResourceArray.get(0);
	}

	public static int getPathDrawableId(String pos){
		int ipos = 0;
		if(pos!=null && pos.length()>0 )
			ipos = Integer.parseInt(pos);
		return getPathDrawableId(ipos);
	}

	public static String getImageDownloadUrl(String sUrl){
		return SCFBApplication.getInstance().getImageDownloadUrl(sUrl);
	}
	
	public static void runOnUiThread(Runnable ab){
		SCFBApplication.getInstance().delayExec(ab);
	}

	public static void initArea(){
		NetHomeQuery.requestArereInfo(new BaseCallback<String>(){

			@Override
			public void onBeforeRequest(Request request) {

			}

			@Override
			public void onFailure(Request request, Exception e) {

			}

			@Override
			public void onResponse(Response response) {

			}

			@Override
			public void onSuccess(Response response, String s) {
				TestEntry testEntry = JSON.parseObject(s, TestEntry.class);
				List<TestEntry.DataBean> data = testEntry.getData();
				TestEntry.DataBean dataBean = data.get(data.size() - 1);
				String id = dataBean.getId();
				if(AppAccountTool.getInstance().getAreaId() == null)
					AppAccountTool.getInstance().setAreaId(id);
				AppGlobalTool.setTestEntry(testEntry);
			}

			@Override
			public void onError(Response response, int code, Exception e) {

			}
		});
	}

	public static void initYoukuSDK(){
		try {
			YoukuPlayerInit.init(SCFBApplication.getApplication());
			String client_id = "80b596dc4100a8d7";//"849ca204b9c994fe";
			String client_secret = "715fba610a2b31c5b2af8b936b557016";//"40e16f29e216f1c13b4f5aded239f69b";
			YKAPIFactory.initSDK(SCFBApplication.getApplication(), client_id, client_secret);
		}catch(Exception e){
			ToastUtils.showLongToast(SCFBApplication.getApplication(), "视频模块初始化失败，您的时候可能无法查看视频");
		}
	}
//	public static void loginInitRemoteData(){
//		NetSetting.refreshToken();
//		AppAccountTool.getInstance().refreshFriends();
//		NetQueryStockData.getAllStockCode(null);
//	}

//	public static void initBaiduLocation(Context ctx) {
//		if (locationProvider == null) {
//			locationProvider = new LocationProvider(ctx);
//			locationProvider.setLocationListener(new BDLocationListener() {
//				@Override
//				public void onReceiveLocation(BDLocation bdLocation) {
//					final double lat = bdLocation.getLatitude();
//					final double lng = bdLocation.getLongitude();
//					mLastLon = lng;
//					mLastLat = lat;
//				}
//			});
//			locationProvider.start();
//		}
//	}
//
//	public static void initBaiduPush(Context ctx){
//		PushManager.startWork(ctx,
//				PushConstants.LOGIN_TYPE_API_KEY,
//				Utils.getMetaValue(ctx, "baidu_push_api_key"));
//	}
}
