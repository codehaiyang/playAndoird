package com.playa.aiy.playandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.playa.aiy.playandroid.base.view.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutIds());
        mUnbinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化控件
     */
    protected abstract void initData();

    /**
     * 初始化数据
     */
    protected abstract void initView();

    /**
     * 获取布局文件id
     * @return
     */
    protected abstract int getLayoutIds();
}
