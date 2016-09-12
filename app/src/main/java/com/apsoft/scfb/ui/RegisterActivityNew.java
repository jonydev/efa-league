package com.apsoft.scfb.ui;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anywhere.appbase.BackCloseActivity;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppAccountTool;
import com.apsoft.scfb.app.AppGlobalTool;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetSCFBLogin;
import com.apsoft.scfb.http.NetSetting;
import com.apsoft.scfb.http.NetStandandLogin;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.Team.TeamFragment2;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class RegisterActivityNew extends BackCloseActivity {
	EditText viewPhone;
	EditText viewSms;
	EditText viewPwd;
	Button btnSms;
	
	Handler mHandler = new Handler();
	int		mSeconds = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_new);
		viewPhone = (EditText) findViewById(R.id.editText1);
		viewSms = (EditText) findViewById(R.id.editText4);
		viewPwd = (EditText) findViewById(R.id.editText3);
		
		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doRegister();
			}
		});
		
		btnSms = (Button) findViewById(R.id.button2);
		btnSms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getSms();
			}
		});
		
		TextView tv = (TextView) findViewById(R.id.textView2);
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent it = new Intent(RegisterActivityNew.this, SettingUserProxyActivity.class);
				//startActivity(it);
			}
		});
	}

	void getSms(){
		mSeconds = 60;
		btnSms.setEnabled(false);
		final String sPhone = viewPhone.getText().toString();
//		AppGlobalTool.execNetRequest(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				NetStandandLogin.getSmsCode(sPhone, new NetSetting.IRemoteQueryCallback() {
//
//					@Override
//					public void onFinish(String data, JSONObject jsonObject) {
//						// TODO Auto-generated method stub
//						if (NetSetting.isResponseOK(jsonObject)) {
//							runOnUiThread(new Runnable() {
//
//								@Override
//								public void run() {
//									// TODO Auto-generated method stub
//									//viewPhone.setEnabled(false);
//									mHandler.postDelayed(new Runnable() {
//
//										@Override
//										public void run() {
//											// TODO Auto-generated method stub
//											if (mSeconds < 1) {
//												btnSms.setText(getString(R.string.getsms));
//												btnSms.setEnabled(true);
//											} else {
//												--mSeconds;
//												updateButtonShowTime();
//												mHandler.postDelayed(this, 1000);
//											}
//										}
//									}, 1000);
//								}
//							});
//						} else {
//							mHandler.post(new Runnable() {
//
//								@Override
//								public void run() {
//									// TODO Auto-generated method stub
//									btnSms.setEnabled(true);
//									//Toast.makeText(RegisterActivityNew.this, text, duration)
//								}
//							});
//						}
//					}
//
//					@Override
//					public void onFailed(Exception e) {
//						// TODO Auto-generated method stub
//						mHandler.post(new Runnable() {
//
//							@Override
//							public void run() {
//								// TODO Auto-generated method stub
//								btnSms.setEnabled(true);
//								Toast.makeText(RegisterActivityNew.this, getString(R.string.getsmsfailed), Toast.LENGTH_SHORT).show();
//							}
//						});
//					}
//				});
//			}
//		});
	}
	
	void updateButtonShowTime(){
		btnSms.setText(String.format(getString(R.string.smsleft), mSeconds));
	}
	
	void doRegister(){
		final String loginName = viewPhone.getText().toString();
		final String sSms = viewSms.getText().toString();
		final String sPwd = AppAccountTool.getInstance().makeServerPassword(viewPwd.getText().toString());
		if (TextUtils.isEmpty(loginName)) {
			Toast.makeText(this, "登录名不能为空！", Toast.LENGTH_SHORT).show();
			viewPhone.requestFocus();
			return;
		} /*else if (TextUtils.isEmpty(sSms)) {
//			Toast.makeText(this, "短信验证码不能为空！", Toast.LENGTH_SHORT).show();
//			viewSms.requestFocus();
//			return;
		}*/else if (TextUtils.isEmpty(sPwd)) {
			Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
			viewPwd.requestFocus();
			return;
		}
		NetSCFBLogin.requestCreateUser(loginName, sPwd, sSms, new BaseCallback<String>(){

			@Override
			public void onBeforeRequest(Request request) {

			}

			@Override
			public void onFailure(Request request, Exception e) {
				Toast.makeText(RegisterActivityNew.this, "该用户名已经被使用", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onResponse(Response response) {
//				Toast.makeText(RegisterActivityNew.this, "该用户名已经被使用", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSuccess(Response response, String s) {
				AppAccountTool.getInstance().setUserName(loginName);
				AppAccountTool.getInstance().setPassword(sPwd);

				AppAccountTool.getInstance().doLogin(loginName,sPwd,new AppAccountTool.ILoginInterface(){
					@Override
					public void onLoginSuccess(Object obj) {
						MainActivity s_mainActivity = MainActivity.s_mainActivity;
						TeamFragment2 teamFragment2 = (TeamFragment2) s_mainActivity.getSupportFragmentManager().findFragmentByTag("teamTag");
						TextView tv = (TextView) teamFragment2.getView().findViewById(R.id.tv_team2_creatTeam);
						tv.setText("创建球队");
						Intent intent=new Intent(getApplicationContext(),PersonalDetailsActivity.class);
						intent.putExtra("flag",true);
						startActivity(intent);
					}

					@Override
					public void onLoginFailed(Object obj) {

					}
				});
				finish();
			}

			@Override
			public void onError(Response response, int code, Exception e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(RegisterActivityNew.this, "该用户名已经被使用", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
//		AppGlobalTool.execNetRequest(new Runnable() {
//			//getString(R.string.defaultnick)
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				NetStandandLogin.ajaxCreateUser(sNickName,sSms, sPhone, sPwd, new NetSetting.IRemoteQueryCallback() {
//
//					@Override
//					public void onFinish(String data, JSONObject jsonObject) {
//						// TODO Auto-generated method stub
//						try {
//							if (NetSetting.isResponseOK(jsonObject)) {
//								AppAccountTool.getInstance().setUserName(sPhone);
//								AppAccountTool.getInstance().setPassword(sPwd);
//
//								try {
//									JSONObject json=new JSONObject(jsonObject.get("userinfo").toString());
//									User.getInstance().uid=json.getString("userid");
//									//User.getInstance().back_photo=json.getString("back_photo");
//									User.getInstance().user_name=json.getString("name");
//									User.getInstance().user_photo=json.getString("imgurl");
//									//User.getInstance().signature=json.getString("signature");
//									User.getInstance().user_sex=json.getString("sex");
//									User.getInstance().user_city=json.getString("city");
//								}
//								catch (JSONException e) {
//								}
//
//								runOnUiThread(new Runnable() {
//
//									@Override
//									public void run() {
//										mSeconds = -1;
//										// TODO Auto-generated method stub
////										startActivity(new Intent(RegisterActivityNew.this, SimpleWebActivity.class));
////										finish();
//									}
//								});
//							}
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//
//					@Override
//					public void onFailed(Exception e) {
//						// TODO Auto-generated method stub
//
//					}
//				});
//			}
//		});
	}
}
