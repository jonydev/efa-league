package com.apsoft.scfb.ui.fragments.matches.racelist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchScoreEntry;
import com.apsoft.scfb.ui.adapter.match.HelpGoalBillboardAdapter;
import com.apsoft.scfb.ui.adapter.match.RedAndYellowCardAdapter;
import com.apsoft.scfb.ui.adapter.match.ScorefragmetAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RedAndYellowFragment extends Fragment {
    private View view;
    private ListView lvScore;
    private RedAndYellowCardAdapter adapter;
    List cacheV;

    public RedAndYellowFragment() {
        // Required empty public constructor
    }

    public static RedAndYellowFragment newInstance() {

        Bundle args = new Bundle();

        RedAndYellowFragment fragment = new RedAndYellowFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_red_yellow, container, false);
        initview();
        adapter=new RedAndYellowCardAdapter(getContext());
        lvScore.setAdapter(adapter);
        if(cacheV != null){
            adapter.updateData(cacheV);
        }
        return view;
    }

    private void initview() {
        lvScore = (ListView) view.findViewById(R.id.lv_score);
    }

    public void setData(List<MatchScoreEntry.RedYellowCard> d){
        if(adapter!=null){
            adapter.updateData(d);
            cacheV = null;
        }else{
            cacheV = d;
        }
    }
}

