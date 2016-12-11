package com.github.kimkevin.cachepot.sample.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.github.kimkevin.cachepot.sample.R;

public class NaviUtil {
    private static final String TAG = NaviUtil.class.getSimpleName();

    public static void initFragment(AppCompatActivity activity, Fragment fragment){
        String tag = fragment.getClass().getSimpleName();

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment, tag);
        fragmentTransaction.setBreadCrumbShortTitle(tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void addFragment(Context context, Fragment fragment) {
        addFragment((AppCompatActivity) context, fragment);
    }

    public static void addFragment(AppCompatActivity activity, Fragment fragment){
        String tag = fragment.getClass().getSimpleName();

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_fragment, fragment, tag);
        fragmentTransaction.setBreadCrumbShortTitle(tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void replaceFragment(Context context, Fragment fragment) {
        replaceFragment((AppCompatActivity) context, fragment);
    }

    public static void replaceFragment(AppCompatActivity activity, Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment, tag);
        fragmentTransaction.setBreadCrumbShortTitle(tag);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
