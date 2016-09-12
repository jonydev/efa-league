package com.apsoft.scfb.ui;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.apsoft.scfb.R;
import com.apsoft.scfb.ui.adapter.team.TeamClothChoiseAdapter;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class TeamClothChoiseActivity extends AppCompatActivity {

    private GridView gv;
    private List<Bitmap>data;
    private TeamClothChoiseAdapter adapter;
    private Button btnCompelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_cloth_choise);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        gv=(GridView) this.findViewById(R.id.gridView);
        btnCompelete = (Button) findViewById(R.id.btn_complete);
        initData();
        adapter=new TeamClothChoiseAdapter(this,data);
        gv.setAdapter(adapter);
        /**
         * gridview的条目点击事件
         */
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                adapter.setIsSelected(position);
                adapter.notifyDataSetChanged();
               //点击完成按钮时候的事件监听
                btnCompelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(position,"cloth_tag");
                        finish();
                    }
                });
            }
        });
    }

    private void initData() {
        data=new ArrayList<>();
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth1 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth2 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth3 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth4 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth5 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth6 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth7 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth8 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth9 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth10 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth11));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth12));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth13 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth14 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth15 ));
        data.add(BitmapFactory.decodeResource( this.getResources(),  R.drawable.cloth16 ));
    }

    @Override
    public boolean isDestroyed() {
        EventBus.getDefault().unregister(this);
        return super.isDestroyed();
    }
}
