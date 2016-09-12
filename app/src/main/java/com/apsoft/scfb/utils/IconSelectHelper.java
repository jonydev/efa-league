package com.apsoft.scfb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.anywhere.simplyadapter.ShareImgAdapter;
import com.anywhere.tools.PicUtil;
import com.anywhere.view.ActionSheet;
import com.anywhere.view.ActionSheet.OnActionSheetSelected;
import com.apsoft.scfb.R;
import com.apsoft.scfb.localdata.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IconSelectHelper implements OnActionSheetSelected, OnCancelListener {
	private final int CAPTURE_CODE = 300;//標志照相后取圖片
	private final int IMAGE_CODE = 200; //標志從相冊中取圖片
	
	private final int REMOVE_IMAGE_CODE = 100; //標志從相冊中取圖片
	
	private ImageView fenxiang_img;
	private List<Bitmap> bitmaps;
	protected List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>(0);
	private String newurl;

	double shared_lat;
	double shared_lng;
	View		mRootView;
	Activity		mCtx;
	
	public List<Map<String, Object>>	getSelImageList(){
		return imagelist;
	}
	
	public void initGrid(Activity act,View view, int rid, boolean isBigImage){
		mCtx = act;
		mRootView = view;
		fenxiang_img = (ImageView) mRootView.findViewById(rid);
		if (User.getInstance().getUser_photo().length()==0||User.getInstance().getUser_photo()==null){
			fenxiang_img.setImageResource(R.mipmap.ic_launcher);
		}else if (User.getInstance().getUser_photo()!=null&&User.getInstance().getUser_photo().length()>0){
			ImageLoader1.getInstance().displayCricleImage(mCtx, User.getInstance().getUser_photo(),fenxiang_img);
		}

		Button btnAdd =  null;//(Button) mRootView.findViewById(R.id.btnAdd);
		if(btnAdd!=null){
			btnAdd.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ActionSheet.showSheet(mCtx, IconSelectHelper.this, IconSelectHelper.this);
				}
			});
		}

		fenxiang_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActionSheet.showSheet(mCtx, IconSelectHelper.this, IconSelectHelper.this);
			}
		});
		int b=imagelist.size()+1;
		b = b%3>0?b/3+1:b/3;
	}

	public void addShareImage(Bitmap bm, String path, double lat, double lng){
		int b = imagelist.size();
		if(b>4)
			return;
		
		Map<String, Object> map = new HashMap<String, Object>();
		ViewGroup.LayoutParams layoutParamss = null;

		map = new HashMap<String, Object>();
		map.put("img",bm);
		map.put("imgUrl",path);
		map.put("lat", lat);
		map.put("lng", lng);
		imagelist.add(map);

		b=imagelist.size();
		if(b==1){
			if((int)lat!=0 && (int)lng!=0){
				shared_lat = lat;
				shared_lng = lng;
			}
		}
		b = b%3>0?b/3+1:b/3;
		layoutParamss=fenxiang_img.getLayoutParams();
		layoutParamss.height=(b*10) + (b*160);
		fenxiang_img.setLayoutParams(layoutParamss);
	}
	
	static class ImageInfo{
		public String imagePath;
		public double lat;
		public double lng;
	}
	
	public static ImageInfo selectImage(Context context,Intent data){
		Uri selectedImage = data.getData();
		if(selectedImage!=null){
			String uriStr=selectedImage.toString();
			String path=uriStr.substring(10,uriStr.length());
			if(path.startsWith("com.sec.android.gallery3d")){
				Log.e("a", "It's auto backup pic path:"+selectedImage.toString());
				return null;
			}
		}		
		String[] filePathColumn = { MediaStore.Images.Media.DATA,  MediaStore.Images.Media.LATITUDE, MediaStore.Images.Media.LONGITUDE};
		Cursor cursor = context.getContentResolver().query(selectedImage,filePathColumn, null, null, null);
		if(cursor.moveToFirst()){
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			int columnLat = cursor.getColumnIndex(filePathColumn[1]);
			int columnLng = cursor.getColumnIndex(filePathColumn[2]);
			double imgLat = cursor.getDouble(columnLat);
			double imgLng = cursor.getDouble(columnLng);
			cursor.close();
			ImageInfo info = new ImageInfo();
			info.imagePath = picturePath;
			info.lat = imgLat;
			info.lng = imgLng;
			return info;
		}else{
			return null;
		}
	}
	
	public void onClick(int whichButton) {
		Intent intent ;
		switch (whichButton) {
			case 0:
				//showToast("点击了图库");
				intent = new Intent(Intent.ACTION_PICK,
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				mCtx.startActivityForResult(intent, 200);
			break;
			
			case 1:
				//showToast("点击了相机");
				File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
			    if (!appDir.exists()) {
			        appDir.mkdir();
			    }
			    String fileName = System.currentTimeMillis() + ".jpg";
			    File file = new File(appDir, fileName);
			    newurl=file.getAbsolutePath();
				intent= new Intent();
			    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(newurl)));
				intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
				mCtx.startActivityForResult(intent, CAPTURE_CODE);
				break;
				
			case 2:
				//showToast("点击了取消");
				break;
			default:
				break;
		}
	}
	

	
	public void onCancel(DialogInterface dialog) {
	}
	
	String content_image = "";
	public String getContentImage(){
		return content_image;
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) {  
            return;// 一定要加，不然后會報錯。  
        }

        // 这里得到相册，通過URI來得到圖片數據  
		if (requestCode == IMAGE_CODE) {  
            Uri originalUri = data.getData();  
            ImageInfo imageinfo = selectImage(mCtx, data);
            //ContentResolver cr = this.getContentResolver();  
            if (originalUri != null) {  
            	String path= imageinfo.imagePath;

				Bitmap bitma=PicUtil.getimage(path);
//                 	查看图片大小KB
				 ByteArrayOutputStream baos = new ByteArrayOutputStream();		
				baos = new ByteArrayOutputStream();		
				bitma.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				 Log.e("图片大小", "图片大小"+baos.toByteArray().length);
				 if(bitmaps==null){
					 bitmaps = new ArrayList<Bitmap>(10);
				 }
				bitmaps.add(bitma);
				bitma=PicUtil.centerSquareScaleBitmap((Bitmap) bitma, 150);
				fenxiang_img.setImageBitmap(bitma);
				addShareImage( bitma,path, imageinfo.lat, imageinfo.lng);
            }
		}

        // 这里相机，得到圖片數據  
		if (requestCode == CAPTURE_CODE && resultCode == Activity.RESULT_OK){
			String sdStatus = Environment.getExternalStorageState();
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
				Log.i("TestFile",
						"SD card is not avaiable/writeable right now.");
				return;
			}
			//Bundle bundle = data.getExtras();  
			//Bitmap bitmap=(Bitmap) bundle.get("data");//PicUtil.getimage(newurl);
			Bitmap bitmap=PicUtil.getimage(newurl);
			//bitmaps.add(PicUtil.bitmaptoString(bitmap));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();		
			baos = new ByteArrayOutputStream();		
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			//String newurl = SysUtil.insertBitmapToSystemPhotoFolder(mCtx,bitmap);
			 if(bitmaps==null){
				 bitmaps = new ArrayList<Bitmap>(10);
			 }
			bitmaps.add(bitmap);
			bitmap=PicUtil.centerSquareScaleBitmap(bitmap, 150);
			fenxiang_img.setImageBitmap(bitmap);
 			addShareImage(bitmap,newurl, 0, 0);
		}
		
		if(requestCode ==REMOVE_IMAGE_CODE && resultCode == Activity.RESULT_OK){
			if("ok".equals(data.getExtras().get("states"))){
			int num=Integer.valueOf(data.getExtras().get("imgNum").toString()) ;
			imagelist.remove(num);
			bitmaps.remove(num);
			}
		}
	}


}
