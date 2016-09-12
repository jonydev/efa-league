package com.apsoft.scfb.ui.adapter.match;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.DiscusisonEntry;
import com.apsoft.scfb.bean.DiscussionDetailEntry;
import com.apsoft.scfb.ui.adapter.AbsAdapter;

import java.util.List;

/**
 * Created by admin on 2016/8/10.
 */
public class DiscussionAdapter extends AbsAdapter<DiscussionDetailEntry> {
    private List<DiscussionDetailEntry> data;
    private LayoutInflater inflater;
    public DiscussionAdapter(Context context, List<DiscussionDetailEntry> data) {
        super(context, data);
        this.data=data;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_discussion_fragment,null);
            holder.txtWhich= (TextView) view.findViewById(R.id.tv_item_discussion_which_race);
            holder.txtTime= (TextView) view.findViewById(R.id.tv_item_discussion_time);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.txtWhich.setText(data.get(i).getTitle());
        holder.txtTime.setText("");
        return view;
    }

    public static class ViewHolder{
        TextView txtWhich;
        TextView txtTime;
    }

}
