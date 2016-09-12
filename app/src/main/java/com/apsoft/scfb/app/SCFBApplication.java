package com.apsoft.scfb.app;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.anywhere.appbase.AppBaseApplication;
import com.anywhere.tools.AppTrack;
import com.df.netprocess.utils.CrashHandler;
import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
///FrontiaApplication baidu push must inherit from FrontiaApplication
public class SCFBApplication extends AppBaseApplication {
    final static String stackTraceUrl = "http://182.92.6.152/logs/stacktrace/";
    final static String activityTrackUrl = "http://182.92.6.152/logs/dolog/activity_track/";

    public void onAppSettings(){
//        EaseUI.getInstance().init(this, null);
//        FrontiaApplication.initFrontiaApplication(getApplicationContext());
        mInitLocationAMap = false;
        CrashHandler.getInstance().init(getApplicationContext(), getApplication(), stackTraceUrl);
        AppTrack.TRACK_URL = activityTrackUrl;
//        AppGlobalTool.initBaiduPush(getApplicationContext());
//        String version = getCurrentVersion();
//        String cacheVersion = getSettingValueFromDefault("cache_version");
//        if(cacheVersion == null || (!cacheVersion.equalsIgnoreCase(version))) {
//            new FileUtil().deepExtractAssertsToContext(this, "extract", "caches");
//            saveSettingValueToDefault("cache_version", version);
//        }
//        AppGlobalTool.initBaiduPush(getApplicationContext());

        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
        AppGlobalTool.initClothData();
        AppGlobalTool.initArea();
        mPostHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AppGlobalTool.initYoukuSDK();
            }
        }, 3000);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static SCFBApplication getApplication(){
        return (SCFBApplication)getInstance();
    }

    public void startCheckTokenExpired(){
    }

    public void onLoginSuccess(){

    }

    public Handler reminderHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 100:
                    Toast.makeText(SCFBApplication.getInstance().getApplicationContext(), (String)msg.obj, Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
