package com.playa.aiy.playandroid.contract;

import com.playa.aiy.playandroid.base.BasePre;
import com.playa.aiy.playandroid.base.view.BaseView;
import com.playa.aiy.playandroid.data.BenarBean;
import com.playa.aiy.playandroid.data.HomePageArticleBean;

import java.util.List;

public class HomeContract {

    public interface View extends BaseView{

        void getHomePageListOk(HomePageArticleBean data,boolean isRefresh);

        void getHomeListErr(String info);

        void getBannerListOk(List<BenarBean> benarBean);

        void getBannerListErr(String info);

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

        /**
         * 获取首页信息
         * @param page
         */
        void getHomepageDataList(int page);

        /**
         * 获取banner图片
         */
        void getBanner();
    }
}
