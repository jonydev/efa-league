package com.apsoft.scfb.ui.adapter.team;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.TeamDetailEntry;
import com.apsoft.scfb.utils.ImageLoader1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/12.
 */
public class TeamMemberAdapter extends BaseAdapter {
    List<TeamDetailEntry.TeamMember> data;
    Context context;
    LayoutInflater inflater;
    public TeamMemberAdapter(Context ctx){
        context = ctx;
        this.inflater= LayoutInflater.from(context);
        data = new ArrayList<>();
    }
    public void updateData(List<TeamDetailEntry.TeamMember> d,String leader){
        data.clear();
        data.addAll(d);
//        for (int i = 0; i < data.size(); i++) {
//            if (data.get(i).getName()!=null&&data.get(i).getName().length()>0) {
//                if (data.get(i).getName().equals(leader)){
//                    data.remove(i);
//                    break;
//                }
//            }
//        }
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
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.lvitem_teammember,null);
            holder.img= (ImageView) convertView.findViewById(R.id.ll_image_header);
            holder.name = (TextView) convertView.findViewById(R.id.ll_team_member_name);
            holder.scoreResult = (TextView) convertView.findViewById(R.id.tv_player_result_content);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        if(data.get(position).getPhoto() != null && data.get(position).getPhoto().length()>0)
            ImageLoader1.getInstance().displayCricleImage(context,data.get(position).getPhoto(),holder.img);
        holder.name.setText(data.get(position).getName());
        holder.scoreResult.setText(data.get(position).getContent());
        return convertView;
    }

    public static class ViewHolder{
        ImageView img;
        TextView name;
        TextView scoreResult;
    }
}
