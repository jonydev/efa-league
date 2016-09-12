package com.apsoft.scfb.ui.adapter.match;

import android.content.Context;
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
public class HelpGoalBillboardAdapter extends AbsAdapterWithData<MatchScoreEntry.HelpGoal> {
    Context context;
    private LayoutInflater inflater ;

    public HelpGoalBillboardAdapter(Context ctx){
        super();
        context = ctx;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_helpgoal_lv,null);
            holder.tv_turn_item_help= (TextView) view.findViewById(R.id.tv_turn_item_help);
            holder.tv_player_name_item_help= (TextView) view.findViewById(R.id.tv_player_name_item_help);
            holder.tv_team_name_item_help= (TextView) view.findViewById(R.id.tv_team_name_item_help);
            holder.tv_score_num= (TextView) view.findViewById(R.id.tv_score_num);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        MatchScoreEntry.HelpGoal value = (MatchScoreEntry.HelpGoal) getItem(i);
        holder.tv_turn_item_help.setText(String.valueOf(i+1));
        holder.tv_player_name_item_help.setText(value.getMember_name());
        holder.tv_team_name_item_help.setText(value.getTeam_name());
        holder.tv_score_num.setText(String.valueOf(value.getNum()));
        return view;
    }

    public static class ViewHolder{
        TextView tv_turn_item_help;
        TextView tv_player_name_item_help;
        TextView tv_team_name_item_help;
        TextView tv_score_num;

    }
}
