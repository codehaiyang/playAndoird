package com.playa.aiy.playandroid.ui.fragment;

import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.base.BaseFragment;

/**
 * 体系fragment
 */
public class SystemFragment extends BaseFragment {


    @Override
    protected void initUi() {
        setRefresh();
    }

    /**
     * 设置刷新
     */
    private void setRefresh() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_system;
    }
}
