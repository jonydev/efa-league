package com.apsoft.scfb.ui.adapter.match;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchGameScheduleEntry;
import com.apsoft.scfb.utils.ImageLoader1;

import java.util.List;

/**
 * Created by admin on 2016/8/10.
 */
public class ExpandableScheduleAdapter extends BaseExpandableListAdapter {
    public static final int VALUE_01=0;
    public static final int VALUE_02=1;


    private Context context;
    List<MatchGameScheduleEntry> matchGameScheduleEntries;

    public ExpandableScheduleAdapter(Context ctx, List<MatchGameScheduleEntry> entries){
        context = ctx;
        matchGameScheduleEntries = entries;
    }

    @Override
    public int getGroupCount() {
        return matchGameScheduleEntries.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return matchGameScheduleEntries.get(groupPosition).getMatch().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return matchGameScheduleEntries.get(groupPosition);
    }

    @Override
    public int getChildTypeCount() {
        return 2;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        boolean status = matchGameScheduleEntries.get(groupPosition).isStatus();
        if (status) {
            return VALUE_01;
        } else{
            return VALUE_02;
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return matchGameScheduleEntries.get(groupPosition).getMatch().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder=null;
        if (view==null){
            groupViewHolder=new GroupViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.item_expandable_group_gamefrag,null);
            groupViewHolder.groupTv= (TextView) view.findViewById(R.id.tv_group_expandble);
            groupViewHolder.groupTime= (TextView) view.findViewById(R.id.tv_time_schudule);
            groupViewHolder.iv_sign= (ImageView) view.findViewById(R.id.iv_sign);
            groupViewHolder.iv_sign_up= (ImageView) view.findViewById(R.id.iv_sign_up);
            groupViewHolder.rl_item_group_top= (RelativeLayout) view.findViewById(R.id.rl_item_group_top);
            view.setTag(groupViewHolder);
        }else {
            groupViewHolder= (GroupViewHolder) view.getTag();
        }
        groupViewHolder.groupTv.setText("第" + matchGameScheduleEntries.get(i).getSerial() + "轮");
        String time = matchGameScheduleEntries.get(i).getTime();
        String substring = time.substring(0, 10);
        groupViewHolder.groupTime.setText(substring);

        if (b){
            groupViewHolder.iv_sign.setVisibility(View.VISIBLE);
            groupViewHolder.iv_sign_up.setVisibility(View.INVISIBLE);
        } else if (!b) {
            groupViewHolder.iv_sign.setVisibility(View.INVISIBLE);
            groupViewHolder.iv_sign_up.setVisibility(View.VISIBLE);
        }
        view.setTop(24);
        view.setPadding(0,60,0,0);
        return view;
    }



    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder=null;
        ChildViewHolder2 childViewHolder2=null;
        int childType = getChildType(i, i1);
        if (view==null){
            switch (childType){
                case VALUE_01:
                    childViewHolder=new ChildViewHolder();
                    view=LayoutInflater.from(context).inflate(R.layout.item_expandable_child_gamefrag,null);
                    childViewHolder.iv_left_teamflag_item= (ImageView) view.findViewById(R.id.iv_left_teamflag_item);
                    childViewHolder.iv_left_teamhead_item= (ImageView) view.findViewById(R.id.iv_left_teamhead_item);
                    childViewHolder.iv_right_teamflag_item= (ImageView) view.findViewById(R.id.iv_right_teamflag_item);
                    childViewHolder.iv_right_teamhead_item= (ImageView) view.findViewById(R.id.iv_right_teamhead_item);
                    childViewHolder.tv_left_teamname_item= (TextView) view.findViewById(R.id.tv_left_teamname_item);
                    childViewHolder.tv_place_child_item= (TextView) view.findViewById(R.id.tv_place_child_item);
                    childViewHolder.tv_right_teamname_item= (TextView) view.findViewById(R.id.tv_right_teamname_item);
                    childViewHolder.tv_time_child_item= (TextView) view.findViewById(R.id.tv_time_child_item);
                    view.setTag(childViewHolder);
                    break;
                case VALUE_02:
                    childViewHolder2=new ChildViewHolder2();
                    view=LayoutInflater.from(context).inflate(R.layout.item_expandable_child_gamefrag22,null);
                    childViewHolder2.iv_left_teamhead_item_22= (ImageView) view.findViewById(R.id.iv_left_teamhead_item_22);
                    childViewHolder2.iv_right_teamhead_item_2= (ImageView) view.findViewById(R.id.iv_right_teamhead_item_22);
                    childViewHolder2.tv_left_teamname_item_2= (TextView) view.findViewById(R.id.tv_left_teamname_item_22);
                    childViewHolder2.tv_right_teamname_item_2= (TextView) view.findViewById(R.id.tv_right_teamname_item_22);
                    childViewHolder2.tv_compare_22= (TextView) view.findViewById(R.id.tv_compare_22);
                    view.setTag(childViewHolder2);
                    break;
            }
        }else {
            switch (childType){
                case VALUE_01:
                    childViewHolder= (ChildViewHolder) view.getTag();
                    break;
                case VALUE_02:
                    childViewHolder2= (ChildViewHolder2) view.getTag();
                    break;
            }}

