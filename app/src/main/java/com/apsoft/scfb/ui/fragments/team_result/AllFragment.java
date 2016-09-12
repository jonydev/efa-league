package com.apsoft.scfb.ui.fragments.team_result;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.TeamScheduleEntry;
import com.apsoft.scfb.ui.adapter.team.TeamAllResultAdapter;
import com.apsoft.scfb.ui.adapter.team.TeamResultAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {

    View view;
    ListView lv;
    TeamAllResultAdapter adapter;
    List cacheV;
    public AllFragment() {

    }

    public static AllFragment newInstance() {

        Bundle args = new Bundle();

        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_all, container, false);
        lv = (ListView) view.findViewById(R.id.ll_list_view);
        adapter = new TeamAllResultAdapter(getActivity());
        lv.setAdapter(adapter );
        if(cacheV != null){
            setData(cacheV);
        }
        return view;
    }

    public void setData(List<TeamScheduleEntry.GameSchedule> d){
        if(adapter != null){
            adapter.updateData(d);
            cacheV = null;
        }else{
            cacheV = d;
        }
    }
}
