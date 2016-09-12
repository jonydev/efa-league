package com.apsoft.scfb.ui.fragments.matches;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchGameScheduleEntry;
import com.apsoft.scfb.bean.MatchScheduleEntry;
import com.apsoft.scfb.bean.PersonalInfoEntry;
import com.apsoft.scfb.bean.ScheduleDetailEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.http.NetSCFBLogin;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.ApplicationTeamActivity;
import com.apsoft.scfb.ui.LoginActivity;
import com.apsoft.scfb.ui.MainActivity;
import com.apsoft.scfb.ui.Match_Detail_Activity;
import com.apsoft.scfb.ui.adapter.match.ChangePeopleAdapter;
import com.apsoft.scfb.ui.adapter.match.ExpandableScheduleAdapter;
import com.apsoft.scfb.utils.ImageLoader1;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameScheduleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private View view;
    private ExpandableListView el;
    String office_id = null;
    String schedule_id = null;
    ScheduleDetailEntry entry;
    MatchGameScheduleEntry.GameSchedule curShowSchedule;
    private ExpandableScheduleAdapter adapter;
    List<MatchGameScheduleEntry>    matchGameScheduleEntries = new ArrayList<>();

    private List<MatchScheduleEntry.GamescheduleDataBean.GroupDataBean>groppData=new ArrayList<>();
    private List<MatchScheduleEntry.GamescheduleDataBean.FirstchilddataBean>firstchilddataBeen=new ArrayList<>();
    private List<MatchScheduleEntry.GamescheduleDataBean.SecondchilddataBean>secondchilddataBeen=new ArrayList<>();
    private List<MatchScheduleEntry.GamescheduleDataBean.ThirdchilddataBean>thirdchilddataBeen=new ArrayList<>();
    private List<MatchScheduleEntry.GamescheduleDataBean.FourchilddataBean>fourchilddataBeen=new ArrayList<>();

//    private ScrollView sv;
//    private LinearLayout ll;
//    private LinearLayout llJudgeResult;
//    private ScrollView slBottom;
//    private ImageView ivLeftTeamHeadDetial;
//    private ImageView ivRightTeamHeadDetial;
//    private TextView tvLeftTeamNameDetail;
//    private TextView tvRightTeamNameDetail;
//    private TextView tvCompareDetail;
//    private TextView tv_left_goal_name;
//    private TextView tv_right_goal_name;
//    private TextView tv_left_goal_score;
//    private TextView tv_right_goal_score;
//    private TextView tv_left_assit_name;
//    private TextView tv_right_assit_name;
//    private TextView tv_left_yellow_card_name;
//    private TextView tv_right_yellow_card_name;
//    private TextView tv_left_yellow_card_num;
//    private TextView tv_right_yellow_card_num;
//    private TextView tv_left_red_card_name;
//    private TextView tv_right_red_card_name;
//    private TextView tv_left_red_card_num;
//    private TextView tv_right_red_card_num;
//    private Button btnLeftTeamName;
//    private Button btnRightTeamName;
//    private TextView tvPrimaryJudge;
//    private TextView tvSecondJudge;
//    private TextView tvFourthOfficious;
//    private TextView primayJudge;
//    private TextView secondJudge;
//    private TextView fourOfficious;
//    private RatingBar ratingRule;
//    private RatingBar ratingFair;
//    private RatingBar ratingControl;
//    private RecyclerView changePeopleView;
//    ChangePeopleAdapter changePeopleAdapter;
    ProgressDialog progressDialog;
    boolean progressShow = false;
    private SwipeRefreshLayout swiperefresh;
    private boolean isfresh;

    public GameScheduleFragment() {
    }

    public static GameScheduleFragment newInstance() {

        Bundle args = new Bundle();

        GameScheduleFragment fragment = new GameScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_game_schedule, container, false);
        initview();
        adapter = new ExpandableScheduleAdapter(getContext(), matchGameScheduleEntries);
        el.setAdapter(adapter);
        setExpandaListener();
        setExpandaStyle();
        setChildClickListener();
        swiperefresh.setOnRefreshListener(this);
        creatData();
        return view;
    }

    private void initview() {
        el = (ExpandableListView) view.findViewById(R.id.el_fragGameSchedule);
         swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
//        ll= (LinearLayout) view.findViewById(R.id.ll_top_game);
//        slBottom= (ScrollView) view.findViewById(R.id.sl_bottom);
//        llJudgeResult= (LinearLayout) view.findViewById(R.id.ll_judgeResult);
//        sv = (ScrollView) view.findViewById(R.id.sv);
//        sv.setVisibility(View.GONE);
//        ll.setVisibility(View.VISIBLE);
//        slBottom.setVisibility(View.GONE);

    }

    /**
     * 设置子条目的点击事件
     */
    private void setChildClickListener() {
        el.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                MatchGameScheduleEntry.GameSchedule schedule = (MatchGameScheduleEntry.GameSchedule) adapter.getChild(i,i1);
//                showMatchDetail(schedule);
                curShowSchedule = schedule;
                if(schedule.getFlag().equalsIgnoreCase("1")){
                    Intent intent=new Intent(getContext(), Match_Detail_Activity.class);
                    schedule_id = schedule.getId();
                    intent.putExtra("schedule_id", schedule_id);
                    intent.putExtra("schedule_json", JSON.toJSONString(schedule));
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(getContext(), ApplicationTeamActivity.class);
                    schedule_id = schedule.getId();
                    intent.putExtra("schedule_id", schedule_id);
                    intent.putExtra("schedule_json", JSON.toJSONString(schedule));
                    startActivity(intent);
                }
                return false;
            }
        });
    }
