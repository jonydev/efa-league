package com.apsoft.scfb.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchGameScheduleEntry;
import com.apsoft.scfb.bean.ScheduleDetailEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.ui.adapter.match.ChangePeopleAdapter;
import com.apsoft.scfb.utils.ImageLoader1;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Match_Detail_Activity extends AppCompatActivity {

    @BindView(R.id.iv_left_team_icon)
    ImageView ivLeftTeamIcon;
    @BindView(R.id.tv_left_team_name)
    TextView tvLeftTeamName;
    @BindView(R.id.tv_left_team_score)
    TextView tvLeftTeamScore;
    @BindView(R.id.tv_right_team_score)
    TextView tvRightTeamScore;
    @BindView(R.id.iv_right_team_icon)
    ImageView ivRightTeamIcon;
    @BindView(R.id.tv_right_team_name)
    TextView tvRightTeamName;
    @BindView(R.id.tv_join_player)
    TextView tvJoinPlayer;
    @BindView(R.id.tv_left_player)
    TextView tvLeftPlayer;
    @BindView(R.id.tv_divider)
    TextView tvDivider;
    @BindView(R.id.tv_right_player)
    TextView tvRightPlayer;
    @BindView(R.id.tv_judge_group)
    TextView tvJudgeGroup;
    @BindView(R.id.tv_primary_judger)
    TextView tvPrimaryJudger;
    @BindView(R.id.tv_second_judger)
    TextView tvSecondJudger;
    @BindView(R.id.tv_fourth_officious)
    TextView tvFourthOfficious;
    @BindView(R.id.tv_race_recode)
    TextView tvRaceRecode;
    @BindView(R.id.ll_match_change_people)
    RecyclerView llMatchChangePeople;
    @BindView(R.id.ratingbar_exact)
    RatingBar ratingbarExact;
    @BindView(R.id.tv_judge_fair)
    TextView tvJudgeFair;
    @BindView(R.id.ratingbar_fair)
    RatingBar ratingbarFair;
    @BindView(R.id.tv_race_control)
    TextView tvRaceControl;
    @BindView(R.id.ratingbar_race_contorl)
    RatingBar ratingbarRaceContorl;
    @BindView(R.id.btn_sub)
    Button btnSub;

    ChangePeopleAdapter changePeopleAdapter;

    private boolean progressShow;
    ProgressDialog progressDialog;
    MatchGameScheduleEntry.GameSchedule schedule;
    ScheduleDetailEntry entry;

    String schedule_id;

    static class r_op{
        public String name;
        public String dt;
        public String type;
        public boolean home_or_away;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match__detail_);
        ButterKnife.bind(this);
        schedule_id = getIntent().getStringExtra("schedule_id");
        String scheduleJson = getIntent().getStringExtra("schedule_json");
        schedule = JSON.parseObject(scheduleJson, MatchGameScheduleEntry.GameSchedule.class);

        changePeopleAdapter = new ChangePeopleAdapter(this);
        llMatchChangePeople.setAdapter(changePeopleAdapter);

        initView();

        NetHomeQuery.requestScheduleDetail(schedule_id, new BaseCallback<String>() {
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
                JSONObject jsonObject = JSONObject.parseObject(o);
                jsonObject = jsonObject.getJSONObject("data");
                entry = JSONObject.toJavaObject(jsonObject, ScheduleDetailEntry.class);
                for(int i=0; i<entry.getHome_ex().size(); ++i){
                    entry.getHome_ex().get(i).setIs_home(true);
                    entry.getHome_ex().get(i).setAlternate_name(getFAMemberNameById(entry.getHome_ex().get(i).getAlternate_id(), true));
                }
                for(int i=0; i<entry.getAway_ex().size(); ++i){
                    entry.getAway_ex().get(i).setIs_home(false);
                    entry.getAway_ex().get(i).setAlternate_name(getFAMemberNameById(entry.getAway_ex().get(i).getAlternate_id(), false));
                }
                String homeAppears = "";
                if(entry.getHome_bases()!=null){
                    for(int i = 0; i<entry.getHome_bases().size(); ++i){
                        ScheduleDetailEntry.FirstAppearance fa = entry.getHome_bases().get(i);
                        homeAppears += fa.getMember_name();
                        homeAppears += "   ";
                        if( (i+1)%2 == 0){
                            homeAppears += "\n";
                        }
                    }
                }

                tvLeftPlayer.setText(homeAppears);
                String awayAppears = "";
                if(entry.getAway_base()!=null){
                    for(int i=0; i<entry.getAway_base().size(); ++i){
                        ScheduleDetailEntry.FirstAppearance fa = entry.getAway_base().get(i);
                        awayAppears += fa.getMember_name();
                        awayAppears += "   ";
                        if( (i+1)%2 == 0){
                            awayAppears += "\n";
                        }
                    }
                }
                tvRightPlayer.setText(awayAppears);
                List<ScheduleDetailEntry.LaterAppearance> appearances = new ArrayList<ScheduleDetailEntry.LaterAppearance>();
                appearances.addAll(entry.getHome_ex());
                appearances.addAll(entry.getAway_ex());
                changePeopleAdapter.updateData(appearances);
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                hideProgressDialog();
            }
        });
        refreshUI();

    }


    public void refreshUI(){
        ImageLoader1.getInstance().displayImage(this,schedule.getHome_team_image(),ivLeftTeamIcon);
        ImageLoader1.getInstance().displayImage(this,schedule.getAway_team_image(),ivRightTeamIcon);
        tvLeftTeamName.setText(schedule.getHome_team_name());
        tvRightTeamName.setText(schedule.getAway_team_name());
        tvLeftTeamScore.setText(schedule.getHomescore());
        tvRightTeamScore.setText(schedule.getAwayscore());


        tvPrimaryJudger.setText(schedule.getMain_referee().getName());
        tvSecondJudger.setText(schedule.getLeft_umpire().getName() + "," + schedule.getRight_umpire().getName());
        tvFourthOfficious.setText("");
    }
    public void initView(){

        llMatchChangePeople.setHasFixedSize(true);



        ///提交申诉报告
        btnSub.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String content = "";
                int rule = ratingbarExact.getNumStars();
                int fair = ratingbarFair.getNumStars();
                int control = ratingbarRaceContorl.getNumStars();
                NetHomeQuery.requestEvaluateReferee(schedule.getOffice_id(), schedule_id,rule, fair, control, content, new BaseCallback<String>() {
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
                    public void onSuccess(Response response, String s) {
                        Toast.makeText(Match_Detail_Activity.this, "评价成功", Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {
                        Toast.makeText(Match_Detail_Activity.this, "评价失败", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void showProgressDialog(String text){
        if(progressShow){
            return;
        }
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

    private String getFAMemberNameById(String memberId, boolean isHome){
        List<ScheduleDetailEntry.FirstAppearance> faMembers = null;
        if(isHome){
            faMembers = entry.getHome_bases();
        }else{
            faMembers = entry.getAway_base();
        }
        for(int i=0; i<faMembers.size(); ++i){
            if(faMembers.get(i).getMember_id().equalsIgnoreCase(memberId)){
                return faMembers.get(i).getMember_name();
            }
        }
        return "";
    }
}
