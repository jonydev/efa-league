package com.apsoft.scfb.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.anywhere.utils.ToastUtils;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppGlobalTool;
import com.apsoft.scfb.bean.CodeBean;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.http.NetSetting;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.Team.TeamFragment2;
import com.apsoft.scfb.utils.ImageSelectHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatTeamActivity extends AppCompatActivity implements View.OnClickListener {

    String imageLogo;
    @BindView(R.id.rl_team_cloth)
    RelativeLayout rlTeamCloth;
    @BindView(R.id.rl_team_paths)
    RelativeLayout rlTeamPaths;
    @BindView(R.id.iv_cloth_show)
    ImageView ivClothShow;
    @BindView(R.id.iv_path_show)
    ImageView ivPathShow;
    private int clothPosition;
    private int pathPosion;

    private List<Bitmap>clothImgs;
    private List<Bitmap>pathImgs;

    boolean progressShow = false;
    ProgressDialog progressDialog;


    ImageSelectHelper imageSelectHelper;

    @Subscriber(tag = "cloth_tag")
    private void updateClothPosition(int cloth_position) {
        Log.e("---->", "cloth_position = " + cloth_position);
        this.clothPosition = cloth_position;
        ivClothShow.setImageBitmap(clothImgs.get(cloth_position));
    }

    @Subscriber(tag = "path_tag")
    private void updatepathPosition(int path_position) {
        Log.e("---->", "path_position = " + path_position);
        this.pathPosion = path_position;
        ivPathShow.setImageBitmap(pathImgs.get(pathPosion));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_team);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        initClothData();
        initPathData();
        ivClothShow.setImageBitmap(clothImgs.get(0));
        ivPathShow.setImageBitmap(pathImgs.get(0));

        rlTeamCloth.setOnClickListener(this);
        rlTeamPaths.setOnClickListener(this);

        imageSelectHelper = new ImageSelectHelper();
        View v = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        imageSelectHelper.initGrid(this, v, R.id.fenxiang_img, false);
        Button btn = (Button) findViewById(R.id.ll_create_team);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog("正在创建球队。。。");
                List<Map<String, Object>> imageSelects = imageSelectHelper.getSelImageList();
                if (imageSelects.size() > 0) {
                    final String imgUrl = (String) imageSelects.get(0).get("imgUrl");
                    final Bitmap bmp = (Bitmap) imageSelects.get(0).get("img");

                    AppGlobalTool.execNetRequest(new Runnable() {
                        @Override
                        public void run() {
                            NetSetting.uploadImage(bmp, imgUrl, "", 0, new NetSetting.IRemoteQueryCallback(){
                                @Override
                                public void onFinish(String data, JSONObject jsonObject) {
                                    try {
                                        String imageAddress = jsonObject.getString("img_address");
                                        imageLogo = imageAddress;
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                doCreateTeam();
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailed(Exception e) {
                                    ToastUtils.showLongToast(CreatTeamActivity.this, "上传logo失败");
                                }
                            });
                        }
                    });
                }else{
                    doCreateTeam();
                }

            }
        });
    }

    void doCreateTeam(){
        String teamName = getEditTextValue(R.id.ll_team_name);
        String teamHome = getEditTextValue(R.id.ll_team_city);
        String teamDes = getEditTextValue(R.id.ll_team_des);
        String lowerColor = String.valueOf(clothPosition);
        String upperColor = String.valueOf(pathPosion);

        if (teamName==null||teamName.length()==0){
            Toast.makeText(CreatTeamActivity.this, "球队名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        NetHomeQuery.requestCreateTeam(teamName, teamHome, teamDes, imageLogo, upperColor, lowerColor, new BaseCallback<String>() {
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

                CodeBean codeBean = JSON.parseObject(o, CodeBean.class);
                int code = codeBean.getCode();
                if (code==400){
                    hideProgressDialog();
                    Toast.makeText(CreatTeamActivity.this, "该球队名已经被使用", Toast.LENGTH_SHORT).show();
                    return;
                }
                CodeBean.dataBean data = codeBean.getData();
                String id = data.getId();
                if (id!=null&&id.length()>0){
                    User.getInstance().setTeam_id(id);
                    User.getInstance().setIs_team_leader("1");
                }
                hideProgressDialog();
                MainActivity s_mainActivity = MainActivity.s_mainActivity;
                TeamFragment2 fragment = (TeamFragment2) s_mainActivity.getSupportFragmentManager().findFragmentByTag("teamTag");
                fragment.forceRefesh();
                TextView tv = (TextView) fragment.getView().findViewById(R.id.tv_team2_creatTeam);
                tv.setText("编辑球队");
                ToastUtils.showLongToast(CreatTeamActivity.this, "创建成功");
                finish();
            }
            @Override
            public void onError(Response response, int code, Exception e) {
                hideProgressDialog();
                ToastUtils.showLongToast(CreatTeamActivity.this, "创建球队失败");
            }
        });
    }
    String getEditTextValue(Integer id) {
        EditText et = (EditText) findViewById(id);
        return et.getText().toString();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_team_cloth:
                Intent intent = new Intent(this, TeamClothChoiseActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_team_paths:
                Intent intent1 = new Intent(this, TeamPathChoiseActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        imageSelectHelper.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initClothData() {
        clothImgs=new ArrayList<>();
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth1 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth2 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth3 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth4 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth5 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth6 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth7 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth8 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth9 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth10 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth11));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth12));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth13 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth14 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth15 ));
        clothImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth16 ));
    }



    private void initPathData() {
        pathImgs=new ArrayList<>();
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path1 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path2 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path3 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path4 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path5 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path6 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path7 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path8 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path9 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path10 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path11));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.ath12));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.ath13 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.ath14 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.ath15 ));
        pathImgs.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.ath16 ));
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }



    public void showProgressDialog(String text){
        hideProgressDialog();
        progressShow = true;
        progressDialog = new ProgressDialog(this);
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


}
