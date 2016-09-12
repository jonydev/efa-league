package com.apsoft.scfb.ui.fragments.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppAccountTool;
import com.apsoft.scfb.app.AppGlobalTool;
import com.apsoft.scfb.bean.TestEntry;
import com.apsoft.scfb.constant.ConstantUrl;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.MainActivity;
import com.apsoft.scfb.ui.SelectAreaActivity;
import com.apsoft.scfb.ui.WebActivity;
import com.apsoft.scfb.ui.adapter.home.LvMainAdapter;
import com.apsoft.scfb.bean.HomeEntry;
import com.apsoft.scfb.ui.NoticeActivity;
import com.apsoft.scfb.ui.fragments.HomeFragment;
import com.apsoft.scfb.utils.LocalImageHolderView;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zaaach.citypicker.CityPickerActivity;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;
import okhttp3.Headers;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public class MainFragment extends Fragment {
    final static int REQUEST_AREA_SELECT = 103;
    private List<String > localImages;
    private ListView LvMain;
    private LvMainAdapter adapter;
    private List<HomeEntry.ListBean >data;
    View rootView;
    private ConvenientBanner convenientBanner;
    private ImageView imgbtnNotice;
    HomeEntry homeEntry;
    private TextView areaText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_main, container, false);
        rootView = view;
        initSubViews();
        return view;
    }


    public void initSubViews(){

        LvMain= (ListView) rootView.findViewById(R.id.lv_main);
        localImages=new ArrayList();
        data=new ArrayList<>();
        adapter=new LvMainAdapter(getContext(),data);

        addHeadView();
        LvMain.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        creatData();

    }

    /**
     * 添加头部
     */
    private void addHeadView() {
        View v=getActivity().getLayoutInflater().inflate(R.layout.homefragment_lv_head, null);
        LvMain.addHeaderView(v);
        View joinMatchView = v.findViewById(R.id.ll_join_match);
        joinMatchView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                MainActivity.s_mainActivity.switchFragment(3,0);
            }
        });

        View joinTeamView = v.findViewById(R.id.ll_join_team);
        joinTeamView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                MainActivity.s_mainActivity.switchFragment(1,0);
            }
        });

        areaText = (TextView) v.findViewById(R.id.ll_area_text);
        areaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CityPickerActivity.class);
                startActivityForResult(intent, CityPickerActivity.REQUEST_CODE_PICK_CITY);
            }
        });



        convenientBanner = (ConvenientBanner) v.findViewById(R.id.convenientBanner);

        imgbtnNotice = (ImageView) v.findViewById(R.id.imgbtn_notice);
        setImgbtnNoticeLisenter();
    }


    private void initImageBannel(){
        initListenter(convenientBanner);
    }
    /**
     * 设置公告栏的监听
     */
    private void setImgbtnNoticeLisenter() {
        imgbtnNotice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
            }
        });
    }

    //初始化数据
    private void creatData() {
        String araaId = AppAccountTool.getInstance().getAreaId();
        if(araaId == null){
            NetHomeQuery.requestArereInfo(new BaseCallback<String>(){

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
                    TestEntry testEntry = JSON.parseObject(o, TestEntry.class);
                    AppGlobalTool.setTestEntry(testEntry);
                    List<TestEntry.DataBean> data = testEntry.getData();
                    TestEntry.DataBean dataBean = data.get(data.size() - 1);
                    String id = dataBean.getId();
                    AppAccountTool.getInstance().setAreaId(id);
                    NetHomeQuery.requestHomeInfo(id, new BaseCallback<String>() {
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
                            JSONObject obj = JSON.parseObject(o);
                            obj = obj.getJSONObject("data");
                            homeEntry = JSON.toJavaObject(obj, HomeEntry.class);
                            List<HomeEntry.ListBean> homeEntryList = homeEntry.getList();
                            List<HomeEntry.SliderBean> slider = homeEntry.getSlider();
                            for (int i = 0; i < slider.size(); i++) {
                                localImages.add(slider.get(i).getImage());
                            }

                            if(User.getInstance().office_id == null || User.getInstance().office_id.length()<1){
                                User.getInstance().office_id = homeEntryList.get(0).getId();
                            }


                            MainFragment.this.data.clear();
                            MainFragment.this.data.addAll(homeEntryList);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Response response, int code, Exception e) {

                        }
                    });
                }

                @Override
                public void onError(Response response, int code, Exception e) {

                }
            });
        }else{
            NetHomeQuery.requestHomeInfo(araaId, new BaseCallback<String>() {
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
                    JSONObject obj = JSON.parseObject(o);
                    obj = obj.getJSONObject("data");
                    //                        String json = obj.toJSONString();
                    //                        HomeEntry homeEntry = JSONObject.parseObject(json, HomeEntry.class);
                    homeEntry = JSON.toJavaObject(obj, HomeEntry.class);
                    List<HomeEntry.ListBean> homeEntryList = homeEntry.getList();
                    localImages.clear();
                    List<HomeEntry.SliderBean> slider = homeEntry.getSlider();
                    for (int i = 0; i < slider.size(); i++) {
                        localImages.add(slider.get(i).getImage());
                    }

                    for(int i=0; i<homeEntryList.size(); ++i){
                        if(homeEntryList.get(i).getIs_default()!=null && homeEntryList.get(i).getIs_default().equalsIgnoreCase("1")){
                            User.getInstance().office_id = homeEntryList.get(i).getId();
                            break;
                        }
                    }
                    if(User.getInstance().office_id == null || User.getInstance().office_id.length()<1){
                        User.getInstance().office_id = homeEntryList.get(0).getId();
                    }

                    initImageBannel();
                    MainFragment.this.data.clear();
                    MainFragment.this.data.addAll(homeEntryList);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(Response response, int code, Exception e) {

                }
            });
        }
    }

    private void initListenter(ConvenientBanner convenientBanner) {
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                        //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        convenientBanner.startTurning(2000);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
        //        convenientBanner.setManualPageable(false);//设置不能手动影响

        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HomeEntry.SliderBean sb = homeEntry.getSlider().get(position);
                if(sb.getLink() != null && sb.getLink().length()>0){
                    Intent it = new Intent(getActivity(), WebActivity.class);
                    it.putExtra("url", sb.getLink());
                    startActivity(it);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==Activity.RESULT_OK && requestCode == CityPickerActivity.REQUEST_CODE_PICK_CITY){
            String citySelect = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
            areaText.setText(citySelect);
        }

        if(requestCode == REQUEST_AREA_SELECT && resultCode== Activity.RESULT_OK){
            String area_id = data.getStringExtra("area_id");
            User.getInstance().setArea_id(area_id);
            creatData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

