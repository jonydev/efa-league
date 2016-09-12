package com.apsoft.scfb.ui.fragments.matches.racelist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchScoreEntry;
import com.apsoft.scfb.bean.RaceListEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.ui.adapter.match.ScorefragmetAdapter;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {

    private View view;
    private ListView lvScore;
    private ScorefragmetAdapter adapter;
    List cacheV;

    public ScoreFragment() {
    }

    public static ScoreFragment newInstance() {

        Bundle args = new Bundle();

        ScoreFragment fragment = new ScoreFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_score, container, false);
        initview();
        adapter=new ScorefragmetAdapter(getContext());
        lvScore.setAdapter(adapter);
        if(cacheV != null){
            adapter.updateData(cacheV);
        }
        return view;
    }

    private void initview() {
        lvScore = (ListView) view.findViewById(R.id.lv_score);
    }

    public void setData(List<MatchScoreEntry.Integral> d){
        if(adapter!=null){
            adapter.updateData(d);
            cacheV = null;
        }else{
            cacheV = d;
        }
    }

}
