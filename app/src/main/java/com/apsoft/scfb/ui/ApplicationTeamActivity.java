package com.apsoft.scfb.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppGlobalTool;
import com.apsoft.scfb.bean.MatchGameScheduleEntry;
import com.apsoft.scfb.bean.PersonalInfoEntry;
import com.apsoft.scfb.bean.TeamDetailEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.http.NetSCFBLogin;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.utils.ImageLoader1;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import java.util.ArrayList;
import java.util.List;

public class ApplicationTeamActivity extends AppCompatActivity {
    String schedule_id;
    MatchGameScheduleEntry.GameSchedule entry;
    List<TeamDetailEntry.TeamMember> members = new ArrayList<>();
    List<TeamDetailEntry.TeamMember> leftMembers = new ArrayList<>();
    List<TeamDetailEntry.TeamMember> rightMembers = new ArrayList<>();
    static public ApplicationTeamActivity sApplicationTeamActivity;
    private boolean isJoined;
    ProgressDialog progressDialog;
    boolean progressShow = false;
//    private String memberflag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        schedule_id = getIntent().getStringExtra("schedule_id");
        if(schedule_id == null){
            finish();
            return;
        }

        /**
         * 查询个人信息判断是否加入球队
         */
//        NetSCFBLogin.requestGetUserInfo(new BaseCallback<String >() {
//            @Override
//            public void onBeforeRequest(Request request) {
//
//            }
//
//            @Override
//            public void onFailure(Request request, Exception e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) {
//
//            }
//
//            @Override
//            public void onSuccess(Response response, String o) {
//                PersonalInfoEntry infoEntry = JSON.parseObject(o, PersonalInfoEntry.class);
//                PersonalInfoEntry.Person data = infoEntry.getData();
//                memberflag = data.getFlag();
//            }
//
//            @Override
//            public void onError(Response response, int code, Exception e) {
//
//            }
//        });

//        if(User.getInstance().getTeam_id()==null || User.getInstance().getTeam_id().length()<1){
//            finish();
//            Toast.makeText(this, "您不是球队成员", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (memberflag==null||memberflag.length()<=0){
//            finish();
//            Toast.makeText(this, "您不是球队成员", Toast.LENGTH_SHORT).show();
//            return;
//        }
        String json = getIntent().getStringExtra("schedule_json");
        entry = JSONObject.parseObject(json, MatchGameScheduleEntry.GameSchedule.class);
        setContentView(R.layout.activity_application_team2);
        sApplicationTeamActivity=this;
        initData();

        ImageView iv = (ImageView) findViewById(R.id.iv_left_teamhead_application);
        ImageLoader1.getInstance().displayImage(this, entry.getHome_team_image(), iv);

        iv = (ImageView) findViewById(R.id.iv_right_teamhead_application);
        ImageLoader1.getInstance().displayImage(this, entry.getAway_team_image(), iv);

        ImageView iv_teamcloth_left_top= (ImageView) findViewById(R.id.iv_teamcloth_left_top);
        ImageView iv_teamcloth_left_bottom= (ImageView) findViewById(R.id.iv_teamcloth_left_bottom);
        ImageView iv_teamcloth_right_top= (ImageView) findViewById(R.id.iv_teamcloth_right_top);
        ImageView iv_teamcloth_right_bottom= (ImageView) findViewById(R.id.iv_teamcloth_right_bottom);
        String home_team_upper = entry.getHome_team_upper();
        String home_team_lower = entry.getHome_team_lower();
        String away_team_upper = entry.getAway_team_upper();
        String away_team_lower = entry.getAway_team_lower();

        if (home_team_upper.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
            iv_teamcloth_left_top.setImageResource(AppGlobalTool.getCloseDrawableId(home_team_upper));
        }else {
            iv_teamcloth_left_top.setImageResource(AppGlobalTool.getCloseDrawableId("0"));
        }
        if (home_team_lower.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
            iv_teamcloth_left_bottom.setImageResource(AppGlobalTool.getPathDrawableId(home_team_lower));
        }else {
            iv_teamcloth_left_bottom.setImageResource(AppGlobalTool.getPathDrawableId("0"));
        }

