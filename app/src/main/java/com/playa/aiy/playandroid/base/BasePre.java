package com.playa.aiy.playandroid.base;

import com.playa.aiy.playandroid.base.view.BaseView;

public interface BasePre<T extends BaseView> {

    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();

}
