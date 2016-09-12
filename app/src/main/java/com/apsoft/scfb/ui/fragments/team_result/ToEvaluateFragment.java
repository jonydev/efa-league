package com.apsoft.scfb.ui.fragments.team_result;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.TeamScheduleEntry;
import com.apsoft.scfb.ui.adapter.team.TeamResultAdapter;
import com.apsoft.scfb.ui.adapter.team.TeamToEvaluateResultAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToEvaluateFragment extends Fragment {


    private View view;
    List cacheV;
    ListView lv;
    private TeamToEvaluateResultAdapter adapter;
    public ToEvaluateFragment() {
    }

    public static ToEvaluateFragment newInstance() {

        Bundle args = new Bundle();

        ToEvaluateFragment fragment = new ToEvaluateFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_to_evaluate, container, false);
        lv = (ListView) view.findViewById(R.id.ll_list_view_toevaluate);
        adapter = new TeamToEvaluateResultAdapter(getActivity());
        lv.setAdapter(adapter );
        if(cacheV != null){
            setData(cacheV);
        }
        return view;
    }

    public void setData(List<TeamScheduleEntry.GameSchedule> d){
        if(adapter == null){
            cacheV = d;
        }else{
            adapter.updateData(d);
            cacheV = null;
        }
    }




}
