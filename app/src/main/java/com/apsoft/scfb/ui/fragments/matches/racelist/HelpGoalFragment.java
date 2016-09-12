package com.apsoft.scfb.ui.fragments.matches.racelist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchScoreEntry;
import com.apsoft.scfb.ui.adapter.match.GoalBillboardAdapter;
import com.apsoft.scfb.ui.adapter.match.HelpGoalBillboardAdapter;
import com.apsoft.scfb.ui.adapter.match.ScorefragmetAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpGoalFragment extends Fragment {
    private View view;
    private ListView lvScore;
    private HelpGoalBillboardAdapter adapter;
    List cacheV;

    public HelpGoalFragment() {
        // Required empty public constructor
    }

    public static HelpGoalFragment newInstance() {

        Bundle args = new Bundle();

        HelpGoalFragment fragment = new HelpGoalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_help_goal, container, false);
        initview();
        adapter=new HelpGoalBillboardAdapter(getContext());
        lvScore.setAdapter(adapter);
        if(cacheV != null){
            adapter.updateData(cacheV);
        }
        return view;
    }

    private void initview() {
        lvScore = (ListView) view.findViewById(R.id.lv_score);
    }

    public void setData(List<MatchScoreEntry.HelpGoal> d){
        if(adapter!=null){
            adapter.updateData(d);
            cacheV = null;
        }else{
            cacheV = d;
        }
    }
}
