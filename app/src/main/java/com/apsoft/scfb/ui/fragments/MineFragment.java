package com.apsoft.scfb.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.LoginActivity;
import com.apsoft.scfb.ui.OpinionActivity;
import com.apsoft.scfb.ui.PersonalDetailsActivity;
import com.apsoft.scfb.ui.SetActivity;
import com.apsoft.scfb.ui.ShareActivity;
import com.apsoft.scfb.ui.SystemMessageActivity;
import com.apsoft.scfb.utils.ImageLoader1;

import org.simple.eventbus.Subscriber;
import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public class MineFragment extends Fragment implements View.OnClickListener{
    private View view;
    private RelativeLayout rlPersonalMessage;
    private RelativeLayout rlSystemMessage;
    private RelativeLayout rlSet;
    private RelativeLayout rlTease;
    private RelativeLayout rlShare;
    private ImageView iv;


    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        initview();
        rlPersonalMessage.setOnClickListener(this);
        rlSystemMessage.setOnClickListener(this);
        rlSet.setOnClickListener(this);
        rlTease.setOnClickListener(this);
        rlShare.setOnClickListener(this);

//        if(!User.getInstance().getIs_login()){
//            Intent it = new Intent(getActivity(), LoginActivity.class);
//            startActivity(it);
//        }
        return view;
    }

    private void initview() {
        rlPersonalMessage = (RelativeLayout) view.findViewById(R.id.rl_presonalMessage);
        rlSystemMessage = (RelativeLayout) view.findViewById(R.id.rl_system_message);
        rlSet = (RelativeLayout) view.findViewById(R.id.rl_set);
        rlTease = (RelativeLayout) view.findViewById(R.id.rl_tease);
        rlShare = (RelativeLayout) view.findViewById(R.id.rl_share);
        TextView tv = (TextView) view.findViewById(R.id.tv_mine_player_name);
        if(User.getInstance().getMember_name()!=null && User.getInstance().getMember_name().length()>0){
            tv.setText(User.getInstance().getMember_name());
        }else{
            tv.setText(User.getInstance().getUser_name());
        }
        if(User.getInstance().getUser_photo()!= null && User.getInstance().getUser_photo().length()>0 ){
            iv = (ImageView) view.findViewById(R.id.iv_mine_head_photo);
            ImageLoader1.getInstance().displayCricleImage(getActivity(), User.getInstance().getUser_photo(), iv);
        }
    }

    private void updateUserInfo(){
        TextView tv = (TextView) view.findViewById(R.id.tv_mine_player_name);
        if(User.getInstance().getMember_name()!=null && User.getInstance().getMember_name().length()>0){
            tv.setText(User.getInstance().getMember_name());
        }else{
            tv.setText(User.getInstance().getUser_name());
        }
        if(User.getInstance().getUser_photo()!= null && User.getInstance().getUser_photo().length()>0 ){
            ImageView iv = (ImageView) view.findViewById(R.id.iv_mine_head_photo);
            ImageLoader1.getInstance().displayCricleImage(getActivity(), User.getInstance().getUser_photo(), iv);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_presonalMessage:
//                if(!User.getInstance().getIs_login()){
//                    Intent it = new Intent(getActivity(), LoginActivity.class);
//                    startActivity(it);
//                    break;
//                }
                Intent intent=new Intent(getContext(), PersonalDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_system_message:
//                if(!User.getInstance().getIs_login()){
//                Intent it = new Intent(getActivity(), LoginActivity.class);
//                startActivity(it);
//                break;
//            }
                Intent intent1=new Intent(getContext(), SystemMessageActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_set:
                Intent intent2=new Intent(getContext(), SetActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_tease:
//                if(!User.getInstance().getIs_login()){
//                    Intent it = new Intent(getActivity(), LoginActivity.class);
//                    startActivity(it);
//                    break;
//                }
                Intent intent3=new Intent(getContext(), OpinionActivity.class);
                startActivity(intent3);
                break;
            case R.id.rl_share:
                Intent intent4=new Intent(getContext(), ShareActivity.class);
                startActivity(intent4);
                break;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && view!=null){
            updateUserInfo();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden && view!=null){
            updateUserInfo();
        }
        super.onHiddenChanged(hidden);
    }
}