//
//    private String getFAMemberNameById(String memberId, boolean isHome){
//        List<ScheduleDetailEntry.FirstAppearance> faMembers = null;
//        if(isHome){
//            faMembers = entry.getHome_bases();
//        }else{
//            faMembers = entry.getAway_base();
//        }
//        for(int i=0; i<faMembers.size(); ++i){
//            if(faMembers.get(i).getMember_id().equalsIgnoreCase(memberId)){
//                return faMembers.get(i).getMember_name();
//            }
//        }
//        return "";
//    }
//    private void showMatchDetail(final MatchGameScheduleEntry.GameSchedule schedule){
//        curShowSchedule = schedule;
//        schedule_id = schedule.getId();
//        ImageLoader1.getInstance().displayImage(getContext(),schedule.getHome_team_image(),ivLeftTeamHeadDetial);
//        ImageLoader1.getInstance().displayImage(getContext(),schedule.getAway_team_image(),ivRightTeamHeadDetial);
//        tvLeftTeamNameDetail.setText(schedule.getHome_team_name());
//        tvRightTeamNameDetail.setText(schedule.getAway_team_name());
//        tvCompareDetail.setText(schedule.getHomescore() + ":" + schedule.getAwayscore());
//        showProgressDialog("正在读取数据");
//        NetHomeQuery.requestScheduleDetail(schedule.getId(), new BaseCallback<String>() {
//                    @Override
//                    public void onBeforeRequest(Request request) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Request request, Exception e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Response response) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Response response, String o) {
//                        hideProgressDialog();
//                        JSONObject jsonObject = JSONObject.parseObject(o);
//                        jsonObject = jsonObject.getJSONObject("data");
//                        entry = JSONObject.toJavaObject(jsonObject, ScheduleDetailEntry.class);
//                        for(int i=0; i<entry.getHome_ex().size(); ++i){
//                            entry.getHome_ex().get(i).setIs_home(true);
//                            entry.getHome_ex().get(i).setAlternate_name(getFAMemberNameById(entry.getHome_ex().get(i).getAlternate_id(), true));
//                        }
//                        for(int i=0; i<entry.getAway_ex().size(); ++i){
//                            entry.getAway_ex().get(i).setIs_home(false);
//                            entry.getAway_ex().get(i).setAlternate_name(getFAMemberNameById(entry.getAway_ex().get(i).getAlternate_id(), false));
//                        }
//                        String strLeft = ""; String strLeftTime="";
//                        String strRight = ""; String strRightTime="";
//                        for(ScheduleDetailEntry.Goal goal : entry.getGoals()){
//                            if(goal.getTeam_name()!=null){
//                                if( goal.getTeam_name().equals(schedule.getHome_team_name())){
//                                    strLeft += goal.getMember_name();
//                                    strLeftTime += goal.getTime();
//                                }else{
//                                    strRight += goal.getMember_name();
//                                    strRightTime += goal.getTime();
//                                }
//                            }
//                        }
//                        tv_left_goal_name.setText(strLeft);
//                        tv_left_goal_score.setText(strLeftTime);
//                        tv_right_goal_name.setText(strRight);
//                        tv_right_goal_score.setText(strRightTime);
//
//                        strLeft = ""; strRight=""; strLeftTime=""; strRightTime="";
//                        for(ScheduleDetailEntry.HelpGoal goal : entry.getHelp_goals()){
//                            if(goal.getTeam_name()!=null) {
//                                if (goal.getTeam_name().equals(schedule.getHome_team_name())) {
//                                    strLeft += goal.getMember_name();
//                                    strLeftTime += goal.getTime();
//                                } else {
//                                    strRight += goal.getMember_name();
//                                    strRightTime += goal.getTime();
//                                }
//                            }
//                        }
//                        tv_left_assit_name.setText(strLeft);
//                        tv_right_assit_name.setText(strRight);
//
//                        strLeft = ""; strRight=""; strLeftTime=""; strRightTime="";
//                        for(ScheduleDetailEntry.Card card : entry.getYellow_cards()){
//                            if(card.getTeam_name()!=null) {
//                                if (card.getTeam_name().equals(schedule.getHome_team_name())) {
//                                    strLeft += card.getMember_name();
//                                    strLeftTime += card.getTime();
//                                } else {
//                                    strRight += card.getMember_name();
//                                    strRightTime += card.getTime();
//                                }
//                            }
//                        }
//                        tv_left_yellow_card_name.setText(strLeft);
//                        tv_left_yellow_card_num.setText(strLeftTime);
//                        tv_right_yellow_card_name.setText(strRight);
//                        tv_right_yellow_card_num.setText(strRightTime);
//
//                        strLeft = ""; strRight=""; strLeftTime=""; strRightTime="";
//                        for(ScheduleDetailEntry.Card card : entry.getRed_cards()){
//                            if(card.getTeam_name()!=null) {
//                                if (card.getTeam_name().equals(schedule.getHome_team_name())) {
//                                    strLeft += card.getMember_name();
//                                    strLeftTime += card.getTime();
//                                } else {
//                                    strRight += card.getMember_name();
//                                    strRightTime += card.getTime();
//                                }
//                            }
//                        }
//                        tv_left_red_card_name.setText(strLeft);
//                        tv_left_red_card_num.setText(strLeftTime);
//                        tv_right_red_card_name.setText(strRight);
//                        tv_right_red_card_num.setText(strRightTime);
//
//                        List<ScheduleDetailEntry.LaterAppearance> appearances = new ArrayList<ScheduleDetailEntry.LaterAppearance>();
//                        appearances.addAll(entry.getHome_ex());
//                        appearances.addAll(entry.getAway_ex());
//                        changePeopleAdapter.updateData(appearances);
//                    }
//
//                    @Override
//                    public void onError(Response response, int code, Exception e) {
//                        hideProgressDialog();
//                    }
//                });
//
//        btnLeftTeamName.setText(schedule.getHome_team_name());
//        btnRightTeamName.setText(schedule.getAway_team_name());
//
//        tvPrimaryJudge.setText(schedule.getMain_referee().getName());
//        tvSecondJudge.setText(schedule.getLeft_umpire().getName() + "," + schedule.getRight_umpire().getName());
//        primayJudge.setText(schedule.getMain_referee().getName());
//        secondJudge.setText(schedule.getLeft_umpire().getName() + "," + schedule.getRight_umpire().getName());
//
//    }

    /**
     * 设置点击  评判本场罚判结果  的监听事件
     */
