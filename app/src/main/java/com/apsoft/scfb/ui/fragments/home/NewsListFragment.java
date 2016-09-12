package com.apsoft.scfb.ui.fragments.home;

import com.anywhere.tools.JsonUtil;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppGlobalTool;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.http.NetSetting;
import com.apsoft.scfb.ui.adapter.home.NewsListAdapter;
import com.apsoft.scfb.ui.fragments.ListViewFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public class NewsListFragment extends ListViewFragment {
    static class NewData{
        int id;
        String name;
        String image;
    }
    List<NewData>       newsList = new ArrayList<NewData>();
    NewsListAdapter     newsListAdapter;

    public int getViewLayoutId(){
        return R.layout.fragment_home_newslist;
    }

    public void fragmentInit(){
        newsListAdapter  = new NewsListAdapter(getContext(), newsList);
        mListView.setAdapter(newsListAdapter);
    }

    public void queryData(final boolean bFirst){
        showProgressDialog("正在加载数据");
//        AppGlobalTool.execNetRequest(new Runnable() {
//
//            @Override
//            public void run() {
//                NetSCFBRequest.getNewsList("2a77aad75c274f4ebb7d986648ebfe2d", mQueryStart, mPageSize, new NetSetting.IRemoteQueryCallback() {
//
//                    @Override
//                    public void onFinish(String data, JSONObject jsonObject) {
//                        try {
//                            final List<Map<String, Object>> listData = JsonUtil.getlistForJson(jsonObject.getJSONArray("data"));
//                            mPostHandler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    processNewData(listData, bFirst);
//                                }
//                            });
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        mPostHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                hideProgressDialog();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailed(Exception e) {
//                        mPostHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                hideProgressDialog();
//                            }
//                        });
//                    }
//                });
//            }
//        });
    }

    public List getData(){
        return newsList;
    }

    public void removeExistData(int id, boolean bFirst){
        if(bFirst){
            for(int i=0; i<newsList.size(); ++i){
                if(newsList.get(i).id == id){
                    newsList.remove(i);
                    break;
                }
            }
        }else{
            for(int i=newsList.size()-1; i>=0; --i){
                if(newsList.get(i).id == id){
                    newsList.remove(i);
                    break;
                }
            }
        }

    }

    public void processNewData(List<Map<String, Object>> inputData, boolean bFirst){
        if(inputData==null || inputData.size()<1){return;}
        if(bFirst){
            newsList.clear(); ///may insert
            for(Map<String,Object> obj : inputData){
                NewData newD = new NewData();
                newD.name = (String) obj.get("name");
                newD.image = (String) obj.get("image");
                newD.id = Integer.parseInt((String) obj.get("id"));
                newsList.add(newD);
            }
        }else{
            for(Map<String,Object> obj : inputData){
                NewData newD = new NewData();
                newD.name = (String) obj.get("name");
                newD.image = (String) obj.get("image");
                newD.id = Integer.parseInt((String) obj.get("id"));
                removeExistData(newD.id, bFirst);
                newsList.add(newD);
            }
        }

        newsListAdapter.notifyDataSetChanged();
    }

}
