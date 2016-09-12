package com.apsoft.scfb.ui.fragments.matches;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchScoreEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.matches.racelist.GetGoalFragment;
import com.apsoft.scfb.ui.fragments.matches.racelist.HelpGoalFragment;
import com.apsoft.scfb.ui.fragments.matches.racelist.RedAndYellowFragment;
import com.apsoft.scfb.ui.fragments.matches.racelist.ScoreFragment;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RaceListFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

    private View view;
    private RadioGroup rgRaceList;
    private RadioButton rbRaceScore;
    private FragmentManager manager;
    private FragmentTransaction fragmentTransaction;
    private ScoreFragment scoreFragment;
    private GetGoalFragment getGoalFragment;
    private HelpGoalFragment helpGoalFragment;
    private RedAndYellowFragment redAndYellowFragment;
    private RadioButton rb_get_score;
    private RadioButton rb_help_score;
    private RadioButton rb_red_yellow_card;

    private Fragment currentFrag;
    private int currentIndex;

    MatchScoreEntry matchScoreEntry;
    String lastOfficeId;

    public RaceListFragment() {
    }

    public static RaceListFragment newInstance() {

        Bundle args = new Bundle();

        RaceListFragment fragment = new RaceListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_race_list, container, false);
        initview();
        manager = getChildFragmentManager();
        rgRaceList.setOnCheckedChangeListener(this);
        rbRaceScore.setChecked(true);
        queryData();
        return view;
}

    /**
     * 设置每次进入榜单都是默认进入积分界面
     * @param index
     */
    public void setSwichFrag(int index){
        if (view==null){
            return;
        }else {
            fragmentTransaction = manager.beginTransaction();
            hideAllFragment(fragmentTransaction);
            switch (index){
                case 0:
                    rbRaceScore.setTextColor(Color.parseColor("#274082"));
                    rb_get_score.setTextColor(Color.parseColor("#333333"));
                    rb_help_score.setTextColor(Color.parseColor("#333333"));
                    rb_red_yellow_card.setTextColor(Color.parseColor("#333333"));
                    if (scoreFragment==null){
                        scoreFragment= ScoreFragment.newInstance();
                        fragmentTransaction.add(R.id.fl_race_list,scoreFragment);
                    }else {
                        fragmentTransaction.show(scoreFragment);
                    }
                    if(matchScoreEntry != null) {
                        scoreFragment.setData(matchScoreEntry.getData().getIntegrals());
                    }
                    currentFrag=scoreFragment;
                    currentIndex=0;
                    break;
                case 1:
                    rbRaceScore.setTextColor(Color.parseColor("#333333"));
                    rb_get_score.setTextColor(Color.parseColor("#274082"));
                    rb_help_score.setTextColor(Color.parseColor("#333333"));
                    rb_red_yellow_card.setTextColor(Color.parseColor("#333333"));
                    if (getGoalFragment ==null){
                        getGoalFragment = GetGoalFragment.newInstance();
                        fragmentTransaction.add(R.id.fl_race_list, getGoalFragment);
                    }else {
                        fragmentTransaction.show(getGoalFragment);
                    }
                    if(matchScoreEntry!=null){
                        getGoalFragment.setData(matchScoreEntry.getData().getGoals());
                    }
                    currentFrag=getGoalFragment;
                    currentIndex=1;
                    break;
                case 2:
                    rbRaceScore.setTextColor(Color.parseColor("#333333"));
                    rb_get_score.setTextColor(Color.parseColor("#333333"));
                    rb_help_score.setTextColor(Color.parseColor("#274082"));
                    rb_red_yellow_card.setTextColor(Color.parseColor("#333333"));
                    if (helpGoalFragment ==null){
                        helpGoalFragment = HelpGoalFragment.newInstance();
                        fragmentTransaction.add(R.id.fl_race_list, helpGoalFragment);
                    }else {
                        fragmentTransaction.show(helpGoalFragment);
                    }
                    if(matchScoreEntry!=null){
                        helpGoalFragment.setData(matchScoreEntry.getData().getHelp_goals());
                    }
                    currentFrag=helpGoalFragment;
                    currentIndex=2;
                    break;
                case 3:
                    rbRaceScore.setTextColor(Color.parseColor("#333333"));
                    rb_get_score.setTextColor(Color.parseColor("#333333"));
                    rb_help_score.setTextColor(Color.parseColor("#333333"));
                    rb_red_yellow_card.setTextColor(Color.parseColor("#274082"));
                    if (redAndYellowFragment==null){
                        redAndYellowFragment= RedAndYellowFragment.newInstance();
                        fragmentTransaction.add(R.id.fl_race_list,redAndYellowFragment);
                    }else {
                        fragmentTransaction.show(redAndYellowFragment);
                    }
                    if(matchScoreEntry!=null){
                        redAndYellowFragment.setData(matchScoreEntry.getData().getRy_cards());
                    }
                    currentFrag=redAndYellowFragment;
                    currentIndex=3;
                    break;
            }

            RadioButton childAt = (RadioButton) rgRaceList.getChildAt(index);
            childAt.setChecked(true);
        }
    }


    private void initview() {
        rgRaceList = (RadioGroup) view.findViewById(R.id.rg_race_list);
        rbRaceScore = (RadioButton) view.findViewById(R.id.rb_race_score);
        rb_get_score = (RadioButton) view.findViewById(R.id.rb_get_score);
        rb_help_score = (RadioButton) view.findViewById(R.id.rb_help_score);
        rb_red_yellow_card = (RadioButton) view.findViewById(R.id.rb_red_yellow_card);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        fragmentTransaction = manager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (checkedId){
            case R.id.rb_race_score:
                rbRaceScore.setTextColor(Color.parseColor("#274082"));
                rb_get_score.setTextColor(Color.parseColor("#333333"));
                rb_help_score.setTextColor(Color.parseColor("#333333"));
                rb_red_yellow_card.setTextColor(Color.parseColor("#333333"));
                if (scoreFragment==null){
                    scoreFragment= ScoreFragment.newInstance();
                    fragmentTransaction.add(R.id.fl_race_list,scoreFragment);
                }else {
                    fragmentTransaction.show(scoreFragment);
                }
                if(matchScoreEntry != null) {
                    scoreFragment.setData(matchScoreEntry.getData().getIntegrals());
                }
                 currentFrag=scoreFragment;
                currentIndex=0;

                break;
            case R.id.rb_get_score:
                rbRaceScore.setTextColor(Color.parseColor("#333333"));
                rb_get_score.setTextColor(Color.parseColor("#274082"));
                rb_help_score.setTextColor(Color.parseColor("#333333"));
                rb_red_yellow_card.setTextColor(Color.parseColor("#333333"));
                if (getGoalFragment ==null){
                    getGoalFragment = GetGoalFragment.newInstance();
                    fragmentTransaction.add(R.id.fl_race_list, getGoalFragment);
                }else {
                    fragmentTransaction.show(getGoalFragment);
                }
                if(matchScoreEntry!=null){
                    getGoalFragment.setData(matchScoreEntry.getData().getGoals());
                }
                currentFrag=getGoalFragment;
                currentIndex=1;
                break;
            case R.id.rb_help_score:
                rbRaceScore.setTextColor(Color.parseColor("#333333"));
                rb_get_score.setTextColor(Color.parseColor("#333333"));
                rb_help_score.setTextColor(Color.parseColor("#274082"));
                rb_red_yellow_card.setTextColor(Color.parseColor("#333333"));
                if (helpGoalFragment ==null){
                    helpGoalFragment = HelpGoalFragment.newInstance();
                    fragmentTransaction.add(R.id.fl_race_list, helpGoalFragment);
                }else {
                    fragmentTransaction.show(helpGoalFragment);
                }
                if(matchScoreEntry!=null){
                    helpGoalFragment.setData(matchScoreEntry.getData().getHelp_goals());
                }
                currentFrag=helpGoalFragment;
                currentIndex=2;
                break;
            case R.id.rb_red_yellow_card:
                rbRaceScore.setTextColor(Color.parseColor("#333333"));
                rb_get_score.setTextColor(Color.parseColor("#333333"));
                rb_help_score.setTextColor(Color.parseColor("#333333"));
                rb_red_yellow_card.setTextColor(Color.parseColor("#274082"));
                if (redAndYellowFragment==null){
                    redAndYellowFragment= RedAndYellowFragment.newInstance();
                    fragmentTransaction.add(R.id.fl_race_list,redAndYellowFragment);
                }else {
                    fragmentTransaction.show(redAndYellowFragment);
                }
                if(matchScoreEntry!=null){
                    redAndYellowFragment.setData(matchScoreEntry.getData().getRy_cards());
                }
                currentFrag=redAndYellowFragment;
                currentIndex=3;
                break;
        }

        fragmentTransaction.commit();

    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (scoreFragment!=null){
            fragmentTransaction.hide(scoreFragment);
        }
        if (getGoalFragment !=null){
            fragmentTransaction.hide(getGoalFragment);
        }
        if (helpGoalFragment !=null){
            fragmentTransaction.hide(helpGoalFragment);
        }
        if (redAndYellowFragment!=null){
            fragmentTransaction.hide(redAndYellowFragment);
        }
    }

    void queryData(){
        lastOfficeId = User.getInstance().getCurrentOffice();
        if(lastOfficeId == null ){
            return;
        }
        NetHomeQuery.requestMatchScoreList(lastOfficeId, new BaseCallback<String>() {
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
                matchScoreEntry = JSON.parseObject(o, MatchScoreEntry.class);
                if(scoreFragment!=null){
                    scoreFragment.setData(matchScoreEntry.getData().getIntegrals());
                }
                if(getGoalFragment!=null){
                    getGoalFragment.setData(matchScoreEntry.getData().getGoals());
                }
                if(helpGoalFragment!=null){
                    helpGoalFragment.setData(matchScoreEntry.getData().getHelp_goals());
                }
                if(redAndYellowFragment!=null){
                    redAndYellowFragment.setData(matchScoreEntry.getData().getRy_cards());
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    public void forceRefresh(){
        if(User.getInstance().getCurrentOffice() != lastOfficeId){
            queryData();
        }
    }

}