//    private void setJudgeResult() {
//        llJudgeResult.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ll.setVisibility(View.GONE);
//                sv.setVisibility(View.GONE);
//                slBottom.setVisibility(View.VISIBLE);
//            }
//        });
//    }

    //设置组的点击事件
    private void setExpandaListener() {
        el.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return false;
            }
        });
    }
    /**
     * 设置expandlistview的类型
     */
    private void setExpandaStyle() {
//母列表无箭头
        el.setGroupIndicator(null);
//设置每组都为默认展开
        for (int i = 0; i < groppData.size(); i++) {
            el.expandGroup(i, false);
        }



    }

    private void creatData(){
        queryData();
    }

//    public boolean onBackPressed(){
//        if(sv.getVisibility() == View.VISIBLE){
//            sv.setVisibility(View.GONE);
//            ll.setVisibility(View.VISIBLE);
//            return true;
//        }else if (slBottom.getVisibility()==View.VISIBLE){
//            ll.setVisibility(View.GONE);
//            slBottom.setVisibility(View.GONE);
//            sv.setVisibility(View.VISIBLE);
//            return true;
//        }
//        return false;
//    }

    public void queryData(){
        office_id = User.getInstance().getCurrentOffice();
        if(office_id == null){
            return;
        }
        if (!isfresh){
            MainActivity.s_mainActivity.showProgressDialog("正在加载联赛信息...");
        }
            NetHomeQuery.requestMatchGameSchedule(office_id, new BaseCallback<String>() {
                @Override
                public void onBeforeRequest(Request request) {

                }

                @Override
                public void onFailure(Request request, Exception e) {
                    MainActivity.s_mainActivity.hideProgressDialog();
                }

                @Override
                public void onResponse(Response response) {

                }

                @Override
                public void onSuccess(Response response, String o) {
                    if (!isfresh){
                        MainActivity.s_mainActivity.hideProgressDialog();
                    }

                    JSONObject jsonObject = JSONObject.parseObject(o);
                    JSONArray turnArray = jsonObject.getJSONArray("data");

                    matchGameScheduleEntries.clear();
                    if (turnArray != null) {
                        for (int i = 0; i < turnArray.size(); ++i) {
                            matchGameScheduleEntries.add(JSON.toJavaObject(turnArray.getJSONObject(i), MatchGameScheduleEntry.class));
                        }
                    }
                    adapter.notifyDataSetChanged();
                    swiperefresh.setRefreshing(false);
                    if (isfresh){
                        Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                    isfresh=false;
                }

                @Override
                public void onError(Response response, int code, Exception e) {
                    office_id = null;
                    MainActivity.s_mainActivity.hideProgressDialog();
                }
            });

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(User.getInstance().getCurrentOffice() != office_id){
                queryData();
            }
        }
    }

    public void forceRefresh(){
        if(User.getInstance().getCurrentOffice() != office_id){
            queryData();
        }
    }


    public void showProgressDialog(String text){
        hideProgressDialog();
        progressShow = true;
        progressDialog = new ProgressDialog(getActivity());
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

    @Override
    public void onRefresh() {
        if(office_id != User.getInstance().getCurrentOffice()){
            isfresh=true;
            queryData();
        }else {
            swiperefresh.setRefreshing(false);
            Toast.makeText(getActivity(), "没有数据变化不需要刷新", Toast.LENGTH_SHORT).show();
        }
    }
}
