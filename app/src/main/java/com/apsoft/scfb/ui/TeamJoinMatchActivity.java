package com.apsoft.scfb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.apsoft.scfb.R;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.MineFragment;
import com.apsoft.scfb.ui.fragments.matches.MatchFragment;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by admin on 2016/8/13.
 */
public class TeamJoinMatchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_join_match);
        Button btn = (Button) findViewById(R.id.btn_add_send);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String officeId = User.getInstance().getCurrentOffice();
                NetHomeQuery.requestTeamJoinMatch(officeId, new BaseCallback<String>() {
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
                        User.getInstance().setTeam_office(officeId);
                        MainActivity s_mainActivity = MainActivity.s_mainActivity;
                        MatchFragment fragment = (MatchFragment) s_mainActivity.getSupportFragmentManager().findFragmentByTag("matchTag");
                        View view = fragment.getView();
                        ImageButton ivsign = (ImageButton) view.findViewById(R.id.ivbtn_sign_up);
                        ivsign.setVisibility(View.GONE);

                        Toast.makeText(TeamJoinMatchActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {
                            Toast.makeText(TeamJoinMatchActivity.this, "请先加入一支球队", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
