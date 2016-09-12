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
import com.apsoft.scfb.ui.fragments.ListViewFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotStartFragment extends Fragment {

    View view;
    ListView lv;
    TeamResultAdapter adapter;
    List cacheV;
    public NotStartFragment() {
    }

    public static NotStartFragment newInstance() {

        Bundle args = new Bundle();

        NotStartFragment fragment = new NotStartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_not_start, container, false);
        lv = (ListView) view.findViewById(R.id.ll_list_view);
        adapter = new TeamResultAdapter(getActivity());
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
