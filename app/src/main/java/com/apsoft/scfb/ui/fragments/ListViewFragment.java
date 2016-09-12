package com.apsoft.scfb.ui.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anywhere.view.listviewshangxia.XListView;
import com.apsoft.scfb.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public class ListViewFragment extends Fragment {
    protected int					mQueryStart = 0;
    protected int					mPageSize = 10;
    protected XListView			    mListView;
    View                rootView;
    protected Handler mPostHandler = new Handler();
    private boolean progressShow;
    ProgressDialog progressDialog;

    public void queryData(final boolean bFirst){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resource = getViewLayoutId();
        rootView = inflater.inflate(resource, container, false);
        initXList(rootView);
        fragmentInit();
        queryFirst();
        return rootView;
    }

    public void fragmentInit(){

    }
    public int getViewLayoutId(){
        return R.layout.fragment_empty_list;
    }

    public void initXList(View rootView){
        mListView = (XListView) rootView.findViewById(R.id.listView1);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(new XListView.IXListViewListener() {

            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                queryFirst();
                mPostHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        onLoadFinish();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                // TODO Auto-generated method stub
                queryNext();
                mPostHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        onLoadFinish();
                    }
                }, 2000);
            }
        });
    }

    public void queryFirst(){
        mQueryStart = 0;
        queryData(true);
    }

    public List getData(){
        return null;
    }

    public void queryNext(){
        List d = getData();
        if(d!=null){
            mQueryStart = d.size();
        }else{
            mQueryStart = 0;
        }
        queryData(false);
    }

    void onLoadFinish(){
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    public void showProgressDialog(String text){
        hideProgressDialog();
        progressShow = true;
        progressDialog = new ProgressDialog(getActivity());
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
