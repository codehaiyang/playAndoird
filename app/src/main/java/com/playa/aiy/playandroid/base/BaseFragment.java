package com.playa.aiy.playandroid.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playa.aiy.playandroid.MyApplication;
import com.playa.aiy.playandroid.base.view.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * @author aiy
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    protected Activity activity;
    protected MyApplication application;
    protected MyApplication context;
    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        context = MyApplication.getInstance();
        mBind = ButterKnife.bind(this,view);
        initUi();
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    protected void initData() {
    }

    protected void initUi(){}

    protected abstract int getLayoutResId();

    @Override
    public void showLoading() {

    }
}
