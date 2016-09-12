package com.apsoft.scfb.http;

import android.graphics.Bitmap;

import com.anywhere.http.HttpDirectUploadManager;
import com.anywhere.http.HttpResultModel;
import com.anywhere.http.RequestByHttpPost;
import com.anywhere.http.qiniu.ResponseInfo;
import com.anywhere.http.qiniu.UpCompletionHandler;
import com.anywhere.http.qiniu.UploadOptions;
import com.anywhere.tools.PicUtil;
import com.apsoft.scfb.localdata.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class NetSetting {
	public static final String s_scfbeddress = "http://182.92.6.152:8015";
//	public static final String s_scfbeddress = "http://192.168.1.102:8000";
	public static final String s_accountbaseddress = s_scfbeddress;

	public static final String s_qiniuTokenurl = s_scfbeddress + "/account/qiniu/token/";

	public static boolean isQiniu = true;
	public static String qiniuToken = "";
	public static Date qiniuTokenGetTime = null;
	
	public interface IRemoteQueryCallback{
		public void onFinish(String data, JSONObject jsonObject);
		public void onFailed(Exception e);
	}
	
	public interface IDataNotifyCallback{
		public void onNotify(int nCode);
	}
	
	public static boolean isResponseOK(JSONObject obj){
		try{
			int responseCode = obj.getInt("code");
			if(responseCode<400){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}
	
	public static boolean isResponseOK(HttpResultModel model, JSONObject obj){
		if(model.getResultCode()>=200 && model.getResultCode()<300){
			return isResponseOK(obj);
		}
		return false;
	}
	
	public static String getResponseRemark(JSONObject obj){
		try{
			String sOK = obj.getString("remark").toString();
			return sOK;
		}catch(Exception e){
			return null;
		}
	}
	
	public static boolean isValidToken(){
		if(qiniuToken==null){
			return false;
		}
		if(qiniuToken.length()<1){
			return false;
		}
		if(qiniuTokenGetTime==null){
			return false;
		}
		Date d = new Date();
		if(d.getTime() - qiniuTokenGetTime.getTime()>7100*1000){
			return false;
		}
		return true;
	}
	
	public static void updateQiniuToken(JSONObject json){
		try {
			String sToken = json.getString("token");
			qiniuToken = sToken;
			try{
				String expiredTime = json.getString("expired_time");
				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				qiniuTokenGetTime = sdf.parse(expiredTime);
			}catch(Exception e) {
				qiniuTokenGetTime = new Date();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void refreshToken(){
		if(!isValidToken()){
			getQiniuToken(null, null);
		}
	}
	
	public static void getQiniuToken(String s,IRemoteQueryCallback cbx){
		try {
			HttpResultModel model = RequestByHttpPost.doPost(s_qiniuTokenurl);
			String data=model.getData();
			JSONObject json = new JSONObject(data);
			if(NetSetting.isResponseOK(json)){
				updateQiniuToken(json);
			}
			if(cbx!=null)
				cbx.onFinish(data, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(cbx!=null)
				cbx.onFailed(e);
		}
	}
	
	static int nLie = 1000;
	
	public static void upload_img_to_qiniu(Bitmap bmp, String imageName, String imageType, int nB, final IRemoteQueryCallback cbx){
//		refreshToken();
//		if(!isValidToken()){
//			if(cbx!=null){
//				cbx.onFailed(new Exception());
//			}
//			return;
//		}
		//强制刷新token
		getQiniuToken(null, null);
		HttpDirectUploadManager uploadManager = new HttpDirectUploadManager();
		UploadOptions options = new UploadOptions(null, "image/jpeg", false, null, null);
		String sTime = String.valueOf(new Date().getTime() / 1000);
		String sKey = "";
		String mid = User.getInstance().getMember_id();
		if(mid == null){
			mid = "";
		}
		mid = mid.replaceAll("-", "");
		if(imageName.equalsIgnoreCase("head.jpg")){
			imageType = "head";
			sKey = mid + "_" + imageType + "_userset";
		}	else{
				imageType = "share";
				sKey = mid + "_" + imageType + "_" + sTime + "_" + nLie++;
		}
		uploadManager.put(PicUtil.bitmapToBytes(bmp), sKey, qiniuToken, new UpCompletionHandler() {
			
			@Override
			public void complete(String key, ResponseInfo info, JSONObject response) {
				// TODO Auto-generated method stub
				try{
					String s = response.getString("error");
					if(s!=null){
						if(cbx!=null){
							cbx.onFailed(new Exception(s));
						}
						return;
					}
				}
				catch (JSONException e) {
					// TODO Auto-generated catch block
				}
				if(key==null){
					try {
						key = response.getString("key");
						JSONObject jsonD = new JSONObject();
						try {
							jsonD.put("img_address", key);
							jsonD.put("states", "ok");
							jsonD.put("code", 200);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(cbx!=null){
							cbx.onFinish(jsonD.toString(), jsonD);
						}
						return;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(cbx!=null){
					cbx.onFailed(new Exception("Upload Failed"));
				}
			}
		}, options);
	}

	public static void uploadImage(Bitmap bmp, String sBmpName, String imageType, int nB,IRemoteQueryCallback cbx){
		upload_img_to_qiniu(bmp, sBmpName, imageType, nB,cbx);
		//NetStandandLogin.uploadFile(PicUtil.bitmapToBytes(bmp), sBmpName, imageType, cbx);
	}
}
