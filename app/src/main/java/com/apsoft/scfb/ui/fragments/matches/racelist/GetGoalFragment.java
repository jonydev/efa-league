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
import com.apsoft.scfb.ui.adapter.match.ScorefragmetAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetGoalFragment extends Fragment {

    private View view;
    private ListView lvScore;
    private GoalBillboardAdapter adapter;
    List cacheV;

    public GetGoalFragment() {
        // Required empty public constructor
    }

    public static GetGoalFragment newInstance() {

        Bundle args = new Bundle();

        GetGoalFragment fragment = new GetGoalFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_get_goal, container, false);
        initview();
        adapter=new GoalBillboardAdapter(getContext());
        lvScore.setAdapter(adapter);
        if(cacheV != null){
            adapter.updateData(cacheV);
        }
        return view;
    }

    private void initview() {
        lvScore = (ListView) view.findViewById(R.id.lv_score);
    }

    public void setData(List<MatchScoreEntry.Goal> d){
        if(adapter!=null){
            adapter.updateData(d);
            cacheV = null;
        }else{
            cacheV = d;
        }
    }

}
