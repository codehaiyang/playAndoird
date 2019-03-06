package com.playa.aiy.playandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.playa.aiy.playandroid.MyApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author aiy
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected Unbinder mBind;
    protected MyApplication context;
    protected BaseActivity activity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        context = MyApplication.getInstance();
        activity = this;
        initView();
        initData();
        initToolBar();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 获取布局文件id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化toolbar
     */
    protected void initToolBar(){}
}
