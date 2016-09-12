package com.apsoft.scfb.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.PinlunEntry;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.matches.DiscussionFragment;
import com.apsoft.scfb.ui.fragments.matches.MatchFragment;

import org.simple.eventbus.EventBus;

public class PingLunActivity extends AppCompatActivity {

    private TextView plAll;
    private EditText etPinlun;
    private String punlun;
    private String content;
    private String name;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_lun);
//        EventBus.getDefault().register(this);
        plAll = (TextView) findViewById(R.id.tv_pinlun_all);
        etPinlun = (EditText) findViewById(R.id.et_pinglun);
        content = getIntent().getStringExtra("content");
        name = getIntent().getStringExtra("name");
        index=getIntent().getIntExtra("index",0);
        plAll.setText(content);

    }
    public void click(View view){
        punlun = etPinlun.getText().toString();
        plAll.setText(content+"     "+ User.getInstance().getUser_name()+"@"+name+" "+punlun);
        String str=String .valueOf(content+"     "+ User.getInstance().getUser_name()+"@"+name+" "+punlun);
//        EventBus.getDefault().post(str,"plTag");
//        MainActivity s_mainActivity = MainActivity.s_mainActivity;
//        MatchFragment matchfrag = (MatchFragment) s_mainActivity.getSupportFragmentManager().findFragmentByTag("matchTag");
//        DiscussionFragment disfrag = (DiscussionFragment) matchfrag.getChildFragmentManager().findFragmentByTag("disTag");
//        disfrag.refreshDetailReviewData(index,content+"     "+ User.getInstance().getUser_name()+"@"+name+" "+punlun);
        finish();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
}
