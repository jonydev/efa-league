package com.apsoft.scfb.ui.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.apsoft.scfb.R;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public class BaseParentFragment extends Fragment {
    View    rootView;
    int currentTabIndex=0;
    protected Fragment []fragments;
    protected Button[] mTabs;

//    public void switchFragment(Fragment newFragment){
//        FragmentUtil.setupFragment(this, R.id.fragment_container, newFragment);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resource = getViewLayoutId();
        rootView = inflater.inflate(resource, container, false);
        setupFragments();
        if(mTabs!=null){
            for(int i=0; i<mTabs.length; ++i){
                mTabs[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onTabClicked(v);
                    }
                });
            }
        }

        if(fragments.length>1){
            getChildFragmentManager().beginTransaction().add(getFragmentContainer(), fragments[0])
                    .add(R.id.fragment_container, fragments[1]).hide(fragments[1]).show(fragments[0])
                    .commit();
        }else{
            getChildFragmentManager().beginTransaction().add(getFragmentContainer(), fragments[0])
                    .show(fragments[0]).commit();
        }

        return rootView;
    }

    public int getViewLayoutId(){
        return R.layout.fragment_cotainer;
    }

    public void setupFragments(){
    }

    public int getFragmentContainer(){
        return R.id.fragment_container;
    }

    public void onTabClicked(View view) {
        int index = 0;
        for (int i = 0; i < mTabs.length; ++i) {
            if (mTabs[i] == view) {
                index = i;
                break;
            }
        }
        switchToFragment(index);
    }

    public void switchToFragment(int index){
        int resourceId = getFragmentContainer();
        if (currentTabIndex != index) {
            FragmentTransaction trx = getChildFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(resourceId, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
