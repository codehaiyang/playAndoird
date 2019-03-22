package com.playa.aiy.playandroid.contract;

import com.playa.aiy.playandroid.base.BasePre;
import com.playa.aiy.playandroid.base.view.BaseView;
import com.playa.aiy.playandroid.data.SystemBean;

import java.util.List;

public class SystemContract {

    public interface View extends BaseView{

        void getSystemListOk(List<SystemBean> systemBeanList);

        void getSystemListErr(String msg);

    }

    public interface Pre extends BasePre<View>{
        void getSystemList();
    }
}
