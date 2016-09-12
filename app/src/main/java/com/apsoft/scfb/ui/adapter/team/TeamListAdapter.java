package com.apsoft.scfb.ui.adapter.team;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.PersonalInfoEntry;
import com.apsoft.scfb.bean.Tean2StartEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetSCFBLogin;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.AddToTeamActivity;
import com.apsoft.scfb.ui.LoginActivity;
import com.apsoft.scfb.ui.MainActivity;
import com.apsoft.scfb.utils.ImageLoader1;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/10.
 */
public class TeamListAdapter extends BaseAdapter {
    private List<Tean2StartEntry.CompetitionZoneBean.DataBean> data;
    private LayoutInflater inflater;
    private Context context;
    private List<String >teamids=new ArrayList<>();
    private String memberflag;

    public TeamListAdapter(Context context, List<Tean2StartEntry.CompetitionZoneBean.DataBean> data, List<String >teamids) {
        this.data = data;
        this.context=context;
        this.inflater=LayoutInflater.from(context);
        this.teamids=teamids;
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_team2_lv,null);
            holder.img= (ImageView) view.findViewById(R.id.iv_item_team2);
            holder.name = (TextView) view.findViewById(R.id.tv_item_team2_teamname);
            holder.team_member_number = (TextView) view.findViewById(R.id.ll_team_member_number);
            holder.team_rate = (TextView)view.findViewById(R.id.ll_team_v_rate);
            holder.team_join_btn = (Button) view.findViewById(R.id.ll_team_join_layout);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }

        ImageLoader1.getInstance().displayImage(context,data.get(i).getPhoto(),holder.img);
        holder.name.setText(data.get(i).getName());
//        if (data.get(i).getTeam_member_number()!=null&&data.get(i).getTeam_member_number()>0){
//            holder.team_member_number.setText(String.valueOf(data.get(i).getTeam_member_number()-1));
//        }else {
            holder.team_member_number.setText(String.valueOf(data.get(i).getTeam_member_number()));
//        }

        if(data.get(i).getWinning()!=null && data.get(i).getWinning().length()>0){
            holder.team_rate.setText(data.get(i).getWinning());
        }else{
            holder.team_rate.setText("0%");
        }


        if (User.getInstance().getIs_login()) {
            if (User.getInstance().getTeam_id() != null && User.getInstance().getTeam_id().length() > 0||User.getInstance().isTeamLeader()) {
//                holder.team_join_btn.setBackground(context.getDrawable(R.drawable.jointeam_bg_btn));
                holder.team_join_btn.setBackground(context.getResources().getDrawable(R.drawable.jointeam_bg_btn));
                holder.team_join_btn.setTextColor(Color.parseColor("#999999"));
            } else {
//                holder.team_join_btn.setBackground(context.getDrawable(R.drawable.add_team_bg));
                holder.team_join_btn.setBackground(context.getResources().getDrawable(R.drawable.add_team_bg));
                holder.team_join_btn.setTextColor(Color.parseColor("#357CB4"));
            }
        }else if(!User.getInstance().getIs_login()){
//            holder.team_join_btn.setBackground(context.getDrawable(R.drawable.add_team_bg));
            holder.team_join_btn.setBackground(context.getResources().getDrawable(R.drawable.add_team_bg));
            holder.team_join_btn.setTextColor(Color.parseColor("#357CB4"));
        }

        /**
         * 加入按钮  监听
         */
        holder.team_join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (User.getInstance().getIs_login()) {
                    if (User.getInstance().getTeam_id() != null) {
                        if (User.getInstance().isTeamLeader()){
                            Toast.makeText(context, "你是一个球队的领队，不能加入其它球队", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "你已加入了一支球队", Toast.LENGTH_SHORT).show();
                        }
                    } else if (User.getInstance().getTeam_id() == null || User.getInstance().getTeam_id().length() <= 0) {
                        Intent intent = new Intent(context, AddToTeamActivity.class);
                        intent.putExtra("team_id", teamids.get(i));
                        context.startActivity(intent);
                    }
                }else {
                    MainActivity s_mainActivity = MainActivity.s_mainActivity;
                    s_mainActivity.hideAllFragment();
                    Intent it = new Intent(context, LoginActivity.class);
                    context.startActivity(it);
                    return;
                }
            }
        });
        return view;
    }

    public static class ViewHolder{
        ImageView img;
        TextView name;
        TextView team_member_number;
        TextView team_rate;
        Button team_join_btn;
    }
}
