package com.apsoft.scfb.ui.fragments.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppAccountTool;
import com.apsoft.scfb.bean.NoticeEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.ui.DetailNoticeActivity;
import com.apsoft.scfb.ui.WebActivity;
import com.apsoft.scfb.ui.adapter.home.NoticeAdapter;
import com.apsoft.scfb.ui.fragments.ListViewFragment;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends Fragment {


    private View view;
    private ListView lvNotice;
    private NoticeAdapter adapter;
    //private List<NoticeEntry.NoticedataBean> data = new ArrayList<>();
    NoticeEntry noticeEntry;
    public static final String path="http://182.92.6.152:8080/mockjsdata/1/notice";

    public NoticeFragment() {
    }

    public static NoticeFragment newInstance() {

        Bundle args = new Bundle();
        NoticeFragment fragment = new NoticeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_notice, container, false);
        initView();
        creatData();

        setItemClick();
        return view;
    }

    /**
     * 条目点击事件
     */
    private void setItemClick() {
        lvNotice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), WebActivity.class);
                NoticeEntry.NoticedataBean bean = (NoticeEntry.NoticedataBean) adapter.getItem(i);
                intent.putExtra("url",  bean.getLink());
                startActivity(intent);
            }
        });
    }


    private void initView() {
        lvNotice = (ListView) view.findViewById(R.id.lv_notice);
    }

    private void creatData() {
//        HttpRequest.get(path,new BaseHttpRequestCallback<String >(){
//            @Override
//            public void onStart() {
//                super.onStart();
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//            }
//
//            @Override
//            protected void onSuccess(String s) {
//                super.onSuccess(s);
//                NoticeEntry noticeEntry = JSON.parseObject(s, NoticeEntry.class);
//                List<NoticeEntry.NoticedataBean> noticedataBeanList = noticeEntry.getNoticedata();
//                data.addAll(noticedataBeanList);
//                adapter.notifyDataSetChanged();
//            }
//        });
        queryData();
    }

    void queryData(){
        NetHomeQuery.requestNewList(AppAccountTool.getInstance().getAreaId(), null, null, null, new BaseCallback<String>() {
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
                noticeEntry = JSON.parseObject(s, NoticeEntry.class);
                adapter=new NoticeAdapter(getActivity(),noticeEntry.getData());
                lvNotice.setAdapter(adapter);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }



}
