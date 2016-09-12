package com.apsoft.scfb.ui.adapter.match;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchScoreEntry;
import com.apsoft.scfb.ui.adapter.AbsAdapterWithData;

/**
 * Created by admin on 2016/8/12.
 */
public class GoalBillboardAdapter extends AbsAdapterWithData<MatchScoreEntry.Goal> {
    Context context;
    private LayoutInflater inflater ;
    public GoalBillboardAdapter(Context ctx){
        super();
        context = ctx;
        this.inflater=LayoutInflater.from(context);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_goal_bill_borad_lv,null);
            holder.tv_turn_get_goal_item= (TextView) view.findViewById(R.id.tv_turn_get_goal_item);
            holder.tv_player_name_get_goal_item= (TextView) view.findViewById(R.id.tv_player_name_get_goal_item);
            holder.tv_team_name_get_goal_item= (TextView) view.findViewById(R.id.tv_team_name_get_goal_item);
            holder.tv_score_num_get_goal_item= (TextView) view.findViewById(R.id.tv_score_num_get_goal_item);
            holder.tv_dianqiu_num_get_goal_item= (TextView) view.findViewById(R.id.tv_dianqiu_num_get_goal_item);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        MatchScoreEntry.Goal value = (MatchScoreEntry.Goal) getItem(i);
        holder.tv_turn_get_goal_item.setText(String.valueOf(i+1));
        holder.tv_player_name_get_goal_item.setText(value.getMember_name());
        holder.tv_team_name_get_goal_item.setText(value.getTeam_name()+"");
        holder.tv_score_num_get_goal_item.setText(String.valueOf(value.getNum()));
        holder.tv_dianqiu_num_get_goal_item.setText("0");
        return view;
    }

    public static class ViewHolder{
        TextView tv_turn_get_goal_item;
        TextView tv_player_name_get_goal_item;
        TextView tv_team_name_get_goal_item;
        TextView tv_score_num_get_goal_item;
        TextView tv_dianqiu_num_get_goal_item;

    }
}
