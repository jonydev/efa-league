package com.apsoft.scfb.ui.adapter.match;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchScheduleEntry;
import com.apsoft.scfb.utils.ImageLoader1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class ExpandableAdapter extends BaseExpandableListAdapter{

    public static final int VALUE_01=0;
    public static final int VALUE_02=1;


    private Context context;
    private List<MatchScheduleEntry.GamescheduleDataBean.GroupDataBean>groppData;
    private List<MatchScheduleEntry.GamescheduleDataBean.FirstchilddataBean>firstchilddataBeen;
    private List<MatchScheduleEntry.GamescheduleDataBean.SecondchilddataBean>secondchilddataBeen;
    private List<MatchScheduleEntry.GamescheduleDataBean.ThirdchilddataBean>thirdchilddataBeen;
    private List<MatchScheduleEntry.GamescheduleDataBean.FourchilddataBean>fourchilddataBeen;
    public ExpandableAdapter(Context context, List<MatchScheduleEntry.GamescheduleDataBean.GroupDataBean> groppData, List<MatchScheduleEntry.GamescheduleDataBean.FirstchilddataBean> firstchilddataBeen, List<MatchScheduleEntry.GamescheduleDataBean.SecondchilddataBean> secondchilddataBeen, List<MatchScheduleEntry.GamescheduleDataBean.ThirdchilddataBean> thirdchilddataBeen, List<MatchScheduleEntry.GamescheduleDataBean.FourchilddataBean> fourchilddataBeen) {
        this.context = context;
        this.groppData = groppData;
        this.firstchilddataBeen = firstchilddataBeen;
        this.secondchilddataBeen = secondchilddataBeen;
        this.thirdchilddataBeen = thirdchilddataBeen;
        this.fourchilddataBeen = fourchilddataBeen;
    }

    @Override
    public int getGroupCount() {
        return groppData!=null?groppData.size():0;
    }


    @Override
    public int getChildrenCount(int i) {
        return 6;
    }

    @Override
    public int getChildTypeCount() {
        return 2;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        String whetherGame = groppData.get(groupPosition).getWhetherGame();
        if (whetherGame.equals("false")) {
            return VALUE_01;
        } else if (whetherGame.equals("true")) {
            return VALUE_02;
        }

        return super.getChildType(groupPosition, childPosition);
    }


    @Override
    public Object getGroup(int i) {
        return groppData!=null?groppData.get(i):null;
    }

    @Override
    public Object getChild(int i, int i1) {
        if (i == 0) {
            return firstchilddataBeen.get(i1);
        } else if(i==1){
            return secondchilddataBeen.get(i1);
        } else if (i==2){
            return thirdchilddataBeen.get(i1);
        }else if (i==3){
            return fourchilddataBeen.get(i1);
        }
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    //组合子元素持有稳定的id
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
            view.setTag(groupViewHolder);
        }else {
            groupViewHolder= (GroupViewHolder) view.getTag();
        }
        groupViewHolder.groupTv.setText(groppData.get(i).getTitle_group());
        groupViewHolder.groupTime.setText(groppData.get(i).getTime_group());
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
//                    childViewHolder2.iv_left_teamhead_item_22= (ImageView) view.findViewById(R.id.iv_left_teamhead_item_22);
//                    childViewHolder2.iv_right_teamhead_item_2= (ImageView) view.findViewById(R.id.iv_right_teamhead_item_22);
//                    childViewHolder2.tv_left_teamname_item_2= (TextView) view.findViewById(R.id.tv_left_teamname_item_22);
//                    childViewHolder2.tv_right_teamname_item_2= (TextView) view.findViewById(R.id.tv_right_teamname_item_22);
//                    childViewHolder2.tv_compare_22= (TextView) view.findViewById(R.id.tv_compare_22);
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

        switch (childType){
            case VALUE_01:
                switch (i){
                    case 0:
                        childViewHolder.tv_left_teamname_item.setText(firstchilddataBeen.get(i1).getLeft_team_name());
                        childViewHolder.tv_place_child_item.setText(firstchilddataBeen.get(i1).getBall_park());
                        childViewHolder.tv_time_child_item.setText(firstchilddataBeen.get(i1).getDetail_time());
                        childViewHolder.tv_right_teamname_item.setText(firstchilddataBeen.get(i1).getRight_team_name());
                        ImageLoader1.getInstance().displayImage(context,firstchilddataBeen.get(i1).getLeft_team_flag(),childViewHolder.iv_left_teamflag_item);
                        ImageLoader1.getInstance().displayImage(context,firstchilddataBeen.get(i1).getLeft_team_cloth(),childViewHolder.iv_left_teamhead_item);
                        ImageLoader1.getInstance().displayImage(context,firstchilddataBeen.get(i1).getRight_team_glag(),childViewHolder.iv_right_teamflag_item);
                        ImageLoader1.getInstance().displayImage(context,firstchilddataBeen.get(i1).getRight_team_cloth(),childViewHolder.iv_right_teamhead_item);
                        break;
                    case 1:
                        childViewHolder.tv_left_teamname_item.setText(secondchilddataBeen.get(i1).getLeft_team_name());
                        childViewHolder.tv_place_child_item.setText(secondchilddataBeen.get(i1).getBall_park());
                        childViewHolder.tv_time_child_item.setText(secondchilddataBeen.get(i1).getDetail_time());
                        childViewHolder.tv_right_teamname_item.setText(secondchilddataBeen.get(i1).getRight_team_name());
                        ImageLoader1.getInstance().displayImage(context,secondchilddataBeen.get(i1).getLeft_team_flag(),childViewHolder.iv_left_teamflag_item);
                        ImageLoader1.getInstance().displayImage(context,secondchilddataBeen.get(i1).getLeft_team_cloth(),childViewHolder.iv_left_teamhead_item);
                        ImageLoader1.getInstance().displayImage(context,secondchilddataBeen.get(i1).getRight_team_glag(),childViewHolder.iv_right_teamflag_item);
                        ImageLoader1.getInstance().displayImage(context,secondchilddataBeen.get(i1).getRight_team_cloth(),childViewHolder.iv_right_teamhead_item);
                        break;
                    case 2:
                        childViewHolder.tv_left_teamname_item.setText(thirdchilddataBeen.get(i1).getLeft_team_name());
                        childViewHolder.tv_place_child_item.setText(thirdchilddataBeen.get(i1).getBall_park());
                        childViewHolder.tv_time_child_item.setText(thirdchilddataBeen.get(i1).getDetail_time());
                        childViewHolder.tv_right_teamname_item.setText(thirdchilddataBeen.get(i1).getRight_team_name());
                        ImageLoader1.getInstance().displayImage(context,thirdchilddataBeen.get(i1).getLeft_team_flag(),childViewHolder.iv_left_teamflag_item);
                        ImageLoader1.getInstance().displayImage(context,thirdchilddataBeen.get(i1).getLeft_team_cloth(),childViewHolder.iv_left_teamhead_item);
                        ImageLoader1.getInstance().displayImage(context,thirdchilddataBeen.get(i1).getRight_team_glag(),childViewHolder.iv_right_teamflag_item);
                        ImageLoader1.getInstance().displayImage(context,thirdchilddataBeen.get(i1).getRight_team_cloth(),childViewHolder.iv_right_teamhead_item);
                        break;
                    case 3:
                        childViewHolder.tv_left_teamname_item.setText(fourchilddataBeen.get(i1).getLeft_team_name());
                        childViewHolder.tv_place_child_item.setText(fourchilddataBeen.get(i1).getBall_park());
                        childViewHolder.tv_time_child_item.setText(fourchilddataBeen.get(i1).getDetail_time());
                        childViewHolder.tv_right_teamname_item.setText(fourchilddataBeen.get(i1).getRight_team_name());
                        ImageLoader1.getInstance().displayImage(context,fourchilddataBeen.get(i1).getLeft_team_flag(),childViewHolder.iv_left_teamflag_item);
                        ImageLoader1.getInstance().displayImage(context,fourchilddataBeen.get(i1).getLeft_team_cloth(),childViewHolder.iv_left_teamhead_item);
                        ImageLoader1.getInstance().displayImage(context,fourchilddataBeen.get(i1).getRight_team_glag(),childViewHolder.iv_right_teamflag_item);
                        ImageLoader1.getInstance().displayImage(context,fourchilddataBeen.get(i1).getRight_team_cloth(),childViewHolder.iv_right_teamhead_item);
                        break;
                }
                break;
            case VALUE_02:

                switch (i){
                    case 0:
                        childViewHolder2.tv_left_teamname_item_2.setText(firstchilddataBeen.get(i1).getLeft_team_name());
                        childViewHolder2.tv_right_teamname_item_2.setText(firstchilddataBeen.get(i1).getRight_team_name());
                        childViewHolder2.tv_compare_22.setText(firstchilddataBeen.get(i1).getContrastScore());
                        ImageLoader1.getInstance().displayImage(context,firstchilddataBeen.get(i1).getLeft_team_flag(),childViewHolder2.iv_left_teamhead_item_22);
                        ImageLoader1.getInstance().displayImage(context,firstchilddataBeen.get(i1).getRight_team_glag(),childViewHolder2.iv_right_teamhead_item_2);
                        break;
                    case 1:
                        childViewHolder2.tv_left_teamname_item_2.setText(secondchilddataBeen.get(i1).getLeft_team_name());
                        childViewHolder2.tv_right_teamname_item_2.setText(secondchilddataBeen.get(i1).getRight_team_name());
                        childViewHolder2.tv_compare_22.setText(secondchilddataBeen.get(i1).getContrastScore());
                        ImageLoader1.getInstance().displayImage(context,secondchilddataBeen.get(i1).getLeft_team_flag(),childViewHolder2.iv_left_teamhead_item_22);
                        ImageLoader1.getInstance().displayImage(context,secondchilddataBeen.get(i1).getRight_team_glag(),childViewHolder2.iv_right_teamhead_item_2);
                        break;
                    case 2:
                        childViewHolder2.tv_left_teamname_item_2.setText(thirdchilddataBeen.get(i1).getLeft_team_name());
                        childViewHolder2.tv_right_teamname_item_2.setText(thirdchilddataBeen.get(i1).getRight_team_name());
                        childViewHolder2.tv_compare_22.setText(thirdchilddataBeen.get(i1).getContrastScore());
                        ImageLoader1.getInstance().displayImage(context,thirdchilddataBeen.get(i1).getLeft_team_flag(),childViewHolder2.iv_left_teamhead_item_22);
                        ImageLoader1.getInstance().displayImage(context,thirdchilddataBeen.get(i1).getRight_team_glag(),childViewHolder2.iv_right_teamhead_item_2);
                        break;
                    case 3:
                        childViewHolder2.tv_left_teamname_item_2.setText(fourchilddataBeen.get(i1).getLeft_team_name());
                        childViewHolder2.tv_right_teamname_item_2.setText(fourchilddataBeen.get(i1).getRight_team_name());
                        childViewHolder2.tv_compare_22.setText(fourchilddataBeen.get(i1).getContrastScore());
                        ImageLoader1.getInstance().displayImage(context,fourchilddataBeen.get(i1).getLeft_team_flag(),childViewHolder2.iv_left_teamhead_item_22);
                        ImageLoader1.getInstance().displayImage(context,fourchilddataBeen.get(i1).getRight_team_glag(),childViewHolder2.iv_right_teamhead_item_2);
                        break;
                }
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
