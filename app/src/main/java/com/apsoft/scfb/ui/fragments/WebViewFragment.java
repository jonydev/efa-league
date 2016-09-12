package com.apsoft.scfb.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.apsoft.scfb.R;


public class WebViewFragment extends Fragment {

    private String initUrl = "http://www.baidu.com";
    private WebView mWebView;

    public void setInitUrl(String url){
        initUrl = url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        mWebView = (WebView) view.findViewById(R.id.activity_main_webview);

//        // Force links and redirects to open in the WebView instead of in a browser
//        mWebView.setWebViewClient(new WebViewClient());
//
//        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new AppInterface(), "_app");

        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });

        // Use remote resource
        // mWebView.loadUrl("http://example.com");

        // Stop local links and redirects from opening in browser instead of WebView
        // mWebView.setWebViewClient(new MyAppWebViewClient());

        // Use local resource
        //mWebView.loadUrl(initUrl);
        return view;
    }


    // Prevent the back-button from closing the app
    public boolean onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else {
        }
        return false;
    }


    static class AppInterface{
        public AppInterface(){
        }

        @JavascriptInterface
        public void goActivity(String activeName){
//            Intent intent = new Intent(getActivity(), MainActivity.class);
//            startActivity(intent);
        }

        @JavascriptInterface
        public void switchFragment(String fragment){
        }
    }
}

