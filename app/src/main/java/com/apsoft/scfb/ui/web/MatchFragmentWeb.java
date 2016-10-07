package com.apsoft.scfb.ui.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.apsoft.scfb.R;

/**
 * Created by zhuang on 10/2/16.
 */
public class MatchFragmentWeb extends Fragment {
    WebView mWebview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_match_fragment,container,false);
        mWebview = (WebView) view.findViewById(R.id.webview);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mWebview.loadUrl("http://120.76.206.174:8080/Match/Schedule.html");
        WebSettings settings = mWebview.getSettings();
        settings.getJavaScriptCanOpenWindowsAutomatically();
        settings.setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                String msg = "17981239632246c18e0404785da78e8c";
                view.loadUrl("javascript:setOfficeId('" + msg + "')");
            }
        });
    }
}
