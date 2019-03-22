package com.playa.aiy.playandroid.contract;

import com.playa.aiy.playandroid.base.BasePre;
import com.playa.aiy.playandroid.base.view.BaseView;

public class WxContract {

    public interface View extends BaseView{
        void getWxListOk();

        void getWxListErr();
    }

    public interface Presenter extends BasePre<View>{
        void autoRefresh();

        void getWxTitleList();
    }

}
