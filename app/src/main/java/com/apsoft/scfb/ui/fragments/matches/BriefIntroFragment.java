package com.apsoft.scfb.ui.fragments.matches;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchBriefEntry;
import com.apsoft.scfb.constant.ConstantUrl;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.utils.LocalImageHolderView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class BriefIntroFragment extends Fragment {

    private static final String TAG = BriefIntroFragment.class.getSimpleName();
    private WebView webView;

//    private String  office_id;

    public BriefIntroFragment() {

    }

    public static BriefIntroFragment newInstance() {
        Bundle args = new Bundle();
        BriefIntroFragment fragment = new BriefIntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brief_intro, container, false);
//        initview();
//        creatData();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        webView = (WebView) view.findViewById(R.id.webview);
        loadUrl();
    }

    public void forceRefresh() {
        loadUrl();
    }

    private void loadUrl() {
        webView.loadUrl("http://120.76.206.174:8080/Match/intro.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                try {
                    String officeId = getOfficeId();
                    Log.i(TAG, officeId);
                    view.loadUrl("javascript:setOfficeId('" + officeId + "')");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getOfficeId() {
        String officeID = User.getInstance().getCurrentOffice();
        if (null == officeID) {
            return User.getInstance().getOffice_id();
        }
        return officeID;
    }


    //
//    private void creatData() {
//        queryData();
//
//    }
//
//    private void initview() {
//        tvRaceBrief= (TextView) view.findViewById(R.id.tv_race_brief);
//        tvRuleIntro= (TextView) view.findViewById(R.id.tv_rule_intro);
//        tvJudgeIntro= (TextView) view.findViewById(R.id.tv_judge_intro);
//    }
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser && view!=null){
//            queryData();
//        }
//    }
//
//    public void queryData(){
//        String officeIDSet = User.getInstance().getCurrentOffice();
////        if(officeIDSet == null){
////            officeIDSet = User.getInstance().getOffice_id();
////        }
//        office_id = officeIDSet;
//        if(officeIDSet == null){
//            return;
//        }
//        NetHomeQuery.requestMatchInfo(officeIDSet, new BaseCallback<String>() {
//            @Override
//            public void onBeforeRequest(Request request) {
//
//            }
//
//            @Override
//            public void onFailure(Request request, Exception e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) {
//
//            }
//
//            @Override
//            public void onSuccess(Response response, String o) {
//                JSONObject jsonObject = JSON.parseObject(o);
//                jsonObject = jsonObject.getJSONObject("data");
//                String matchInfo = jsonObject.getString("match_info");
//                String ruleInfo = jsonObject.getString("rule_info");
//                String referInfo = jsonObject.getString("referee_info");
//                JSONArray jsonArray = jsonObject.getJSONArray("images");
//                if(jsonArray!=null) {
//                    List<String> images = new ArrayList<String>();
//                    for (int i = 0; i < jsonArray.size(); ++i) {
//                        images.add(jsonArray.getString(i));
//                    }
//
//                    ConvenientBanner convenientBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
//                    convenientBanner.setPages(
//                            new CBViewHolderCreator<LocalImageHolderView>() {
//                                @Override
//                                public LocalImageHolderView createHolder() {
//                                    LocalImageHolderView v = new LocalImageHolderView();
//                                    v.setScaleType(ImageView.ScaleType.CENTER);
//                                    return v;
//                                }
//                            }, images)
//                    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                    //                        .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
//                    //设置指示器的方向
//                    //                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
//                    ;
//                    convenientBanner.startTurning(2000);
//                }
//                tvRaceBrief.setText(matchInfo);
//                tvRuleIntro.setText(ruleInfo);
//                tvJudgeIntro.setText(referInfo);
//
//
//
//            }
//
//            @Override
//            public void onError(Response response, int code, Exception e) {
//
//            }
//        });
//    }
//
//    public void forceRefresh(){
//        if(User.getInstance().getCurrentOffice() != office_id){
//            queryData();
//        }
//    }
}
