package com.apsoft.scfb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.anywhere.appbase.BackCloseActivity;
import com.anywhere.view.listviewshangxia.XListView;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppGlobalTool;
import com.apsoft.scfb.bean.TestEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/16.
 */
public class SelectAreaActivity extends BackCloseActivity {
    XListView mListView;
    List<String>	mAreaShows = new ArrayList<>();
    EditText		mEditText;
    BaseAdapter mAdapter;
    Map<String, TestEntry.DataBean> listDatas = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_select);

        mEditText = (EditText) findViewById(R.id.editText1);
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                filterArea(arg0.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });

        mListView = (XListView) findViewById(R.id.listView1);
        mListView.setPullLoadEnable(false);
        mListView.setPullRefreshEnable(false);
        fillListView();
        filterArea("");
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent it = new Intent();
                String areaName = (String) arg0.getItemAtPosition(arg2);
                TestEntry.DataBean bean = listDatas.get(areaName);
                it.putExtra("area_id", bean.getId());
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }

    public void fillListView(){
        mListView.setAdapter(mAdapter=new BaseAdapter() {

            @Override
            public View getView(int arg0, View convertView, ViewGroup arg2) {
                // TODO Auto-generated method stub
                if(convertView==null){
                    convertView = View.inflate(SelectAreaActivity.this, android.R.layout.simple_list_item_2, null);
                }
                String sCode = (String) getItem(arg0);

                TextView tv1 = (TextView) convertView.findViewById(android.R.id.text1);
                TextView tv2 = (TextView) convertView.findViewById(android.R.id.text2);
                TestEntry.DataBean db = listDatas.get(sCode);
                tv1.setText(db.getName());
                tv2.setText(db.getCreate_date());
                return convertView;
            }

            @Override
            public long getItemId(int arg0) {
                // TODO Auto-generated method stub
                return arg0;
            }

            @Override
            public Object getItem(int arg0) {
                // TODO Auto-generated method stub
                return mAreaShows.get(arg0);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                if(mAreaShows==null){
                    return 0;
                }
                return mAreaShows.size();
            }
        });
    }

    String mOldArea="_abc";
    public void filterArea(String s){
        if(mOldArea.equalsIgnoreCase(s)){
            return;
        }
        mOldArea = s;
        TestEntry entry = AppGlobalTool.getTestEntry();
        mAreaShows.clear();
        listDatas.clear();
        for(int i=0; i<entry.getData().size(); ++i ){
            TestEntry.DataBean bean = entry.getData().get(i);
            mAreaShows.add(bean.getName());
            listDatas.put(bean.getName(), bean);
        }
        mAdapter.notifyDataSetChanged();
    }
}
