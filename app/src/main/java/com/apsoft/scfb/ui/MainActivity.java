package com.apsoft.scfb.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anywhere.appbase.BaseFragmentActivity;
import com.apsoft.scfb.R;

import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.HomeFragment;
import com.apsoft.scfb.ui.fragments.MineFragment;
import com.apsoft.scfb.ui.fragments.Team.TeamFragment2;
import com.apsoft.scfb.ui.fragments.WebViewFragment;
import com.apsoft.scfb.ui.fragments.matches.DiscussionFragment;
import com.apsoft.scfb.ui.fragments.matches.MatchFragment;


public class MainActivity extends BaseFragmentActivity {
    public static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 110;
    private Button[] mTabs;
    private HomeFragment homeFragment;
    private TeamFragment2 teamFragment2;
    private MineFragment mineFragment;
    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;
    private MatchFragment matchFragment;
    private boolean progressShow;
    ProgressDialog progressDialog;
    static public MainActivity s_mainActivity;
    public static int REQUEST_CODE = 0; // 请求码

    // 所需的全部权限
    public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private PermissionsChecker mPermissionsChecker; // 权限检测器


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        s_mainActivity = this;
        setContentView(R.layout.activity_main);
        mPermissionsChecker = new PermissionsChecker(this);
        mTabs = new Button[4];
        mTabs[0] = (Button) findViewById(R.id.btn_conversation);
        mTabs[1] = (Button) findViewById(R.id.btn_address_list);
        mTabs[2] = (Button) findViewById(R.id.btn_setting);
        mTabs[3] = (Button) findViewById(R.id.btn_race_list);
        // 把第一个tab设为选中状态
        mTabs[0].setSelected(true);
        homeFragment = new HomeFragment();
        teamFragment2 = new TeamFragment2();
        mineFragment = new MineFragment();
        matchFragment = new MatchFragment();
        fragments = new Fragment[]{homeFragment, teamFragment2, mineFragment, matchFragment};
        // 添加显示第一个fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment)
                .add(R.id.fragment_container, teamFragment2,"teamTag").add(R.id.fragment_container,matchFragment,"matchTag").add(R.id.fragment_container,mineFragment,"mineTag").hide(teamFragment2).hide(matchFragment).hide(mineFragment).show(homeFragment)
                .commit();

    }

    @Override protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        }
    }
    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }

    /**
     * button点击事件
     *
     * @param view
     */
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_conversation:
                index = 0;
                break;
            case R.id.btn_address_list:
                index = 1;
                break;
            case R.id.btn_setting:
                index = 2;
                break;
            case R.id.btn_race_list:
                index = 3;
                break;
        }

        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (User.getInstance().getIs_login()||index!=2){
                trx.show(fragments[index]).commit();
            }else if (!User.getInstance().getIs_login()&&index==2){
                hideAllFragment();
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        }

        if (currentTabIndex!=index){
            mTabs[currentTabIndex].setSelected(false);
            // 把当前tab设为选中状态
            mTabs[index].setSelected(true);
            currentTabIndex = index;
        }

    }

    public void hideAllFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(homeFragment).hide(matchFragment).hide(teamFragment2).hide(mineFragment).commit();
    }

    public void switchFragment(int newIndex, int subIndex){
            index = newIndex;
            if (currentTabIndex != index||index==2) {
                FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
                if (index==0){
                    trx.hide(fragments[1]);
                    trx.hide(fragments[2]);
                    trx.hide(fragments[3]);
                }
                trx.show(fragments[index]).commitAllowingStateLoss();
            }
        if (currentTabIndex!=index){
            mTabs[currentTabIndex].setSelected(false);
            // 把当前tab设为选中状态
            mTabs[index].setSelected(true);
            currentTabIndex = index;
        }
            if (subIndex >= 0) {
                Fragment f = fragments[currentTabIndex];
                if (f instanceof MatchFragment) {
                    MatchFragment m = (MatchFragment) f;
                    m.switchFragment(subIndex);
                    m.forceRefresh();
                } else if (f instanceof TeamFragment2) {
                    TeamFragment2 t = (TeamFragment2) f;
                    t.setWitchtovisibility(subIndex);
                    t.forceRefesh();
                }
            }
    }

    private int mBackKeyPressedTimes = 0;
    @Override
    public void onBackPressed() {
        Fragment f = fragments[currentTabIndex];
        if (f instanceof WebViewFragment) {
            WebViewFragment webViewFragment = (WebViewFragment) f;
            if (webViewFragment.onBackPressed()) {
                return;
            }
        }else if(f instanceof MatchFragment){
            MatchFragment matchFragment = (MatchFragment)f;
            if(matchFragment.onBackPressed()){
                return;
            }
        }
        if (mBackKeyPressedTimes == 0) {
            Toast.makeText(this, "再按一次退出程序 ", Toast.LENGTH_SHORT).show();
            mBackKeyPressedTimes = 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mBackKeyPressedTimes = 0;
                    }
                }
            }.start();
            return;

        } else{
            this.finish();
        }


        super.onBackPressed();
    }

    public void showProgressDialog(String text){
        if(progressShow){
            return;
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
