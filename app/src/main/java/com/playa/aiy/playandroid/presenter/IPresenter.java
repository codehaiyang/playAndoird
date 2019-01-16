package com.playa.aiy.playandroid.presenter;

import com.playa.aiy.playandroid.base.view.BaseView;

/**
 * presenter接口
 * @param <T>
 */
public interface IPresenter<T extends BaseView> {

    //注入View
    void attachView(T view);

    //判断是否注入了View
    boolean isAttachView();

    //解除View
    void detachView();

    //订阅事件
    void subscribleEvent();

    boolean getNoImageState();
    boolean getAutoCacheState();
    boolean getNightModeState();
    boolean getStatusBarState();
    boolean getAutoUpdataState();
}
