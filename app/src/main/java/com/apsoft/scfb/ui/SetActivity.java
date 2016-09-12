package com.apsoft.scfb.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.anywhere.utils.ToastUtils;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppAccountTool;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetSCFBLogin;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.MineFragment;
import com.apsoft.scfb.ui.fragments.Team.TeamFragment2;
import com.apsoft.scfb.ui.fragments.matches.MatchFragment;
import com.apsoft.scfb.utils.ImageLoader1;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class SetActivity extends AppCompatActivity {

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(!User.getInstance().getIs_login()){
                    findViewById(R.id.logout).setEnabled(false);
                    ToastUtils.showToast("您还未登录");
                }

                NetSCFBLogin.requestLogout(new BaseCallback<String>() {
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
                    public void onSuccess(Response response, String o) {
                        AppAccountTool.getInstance().logout();
                        /**
                         * 实时刷新minefragment里面的数据(fragment和activity之间的通信)
                         */
                        MainActivity s_mainActivity = MainActivity.s_mainActivity;
                        MineFragment fragment = (MineFragment) s_mainActivity.getSupportFragmentManager().findFragmentByTag("mineTag");
                        View view = fragment.getView();
                        ImageView img = (ImageView) view.findViewById(R.id.iv_mine_head_photo);
                        TextView namefrag = (TextView) view.findViewById(R.id.tv_mine_player_name);
                        img.setImageResource(R.drawable.icon_person);

                        MatchFragment matchfrag = (MatchFragment) s_mainActivity.getSupportFragmentManager().findFragmentByTag("matchTag");
                        View viewmatch = matchfrag.getView();
                        ImageButton ivsign = (ImageButton) viewmatch.findViewById(R.id.ivbtn_sign_up);
                        ivsign.setVisibility(View.VISIBLE);

                        TeamFragment2 teamFragment2 = (TeamFragment2) s_mainActivity.getSupportFragmentManager().findFragmentByTag("teamTag");
                        TextView tv = (TextView) teamFragment2.getView().findViewById(R.id.tv_team2_creatTeam);
                        tv.setText("创建球队");
                        teamFragment2.forceRefesh();

                        if (User.getInstance().getIs_login()) {
                            ToastUtils.showToast("退出登录");
                        }

                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {

                    }
                });
            }
        });
    }

//    }
}
