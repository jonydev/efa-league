package com.apsoft.scfb.ui.adapter.match;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.DiscusisonEntry;
import com.apsoft.scfb.bean.DiscussionDetailEntry;
import com.apsoft.scfb.bean.DiscussionReviewEntry;
import com.apsoft.scfb.ui.PingLunActivity;
import com.apsoft.scfb.ui.adapter.AbsAdapter;
import com.apsoft.scfb.utils.ImageLoader1;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2016/8/10.
 */
public class DiscussionDetailAdapter extends AbsAdapter<DiscussionReviewEntry> {
    private List<DiscussionReviewEntry> data;
    private LayoutInflater inflater;
    private Context context;
    public DiscussionDetailAdapter(Context context, List<DiscussionReviewEntry> data) {
        super(context, data);
        this.data=data;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_discussion_detail_surface,null);
            holder.tv_discussion_player_name= (TextView) view.findViewById(R.id.tv_discussion_player_name);
            holder.tv_discussion_publish_time= (TextView) view.findViewById(R.id.tv_discussion_publish_time);
            holder.tv_discussion_content= (TextView) view.findViewById(R.id.tv_discussion_content);
            holder.tv_zan_num= (TextView) view.findViewById(R.id.tv_zan_num);
            holder.tv_discussion_num= (TextView) view.findViewById(R.id.tv_discussion_num);
            holder.iv_discussion_player_icon = (ImageView)view.findViewById(R.id.iv_discussion_player_icon);
//            holder.iv_pinglun = (ImageView)view.findViewById(R.id.iv_pinglun);
//            holder.tv_pinglun= (TextView) view.findViewById(R.id.tv_pinglun);

            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tv_discussion_player_name.setText(data.get(i).getMember_name());
        String substring = data.get(i).getDatetime().substring(11, 13);
        String time=String .valueOf(((Integer.valueOf(substring)+8)%24));
        final String date=data.get(i).getDatetime().substring(0,11)+time+data.get(i).getDatetime().substring(13);
        holder.tv_discussion_publish_time.setText(date);
        holder.tv_discussion_content.setText(data.get(i).getContent());
        holder.tv_zan_num.setVisibility(View.INVISIBLE);
        holder.tv_discussion_num.setVisibility(View.INVISIBLE);
        if(data.get(i).getPhoto()!=null && data.get(i).getPhoto().length()>0) {
            ImageLoader1.getInstance().displayCricleImage(mContext, data.get(i).getPhoto(), holder.iv_discussion_player_icon);
        }else {
            holder.iv_discussion_player_icon.setImageResource(R.drawable.icon_person);
        }
//        holder.tv_zan_num.setText(data.get(i).getTv_zan_num());
//        holder.tv_discussion_num.setText(data.get(i).getTv_discussion_num());

        /**
         * 评论图片   监听
         */
       /* holder.iv_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PingLunActivity.class);
                intent.putExtra("content",data.get(i).getContent());
                intent.putExtra("name",data.get(i).getMember_name());
                intent.putExtra("index",i);
                context.startActivity(intent);
            }
        });
*/
        return view;
    }

    public static Date stringToDate(String time){
        SimpleDateFormat formatter;
        int tempPos=time.indexOf("AD") ;
        time=time.trim() ;
        formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss z");
        if(tempPos>-1){
            time=time.substring(0,tempPos)+
                    "公元"+time.substring(tempPos+"AD".length());//china
            formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss z");
        }
        tempPos=time.indexOf("-");
        if(tempPos>-1&&(time.indexOf(" ")<0)){
            formatter = new SimpleDateFormat ("yyyyMMddHHmmssZ");
        }
        else if((time.indexOf("/")>-1) &&(time.indexOf(" ")>-1)){
            formatter = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
        }
        else if((time.indexOf("-")>-1) &&(time.indexOf(" ")>-1)){
            formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        }
        else if((time.indexOf("/")>-1) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){
            formatter = new SimpleDateFormat ("yyyy-MM-dd KK:mm:ss a");
        }
        else if((time.indexOf("-")>-1) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){
            formatter = new SimpleDateFormat ("yyyy-MM-dd KK:mm:ss a");
        }
        ParsePosition pos = new ParsePosition(0);
        java.util.Date ctime = formatter.parse(time, pos);
        return ctime;
    }
    public static class ViewHolder{
        ImageView iv_discussion_player_icon;
        TextView tv_discussion_player_name;
        TextView tv_discussion_publish_time;
        TextView tv_discussion_content;
        TextView tv_zan_num;
        TextView tv_discussion_num;

//        ImageView iv_pinglun;
//        TextView tv_pinglun;
    }

}
