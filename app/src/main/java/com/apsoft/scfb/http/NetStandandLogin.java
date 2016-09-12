package com.apsoft.scfb.http;

import com.anywhere.http.HttpResultModel;
import com.anywhere.http.RequestByHttpPost;
import com.anywhere.utils.StringUtil;
import com.apsoft.scfb.app.AppAccountTool;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NetStandandLogin {
	public static final String s_loginurl = NetSetting.s_accountbaseddress + "/account/login/";
	public static final String s_findpwdurl = NetSetting.s_accountbaseddress + "/account/findpwd/";
	public static final String s_createuserurl = NetSetting.s_accountbaseddress + "/account/createuser/";
	public static final String s_resetpwdurl = NetSetting.s_accountbaseddress + "/account/resetpwd/";
	public static final String s_bindphoneurl = NetSetting.s_accountbaseddress + "/account/bindphone/";
	public static final String s_changemyinfourl = NetSetting.s_accountbaseddress + "/account/changemyinfo/";

	public static final String s_sendadviceurl = NetSetting.s_accountbaseddress + "/account/sendadvice/";
	public static final String s_uploadfileurl = NetSetting.s_accountbaseddress + "/upload/";
	


	public static void bindPhone(String sNewPhone, String sms, NetSetting.IRemoteQueryCallback cbx){
	}
	
	public static void updateUserPwd(String sUsername, String sms, String spwd, NetSetting.IRemoteQueryCallback cbx){
	}
	
	public static void getSmsCode(String sms, NetSetting.IRemoteQueryCallback cb){
		
	}
	
	public static void changeMyInfo(String mPhone, String mNickName, String mCity, String mSex, String signature, String headImage, String headImageType, NetSetting.IRemoteQueryCallback cbx){
		try {
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("phone", mPhone));
			params.add(new BasicNameValuePair("nickname", mNickName));
			params.add(new BasicNameValuePair("city", mCity));
			params.add(new BasicNameValuePair("sex", mSex));
			params.add(new BasicNameValuePair("signature", signature));
			params.add(new BasicNameValuePair("headimageaddr", headImage));
			params.add(new BasicNameValuePair("headimagetype", headImageType));
			HttpResultModel model = RequestByHttpPost.doPost(
					params, s_changemyinfourl);
			String data=model.getData();
			JSONObject json = new JSONObject(data);
			if(NetSetting.isResponseOK(json)){
			}
			if(cbx!=null)
				cbx.onFinish(data, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(cbx!=null)
				cbx.onFailed(e);
		}
	}


	public static void uploadFile(String sFilename, NetSetting.IRemoteQueryCallback cbx){
		try{
			List<String> fileNames = new ArrayList<String>(3);
			fileNames.add(sFilename);
			HttpResultModel model = RequestByHttpPost.doPostWithFiles(null, s_uploadfileurl, fileNames);
			String data=model.getData();
			JSONObject json = new JSONObject(data);
			if(cbx!=null){
				cbx.onFinish(data, json);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(cbx!=null){
				cbx.onFailed(e);
			}
		}
	}
	
	public static void uploadFile(final byte[] uploadData, String sName, String sMimeType, NetSetting.IRemoteQueryCallback cbx){
		try{
			HttpResultModel model = RequestByHttpPost.doPostWithData(null, s_uploadfileurl, uploadData, sName, sMimeType);
			String data=model.getData();
			JSONObject json = new JSONObject(data);
			
			String svPath = json.getString(sName);
			JSONObject jsonD = new JSONObject();
			try {
				jsonD.put("img_address", svPath);
				jsonD.put("states", "ok");
				jsonD.put("code", 200);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(cbx!=null){
				cbx.onFinish(jsonD.toString(), jsonD);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(cbx!=null){
				cbx.onFailed(e);
			}
		}
	}
}