        if (away_team_upper.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
            iv_teamcloth_right_top.setImageResource(AppGlobalTool.getCloseDrawableId(away_team_upper));
        }else {
            iv_teamcloth_right_top.setImageResource(AppGlobalTool.getCloseDrawableId("0"));
        }

        if (away_team_lower.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
            iv_teamcloth_right_bottom.setImageResource(AppGlobalTool.getPathDrawableId(away_team_lower));
        }else {
            iv_teamcloth_right_bottom.setImageResource(AppGlobalTool.getPathDrawableId("0"));
        }

        TextView tv = (TextView) findViewById(R.id.tv_left_teamname_item);
        tv.setText(entry.getHome_team_name());

        tv = (TextView) findViewById(R.id.tv_right_teamname_application);
        tv.setText(entry.getAway_team_name());

        tv = (TextView) findViewById(R.id.ll_scedule_time);
        tv.setText(entry.getDatetime().substring(0,10));

        tv = (TextView) findViewById(R.id.tv_time_child_application);
        String timePart = "";
        if(entry.getDatetime() != null){
            timePart = entry.getDatetime().substring(11,16);
        }
        tv.setText(timePart);
        tv = (TextView) findViewById(R.id.tv_place_child_application);
        tv.setText(entry.getPlace());

        tv = (TextView) findViewById(R.id.ll_join_match);
//        if(!entry.getHome_id().equalsIgnoreCase(User.getInstance().getTeam_id()) && !entry.getAway_id().equalsIgnoreCase(User.getInstance().getTeam_id())){
//            tv.setEnabled(false);
//        }
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!entry.getHome_id().equalsIgnoreCase(User.getInstance().getTeam_id()) && !entry.getAway_id().equalsIgnoreCase(User.getInstance().getTeam_id())){
                    Toast.makeText(ApplicationTeamActivity.this, "您不是这两只球队的成员", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!User.getInstance().isMemberAccept()){
                    Toast.makeText(ApplicationTeamActivity.this, "您还不是正式成员", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isJoined){
                    new AlertDialog.Builder(sApplicationTeamActivity)
                            .setMessage("您确定要报名参赛吗？")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    showProgressDialog("正在报名参赛...");
                                    NetHomeQuery.requestUseJoinTeamMatch( schedule_id, new BaseCallback<String>() {
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
                                            hideProgressDialog();
                                            Toast.makeText(ApplicationTeamActivity.this, "报名成功", Toast.LENGTH_SHORT).show();
                                            isJoined=true;
                                            initData();
                                        }

                                        @Override
                                        public void onError(Response response, int code, Exception e) {

                                        }
                                    });
                                }
                            })
                            .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(ApplicationTeamActivity.this, "再考虑一下", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }else if (isJoined){
                    Toast.makeText(ApplicationTeamActivity.this, "您已经报过名了", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv = (TextView) findViewById(R.id.ll_stop_join_match);
//        tv.setEnabled(false);
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                if(!entry.getHome_id().equalsIgnoreCase(User.getInstance().getTeam_id()) && !entry.getAway_id().equalsIgnoreCase(User.getInstance().getTeam_id())){
//                    Toast.makeText(ApplicationTeamActivity.this, "您不是这两只球队的成员", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                for(int i=0; i<members.size(); ++i) {
                    TeamDetailEntry.TeamMember tm = members.get(i);
                    if (tm.getId().equalsIgnoreCase(User.getInstance().getMember_id())) {
                        isJoined = true;
                    }
                }
                if (!isJoined){
                    Toast.makeText(ApplicationTeamActivity.this, "您还没有报名参加", Toast.LENGTH_SHORT).show();
                    return;
                }else if (isJoined){
                    new AlertDialog.Builder(sApplicationTeamActivity)
                            .setMessage("您真的要放弃这次比赛请假吗？")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    showProgressDialog("您正在请假...");
                                    NetHomeQuery.requestStopJoinMatch( schedule_id,  new BaseCallback<String>() {
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
                                            hideProgressDialog();
                                            Toast.makeText(ApplicationTeamActivity.this, "请假成功", Toast.LENGTH_SHORT).show();
                                            isJoined=false;
                                            initData();
                                        }

                                        @Override
                                        public void onError(Response response, int code, Exception e) {

                                        }
                                    });
                                }
                            })
                            .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(ApplicationTeamActivity.this, "您已放弃请假", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }

            }
        });
    }

    public void initData(){
        //NetHomeQuery.requestGetTeamJoinMembers(schedule_id, new BaseCallback<String>() {
        NetHomeQuery.requestScheduleJoinMembers(schedule_id, new BaseCallback<String>() {
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
                JSONObject obj = JSON.parseObject(o);
                JSONArray array = obj.getJSONArray("data");
                members.clear();
                leftMembers.clear();
                rightMembers.clear();
                for(int i=0; i<array.size(); ++i){
                    JSONObject obj2 = array.getJSONObject(i);
                    TeamDetailEntry.TeamMember tm = JSON.toJavaObject(obj2, TeamDetailEntry.TeamMember.class);
                    members.add(tm);
                    if(tm.getTeam_id()!=null && tm.getTeam_id().equalsIgnoreCase(entry.getHome_id())){
                        leftMembers.add(tm);
                    }else{
                        rightMembers.add(tm);
                    }
                }
                String cf="";//前锋
                String cm="";//中场
                String dc="";//后卫
                String gk="";//门将
//                if (entry.getHome_id().equalsIgnoreCase(User.getInstance().getTeam_id())){
                {
                    for(int i=0; i<leftMembers.size(); ++i){
                        TeamDetailEntry.TeamMember tm = leftMembers.get(i);
                        if(tm.getId().equalsIgnoreCase(User.getInstance().getMember_id()) ){
                            isJoined = true;
                        }
                        String v = TeamDetailEntry.getNameByPosition(tm.getPosition());
                        if(v.equalsIgnoreCase("前锋")){
                            if (tm.getNumber()==null||tm.getNumber().length()<=0){
                                tm.setNumber("0");
                            }
                            cf += tm.getNumber() + "/" + tm.getName() + " ";
                        }else if(v.equalsIgnoreCase("中场")){
                            if (tm.getNumber()==null||tm.getNumber().length()<=0){
                                tm.setNumber("0");
                            }
                            cm += tm.getNumber() + "/" + tm.getName() + " ";
                        }else if(v.equalsIgnoreCase("后卫")){
                            if (tm.getNumber()==null||tm.getNumber().length()<=0){
                                tm.setNumber("0");
                            }
                            dc += tm.getNumber() + "/" + tm.getName() + " ";
                        }else if(v.equalsIgnoreCase("门将")){
                            if (tm.getNumber()==null||tm.getNumber().length()<=0){
                                tm.setNumber("0");
                            }
                            gk += tm.getNumber() + "/" + tm.getName() + " ";
                        }
                    }
                    TextView tv = (TextView) findViewById(R.id.ll_team_gk_name_left);
                    tv.setText(gk);
                    tv = (TextView) findViewById(R.id.ll_team_md_name_left);
                    tv.setText(cm);
                    tv = (TextView) findViewById(R.id.ll_team_fw_name_left);
                    tv.setText(cf);
                    tv = (TextView) findViewById(R.id.ll_team_guard_name_left);
                    tv.setText(dc);
                    tv = (TextView) findViewById(R.id.ll_join_match_status_left);
                    if(isJoined){
                        tv.setText("已报名");
//                        tv = (TextView) findViewById(R.id.ll_join_match);
//                    tv.setEnabled(false);
//                        tv = (TextView) findViewById(R.id.ll_stop_join_match);
//                    tv.setEnabled(true);
                    }else{
                        tv.setText("未报名");
//                        tv = (TextView) findViewById(R.id.ll_join_match);
//                        if(entry.getHome_id().equalsIgnoreCase(User.getInstance().getTeam_id()) || entry.getAway_id().equalsIgnoreCase(User.getInstance().getTeam_id())){
//                        tv.setEnabled(true);
//                        }else{
//                        tv.setEnabled(false);
//                        }
//                        tv = (TextView) findViewById(R.id.ll_stop_join_match);
//                    tv.setEnabled(false);
                    }
                }
//                else {                                    ////////////////////////////////////////////////////////////////////////////////////

                cf="";//前锋
                cm="";//中场
                dc="";//后卫
                gk="";//门将
                {
                    for(int i=0; i<rightMembers.size(); ++i){
                        TeamDetailEntry.TeamMember tm = rightMembers.get(i);
                        if(tm.getId().equalsIgnoreCase(User.getInstance().getMember_id()) ){
                            isJoined = true;
                        }
                        String v = TeamDetailEntry.getNameByPosition(tm.getPosition());
                        if(v.equalsIgnoreCase("前锋")){
                            if (tm.getNumber()==null||tm.getNumber().length()<=0){
                                tm.setNumber("0");
                            }
                            cf += tm.getNumber() + "/" + tm.getName() + " ";
                        }else if(v.equalsIgnoreCase("中场")){
                            if (tm.getNumber()==null||tm.getNumber().length()<=0){
                                tm.setNumber("0");
                            }
                            cm += tm.getNumber() + "/" + tm.getName() + " ";
                        }else if(v.equalsIgnoreCase("后卫")){
                            if (tm.getNumber()==null||tm.getNumber().length()<=0){
                                tm.setNumber("0");
                            }
                            dc += tm.getNumber() + "/" + tm.getName() + " ";
                        }else if(v.equalsIgnoreCase("门将")){
                            if (tm.getNumber()==null||tm.getNumber().length()<=0){
                                tm.setNumber("0");
                            }
                            gk += tm.getNumber() + "/" + tm.getName() + " ";
                        }
                    }
                    TextView tv = (TextView) findViewById(R.id.ll_team_gk_name_right);
                    tv.setText(gk);
                    tv = (TextView) findViewById(R.id.ll_team_md_name_right);
                    tv.setText(cm);
                    tv = (TextView) findViewById(R.id.ll_team_fw_name_right);
                    tv.setText(cf);
                    tv = (TextView) findViewById(R.id.ll_team_guard_name_right);
                    tv.setText(dc);
                    tv = (TextView) findViewById(R.id.ll_join_match_status_right);
                    if(isJoined){
                        tv.setText("已报名");
//                        tv = (TextView) findViewById(R.id.ll_join_match);
//                    tv.setEnabled(false);
//                        tv = (TextView) findViewById(R.id.ll_stop_join_match);
//                    tv.setEnabled(true);
                    }else {
                        tv.setText("未报名");
//                        tv = (TextView) findViewById(R.id.ll_join_match);
//                        if (entry.getHome_id().equalsIgnoreCase(User.getInstance().getTeam_id()) || entry.getAway_id().equalsIgnoreCase(User.getInstance().getTeam_id())) {
//                        tv.setEnabled(true);
//                        } else {
//                        tv.setEnabled(false);
//                        }
//                        tv = (TextView) findViewById(R.id.ll_stop_join_match);
//                    tv.setEnabled(false);
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    public void showProgressDialog(String text){
        hideProgressDialog();
        progressShow = true;
        progressDialog = new ProgressDialog(sApplicationTeamActivity);
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
