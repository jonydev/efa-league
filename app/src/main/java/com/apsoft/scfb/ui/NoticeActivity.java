package com.apsoft.scfb.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.ui.fragments.home.NoticeFragment;

import org.simple.eventbus.EventBus;

public class NoticeActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private NoticeFragment noticeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        setDefaultFragment();
    }

    /**
     * 设置默认的fragment
     */
    private void setDefaultFragment() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        noticeFragment = NoticeFragment.newInstance();
        transaction.add(R.id.fl_contain_notice, noticeFragment);
        transaction.show(noticeFragment).commit();
    }

}