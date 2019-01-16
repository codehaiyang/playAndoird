package com.playa.aiy.playandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playa.aiy.playandroid.base.view.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * @author aiy
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    protected abstract void initView();

    protected abstract int getLayoutId();

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