        MatchGameScheduleEntry.GameSchedule schedule = (MatchGameScheduleEntry.GameSchedule) getChild(i,i1);
        switch (childType){
            case VALUE_01:
                childViewHolder.tv_left_teamname_item.setText(schedule.getHome_team_name());
                childViewHolder.tv_place_child_item.setText(schedule.getPlace());
                childViewHolder.tv_time_child_item.setText(schedule.getDatetime());
                childViewHolder.tv_right_teamname_item.setText(schedule.getAway_team_name());
                ImageLoader1.getInstance().displayImage(context,schedule.getHome_team_image(),childViewHolder.iv_left_teamflag_item);
//                ImageLoader1.getInstance().displayImage(context,schedule.getLeft_team_cloth(),childViewHolder.iv_left_teamhead_item);
                ImageLoader1.getInstance().displayImage(context,schedule.getHome_team_image(),childViewHolder.iv_right_teamflag_item);
//                ImageLoader1.getInstance().displayImage(context,schedule.getRight_team_cloth(),childViewHolder.iv_right_teamhead_item);
                break;
            case VALUE_02:
                childViewHolder2.tv_left_teamname_item_2.setText(schedule.getHome_team_name());
                childViewHolder2.tv_right_teamname_item_2.setText(schedule.getAway_team_name());
                if(schedule.getFlag().equalsIgnoreCase("1"))
                    childViewHolder2.tv_compare_22.setText(schedule.getHomescore() + ":" + schedule.getAwayscore());
                else
                    childViewHolder2.tv_compare_22.setText("未开赛");
                ImageLoader1.getInstance().displayImage(context,schedule.getHome_team_image(),childViewHolder2.iv_left_teamhead_item_22);
                ImageLoader1.getInstance().displayImage(context,schedule.getAway_team_image(),childViewHolder2.iv_right_teamhead_item_2);
                break;
        }
        return view;
    }




    @Override
    public boolean isChildSelectable(int i, int i1) {
        int childType = getChildType(i, i1);
        switch (childType) {
            case VALUE_01:
                return false;
            case VALUE_02:
                return true;
        }
        return false;
    }

    class GroupViewHolder{
        TextView groupTv;
        TextView groupTime;
        TextView tvWhetherGame;

        ImageView iv_sign;
        ImageView iv_sign_up;
        RelativeLayout rl_item_group_top;
    }

    class ChildViewHolder{
        ImageView iv_left_teamhead_item;
        ImageView iv_left_teamflag_item;
        TextView tv_left_teamname_item;
        TextView tv_time_child_item;
        TextView tv_place_child_item;
        TextView tv_right_teamname_item;
        ImageView iv_right_teamflag_item;
        ImageView iv_right_teamhead_item;
    }

    class ChildViewHolder2{
        ImageView iv_left_teamhead_item_22;
        TextView tv_left_teamname_item_2;
        TextView tv_compare_22;
        TextView tv_right_teamname_item_2;
        ImageView iv_right_teamhead_item_2;
    }
}
