package com.playa.aiy.playandroid.contract;

import com.playa.aiy.playandroid.base.view.BaseView;
import com.playa.aiy.playandroid.presenter.IPresenter;

public interface MainContract {

    interface View extends BaseView{

    }

    interface Presenter extends IPresenter<MainContract.View>{

    }
}
