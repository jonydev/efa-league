package com.apsoft.scfb.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchGameScheduleEntry;
import com.apsoft.scfb.bean.TeamScheduleEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.ui.fragments.team_result.AllFragment;
import com.apsoft.scfb.ui.fragments.team_result.CompletedFragment;
import com.apsoft.scfb.ui.fragments.team_result.NotStartFragment;
import com.apsoft.scfb.ui.fragments.team_result.ToEvaluateFragment;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

public class TeamResultActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private FragmentManager manager;
    private RadioGroup rgTeamResult;
    private RadioButton rbNotStart;
    private FragmentTransaction fragmentTransaction;
    private NotStartFragment notStartFragment;
    private CompletedFragment completedFragment;
    private ToEvaluateFragment toEvaluateFragment;
    private AllFragment allFragment;
    private String team_id;
    TeamScheduleEntry teamScheduleEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_result);
        team_id = getIntent().getStringExtra("team_id");
        manager = getSupportFragmentManager();
        rgTeamResult = (RadioGroup) findViewById(R.id.rg_teamResult);
        rbNotStart = (RadioButton) findViewById(R.id.rb_notStart);
        rgTeamResult.setOnCheckedChangeListener(this);
        rbNotStart.setChecked(true);
        queryData();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        fragmentTransaction = manager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (checkedId){
            case R.id.rb_notStart:
                if (notStartFragment==null){
                    notStartFragment=NotStartFragment.newInstance();
                    fragmentTransaction.add(R.id.fl_contain_teamResult,notStartFragment);
                }else {
                    fragmentTransaction.show(notStartFragment);
                }
                notStartFragment.setData(getNotStart());
                break;
            case R.id.rb_complete:
                if (completedFragment == null) {
                    completedFragment = CompletedFragment.newInstance();
                    fragmentTransaction.add(R.id.fl_contain_teamResult, completedFragment);
                } else {
                    fragmentTransaction.show(completedFragment);
                }
                completedFragment.setData(getFinished());
                break;
            case R.id.rb_toEvaluate:
                if (toEvaluateFragment == null) {
                    toEvaluateFragment = ToEvaluateFragment.newInstance();
                    fragmentTransaction.add(R.id.fl_contain_teamResult, toEvaluateFragment);
                } else {
                    fragmentTransaction.show(toEvaluateFragment);
                }
                toEvaluateFragment.setData(getFinished());
                break;
            case R.id.rb_All:
                if (allFragment == null) {
                    allFragment = AllFragment.newInstance();
                    fragmentTransaction.add(R.id.fl_contain_teamResult, allFragment);
                } else {
                    fragmentTransaction.show(allFragment);
                }
                allFragment.setData(getAll());
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (notStartFragment!=null){
            fragmentTransaction.hide(notStartFragment);
        }
        if (completedFragment!=null){
            fragmentTransaction.hide(completedFragment);
        }
        if (toEvaluateFragment!=null){
            fragmentTransaction.hide(toEvaluateFragment);
        }
        if (allFragment!=null){
            fragmentTransaction.hide(allFragment);
        }
    }

    private void queryData(){
        NetHomeQuery.requestTeamScheduleList(team_id, new BaseCallback<String>() {
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
//                teamScheduleEntry = new TeamScheduleEntry();
//                teamScheduleEntry.setData(new ArrayList<TeamScheduleEntry.GameSchedule>());
//                JSONArray dataArray=  JSONObject.parseObject(o).getJSONArray("data");
//                for(int i=0; i<dataArray.size(); ++i){
//                    JSONObject object = dataArray.getJSONObject(i);
//                    TeamScheduleEntry.GameSchedule schedule = JSONObject.toJavaObject(object, TeamScheduleEntry.GameSchedule.class);
//                    teamScheduleEntry.getData().add(schedule);
//                }

                teamScheduleEntry = JSON.parseObject(o, TeamScheduleEntry.class);
                if(allFragment!=null){
                    allFragment.setData(getAll());
                }
                if(notStartFragment!=null){
                    notStartFragment.setData(getNotStart());
                }if (completedFragment!=null){
                    completedFragment.setData(getFinished());
                }
                if (toEvaluateFragment!=null){
                    toEvaluateFragment.setData(getFinished());
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    public List<TeamScheduleEntry.GameSchedule> getAll(){

        if( teamScheduleEntry!=null&&teamScheduleEntry.getData() != null){
            return teamScheduleEntry.getData();
        }else{
            List<TeamScheduleEntry.GameSchedule> s = new ArrayList<TeamScheduleEntry.GameSchedule>();
            return s;
        }
    }

    public List<TeamScheduleEntry.GameSchedule> getNotStart(){
        List<TeamScheduleEntry.GameSchedule> notFinishSchedule = new ArrayList<TeamScheduleEntry.GameSchedule>();
        if(teamScheduleEntry==null || teamScheduleEntry.getData() == null){
            return notFinishSchedule;
        }
        for(int i=0; i<teamScheduleEntry.getData().size(); ++i){
            TeamScheduleEntry.GameSchedule schedule = teamScheduleEntry.getData().get(i);
            if(schedule.getFlag()!=null && schedule.getFlag().equalsIgnoreCase("1")){

            }else{
                notFinishSchedule.add(schedule);
            }
        }
        return notFinishSchedule;
    }

    public List<TeamScheduleEntry.GameSchedule> getFinished(){
        List<TeamScheduleEntry.GameSchedule> finishSchedule = new ArrayList<TeamScheduleEntry.GameSchedule>();
        if(teamScheduleEntry==null || teamScheduleEntry.getData() == null){
            return finishSchedule;
        }
        for(int i=0; i<teamScheduleEntry.getData().size(); ++i){
            TeamScheduleEntry.GameSchedule schedule = teamScheduleEntry.getData().get(i);
            if(schedule.getHomescore()!=null && schedule.getHomescore().length()>0){
                finishSchedule.add(schedule);
            }
        }
        return finishSchedule;
    }


}
