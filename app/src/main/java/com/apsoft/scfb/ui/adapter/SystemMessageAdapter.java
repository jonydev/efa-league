package com.apsoft.scfb.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anywhere.utils.ToastUtils;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.SystemMessageEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.List;

/**
 * Created by admin on 2016/8/12.
 */
public class SystemMessageAdapter extends AbsAdapter<SystemMessageEntry> {
    private List<SystemMessageEntry>data;
    private LayoutInflater inflater;
    public SystemMessageAdapter(Context context, List<SystemMessageEntry> data) {
        super(context, data);
        this.data=data;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_system_message_lv,null);
            holder.tvPlayer= (TextView) view.findViewById(R.id.tv_c_luo_item);
            holder.tvTime= (TextView) view.findViewById(R.id.tv_add_time);
            holder.btnAgree = (Button) view.findViewById(R.id.btn_pass_item);
            holder.btnDisagree = (Button) view.findViewById(R.id.btn_not_pass_item);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        final SystemMessageEntry svalue = data.get(i);
        holder.tvPlayer.setText(data.get(i).getName());
        holder.tvTime.setText(data.get(i).getDatetime());
        holder.btnAgree.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                doAgreeJoinTeam(svalue.getId(), i);
            }
        });
        holder.btnDisagree.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                disAgreeJoinTeam(svalue.getId(), i);
            }
        });
        return view;
    }


    public static class ViewHolder{
        TextView tvPlayer;
        TextView tvTime;
        Button btnAgree;
        Button btnDisagree;
    }

    void doAgreeJoinTeam(String uid, final int i){
        NetHomeQuery.requestAcceptUserJoinTeam(uid, new BaseCallback<String>() {
            @Override
            public void onBeforeRequest(Request request) {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, String o) {
                data.remove(i);
                notifyDataSetChanged();
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                ToastUtils.showLongToast(mContext, "操作失败");
            }
        });
    }

    void disAgreeJoinTeam(String uid, final int i){
        NetHomeQuery.requestRejectUserJoinTeam(uid, new BaseCallback<String>() {
            @Override
            public void onBeforeRequest(Request request) {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, String o) {
                data.remove(i);
                notifyDataSetChanged();
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                ToastUtils.showLongToast(mContext, "操作失败");
            }
        });
    }
}
