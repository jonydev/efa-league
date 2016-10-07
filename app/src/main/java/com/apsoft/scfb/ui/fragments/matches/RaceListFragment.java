package com.apsoft.scfb.ui.fragments.matches;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchScoreEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.fragments.matches.racelist.GetGoalFragment;
import com.apsoft.scfb.ui.fragments.matches.racelist.HelpGoalFragment;
import com.apsoft.scfb.ui.fragments.matches.racelist.RedAndYellowFragment;
import com.apsoft.scfb.ui.fragments.matches.racelist.ScoreFragment;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RaceListFragment extends Fragment {

    private static final String TAG = RaceListFragment.class.getSimpleName();
    private WebView webView;

    private View view;
    private RadioGroup rgRaceList;
    private RadioButton rbRaceScore;
    private FragmentManager manager;
    private FragmentTransaction fragmentTransaction;
    private ScoreFragment scoreFragment;
    private GetGoalFragment getGoalFragment;
    private HelpGoalFragment helpGoalFragment;
    private RedAndYellowFragment redAndYellowFragment;
    private RadioButton rb_get_score;
    private RadioButton rb_help_score;
    private RadioButton rb_red_yellow_card;

    private Fragment currentFrag;
    private int currentIndex;

    MatchScoreEntry matchScoreEntry;
    String lastOfficeId;
    private int swichFrag;

    public RaceListFragment() {
    }

    public static RaceListFragment newInstance() {

        Bundle args = new Bundle();

        RaceListFragment fragment = new RaceListFragment();
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
        webView.loadUrl("http://120.76.206.174:8080/Match/Billboard.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                String officeId = getOfficeId();
                Log.i(TAG, officeId);
                view.loadUrl("javascript:setOfficeId('" + officeId + "')");
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

    public void setSwichFrag(int swichFrag) {
        this.swichFrag = swichFrag;
    }
}