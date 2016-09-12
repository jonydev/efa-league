package com.apsoft.scfb.utils;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;


public class MediaPlayHelper {
	public static final String TAG = "MediaPlayHelper";

	private int currFilePos = 0;
	public MediaPlayHelper self = this;

	private MediaPlayer mediaPlayer;
	private SurfaceHolder surfaceHolder;
	private boolean isSurfaceViewExist = false;
	private String mFilename = null;


	private Callback surfaceCallback;

	public MediaPlayHelper(SurfaceView surfaceView, String filename) {
		// TODO Auto-generated constructor stub

		self.surfaceHolder = surfaceView.getHolder();
		mFilename = filename;
		init();
	}

	private void init() {
		surfaceCallback = new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				isSurfaceViewExist = false;
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				isSurfaceViewExist = true;
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
				// TODO Auto-generated method stub

			}
		};
		surfaceHolder.addCallback(surfaceCallback);
	}


	public void setUrl(String url){
		mFilename = url;
	}


	public void playFile(String filename) {
		if (mediaPlayer != null) {
			mediaPlayer.reset();
		} else {
			mediaPlayer = new MediaPlayer();
		}

		mediaPlayer.setDisplay(surfaceHolder);
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

		try {
			mediaPlayer.setDataSource(filename);
			mediaPlayer.prepare();
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
				}
			});
			mediaPlayer.start();
		} catch (Exception e) {
			e.printStackTrace();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}
	
	public boolean start(){
		if(isSurfaceViewExist){
			play();
			return true;
		}
		return false;
	}
	
	public void stop() {
		if (null != mediaPlayer) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	public void play() {
		if(isSurfaceViewExist && mFilename!=null && mFilename.length()>0){
			playFile(mFilename);
		}
	}
}
