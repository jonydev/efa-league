package com.apsoft.scfb.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.TeamDetailEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.adapter.team.TeamMemberAdapter;
import com.apsoft.scfb.utils.ImageLoader1;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.simple.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeamDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout llTeamResult;
    private Button btnAddToTeam;
    String team_id;
    TeamDetailEntry teamDetailEntry;
    TeamMemberAdapter teamMemberAdapter;
    ListView teamMemberList;
    public static TeamDetailActivity sTeamDetailActivity;
    ScrollView baseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        sTeamDetailActivity=this;
        baseView = (ScrollView) findViewById(R.id.base_layout);
        llTeamResult = (LinearLayout) findViewById(R.id.ll_team_result);
        btnAddToTeam = (Button) findViewById(R.id.btn_add_to_team);
        teamMemberList = (ListView) findViewById(R.id.lv_team_member);
        teamMemberAdapter = new TeamMemberAdapter(this);
        teamMemberList.setAdapter(teamMemberAdapter);
        llTeamResult.setOnClickListener(this);
        btnAddToTeam.setOnClickListener(this);
        team_id = getIntent().getStringExtra("team_id");
        queryData();
    }

    static String zORs(String v){
        if(v == null){
            return "0";
        }else{
            return v;
        }
    }

    public void refreshUI() {
        String photo = teamDetailEntry.getTeam().getPhoto();
        if (photo != null && photo.length() > 0) {
            ImageView iv = (ImageView) findViewById(R.id.iv_team_logo);
            ImageLoader1.getInstance().displayCricleImage(this, photo, iv);
        }
        TextView detailView = (TextView) findViewById(R.id.tv_team_detail_teamName);
        detailView.setText(teamDetailEntry.getTeam().getName()+"足球队");
        detailView = (TextView) findViewById(R.id.tv_teamName);
        detailView.setText(teamDetailEntry.getTeam().getName());
        TextView leaderView = (TextView) findViewById(R.id.tv_team_leader);
        leaderView.setText("领队:" + teamDetailEntry.getTeam().getLeaderShowName());
        TextView teamHeader = (TextView) findViewById(R.id.tv_team_header);
        teamHeader.setText("队长:" + teamDetailEntry.getTeam().getCaptainShowName());
        TextView resultView = (TextView) findViewById(R.id.ll_team_v_result);
        resultView.setText("胜/" + zORs(teamDetailEntry.getTeam().getWin()) + " 平/" + zORs(teamDetailEntry.getTeam().getFlat()) + " 负/" + zORs(teamDetailEntry.getTeam().getLoss()) + " 胜率:" + zORs(teamDetailEntry.getTeam().getWinning()));
        Integer cf=0;//前锋
        Integer cm=0;//中场
        Integer dc=0;//后卫
        Integer gk=0;//门将
        for(int i=0; i<teamDetailEntry.getTeam_members().size(); ++i){
            TeamDetailEntry.TeamMember tm = teamDetailEntry.getTeam_members().get(i);
            String v = TeamDetailEntry.getNameByPosition(tm.getPosition());
            if(v.equalsIgnoreCase("前锋")){
                cf += 1;
            }else if(v.equalsIgnoreCase("中场")){
                cm += 1;
            }else if(v.equalsIgnoreCase("后卫")){
                dc += 1;
            }else if(v.equalsIgnoreCase("门将")){
                gk += 1;
            }
        }
        {
            TextView tv = (TextView) findViewById(R.id.tv_gk_number);
            tv.setText(String.valueOf(gk));
        }
        {
            TextView tv = (TextView) findViewById(R.id.tv_houwei_num);
            tv.setText(String.valueOf(dc));
        }
        {
            TextView tv = (TextView) findViewById(R.id.tv_cw_num);
            tv.setText(String.valueOf(cm));
        }
        {
            TextView tv = (TextView) findViewById(R.id.tv_cf_num);
            tv.setText(String.valueOf(cf));
        }
        TextView tvCreateTime = (TextView) findViewById(R.id.tv_team_creat_time);
        String time = teamDetailEntry.getTeam().getDatetime().substring(0, 10);
        tvCreateTime.setText("球队创建于" + time);
            TextView tvTeamModel = (TextView) findViewById(R.id.tv_team_model);
            tvTeamModel.setText( teamDetailEntry.getTeam().getContent());
        teamMemberAdapter.updateData(teamDetailEntry.getTeam_members(),teamDetailEntry.getTeam().getLeaderShowName());
        baseView.smoothScrollTo(0, 20);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_team_result:
                Intent intent=new Intent(getApplicationContext(),TeamResultActivity.class);
                intent.putExtra("team_id", team_id);
                startActivity(intent);
                break;
            case R.id.btn_add_to_team:
                if(!User.getInstance().getIs_login()){
                    Intent it = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(it);
                    return;
                }
                Intent intent1=new Intent(getApplicationContext(),AddToTeamActivity.class);
                intent1.putExtra("team_id", team_id);
                startActivity(intent1);
                break;
        }
    }

    public void queryData(){
        NetHomeQuery.requestTeamDetail(team_id, new BaseCallback<String>() {
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
                JSONObject obj = JSONObject.parseObject(o);
                obj = obj.getJSONObject("data");
                teamDetailEntry = JSONObject.toJavaObject(obj, TeamDetailEntry.class);
                refreshUI();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }
}
