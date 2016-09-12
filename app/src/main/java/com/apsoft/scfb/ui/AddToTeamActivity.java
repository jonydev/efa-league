package com.apsoft.scfb.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.apsoft.scfb.R;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.Team.TeamFragment2;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class AddToTeamActivity extends AppCompatActivity {
    String team_id;
    ProgressDialog progressDialog;
    boolean progressShow = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_team);
        if(User.getInstance().getTeam_id()!=null && User.getInstance().getTeam_id().length()>0){
            Toast.makeText(this, "您已加入球队，不能重复申请", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        team_id = getIntent().getStringExtra("team_id");
        Button btn = (Button) findViewById(R.id.btn_add_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog("正在加入球队...");
                NetHomeQuery.requestJoinTeam(team_id, new BaseCallback<String>() {
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
                        User.getInstance().setTeam_id(team_id);
                        Toast.makeText(getApplicationContext(), "申请成功,请等待对方回应", Toast.LENGTH_LONG).show();
                        MainActivity s_mainActivity = MainActivity.s_mainActivity;
                        TeamFragment2 teamFragment2 = (TeamFragment2) s_mainActivity.getSupportFragmentManager().findFragmentByTag("teamTag");
                        teamFragment2.forceRefesh();
                        hideProgressDialog();
                        finish();
                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {

                    }
                });
            }
        });
    }

    public void showProgressDialog(String text){
        hideProgressDialog();
        progressShow = true;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                progressShow = false;
            }
        });

        progressDialog.setMessage(text);
        progressDialog.show();
    }

    public void hideProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog=null;
            progressShow=false;
        }
    }
}
