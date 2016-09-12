package com.apsoft.scfb.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anywhere.appbase.BackCloseActivity;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppAccountTool;
import com.apsoft.scfb.app.AppGlobalTool;
import com.apsoft.scfb.http.NetSetting;
import com.apsoft.scfb.http.NetStandandLogin;

import org.json.JSONObject;

public class ResetPasswordActivity extends BackCloseActivity {
	EditText viewPhone;
	EditText viewSms;
	EditText viewPwd;
	
	Button btnSms;
			
	int		mSeconds = 60;
	Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resetpwd);
		
		viewPhone = (EditText) findViewById(R.id.editText1);
		viewSms = (EditText) findViewById(R.id.editText4);
		viewPwd = (EditText) findViewById(R.id.editText3);
		String sUsername = AppAccountTool.getInstance().getUserName();
		if(sUsername!=null && sUsername.length()>0){
			viewPhone.setText(sUsername);
		}
		
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doUpdatePwd();
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
	}
	
	void doUpdatePwd(){
		final String sPhone = viewPhone.getText().toString();
		final String sSms = viewSms.getText().toString();
		final String sPwd = viewPwd.getText().toString();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				NetStandandLogin.updateUserPwd(sPhone, sSms, sPwd, new NetSetting.IRemoteQueryCallback() {

					@Override
					public void onFinish(String data, JSONObject jsonObject) {
						// TODO Auto-generated method stub
						if (NetSetting.isResponseOK(jsonObject)) {
							AppAccountTool.getInstance().setUserName(sPhone);
							AppAccountTool.getInstance().setPassword(sPwd);
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									//startActivity(intent)
									mSeconds = -1;
									finish();
								}
							});
						} else {
							final String sRemark = NetSetting.getResponseRemark(jsonObject);
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									//startActivity(intent)
									Toast.makeText(ResetPasswordActivity.this, sRemark, Toast.LENGTH_LONG).show();
								}
							});
						}

					}

					@Override
					public void onFailed(Exception e) {
						// TODO Auto-generated method stub

					}
				});
			}
		}).start();
	}
	
	void getSms(){
		btnSms.setEnabled(false);
		mSeconds = 60;
		final String sPhone = viewPhone.getText().toString();
		AppGlobalTool.execNetRequest(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				NetStandandLogin.getSmsCode(sPhone, new NetSetting.IRemoteQueryCallback() {

					@Override
					public void onFinish(String data, JSONObject jsonObject) {
						// TODO Auto-generated method stub
						if (NetSetting.isResponseOK(jsonObject)) {
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub

									mHandler.postDelayed(new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub
											if (mSeconds < 1) {
												btnSms.setText(getString(R.string.getsms));
												btnSms.setEnabled(true);
											} else {
												--mSeconds;
												updateButtonShowTime();
												mHandler.postDelayed(this, 1000);
											}
										}
									}, 1000);
								}
							});
						} else {
							mHandler.post(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									btnSms.setEnabled(true);
								}
							});
						}
					}

					@Override
					public void onFailed(Exception e) {
						// TODO Auto-generated method stub
						mHandler.post(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								btnSms.setEnabled(true);
							}
						});
					}
				});
			}
		});
	}
	void updateButtonShowTime(){
		btnSms.setText(String.format(getString(R.string.smsleft), mSeconds));
	}
}
