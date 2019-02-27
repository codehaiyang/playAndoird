package com.playa.aiy.playandroid.base;

import com.playa.aiy.playandroid.base.view.BaseView;

public class BasePresenter<T extends BaseView> implements BasePre<T>{


    protected T mView;
    protected int currentPage;


    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
