package com.apsoft.scfb.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.apsoft.scfb.R;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetSCFBLogin;
import com.apsoft.scfb.utils.ImageSelectHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpinionActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.et_advice)
    EditText etAdvice;
//    @BindView(R.id.iv_push_img)
//    ImageView ivPushImg;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private String content;

    ImageSelectHelper imageSelectHelper;
    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);
        ButterKnife.bind(this);
        btnSubmit.setOnClickListener(this);
        imageSelectHelper = new ImageSelectHelper();
        View v = ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
        imageSelectHelper.initGrid(this, v, R.id.fenxiang_img, false );
    }

    @Override
    public void onClick(View v) {
        content=etAdvice.getText().toString();
        List<Map<String, Object>> imageSelects = imageSelectHelper.getSelImageList();
        if(imageSelects.size() > 0){
           imgUrl = (String) imageSelects.get(0).get("imgUrl");
            Bitmap bmp = (Bitmap) imageSelects.get(0).get("img");
        }

        if (content.length()==0&&imgUrl==null){
            Toast.makeText(OpinionActivity.this, "您发送的内容为空", Toast.LENGTH_SHORT).show();
        }else {

            NetSCFBLogin.requestSendAdvice(content, null, new BaseCallback<String>() {
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
                    Toast.makeText(OpinionActivity.this, "很高兴收到您的宝贵意见", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(Response response, int code, Exception e) {

                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        imageSelectHelper.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
