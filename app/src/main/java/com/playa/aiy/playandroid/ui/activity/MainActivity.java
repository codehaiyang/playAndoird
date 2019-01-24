package com.playa.aiy.playandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.base.BaseActivity;
import com.playa.aiy.playandroid.contract.MainContract;
import com.playa.aiy.playandroid.ui.fragment.HomeFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 *
 * @author aiy
 */
public class MainActivity extends BaseActivity{

    private static final String TAG = "MainActivity";
    private ArrayList<Fragment> fragmentList;


    @Override
    protected void initData() {
        initFragment();

    }

    private void initFragment() {
        fragmentList = new ArrayList<>();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
