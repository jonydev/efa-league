package com.apsoft.scfb.ui.adapter.match;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.MatchScoreEntry;
import com.apsoft.scfb.bean.RaceListEntry;
import com.apsoft.scfb.ui.adapter.AbsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/11.
 */
public class ScorefragmetAdapter extends BaseAdapter {
    private Context context;
    private List<MatchScoreEntry.Integral> data;
    private LayoutInflater inflater ;

    public ScorefragmetAdapter(Context context) {
        data = new ArrayList<MatchScoreEntry.Integral>();
        this.context=context;
        this.inflater=LayoutInflater.from(context);

    }

    public void updateData(List<MatchScoreEntry.Integral> d){
        data.clear();
        data.addAll(d);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data!=null?data.get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_score_fragment_lv,null);
            holder.tv_turn= (TextView) view.findViewById(R.id.tv_turn);
            holder.tv_name= (TextView) view.findViewById(R.id.tv_name);
            holder.tv_goals_num= (TextView) view.findViewById(R.id.tv_goals_num);
            holder.tv_score= (TextView) view.findViewById(R.id.tv_score);
            holder.tv_retain_score= (TextView) view.findViewById(R.id.tv_retain_score);
            holder.tv_yellow_card= (TextView) view.findViewById(R.id.tv_yellow_card);
            holder.tv_red_card= (TextView) view.findViewById(R.id.tv_red_card);

            holder.tv_sheng= (TextView) view.findViewById(R.id.tv_sheng);
            holder.tv_ping= (TextView) view.findViewById(R.id.tv_ping);
            holder.tv_fu= (TextView) view.findViewById(R.id.tv_fu);

            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tv_turn.setText(String.valueOf(i+1));
        holder.tv_name.setText(data.get(i).getTeam_name());
        holder.tv_score.setText(data.get(i).getPoint());
        holder.tv_retain_score.setText(data.get(i).getLost());
        holder.tv_goals_num.setText(data.get(i).getGoal());
        holder.tv_yellow_card.setText(data.get(i).getYellow());
        holder.tv_red_card.setText(data.get(i).getRed());

        holder.tv_sheng.setText(data.get(i).getWon());
        holder.tv_ping.setText(data.get(i).getBeaten());
        holder.tv_fu.setText(data.get(i).getEven());

        return view;
    }

    public static class ViewHolder{
        TextView tv_turn;
        TextView tv_name;
        TextView tv_score;
        TextView tv_goals_num;
        TextView tv_yellow_card;
        TextView tv_red_card;
        TextView tv_retain_score;

        TextView tv_sheng;
        TextView tv_ping;
        TextView tv_fu;

    }
}
