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
public class TeamAllResultAdapter extends BaseAdapter {
    public static final int VALUE_01=0;
    public static final int VALUE_02=1;
    List<TeamScheduleEntry.GameSchedule> data;
    Context context;
    LayoutInflater inflater;
    public TeamAllResultAdapter(Context ctx){
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
    public int getItemViewType(int position) {
        String flag = data.get(position).getFlag();
        if (flag!=null){
            if (flag.equals("0")){
                return VALUE_01;
            }else if (flag.equals("1")){
                return VALUE_02;
            }
        }
        return VALUE_01;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        NotStartViewHolder notStartViewHolder=null;
        CompeleteViewHolder compeleteViewHolder=null;
            if (convertView==null){
                switch (itemViewType) {
                    case VALUE_01:
                        notStartViewHolder=new NotStartViewHolder();
                        convertView=inflater.inflate(R.layout.lvitem_team_notstart,null);
                        notStartViewHolder.iv_teamflag_left= (ImageView) convertView.findViewById(R.id.iv_teamflag_left);
                        notStartViewHolder.tv_team_name_left = (TextView) convertView.findViewById(R.id.tv_team_name_left);
                        notStartViewHolder.iv_teamcloth_left_top= (ImageView) convertView.findViewById(R.id.iv_teamcloth_left_top);
                        notStartViewHolder.iv_teamcloth_left_bottom= (ImageView) convertView.findViewById(R.id.iv_teamcloth_left_bottom);

                        notStartViewHolder.tv_not_start_game = (TextView) convertView.findViewById(R.id.tv_not_start_game);
                        notStartViewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                        notStartViewHolder.tv_detail_time = (TextView) convertView.findViewById(R.id.tv_detail_time);
                        notStartViewHolder.tv_detail_place = (TextView) convertView.findViewById(R.id.tv_detail_place);

                        notStartViewHolder.iv_teamflag_right = (ImageView) convertView.findViewById(R.id.iv_teamflag_right);
                        notStartViewHolder.iv_teamcloth_right_top = (ImageView) convertView.findViewById(R.id.iv_teamcloth_right_top);
                        notStartViewHolder.iv_teamcloth_right_bottom = (ImageView) convertView.findViewById(R.id.iv_teamcloth_right_bottom);
                        notStartViewHolder.tv_team_name_right = (TextView) convertView.findViewById(R.id.tv_team_name_right);
                        convertView.setTag(notStartViewHolder);
                        break;
                    case VALUE_02:
                        compeleteViewHolder=new CompeleteViewHolder();
                        convertView=inflater.inflate(R.layout.lvitem_team_complete,null);

                        compeleteViewHolder.iv_teamflag_left= (ImageView) convertView.findViewById(R.id.iv_teamflag_left);
                        compeleteViewHolder.tv_team_name_left = (TextView) convertView.findViewById(R.id.tv_team_name_left);
                        compeleteViewHolder.iv_teamcloth_left_top= (ImageView) convertView.findViewById(R.id.iv_teamcloth_left_top);
                        compeleteViewHolder.iv_teamcloth_left_bottom= (ImageView) convertView.findViewById(R.id.iv_teamcloth_left_bottom);

                        compeleteViewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                        compeleteViewHolder.tv_detail_time = (TextView) convertView.findViewById(R.id.tv_detail_time);

                        compeleteViewHolder.iv_teamflag_right = (ImageView) convertView.findViewById(R.id.iv_teamflag_right);
                        compeleteViewHolder.tv_team_name_right = (TextView) convertView.findViewById(R.id.tv_team_name_right);

                        compeleteViewHolder.tv_left_score = (TextView) convertView.findViewById(R.id.tv_left_score);
                        compeleteViewHolder.tv_right_score = (TextView) convertView.findViewById(R.id.tv_right_score);
                        compeleteViewHolder.iv_teamcloth_right_top = (ImageView) convertView.findViewById(R.id.iv_teamcloth_right_top);
                        compeleteViewHolder.iv_teamcloth_right_bottom = (ImageView) convertView.findViewById(R.id.iv_teamcloth_right_bottom);
                        convertView.setTag(compeleteViewHolder);
                        break;
                }
            }else {
                switch (itemViewType){
                    case VALUE_01:
                        notStartViewHolder= (NotStartViewHolder) convertView.getTag();
                        break;
                    case VALUE_02:
                        compeleteViewHolder= (CompeleteViewHolder) convertView.getTag();
                        break;
                }
            }
        TeamScheduleEntry.GameSchedule schedule = data.get(position);
            switch (itemViewType) {
                case VALUE_01:
                    ImageLoader1.getInstance().displayImage(context,data.get(position).getHome_team_image(),notStartViewHolder.iv_teamflag_left);
                    ImageLoader1.getInstance().displayImage(context,data.get(position).getAway_team_image(),notStartViewHolder.iv_teamflag_right);
                    if (schedule.getHome_team_upper().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
                        notStartViewHolder.iv_teamcloth_left_top.setImageResource(AppGlobalTool.getCloseDrawableId(schedule.getHome_team_upper()));
                    } else {
                        notStartViewHolder.iv_teamcloth_left_top.setImageResource(AppGlobalTool.getCloseDrawableId("0"));
                    }
                    if (schedule.getHome_team_lower().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
                        notStartViewHolder.iv_teamcloth_left_bottom.setImageResource(AppGlobalTool.getPathDrawableId(schedule.getHome_team_lower()));
                    } else {
                        notStartViewHolder.iv_teamcloth_left_bottom.setImageResource(AppGlobalTool.getPathDrawableId("0"));
                    }
                    if (schedule.getAway_team_upper().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
                        notStartViewHolder.iv_teamcloth_right_top.setImageResource(AppGlobalTool.getCloseDrawableId(schedule.getAway_team_upper()));
                    } else {
                        notStartViewHolder.iv_teamcloth_right_top.setImageResource(AppGlobalTool.getCloseDrawableId("0"));
                    }
                    if (schedule.getAway_team_lower().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
                        notStartViewHolder.iv_teamcloth_right_bottom.setImageResource(AppGlobalTool.getPathDrawableId(schedule.getAway_team_lower()));
                    } else {
                        notStartViewHolder.iv_teamcloth_right_bottom.setImageResource(AppGlobalTool.getPathDrawableId("0"));
                    }

                    notStartViewHolder.tv_team_name_left.setText(data.get(position).getHome_team_name());
                    notStartViewHolder.tv_team_name_right.setText(data.get(position).getAway_team_name());
                    String date = data.get(position).getDatetime().substring(0,10);
                    String detailTime = data.get(position).getDatetime().substring(12, 16);
                    notStartViewHolder.tv_time.setText(date);
                    notStartViewHolder.tv_detail_time.setText(detailTime);
                    break;
                case VALUE_02:
                    ImageLoader1.getInstance().displayImage(context,data.get(position).getHome_team_image(),compeleteViewHolder.iv_teamflag_left);
                    ImageLoader1.getInstance().displayImage(context,data.get(position).getAway_team_image(),compeleteViewHolder.iv_teamflag_right);
                    if (schedule.getHome_team_upper().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
                        compeleteViewHolder.iv_teamcloth_left_top.setImageResource(AppGlobalTool.getCloseDrawableId(schedule.getHome_team_upper()));
                    } else {
                        compeleteViewHolder.iv_teamcloth_left_top.setImageResource(AppGlobalTool.getCloseDrawableId("0"));
                    }
                    if (schedule.getHome_team_lower().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
                        compeleteViewHolder.iv_teamcloth_left_bottom.setImageResource(AppGlobalTool.getPathDrawableId(schedule.getHome_team_lower()));
                    } else {
                        compeleteViewHolder.iv_teamcloth_left_bottom.setImageResource(AppGlobalTool.getPathDrawableId("0"));
                    }
                    if (schedule.getAway_team_upper().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
                        compeleteViewHolder.iv_teamcloth_right_top.setImageResource(AppGlobalTool.getCloseDrawableId(schedule.getAway_team_upper()));
                    } else {
                        compeleteViewHolder.iv_teamcloth_right_top.setImageResource(AppGlobalTool.getCloseDrawableId("0"));
                    }
                    if (schedule.getAway_team_lower().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
                        compeleteViewHolder.iv_teamcloth_right_bottom.setImageResource(AppGlobalTool.getPathDrawableId(schedule.getAway_team_lower()));
                    } else {
                        compeleteViewHolder.iv_teamcloth_right_bottom.setImageResource(AppGlobalTool.getPathDrawableId("0"));
                    }
                    compeleteViewHolder.tv_team_name_left.setText(data.get(position).getHome_team_name());
                    compeleteViewHolder.tv_team_name_right.setText(data.get(position).getAway_team_name());
                    compeleteViewHolder.tv_left_score.setText(data.get(position).getHomescore());
                    compeleteViewHolder.tv_right_score.setText(data.get(position).getAwayscore());
                    String dates = data.get(position).getDatetime().substring(0,10);
                    String datesdetailtime = data.get(position).getDatetime().substring(12, 16);
                    compeleteViewHolder.tv_time.setText(dates);
                    compeleteViewHolder.tv_detail_time.setText(datesdetailtime);
                    break;
            }

        return convertView;
    }


    public static class NotStartViewHolder{
        ImageView iv_teamflag_left;
        TextView tv_team_name_left;
        ImageView iv_teamcloth_left_top;
        ImageView iv_teamcloth_left_bottom;

        TextView tv_not_start_game;
        TextView tv_time;
        TextView tv_detail_time;
        TextView tv_detail_place;

        ImageView iv_teamflag_right;
        TextView tv_team_name_right;
        ImageView iv_teamcloth_right_top;
        ImageView iv_teamcloth_right_bottom;
    }

    public static class CompeleteViewHolder{
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
