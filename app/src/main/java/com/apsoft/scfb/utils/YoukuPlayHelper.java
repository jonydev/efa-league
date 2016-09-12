package com.apsoft.scfb.utils;

import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ykcloud.sdk.opentools.player.VODPlayer;
import com.ykcloud.sdk.opentools.player.VODPlayerStatListener;
import com.ykcloud.sdk.opentools.player.auth.VideoAuth;
import com.ykcloud.sdk.opentools.player.auth.VideoAuthCallback;
import com.ykcloud.sdk.opentools.player.entity.VideoLists;

/**
 * Created by admin on 2016/8/18.
 */
public class YoukuPlayHelper {
    private String TAG = "YoukuPlayHelper";
    VODPlayer player;
    RelativeLayout layout_player;
    Context context;
    public static int STATUS_STOP = 2;
    public static int STATUS_PAUSED = 3;
    public static int STATUS_PLAYING = 1;
    int status = 0;

    public void initView(Context ctx, RelativeLayout layout){
        context = ctx;
        player = new VODPlayer(ctx, true);
        //横竖屏切换(横屏全屏播放)
        //player.changeOrientation(VODPlayer.Orientation_land);
        //横竖屏切换(竖屏半屏播放)
        player.changeOrientation(VODPlayer.Orientation_portrait);
        //状态回调函数
        player.setmStatListener(new VODPlayerStatListener() {
            @Override
            public void onPlayerStat(int stat, int ext) {
//                ToastUtils.show("onPlayerStat : " + stat + "", 2000);
                Log.i(TAG, "player stat " + stat);
            }
        });

        layout_player = layout;
        layout_player.addView(player.getPlayRootLayout());

        //竖屏播放 点击返回按钮退出播放界面
//        player.getPlayRootLayout().findViewById(R.id.half_back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toggle();
//        }
//        });

    }

    public void play(String vid){
        if(vid==null || vid.isEmpty()){
            return;
        }
        //自行获取视频信息 填充 M3u8Video对象,调用play播放
        VideoAuth.requestM3u8(vid, context, new VideoAuthCallback() {
            @Override
            public void onAuthComplete(Object obj) {
                if (obj instanceof VideoLists) {
                    ((VideoLists) obj).VideoName = "视频标题";
                    player.playVideo((VideoLists) obj);
                    status = STATUS_PLAYING;
                }
            }

            @Override
            public void onError(String error) {
                com.ykcloud.sdk.platformtools.Log.e(TAG, " error : " + error);
                if (player != null) {
                    player.destroyVideo();
                    player.showNotice(200, "获取视频失败,无法播放:" + error, true);
                }
                Toast.makeText(context, "获取视频失败,无法播放:" + error, Toast.LENGTH_SHORT).show();
                layout_player.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //finish();
                    }
                }, 1000);
            }
        });
    }

    public void stop(){
        if (player != null) {
            player.stopVideo();
            status = STATUS_STOP;
        }
    }

    public void toggle(){
        if(player != null){
            if(status == STATUS_PAUSED){
                player.resumeVideo();
                status = STATUS_PLAYING;
            }else if(status == STATUS_PLAYING){
                player.pauseVideo();
                status = STATUS_PAUSED;
            }else if(status == STATUS_STOP){
                player.startPlayVideo();
                status = STATUS_PLAYING;
            }
        }
    }

    public void destoryPlayer() {
        player.destroyVideo();
    }
}
