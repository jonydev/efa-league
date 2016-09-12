package com.apsoft.scfb.utils;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class LoopMediaPlayer extends MediaPlayer{
	LoopMediaPlayer self = this;
	private int n;
	private ArrayList<AssetFileDescriptor> assetFileDescriptors;
	public LoopMediaPlayer(ArrayList<AssetFileDescriptor> assetFileDescriptors) {
		// TODO Auto-generated constructor stub
		self.assetFileDescriptors = assetFileDescriptors;
	}
	
	@Override
	public void setDataSource(FileDescriptor fd, long offset, long length)
			throws IOException, IllegalArgumentException, IllegalStateException {
		// TODO Auto-generated method stub
		super.setDataSource(fd, offset, length);
	}
	
	@Override
	public void start() throws IllegalStateException {
		// TODO Auto-generated method stub
		super.start();
	}
}
