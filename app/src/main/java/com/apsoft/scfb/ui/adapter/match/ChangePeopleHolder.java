package com.apsoft.scfb.ui.adapter.match;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.vipul.hp_hp.timelineview.TimelineView;

/**
 * Created by admin on 2016/8/11.
 */
public class ChangePeopleHolder extends RecyclerView.ViewHolder {
    TimelineView timelineView;
    TextView txtTime;
    TextView txtName;

    public ChangePeopleHolder(View itemView, int viewType) {
        super(itemView);
        timelineView= (TimelineView) itemView.findViewById(R.id.time_marker);
        txtTime= (TextView) itemView.findViewById(R.id.tx_time);
        txtName= (TextView) itemView.findViewById(R.id.tx_name);
        timelineView.initLine(viewType);
    }
}
