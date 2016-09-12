package com.apsoft.scfb.ui.adapter.home;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anywhere.utils.ToastUtils;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.HomeEntry;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.MainActivity;
import com.apsoft.scfb.ui.fragments.Team.TeamFragment2;
import com.apsoft.scfb.ui.fragments.matches.MatchFragment;
import com.apsoft.scfb.utils.ImageLoader1;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class LvMainAdapter extends BaseAdapter {
    private List<HomeEntry.ListBean>data;
    private LayoutInflater inflater;
    private Context context;

    public LvMainAdapter(Context context,List<HomeEntry.ListBean> data) {
        this.data = data;
        this.context=context;
        inflater=LayoutInflater.from(context);

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.lvitem_home_office,null);
            holder.imageView= (ImageView) view.findViewById(R.id.iv_mainListviewItem);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        //数据映射
        holder.imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher));
        ImageLoader1.getInstance().displayImage(context,data.get(i).getImage(),holder.imageView);
        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                User.getInstance().setOffice_id_set(data.get(i).getId());
                MainActivity s_mainActivity = MainActivity.s_mainActivity;
                TeamFragment2 fragment = (TeamFragment2) s_mainActivity.getSupportFragmentManager().findFragmentByTag("teamTag");
                fragment.forceRefesh();

                MainActivity.s_mainActivity.switchFragment(3,0);
            }
        });
        return view;
    }

    public static class ViewHolder{
        public ImageView imageView;
    }
}
