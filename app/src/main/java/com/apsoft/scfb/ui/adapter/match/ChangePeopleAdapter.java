package com.apsoft.scfb.ui.adapter.match;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchGameScheduleEntry;
import com.apsoft.scfb.bean.ScheduleDetailEntry;
import com.vipul.hp_hp.timelineview.LineType;
import com.vipul.hp_hp.timelineview.TimelineView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/11.
 */
public class ChangePeopleAdapter extends RecyclerView.Adapter<ChangePeopleHolder> {
    Context mContext;
    private LayoutInflater inflater;
    List<ScheduleDetailEntry.LaterAppearance> mData;

    public ChangePeopleAdapter(Context ctx){
        mContext = ctx;
        mData = new ArrayList<ScheduleDetailEntry.LaterAppearance>();
        inflater=LayoutInflater.from(mContext);
    }

    public void updateData(List<ScheduleDetailEntry.LaterAppearance> d){
        mData.clear();
        if(d!=null)
            mData.addAll(d);
        notifyDataSetChanged();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    public static int getTimeLineViewType(int position, int total_size) {

        if(total_size == 1) {
            return LineType.ONLYONE;
        } else if(position == 0) {
            return LineType.BEGIN;
        } else if(position == total_size - 1) {
            return LineType.END;
        } else {
            return LineType.NORMAL;
        }
    }



    @Override
    public int getItemViewType(int position) {
        ScheduleDetailEntry.LaterAppearance la = mData.get(position);
        int offset = 0;
        if(!la.is_home()){
            offset += 100;
        }
        return TimelineView.getTimeLineViewType(position,getItemCount()) + offset;
    }

    @Override
    public ChangePeopleHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();

        View view;

        int timeLineType = viewType % 100;
        int posType = viewType / 100;
        if(posType == 1) {
            view = View.inflate(parent.getContext(), R.layout.lvitem_timeline_right, null);
        } else {
            view = View.inflate(parent.getContext(), R.layout.lvitem_timeline, null);
        //view = View.inflate(parent.getContext(), R.layout.lvitem_timeline_right, null);
        }

        return new ChangePeopleHolder(view, timeLineType);
    }

    @Override
    public void onBindViewHolder(ChangePeopleHolder holder, int position) {

        ScheduleDetailEntry.LaterAppearance timeLineModel = mData.get(position);

//        Resources res= mContext.getResources();
//        holder.timelineView.setMarker(res.getDrawable(R.drawable.back));
        holder.txtName.setText(timeLineModel.getMember_name() );
        holder.txtTime.setText( timeLineModel.getTime() );

    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }


}
