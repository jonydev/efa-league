package com.apsoft.scfb.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.ui.adapter.team.TeamClothChoiseAdapter;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class TeamPathChoiseActivity extends AppCompatActivity {

    private TeamClothChoiseAdapter adapter;
    private List<Bitmap>data;
    private GridView gv;
    private Button btnCompletePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_path_choise);
        EventBus.getDefault().register(this);
        gv=(GridView) this.findViewById(R.id.gridView_path);
        btnCompletePath = (Button) findViewById(R.id.btn_complete_path);
        initData();
        adapter=new TeamClothChoiseAdapter(this,data);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                adapter.setIsSelected(position);
                adapter.notifyDataSetChanged();
                btnCompletePath.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(position,"path_tag");
                        finish();
                    }
                });
            }
        });
    }

    private void initData() {
        data=new ArrayList<>();
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path1 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path2 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path3 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path4 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path5 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path6 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path7 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path8 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path9 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path10 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.path11));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.ath12));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.ath13 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.ath14 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.ath15 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.ath16 ));
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}

