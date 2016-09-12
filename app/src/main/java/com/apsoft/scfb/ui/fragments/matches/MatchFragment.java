package com.apsoft.scfb.ui.fragments.matches;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.TestEntry;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.ApplicationTeamActivity;
import com.apsoft.scfb.ui.LoginActivity;
import com.apsoft.scfb.ui.MainActivity;
import com.apsoft.scfb.ui.TeamJoinMatchActivity;

import org.simple.eventbus.EventBus;

import java.lang.reflect.Field;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends Fragment implements View.OnClickListener{

    private View view;
    private RadioGroup rgMatch;
    private RadioButton rbBriefIntro;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private BriefIntroFragment briefIntroFragment;
    private GameScheduleFragment gameScheduleFragment;
    private RaceListFragment raceListFragment;
    private DiscussionFragment discussionFragment;
    private int curentIndex=0;


    Fragment    currentShowFragment;
    int init_index = 0;
    private ImageButton ivSignUp;

    public MatchFragment() {
    }
    public static MatchFragment newInstance() {
        Bundle args = new Bundle();
        MatchFragment fragment = new MatchFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_match, container, false);
        initView();
        EventBus.getDefault().register(this);
        manager=getFragmentManager();
        ivSignUp.setOnClickListener(this);
        initRadio();
        //默认第一项为选中
        DiscussionFragment discussionFragment = DiscussionFragment.newInstance();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.fl_contain,discussionFragment,"disTag").hide(discussionFragment).commit();
        rbBriefIntro.setChecked(true);

        if(init_index>0){
            switchFragment(init_index);
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        if(!User.getInstance().getIs_login()){
            MainActivity s_mainActivity = MainActivity.s_mainActivity;
            s_mainActivity.hideAllFragment();
            Intent it = new Intent(getContext(), LoginActivity.class);
            startActivity(it);
            return;
        }
        Intent intent=new Intent(getContext(), TeamJoinMatchActivity.class);
        startActivity(intent);
    }

    private void initRadio() {
        rgMatch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (User.getInstance().getIs_login()) {
                    if (i == R.id.rb_interation || i == R.id.rb_gameSchedule) {
                        ivSignUp.setVisibility(View.GONE);
                    } else {
                        if (User.getInstance().isTeamJoinMatch()) {
                            ivSignUp.setVisibility(View.GONE);
                        } else {
                            ivSignUp.setVisibility(View.VISIBLE);
                        }
                    }
                }else {
                    if (i==R.id.rb_interation||i==R.id.rb_gameSchedule) {
                        ivSignUp.setVisibility(View.GONE);
                    }else {
                        ivSignUp.setVisibility(View.VISIBLE);
                    }
                }

                transaction = manager.beginTransaction();
                hideAllFrag(transaction);
                switch (i){
                    case R.id.rb_briefIntro:
                        if (briefIntroFragment==null){
                            briefIntroFragment=BriefIntroFragment.newInstance();
                            transaction.add(R.id.fl_contain,briefIntroFragment);
                        }else {
                            transaction.show(briefIntroFragment);
                            briefIntroFragment.forceRefresh();
                        }
                        currentShowFragment = briefIntroFragment;
                        curentIndex=0;
                        if (raceListFragment!=null){
                            raceListFragment.setSwichFrag(0);
                        }
                        break;
                    case R.id.rb_gameSchedule:
                        if (gameScheduleFragment==null){
                            gameScheduleFragment=GameScheduleFragment.newInstance();
                            transaction.add(R.id.fl_contain,gameScheduleFragment);
                        }else {
                            transaction.show(gameScheduleFragment);
                            gameScheduleFragment.forceRefresh();
                        }
                        currentShowFragment = gameScheduleFragment;
                        curentIndex=1;
                        if (raceListFragment!=null){
                            raceListFragment.setSwichFrag(0);
                        }
                        break;
                    case R.id.rb_totalScore:
                        if (raceListFragment ==null){
                            raceListFragment = RaceListFragment.newInstance();
                            transaction.add(R.id.fl_contain, raceListFragment);
                        }else {
                            transaction.show(raceListFragment);
                            raceListFragment.forceRefresh();
                        }
                        currentShowFragment = raceListFragment;
                        curentIndex=2;
                        break;
                    case R.id.rb_interation:
                        if (discussionFragment ==null){
                            discussionFragment = DiscussionFragment.newInstance();
                            transaction.add(R.id.fl_contain, discussionFragment,"disTag").show(discussionFragment);
                        }else {
                            transaction.show(discussionFragment);
                            discussionFragment.forceRefresh();
                        }
                        currentShowFragment = discussionFragment;
                        curentIndex=3;
                        if (raceListFragment!=null){
                            raceListFragment.setSwichFrag(0);
                        }
                        break;
                }
                transaction.commit();
            }
        });
    }

    public void switchFragment(int index){
        if(view == null){
            init_index = index;
            return;
        }else{
            transaction = manager.beginTransaction();
            hideAllFrag(transaction);
            switch(index){
                case 0:
                    if (briefIntroFragment==null){
                        briefIntroFragment=BriefIntroFragment.newInstance();
                        transaction.add(R.id.fl_contain,briefIntroFragment);
                    }else {
                        transaction.show(briefIntroFragment);
                    }
                    currentShowFragment = briefIntroFragment;

                    if (raceListFragment!=null){
                        raceListFragment.setSwichFrag(0);
                    }
                    break;
                case 1:
                    if (gameScheduleFragment==null){
                        gameScheduleFragment=GameScheduleFragment.newInstance();
                        transaction.add(R.id.fl_contain,gameScheduleFragment);
                    }else {
                        transaction.show(gameScheduleFragment);
                    }
                    currentShowFragment = gameScheduleFragment;
                    if (raceListFragment!=null){
                        raceListFragment.setSwichFrag(0);
                    }
                    break;
                case 2:
                    if (raceListFragment ==null){
                        raceListFragment = RaceListFragment.newInstance();
                        transaction.add(R.id.fl_contain, raceListFragment);
                    }else {
                        transaction.show(raceListFragment);
                    }
                    currentShowFragment = raceListFragment;
                    break;
                case 3:
                    if (discussionFragment ==null){
                        discussionFragment = DiscussionFragment.newInstance();
                        transaction.add(R.id.fl_contain, discussionFragment,"disTag").show(discussionFragment);
                    }else {
                        transaction.show(discussionFragment);
                    }
                    currentShowFragment = discussionFragment;
                    if (raceListFragment!=null){
                        raceListFragment.setSwichFrag(0);
                    }
                    break;
            }
            transaction.commit();
        }

        if (index!=curentIndex){
            RadioButton radioButton = (RadioButton) rgMatch.getChildAt(index);
            radioButton.setChecked(true);
            curentIndex=index;
        }

    }

    public void forceRefresh(){
        if(gameScheduleFragment!=null){
            gameScheduleFragment.forceRefresh();
        }
        if(briefIntroFragment!=null){
            briefIntroFragment.forceRefresh();
        }
        if(discussionFragment!=null){
            discussionFragment.forceRefresh();
        }
        if(raceListFragment!=null){
            raceListFragment.forceRefresh();
        }
    }
    /**
     * 隐藏所有Fragment
     */
    private void hideAllFrag(FragmentTransaction transaction) {
        if (briefIntroFragment != null) {
            transaction.hide(briefIntroFragment);
        }
        if (gameScheduleFragment != null) {
            transaction.hide(gameScheduleFragment);
        }
        if (raceListFragment != null) {
            transaction.hide(raceListFragment);
        }
        if (discussionFragment != null) {
            transaction.hide(discussionFragment);
        }

    }


    private void initView() {
        rbBriefIntro = (RadioButton) view.findViewById(R.id.rb_briefIntro);
        rgMatch = (RadioGroup) view.findViewById(R.id.rg_mathch);
        ivSignUp = (ImageButton) view.findViewById(R.id.ivbtn_sign_up);
    }


    public boolean onBackPressed(){
        if(currentShowFragment != null){
            if(currentShowFragment instanceof GameScheduleFragment){
//                return ((GameScheduleFragment) currentShowFragment).onBackPressed();
            }else if (currentShowFragment instanceof DiscussionFragment){
                return ((DiscussionFragment) currentShowFragment).onBackPressed();
            }
        }
        return false;
    }

    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
