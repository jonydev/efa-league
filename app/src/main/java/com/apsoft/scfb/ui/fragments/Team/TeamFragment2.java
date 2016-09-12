package com.apsoft.scfb.ui.fragments.Team;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SearchViewCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.Tean2StartEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.CreatTeamActivity;
import com.apsoft.scfb.ui.EditTeamActivity;
import com.apsoft.scfb.ui.LoginActivity;
import com.apsoft.scfb.ui.MainActivity;
import com.apsoft.scfb.ui.TeamDetailActivity;
import com.apsoft.scfb.ui.adapter.team.TeamListAdapter;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment2 extends Fragment implements PullToRefreshBase.OnRefreshListener2{
    private View view;
    private PullToRefreshListView lvTeam2;
    private TeamListAdapter adapter;
    private List<Tean2StartEntry.CompetitionZoneBean.DataBean> data = new ArrayList<>();
    private TextView tvCreatTeam;
    private List<String >teamids=new ArrayList<>();
    SearchView searchView;
    String mTeamFilter="";
    private ArrayList<String> listNames=new ArrayList<>();

    public TeamFragment2() {
    }
    public static TeamFragment2 newInstance() {
        Bundle args = new Bundle();
        TeamFragment2 fragment = new TeamFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team_fragment2, container, false);
        initView();
        if (!User.getInstance().is_login){
            tvCreatTeam.setText("创建球队");
        }else if (User.getInstance().getIs_login()){
            if (User.getInstance().isTeamLeader()){
                tvCreatTeam.setText("编辑球队");
            }else {
                tvCreatTeam.setText("创建球队");
            }
        }

        adapter = new TeamListAdapter(getActivity(), data,teamids);
        lvTeam2.setAdapter(adapter);
        creatData();
        lvTeam2.setOnRefreshListener(this);
        setOnItemClick();
        setOnCreatTeamClick();
        return view;
    }


    /**
     * 第一个listview条目点击事件
     */
    private void setOnItemClick() {
        lvTeam2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(), TeamDetailActivity.class);
                Tean2StartEntry.CompetitionZoneBean.DataBean db = (Tean2StartEntry.CompetitionZoneBean.DataBean) adapter.getItem(i-1);
                intent.putExtra("team_id", db.getId());
                startActivity(intent);
            }
        });
    }


    /**
     * 点击创建球队时候的点击事件
     */
    private void setOnCreatTeamClick() {
        tvCreatTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!User.getInstance().getIs_login()){
                    MainActivity s_mainActivity = MainActivity.s_mainActivity;
                    s_mainActivity.hideAllFragment();
                    Intent it = new Intent(getActivity(), LoginActivity.class);
                    startActivity(it);
                    return;
                }

                if (User.getInstance().isTeamLeader()){       //自己是领队
                    Intent intent=new Intent(getActivity(),EditTeamActivity.class);
                    startActivity(intent);
                }else {
                    if (User.getInstance().getTeam_id()==null||User.getInstance().getTeam_id().length()<=0){   //不是领队并且还没有加入球队
                        Intent intent=new Intent(getActivity(),CreatTeamActivity.class);
                        startActivity(intent);
                    }else if (User.getInstance().getTeam_id()!=null&&User.getInstance().getTeam_id().length()>0){   //不是领队但是加入了其他球队
                        new AlertDialog.Builder(getActivity())
                                .setMessage("创建球队，你要退出原来球队吗？")
                                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent=new Intent(getActivity(),CreatTeamActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getActivity(), "留在原来球队", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .show();
                    }
                }

            }
        });
    }

    /**
     * 初始化listview的值
     */
    private void creatData() {
        queryData();
    }

    private void initView() {
        lvTeam2 = (PullToRefreshListView) view.findViewById(R.id.lv_team2);
        tvCreatTeam = (TextView) view.findViewById(R.id.tv_team2_creatTeam);
        searchView = (SearchView) view.findViewById(R.id.rl_search);
        searchView.setIconifiedByDefault(true);
        searchView.onActionViewExpanded();
        searchView.setFocusable(false);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(mTeamFilter.equalsIgnoreCase(query)){
                    return false;
                }
                mTeamFilter =query;
                queryData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if ( TextUtils.isEmpty(newText) && (!TextUtils.isEmpty(mTeamFilter) )) {
                    mTeamFilter = "";
                    queryData();
                }
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if(mTeamFilter.equalsIgnoreCase("")){
                    return false;
                }
                mTeamFilter = "";
                queryData();
                return false;
            }
        });
    }



    public void setWitchtovisibility(int index){
        if (view==null){
            return;
        }
        adapter.notifyDataSetChanged();
    }




    public void queryData(){
        NetHomeQuery.requestTeamList(null, "1", "100", mTeamFilter, new BaseCallback<String>() {
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
                lvTeam2.onRefreshComplete();
                data.clear();
                JSONObject jsonObject = JSONObject.parseObject(o);
                Tean2StartEntry.CompetitionZoneBean tean2StartEntry = JSON.toJavaObject(jsonObject, Tean2StartEntry.CompetitionZoneBean.class);
                List<Tean2StartEntry.CompetitionZoneBean.DataBean> dataBeen = tean2StartEntry.getData();
                data.addAll(dataBeen);
                for (int i = 0; i < data.size(); i++) {
                    String id = dataBeen.get(i).getId();
                    teamids.add(id);
                    listNames.add(data.get(i).getName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    public void forceRefesh(){
        queryData();
    }




    @Override
    public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {
       queryData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {

    }
}
