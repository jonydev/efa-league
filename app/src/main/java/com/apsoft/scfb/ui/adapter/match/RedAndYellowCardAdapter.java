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
public class RedAndYellowCardAdapter extends AbsAdapterWithData<MatchScoreEntry.RedYellowCard> {
    Context context;
    private LayoutInflater inflater ;
    public RedAndYellowCardAdapter(Context ctx){
        super();
        context = ctx;
        this.inflater=LayoutInflater.from(context);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_red_yellow_lv,null);
            holder.tv_turn_item_red_yellow= (TextView) view.findViewById(R.id.tv_turn_item_red_yellow);
            holder.tv_player_name_item_red_yellow= (TextView) view.findViewById(R.id.tv_player_name_item_red_yellow);
            holder.tv_team_name_item_red_yellow= (TextView) view.findViewById(R.id.tv_team_name_item_red_yellow);
            holder.tv_red_num_item_red_yellow= (TextView) view.findViewById(R.id.tv_red_num_item_red_yellow);
            holder.tv_yellow_num_item_red_yellow= (TextView) view.findViewById(R.id.tv_yellow_num_item_red_yellow);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        MatchScoreEntry.RedYellowCard value = (MatchScoreEntry.RedYellowCard) getItem(i);
        holder.tv_turn_item_red_yellow.setText(String.valueOf(i+1));
        holder.tv_player_name_item_red_yellow.setText(value.getMember_name());
        holder.tv_team_name_item_red_yellow.setText(value.getTeam_name());
        holder.tv_red_num_item_red_yellow.setText(value.getRed_number());
        holder.tv_yellow_num_item_red_yellow.setText(value.getYellow_number());
        return view;
    }

    public static class ViewHolder{
        TextView tv_turn_item_red_yellow;
        TextView tv_player_name_item_red_yellow;
        TextView tv_team_name_item_red_yellow;
        TextView tv_red_num_item_red_yellow;
        TextView tv_yellow_num_item_red_yellow;

    }
}
