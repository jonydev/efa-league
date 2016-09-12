package com.apsoft.scfb.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.SystemMessageEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.ui.adapter.SystemMessageAdapter;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

public class SystemMessageActivity extends AppCompatActivity {
    ListView listView;
    SystemMessageAdapter adapter;
    List<SystemMessageEntry> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_message);
        listView = (ListView) findViewById(R.id.lv_system_message);
        adapter = new SystemMessageAdapter(this, data);
        listView.setAdapter(adapter);
        queryData();
    }

    void queryData(){
        NetHomeQuery.requestListTeamAcquireUser(new BaseCallback<String>() {
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
                data.clear();
                if(array!= null) {
                    for (int i = 0; i < array.size(); ++i) {
                        SystemMessageEntry msg = JSON.toJavaObject(array.getJSONObject(i), SystemMessageEntry.class);
                        data.add(msg);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    void doAgreeJoinTeam(String uid){
        NetHomeQuery.requestAcceptUserJoinTeam(uid, new BaseCallback<String>() {
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

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    void disAgreeJoinTeam(String uid){
        NetHomeQuery.requestRejectUserJoinTeam(uid, new BaseCallback<String>() {
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

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }
}
