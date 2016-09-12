package com.apsoft.scfb.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.apsoft.scfb.R;

public class DetailNoticeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notice);
    }
    @Override
    public void onClick(View view) {
        finish();
    }
}
