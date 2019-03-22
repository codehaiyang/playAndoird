package com.playa.aiy.playandroid.contract;

import com.playa.aiy.playandroid.base.view.BaseView;
import com.playa.aiy.playandroid.presenter.IPresenter;

public class MainContract {

    interface View extends BaseView{

    }

    interface Presenter extends IPresenter<MainContract.View>{

    }
}
