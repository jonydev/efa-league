package com.apsoft.scfb.ui.fragments.team_result;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.TeamScheduleEntry;
import com.apsoft.scfb.ui.adapter.team.TeamCompeleteResultAdapter;
import com.apsoft.scfb.ui.adapter.team.TeamResultAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedFragment extends Fragment {


    private View view;
    private ListView lvComplete;
    private TeamCompeleteResultAdapter adapter;
    List cacheV;

    public CompletedFragment() {
    }

    public static CompletedFragment newInstance() {

        Bundle args = new Bundle();

        CompletedFragment fragment = new CompletedFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_completed, container, false);
        lvComplete = (ListView) view.findViewById(R.id.lv_complete);
        adapter=new TeamCompeleteResultAdapter(getActivity());
        lvComplete.setAdapter(adapter);
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
