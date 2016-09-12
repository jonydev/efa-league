package com.apsoft.scfb.app;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSONObject;
import com.anywhere.http.RequestByHttpPost;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetSCFBLogin;
import com.apsoft.scfb.localdata.User;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;

public class AppAccountTool {
	public final String PREF_USERNAME = "username";
	private static final String PREF_PWD = "pwd";
	private static final String PREF_SESSION = "session";
	private static final String PREF_TOKEN_INFO= "token";
	private static final String AREA_ID= "area_id";
	private static final String PREF_AUTO_LOGIN_VALUE = "auto_login";

	public String mUsername;
	public String mPassword;

	public String mUserId = "-1";
	
	Set<String> mFriendsArray = new HashSet<String>();
	Set<String> mContactsMeArray = new HashSet<String>();
	
	static AppAccountTool sAccountTool = new AppAccountTool();
	public static AppAccountTool getInstance(){
		return sAccountTool;
	}
	
	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public String getServerPassword(){
		return mPassword;//MD5(mPassword);
	}

	public String makeServerPassword(String sPwd){
		return sPwd;//MD5(sPwd);
	}

	/**
	 * 获取当前登录用户名
	 *
	 * @return
	 */
	public String getUserName() {
		if (mUsername == null) {
			SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
			mUsername = preferences.getString(PREF_USERNAME, null);
		}
		return mUsername;
	}

	/**
	 * 获取密码
	 *
	 * @return
	 */
	public String getPassword() {
		if (mPassword == null) {
			SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
			mPassword = preferences.getString(PREF_PWD, null);
		}
		return mPassword;
//		return "";
	}

	/**
	 * 设置用户名
	 *
	 * @param username
	 */
	public void setUserName(String username) {
		if (username != null) {
			SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
			SharedPreferences.Editor editor = preferences.edit();
			if (editor.putString(PREF_USERNAME, username).commit()) {
				mUsername = username;
			}
		}
	}

	/**
	 * 设置密码
	 * 
	 * @param pwd
	 */
	public void setPassword(String pwd) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
		SharedPreferences.Editor editor = preferences.edit();
		if (editor.putString(PREF_PWD, pwd).commit()) {
			mPassword = pwd;
		}
	}
	
	public void setSessionID(String sessionID){
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
		SharedPreferences.Editor editor = preferences.edit();
		if (editor.putString(PREF_SESSION, sessionID).commit()) {
			RequestByHttpPost.JSESSIONID = sessionID;
		}
	}

	public void setAreaId(String areaId){
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
		SharedPreferences.Editor editor = preferences.edit();
		if (editor.putString(AREA_ID, areaId).commit()) {
		}
	}

	public String getAreaId(){
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
		String areaId = preferences.getString(AREA_ID, null);
		return areaId;
	}
	
	public String getSessionID(){
		if (RequestByHttpPost.JSESSIONID == null) {
			SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
			RequestByHttpPost.JSESSIONID = preferences.getString(PREF_SESSION, null);
		}
		return RequestByHttpPost.JSESSIONID;
	}

	public void setTokenInfo(String tokenInfo){
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
		SharedPreferences.Editor editor = preferences.edit();
		if (editor.putString(PREF_TOKEN_INFO, tokenInfo).commit()) {
		}
	}

	public String getTokenInfo(){
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
		String tokenInfo = preferences.getString(PREF_TOKEN_INFO, null);
		return tokenInfo;
	}

	public void setAutoLogin(boolean b){
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
		SharedPreferences.Editor editor = preferences.edit();
		if (editor.putBoolean(PREF_AUTO_LOGIN_VALUE, b).commit()) {
		}
	}

	public boolean getAutoLogin(){
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(AppGlobalTool.getAppContext());
		boolean b = preferences.getBoolean(PREF_AUTO_LOGIN_VALUE, false);
		return b;
	}

	public String getUserId(){
		return mUserId;
	}

	public void logout(){
		//ESApplication.getInstance().logout();
		setSessionID("");
		User.getInstance().setIs_login(false);
		User.getInstance().reset();
		setAutoLogin(false);
	}
	
	public void resetFriendsContactsMe(){
		mFriendsArray.clear();
		mContactsMeArray.clear();
	}

	public void setmFriendsArray(Set<String> fr){
		mFriendsArray = fr;
	}


	public static interface ILoginInterface{
		void onLoginSuccess(Object obj);
		void onLoginFailed(Object obj);
	}

	public static void doLogin(final String username, final String password, final ILoginInterface loginInterface){
		if(username==null || password==null || username.isEmpty() || password.isEmpty()){
			loginInterface.onLoginFailed(null);
			return;
		}
		NetSCFBLogin.requestLogin(username, password, new BaseCallback<String>() {
			@Override
			public void onBeforeRequest(Request request) {

			}

			@Override
			public void onFailure(Request request, Exception e) {
				loginInterface.onLoginFailed(null);
			}

			@Override
			public void onResponse(Response response) {

			}

			@Override
			public void onSuccess(Response response, String o) {
				AppAccountTool.getInstance().setUserName(username);
				AppAccountTool.getInstance().setPassword(password);
				JSONObject jsonObject = JSONObject.parseObject(o);
				jsonObject = jsonObject.getJSONObject("data");
				User.getInstance().user_name = jsonObject.getString("name");
				User.getInstance().office_id = jsonObject.getString("office_id");
				User.getInstance().team_id = jsonObject.getString("team_id");
				User.getInstance().office_id = jsonObject.getString("office_id");
				User.getInstance().user_photo = jsonObject.getString("photo");
				User.getInstance().user_sex = jsonObject.getString("sex");
				User.getInstance().user_city = jsonObject.getString("city");
				User.getInstance().member_id = jsonObject.getString("member_id");
				User.getInstance().member_name = jsonObject.getString("member_name");
				User.getInstance().team_flag = jsonObject.getString("team_flag");
				User.getInstance().team_office = jsonObject.getString("team_office");
				User.getInstance().team_leader = jsonObject.getString("team_leader");
				User.getInstance().is_team_leader = jsonObject.getString("is_team_leader");
				User.getInstance().member_flag = jsonObject.getString("member_flag");
				User.getInstance().setIs_login(true);
				AppAccountTool.getInstance().setAutoLogin(true);
				loginInterface.onLoginSuccess(jsonObject);
			}

			@Override
			public void onError(Response response, int code, Exception e) {
				loginInterface.onLoginFailed(null);
			}
		});
	}
}
