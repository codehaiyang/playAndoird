package com.playa.aiy.playandroid.contract;

import com.playa.aiy.playandroid.base.BasePre;
import com.playa.aiy.playandroid.base.view.BaseView;
import com.playa.aiy.playandroid.data.HomePageArticleBean;

public class HomeContract {

    public interface View extends BaseView{

        void getHomePageListOk(HomePageArticleBean data,boolean isRefresh);

        void getHomeListErr(String info);

    }

    public interface Per extends BasePre<View>{
        /**
         * 刷新列表
         */
        void autoRefresh();

        /**
         * 刷新更多
         */
        void loadMore();

        void getHomepageDataList(int page);
    }
}
