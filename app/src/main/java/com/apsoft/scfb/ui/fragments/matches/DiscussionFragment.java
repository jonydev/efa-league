package com.apsoft.scfb.ui.fragments.matches;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anywhere.utils.ToastUtils;
import com.apsoft.scfb.R;
import com.apsoft.scfb.bean.DiscusisonEntry;
import com.apsoft.scfb.bean.DiscussionDetailEntry;
import com.apsoft.scfb.bean.DiscussionReviewEntry;
import com.apsoft.scfb.bean.PinlunEntry;
import com.apsoft.scfb.http.BaseCallback;
import com.apsoft.scfb.http.NetHomeQuery;
import com.apsoft.scfb.localdata.User;
import com.apsoft.scfb.ui.LoginActivity;
import com.apsoft.scfb.ui.MainActivity;
import com.apsoft.scfb.ui.adapter.match.DiscussionAdapter;
import com.apsoft.scfb.ui.adapter.match.DiscussionDetailAdapter;
import com.apsoft.scfb.utils.ImageLoader1;
import com.apsoft.scfb.utils.MediaPlayHelper;
import com.apsoft.scfb.utils.YoukuPlayHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.framed.FrameReader;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscussionFragment extends Fragment {


    private View view;
    private DiscussionAdapter adapter;
    private DiscussionDetailAdapter detailAdapter;
    //private List<DiscusisonEntry> data=new ArrayList<>();
    private List<DiscussionDetailEntry> detailData=new ArrayList<>();
    private List<DiscussionReviewEntry> detailReviewData=new ArrayList<>();
    private ListView lv;
    private ListView lvRaceDetail;
    private View detailHeadView;
    Button btnSend;
//    MediaPlayHelper mediaPlayHelper;
    DiscussionDetailEntry currentDetailEntry;
    RelativeLayout chatLayout;
//    SurfaceView surfaceView;

    RelativeLayout youkuLayoutBase;
    YoukuPlayHelper youkuPlayHelper;

    ImageView imageView;
    Handler mHandler = new Handler();

    String lastOfficeId;
    private String content;
    private int index;
    private String discuss_id;

    public DiscussionFragment() {

    }

    public static DiscussionFragment newInstance() {

        Bundle args = new Bundle();

        DiscussionFragment fragment = new DiscussionFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Subscriber(tag = "plTag")
    private void updateUserWithTag(String  content) {
//        content = pl.getContent();
//        index = pl.getIndex();
        if (content.equals(detailReviewData.get(index).getContent())){
            NetHomeQuery.requestSendDiscussReview(discuss_id, content, new BaseCallback<String >() {
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
                    queryReviewData(discuss_id);
                    ToastUtils.showLongToast(getActivity(), "评论成功");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            lvRaceDetail.setSelection(lvRaceDetail.getBottom());
                        }
                    }, 2500);
                }

                @Override
                public void onError(Response response, int code, Exception e) {

                }
            });
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_discussion, container, false);
//        EventBus.getDefault().register(getActivity());
        initview();
        setonclickitem();
        addDetailDiscussionHead();
        adapter=new DiscussionAdapter(getContext(),detailData);
        lv.setAdapter(adapter);
        detailAdapter=new DiscussionDetailAdapter(getContext(),detailReviewData);
        lvRaceDetail.setAdapter(detailAdapter);
        creatData();
        return view;
    }


    private void initview() {
        chatLayout = (RelativeLayout) view.findViewById(R.id.rl_bottom_discusssion);
        lv = (ListView) view.findViewById(R.id.lv_discussion);
        lvRaceDetail = (ListView) view.findViewById(R.id.lv_race_detail);
        Button btn = (Button) view.findViewById(R.id.btn_discussion_send);
        btnSend = btn;
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!User.getInstance().getIs_login()) {
                    Intent it = new Intent(getActivity(), LoginActivity.class);
                    startActivity(it);
                    return;
                }
                EditText et = (EditText) view.findViewById(R.id.ll_edit_text);
                String s = et.getText().toString();
                discuss_id = currentDetailEntry.getId();
                btnSend.setEnabled(false);
                NetHomeQuery.requestSendDiscussReview(discuss_id, s, new BaseCallback<String>() {
                    @Override
                    public void onBeforeRequest(Request request) {

                    }

                    @Override
                    public void onFailure(Request request, Exception e) {
                        btnSend.setEnabled(true);
                    }

                    @Override
                    public void onResponse(Response response) {

                    }

                    @Override
                    public void onSuccess(Response response, String o) {
                        btnSend.setEnabled(true);
                        queryReviewData(discuss_id);
                        ToastUtils.showLongToast(getActivity(), "评论成功");
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                lvRaceDetail.setSelection(lvRaceDetail.getBottom());
                            }
                        }, 2500);
                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {
                        btnSend.setEnabled(true);
                    }
                });
            }
        });
    }

    /**
     * 第一个lstview的条目点击事件
     */
    private void setonclickitem() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lv.setVisibility(View.GONE);
                lvRaceDetail.setVisibility(View.VISIBLE);
                chatLayout.setVisibility(View.VISIBLE);

                String discussId = detailData.get(position).getId();
                DiscussionDetailEntry entry = detailData.get(position);
                updateDetail(entry);
                creatDetailReviewData(discussId);
            }
        });
    }

    private void addDetailDiscussionHead() {
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.item_discussion_detail_surface_head, null);
        detailHeadView = view1;
