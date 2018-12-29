package com.playa.aiy.playandroid.ui.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.widget.FrameLayout;

import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.base.BaseActivity;

import butterknife.BindView;

/**
 *
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.fbtn_up)
    FloatingActionButton fbtnUp;
    @BindView(R.id.btn_ntv)
    BottomNavigationView btnNtv;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutIds() {
        return R.layout.activity_main;
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showErrorMsg() {

    }

    @Override
    public void reLoad() {

    }
}
