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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.anywhere.utils.ToastUtils;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppGlobalTool;
import com.apsoft.scfb.bean.TeamDetailEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.http.NetSetting;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.Team.TeamFragment2;
import com.apsoft.scfb.utils.ImageSelectHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditTeamActivity extends AppCompatActivity implements View.OnClickListener {
    String imageLogo;
    @BindView(R.id.ll_team_name)
    EditText llTeamName;
    @BindView(R.id.ll_team_city)
    EditText llTeamCity;
    @BindView(R.id.ll_team_des)
    EditText llTeamDes;
    @BindView(R.id.fenxiang_img)
    GridView fenxiangImg;
    @BindView(R.id.iv_cloth_show)
    ImageView ivClothShow;
    @BindView(R.id.rl_team_cloth)
    RelativeLayout rlTeamCloth;
    @BindView(R.id.iv_path_show)
    ImageView ivPathShow;
    @BindView(R.id.rl_team_paths)
    RelativeLayout rlTeamPaths;
    @BindView(R.id.btn_save_team)
    Button btnSaveTeam;
    private String team_id;
    private TeamDetailEntry teamDetailEntry;


    private int clothPosition;
    private int pathPosion;
    private List<Bitmap> clothImgs;
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
        setContentView(R.layout.activity_edit_team);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        team_id = User.getInstance().getTeam_id();
        initClothData();
        initPathData();
        queryData();


        rlTeamCloth.setOnClickListener(this);
        rlTeamPaths.setOnClickListener(this);

        imageSelectHelper = new ImageSelectHelper();
        View v = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        imageSelectHelper.initGrid(this, v, R.id.fenxiang_img, false);

        btnSaveTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog("正在编辑球队。。。");
                List<Map<String, Object>> imageSelects = imageSelectHelper.getSelImageList();
                if (imageSelects.size() > 0) {
                    final String imgUrl = (String) imageSelects.get(0).get("imgUrl");
                    final Bitmap bmp = (Bitmap) imageSelects.get(0).get("img");

                    AppGlobalTool.execNetRequest(new Runnable() {
                        @Override
                        public void run() {
                            NetSetting.uploadImage(bmp, imgUrl, "", 0, new NetSetting.IRemoteQueryCallback(){
                                @Override
                                public void onFinish(String data, org.json.JSONObject jsonObject) {
                                    try {
                                        String imageAddress = jsonObject.getString("img_address");
                                        imageLogo = imageAddress;
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                doSaveTeam();
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailed(Exception e) {
                                    ToastUtils.showLongToast(EditTeamActivity.this, "上传logo失败");
                                }
                            });
                        }
                    });
                }else{
                    doSaveTeam();
                }
            }
        });

    }

    private void doSaveTeam() {
        String teamName = getEditTextValue(R.id.ll_team_name);
        String teamHome = getEditTextValue(R.id.ll_team_city);
        String teamDes = getEditTextValue(R.id.ll_team_des);
        String lowerColor = String.valueOf(clothPosition);
        String upperColor = String.valueOf(pathPosion);
        NetHomeQuery.requestUpdateTeam(team_id,teamName,teamHome,teamDes,imageLogo,upperColor,lowerColor, new BaseCallback<String>() {
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
                hideProgressDialog();
                MainActivity s_mainActivity = MainActivity.s_mainActivity;
                TeamFragment2 fragment = (TeamFragment2) s_mainActivity.getSupportFragmentManager().findFragmentByTag("teamTag");
                fragment.forceRefesh();
                ToastUtils.showLongToast(EditTeamActivity.this, "编辑成功");
                finish();

            }

            @Override
            public void onError(Response response, int code, Exception e) {

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


    public void queryData() {
        NetHomeQuery.requestTeamDetail(team_id, new BaseCallback<String>() {
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
                JSONObject obj = JSONObject.parseObject(o);
                obj = obj.getJSONObject("data");
                teamDetailEntry = JSONObject.toJavaObject(obj, TeamDetailEntry.class);
                TeamDetailEntry.Team team = teamDetailEntry.getTeam();
                llTeamName.setText(team.getName());
                llTeamCity.setText(team.getHome());
                llTeamDes.setText(team.getContent());
                ivClothShow.setImageBitmap(clothImgs.get(Integer.parseInt(team.getUpper())));
                ivPathShow.setImageBitmap(pathImgs.get(Integer.parseInt(team.getLower())));
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        imageSelectHelper.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
