package com.apsoft.scfb.ui.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class FragmentUtil {
	public static void setupFragment(FragmentActivity parent, int nIDContainer, Fragment fragment){
		FragmentTransaction transaction = parent.getSupportFragmentManager().beginTransaction();
        transaction.replace(nIDContainer, fragment, fragment.getClass().getName());
        transaction.commitAllowingStateLoss();
	}

	public static void setupFragment(Fragment parent, int nIDContainer, Fragment fragment){
		FragmentTransaction transaction = parent.getChildFragmentManager().beginTransaction();
		transaction.replace(nIDContainer, fragment, fragment.getClass().getName());
		transaction.commitAllowingStateLoss();
	}
	
	
	public static void setupFragmentWithBacktrack(FragmentActivity parent, int nIDContainer, Fragment fragment){
		FragmentTransaction transaction = parent.getSupportFragmentManager().beginTransaction();
        transaction.replace(nIDContainer, fragment, fragment.getClass().getName());
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
	}
}
