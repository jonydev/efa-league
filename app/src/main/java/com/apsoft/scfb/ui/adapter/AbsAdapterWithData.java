package com.apsoft.scfb.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.apsoft.scfb.bean.MatchScoreEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/12.
 */
public abstract class AbsAdapterWithData<T> extends BaseAdapter {
    private List<T> data;

    public AbsAdapterWithData() {
        this.data = new ArrayList<>();
    }

    public void updateData(List<T> d){
        data.clear();
        data.addAll(d);
        notifyDataSetChanged();
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
    public abstract View getView(int i, View view, ViewGroup viewGroup);
}
