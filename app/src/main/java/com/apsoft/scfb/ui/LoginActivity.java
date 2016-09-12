package com.apsoft.scfb.ui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anywhere.appbase.BaseActivity;
import com.anywhere.utils.ToastUtils;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppAccountTool;
import com.apsoft.scfb.bean.PersonalInfoEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetSCFBLogin;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.MineFragment;
import com.apsoft.scfb.ui.fragments.Team.TeamFragment2;
import com.apsoft.scfb.ui.fragments.matches.MatchFragment;
import com.apsoft.scfb.utils.ImageLoader1;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    //private JSONObject json;
    //private String data = "";
    private String username;
    private String password;
    private boolean progressShow;
    boolean startMain = false;
    static public LoginActivity sLoginactivy;


    Handler mPostHandler = new Handler();
    ProgressDialog pd;
    private MainActivity s_mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sLoginactivy=this;
        s_mainActivity = MainActivity.s_mainActivity;

        //Intent it = new Intent(this, MapPosViewActivity.class);
        //startActivity(it);

        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        // 如果用户名密码都有，直接进入主页面
        /*
		if (AppAccountTool.getInstance().getUserName() != null
				&& AppAccountTool.getInstance().getPassword() != null) {
			usernameEditText.setText(AppAccountTool.getInstance().getUserName());
			passwordEditText.setText(AppAccountTool.getInstance().getPassword());
			try {
				login(null);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//finish();
		}*/
        // 如果用户名改变，清空密码
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                passwordEditText.setText(null);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        if (password == null || password.equals("") || username == null || username.equals("")) {
            Toast.makeText(this, "账号密码不能为空",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        final String newpassword = AppAccountTool.getInstance().makeServerPassword(password);
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            progressShow = true;
            pd = new ProgressDialog(LoginActivity.this);
            pd.setCanceledOnTouchOutside(false);
            pd.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    progressShow = false;
                }
            });
            pd.setMessage("正在登录...");
            pd.show();

            AppAccountTool.getInstance().doLogin(username, password, new AppAccountTool.ILoginInterface() {
                @Override
                public void onLoginSuccess(Object obj) {

                    s_mainActivity.switchFragment(2, 0);

                    TeamFragment2 teamFragment2 = (TeamFragment2) s_mainActivity.getSupportFragmentManager().findFragmentByTag("teamTag");
                    TextView tv = (TextView) teamFragment2.getView().findViewById(R.id.tv_team2_creatTeam);
                    if (User.getInstance().isTeamLeader()) {
                        tv.setText("编辑球队");
                    }else if (!User.getInstance().isTeamLeader()){
                        tv.setText("创建球队");
                    }
                    teamFragment2.forceRefesh();
                    MineFragment fragment = (MineFragment) s_mainActivity.getSupportFragmentManager().findFragmentByTag("mineTag");
                    View view = fragment.getView();
                    ImageView img = (ImageView) view.findViewById(R.id.iv_mine_head_photo);
                    TextView namefrag = (TextView) view.findViewById(R.id.tv_mine_player_name);

                    if (User.getInstance().getUser_photo() == null || User.getInstance().getUser_photo().length() == 0) {
                        img.setImageResource(R.drawable.icon_person);
                    } else {
                        ImageLoader1.getInstance().displayCricleImage(s_mainActivity, User.getInstance().getUser_photo(), img);
                    }
                    namefrag.setText(User.getInstance().getUser_name());

                    if (User.getInstance().isTeamJoinMatch()){
                        MatchFragment matchfrag = (MatchFragment) s_mainActivity.getSupportFragmentManager().findFragmentByTag("matchTag");
                        View viewmatch = matchfrag.getView();
                        ImageButton ivsign = (ImageButton) viewmatch.findViewById(R.id.ivbtn_sign_up);
                        ivsign.setVisibility(View.GONE);
                    }
                    LoginActivity.this.onLoginSuccess();
                }

                @Override
                public void onLoginFailed(Object obj) {
                    LoginActivity.this.onLoginFailed();
                }
            });

//			NetSCFBLogin.requestLogin(username, password, new BaseCallback<String>() {
//				@Override
//				public void onBeforeRequest(Request request) {
//
//				}
//
//				@Override
//				public void onFailure(Request request, Exception e) {
//
//				}
//
//				@Override
//				public void onResponse(Response response) {
//
//				}
//
//				@Override
//				public void onSuccess(Response response, String o) {
//					AppAccountTool.getInstance().setUserName(username);
//					AppAccountTool.getInstance().setPassword(password);
//					JSONObject jsonObject = JSONObject.parseObject(o);
//					jsonObject = jsonObject.getJSONObject("data");
//					User.getInstance().user_name = jsonObject.getString("name");
//					User.getInstance().office_id = jsonObject.getString("office_id");
//					User.getInstance().team_id = jsonObject.getString("team_id");
//					User.getInstance().office_id = jsonObject.getString("office_id");
//					User.getInstance().user_photo = jsonObject.getString("photo");
//					User.getInstance().user_sex = jsonObject.getString("sex");
//					User.getInstance().user_city = jsonObject.getString("city");
//					User.getInstance().setIs_login(true);
//					onLoginSuccess();
//				}
//
//				@Override
//				public void onError(Response response, int code, Exception e) {
//					onLoginFailed();
//				}
//			});
        }
    }

    /**
     * 注册
     *
     * @param view
     */
    public void register(View view) {
        //startActivityForResult(new Intent(this, RegisterActivity.class), 0);
        startActivity(new Intent(this, RegisterActivityNew.class));
    }

    public void newPassword(View view) {
        startActivity(new Intent(this, ResetPasswordActivity.class));
        finish();
    }



    @Override
    protected void onResume() {
        super.onResume();
//        if (User.getInstance().getIs_login()){
//            finish();
//        }
        if (AppAccountTool.getInstance().getUserName() != null && AppAccountTool.getInstance().getUserName().length() > 0
                && AppAccountTool.getInstance().getPassword() != null && AppAccountTool.getInstance().getPassword().length() > 0) {
            usernameEditText.setText(AppAccountTool.getInstance().getUserName());
            passwordEditText.setText(AppAccountTool.getInstance().getPassword());
            if (AppAccountTool.getInstance().getAutoLogin()) {
                try {
                    login(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (AppAccountTool.getInstance().getUserName() != null) {
            usernameEditText
                    .setText(AppAccountTool.getInstance().getUserName());
        }



    }

    boolean isPressExist = true;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isPressExist) {
                if (!User.getInstance().getIs_login()) {
                    s_mainActivity.switchFragment(0, 0);
                }
                finish();
                //System.exit(0);
            } else {
//				isPressExist = true;
//				Toast.makeText(this, getString(R.string.repressexit), Toast.LENGTH_SHORT).show();
//				mPostHandler.postDelayed(new Runnable() {
//
//					@Override
//					public void run() {
//						isPressExist = false;
//					}
//				}, 3000);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void onLoginSuccess() {
        if (pd != null) {
//            pd.dismiss();
            pd = null;
        }
        AppAccountTool.getInstance().setAutoLogin(true);
        startMain = (MainActivity.s_mainActivity == null);
        if (startMain) {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }
        if (!User.getInstance().is_login){
            ToastUtils.showToast(R.string.success_login);
        }
        finish();
    }

    public void onLoginFailed() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
        ToastUtils.showToast(R.string.error_pwdnotcorrect);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}