//        SurfaceView sv = (SurfaceView) detailHeadView.findViewById(R.id.sv);
//        surfaceView = sv;
//        mediaPlayHelper = new MediaPlayHelper(sv, "");
        imageView = (ImageView) detailHeadView.findViewById(R.id.iv_content);
        lvRaceDetail.addHeaderView(view1);



        //初始化优酷播放帮助类
        youkuLayoutBase = (RelativeLayout) detailHeadView.findViewById(R.id.sv);
        youkuPlayHelper = new YoukuPlayHelper();
        youkuPlayHelper.initView(getActivity(), youkuLayoutBase);
    }

    /**
     * 初始化讨论详情数据
     * @param discussId
     */
    private void creatDetailReviewData(String discussId) {
        queryReviewData(discussId);
    }

    /**
     * 初始化讨论区的数据
     */
    private void creatData() {
        queryData();
    }




    /**
     * 查询第一个listview的数据
     */
    void queryData(){
//        MainActivity.s_mainActivity.showProgressDialog("正在加载主题...");
        final String officeId = User.getInstance().getCurrentOffice();
        if(officeId == null){
            return;
        }

        NetHomeQuery.requestDiscussList(officeId, new BaseCallback<String>() {
            @Override
            public void onBeforeRequest(Request request) {

            }

            @Override
            public void onFailure(Request request, Exception e) {
                MainActivity.s_mainActivity.hideProgressDialog();
            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, String o) {
                lastOfficeId = officeId;
//                MainActivity.s_mainActivity.hideProgressDialog();
                JSONObject obj = JSONObject.parseObject(o);
                JSONArray array = obj.getJSONArray("data");
                detailData.clear();
                for(int i=0; i<array.size(); ++i){
                    JSONObject jo = array.getJSONObject(i);
                    DiscussionDetailEntry discussionDetailEntry = JSON.toJavaObject(jo, DiscussionDetailEntry.class);
                    detailData.add(discussionDetailEntry);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                MainActivity.s_mainActivity.hideProgressDialog();
            }
        });
    }

    /**
     * 查询第二个listview的数据
     * @param discussId
     */
    void queryReviewData(String discussId){
        NetHomeQuery.requestDiscussDetail(discussId, new BaseCallback<String>() {
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
                JSONObject obj = JSONObject.parseObject(o);
                obj = obj.getJSONObject("data");
                JSONObject detailInfo = obj.getJSONObject("discuss_info");
                JSONArray reviewInfos = obj.getJSONArray("review_list");
                detailReviewData.clear();
                for (int i = 0; i < reviewInfos.size(); ++i) {
                    JSONObject tmp = reviewInfos.getJSONObject(i);
                    DiscussionReviewEntry discussionReviewEntry = JSON.toJavaObject(tmp, DiscussionReviewEntry.class);
                    detailReviewData.add(discussionReviewEntry);
                }

                detailAdapter.notifyDataSetChanged();
//                DiscussionDetailEntry discussionDetailEntry = JSON.toJavaObject(detailInfo, DiscussionDetailEntry.class);
//                updateDetail(discussionDetailEntry);
//                lvRaceDetail.setSelection(detailAdapter.getCount() - 1);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

    /**
     * 刷新列表
     * @param index
     * @param content
     */
    public void refreshDetailReviewData(int index,String content){
        for (int i = 0; i < detailReviewData.size(); i++) {
            if (i==index){
                detailReviewData.get(index).setContent(content);
            }
            detailAdapter.notifyDataSetChanged();
        }
    }

    public void updateDetail(DiscussionDetailEntry entry){
        TextView tvRace = (TextView) detailHeadView.findViewById(R.id.tv_item_discussion_which_race);
        tvRace.setText(entry.getTitle());
        TextView tvTime = (TextView) detailHeadView.findViewById(R.id.tv_item_discussion_time);
        tvTime.setText(entry.getStage());
        if(entry.getType()!=null && entry.getType().equalsIgnoreCase("1")){
//            mediaPlayHelper.setUrl("http://v.youku.com/player/getRealM3U8/vid/XNzE0NzQ2MDQ4/type/video.m3u8");//entry.getVideo());

            imageView.setVisibility(View.INVISIBLE);
//            surfaceView.setVisibility(View.VISIBLE);
//            mediaPlayHelper.setUrl("http://v.youku.com/player/getRealM3U8/vid/XNzE0NzQ2MDQ4/type/video.m3u8");//entry.getVideo());
            //mediaPlayHelper.setUrl(entry.getVideo());//entry.getVideo());
            //mediaPlayHelper.play();
//            youkuPlayHelper.play("576269e00cf2dac1a330f92a");//(entry.getVideo());
            youkuPlayHelper.play(entry.getVideo());

        }else{
            //mediaPlayHelper.stop();
            youkuPlayHelper.stop();
            imageView.setVisibility(View.VISIBLE);
//            surfaceView.setVisibility(View.INVISIBLE);
            ImageLoader1.getInstance().displayImage(getActivity(), entry.getPhoto(), imageView);
        }
        currentDetailEntry = entry;
    }

    public void forceRefresh(){
        if(User.getInstance().getCurrentOffice() != lastOfficeId){
            queryData();
        }
    }


    public boolean onBackPressed(){
        detailReviewData.clear();
        if (lvRaceDetail.getVisibility()==View.VISIBLE){
            lvRaceDetail.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            chatLayout.setVisibility(View.GONE);
            //mediaPlayHelper.stop();
            youkuPlayHelper.stop();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(getActivity());
        youkuPlayHelper.destoryPlayer();
    }


}
