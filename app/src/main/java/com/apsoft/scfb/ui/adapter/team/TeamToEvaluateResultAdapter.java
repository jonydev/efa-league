package com.apsoft.scfb.ui.adapter.team;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.app.AppGlobalTool;
import com.apsoft.scfb.bean.TeamScheduleEntry;
import com.apsoft.scfb.utils.ImageLoader1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/12.
 */
public class TeamToEvaluateResultAdapter extends BaseAdapter {
    List<TeamScheduleEntry.GameSchedule> data;
    Context context;
    LayoutInflater inflater;
    public TeamToEvaluateResultAdapter(Context ctx){
        context = ctx;
        this.inflater= LayoutInflater.from(context);
        data = new ArrayList<>();
    }
    public void updateData(List<TeamScheduleEntry.GameSchedule> d){
        data.clear();
        data.addAll(d);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String flag = data.get(position).getFlag();
        ViewHolder holder=null;

        if (convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.lvitem_team_to_evaluate,null);

            holder.iv_teamflag_left= (ImageView) convertView.findViewById(R.id.iv_teamflag_left);
            holder.tv_team_name_left = (TextView) convertView.findViewById(R.id.tv_team_name_left);
            holder.iv_teamcloth_left_top= (ImageView) convertView.findViewById(R.id.iv_teamcloth_left_top);
            holder.iv_teamcloth_left_bottom= (ImageView) convertView.findViewById(R.id.iv_teamcloth_left_bottom);

            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_detail_time = (TextView) convertView.findViewById(R.id.tv_detail_time);

            holder.iv_teamflag_right = (ImageView) convertView.findViewById(R.id.iv_teamflag_right);
            holder.tv_team_name_right = (TextView) convertView.findViewById(R.id.tv_team_name_right);

            holder.tv_left_score = (TextView) convertView.findViewById(R.id.tv_left_score);
            holder.tv_right_score = (TextView) convertView.findViewById(R.id.tv_right_score);
            holder.iv_teamcloth_right_top = (ImageView) convertView.findViewById(R.id.iv_teamcloth_right_top);
            holder.iv_teamcloth_right_bottom = (ImageView) convertView.findViewById(R.id.iv_teamcloth_right_bottom);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        TeamScheduleEntry.GameSchedule schedule = data.get(position);
        ImageLoader1.getInstance().displayImage(context,data.get(position).getHome_team_image(),holder.iv_teamflag_left);
        ImageLoader1.getInstance().displayImage(context,data.get(position).getAway_team_image(),holder.iv_teamflag_right);
        holder.tv_team_name_left.setText(data.get(position).getHome_team_name());
        holder.tv_team_name_right.setText(data.get(position).getAway_team_name());
        holder.tv_left_score.setText(data.get(position).getHomescore());
        holder.tv_right_score.setText(data.get(position).getAwayscore());
        String date = data.get(position).getDatetime().substring(0,10);
        String detailTime = data.get(position).getDatetime().substring(12, 16);
        holder.tv_time.setText(date);
        holder.tv_detail_time.setText(detailTime);

        if (schedule.getHome_team_upper().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
            holder.iv_teamcloth_left_top.setImageResource(AppGlobalTool.getCloseDrawableId(schedule.getHome_team_upper()));
        } else {
            holder.iv_teamcloth_left_top.setImageResource(AppGlobalTool.getCloseDrawableId("0"));
        }
        if (schedule.getHome_team_lower().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
            holder.iv_teamcloth_left_bottom.setImageResource(AppGlobalTool.getPathDrawableId(schedule.getHome_team_lower()));
        } else {
            holder.iv_teamcloth_left_bottom.setImageResource(AppGlobalTool.getPathDrawableId("0"));
        }
        if (schedule.getAway_team_upper().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
            holder.iv_teamcloth_right_top.setImageResource(AppGlobalTool.getCloseDrawableId(schedule.getAway_team_upper()));
        } else {
            holder.iv_teamcloth_right_top.setImageResource(AppGlobalTool.getCloseDrawableId("0"));
        }
        if (schedule.getAway_team_lower().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
            holder.iv_teamcloth_right_bottom.setImageResource(AppGlobalTool.getPathDrawableId(schedule.getAway_team_lower()));
        } else {
            holder.iv_teamcloth_right_bottom.setImageResource(AppGlobalTool.getPathDrawableId("0"));
        }

        return convertView;
    }

    public static class ViewHolder{
        ImageView iv_teamflag_left;
        TextView tv_team_name_left;
        ImageView iv_teamcloth_left_top;
        ImageView iv_teamcloth_left_bottom;

        TextView tv_time;
        TextView tv_detail_time;

        ImageView iv_teamflag_right;
        TextView tv_team_name_right;

        TextView tv_left_score;
        TextView tv_right_score;
        ImageView iv_teamcloth_right_top;
        ImageView iv_teamcloth_right_bottom;
    }


}
