package com.apsoft.scfb.ui.fragments;

import android.support.v4.app.Fragment;

import com.apsoft.scfb.R;
import com.apsoft.scfb.ui.fragments.home.MainFragment;
import com.apsoft.scfb.ui.fragments.home.NewsDetailFragment;
import com.apsoft.scfb.ui.fragments.home.NewsListEditFragment;
import com.apsoft.scfb.ui.fragments.home.NewsListFragment;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public class HomeFragment extends BaseParentFragment{
    public int getViewLayoutId(){
        return R.layout.fragment_home;
    }

    public void setupFragments(){
        fragments = new Fragment[1];
        fragments[0] = new MainFragment();
    }
}
