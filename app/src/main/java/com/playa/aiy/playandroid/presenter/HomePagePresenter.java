package com.playa.aiy.playandroid.presenter;

import com.playa.aiy.playandroid.base.BasePresenter;
import com.playa.aiy.playandroid.contract.HomeContract;
import com.playa.aiy.playandroid.data.BenarBean;
import com.playa.aiy.playandroid.data.HomePageArticleBean;
import com.playa.aiy.playandroid.net.ApiService;
import com.playa.aiy.playandroid.net.ApiStore;
import com.playa.aiy.playandroid.net.response.BaseResp;
import com.playa.aiy.playandroid.utils.ConstantUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePagePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Per {

    private static final String TAG = "HomePagePresenter";

    private HomeContract.View view;
    private boolean isRefresh = true;
    private int currentPage;

    public HomePagePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void autoRefresh() {
        isRefresh = true;
        currentPage = 0;
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void getHomepageDataList(int page) {
        ApiStore.createApi(ApiService.class)
                .getArticleList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResp<HomePageArticleBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResp<HomePageArticleBean> homePageArticleBeanBaseResp) {
                        if (homePageArticleBeanBaseResp.getErrorCode() == ConstantUtil.REQUEST_ERROR){
                            view.getHomeListErr(homePageArticleBeanBaseResp.getErrorMsg());
                        }else if (homePageArticleBeanBaseResp.getErrorCode() == ConstantUtil.REQUEST_SUCCESS){
                            view.getHomePageListOk(homePageArticleBeanBaseResp.getData(),isRefresh);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getHomeListErr(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /**
     * 获取banner信息
     */
    @Override
    public void getBanner() {
        ApiStore.createApi(ApiService.class)
                .getBannerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResp<List<BenarBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResp<List<BenarBean>> listBaseResp) {
                        if (listBaseResp.getErrorCode() == ConstantUtil.REQUEST_ERROR){
                            view.getBannerListErr(listBaseResp.getErrorMsg());
                        }else if(listBaseResp.getErrorCode() == ConstantUtil.REQUEST_SUCCESS){
                            view.getBannerListOk(listBaseResp.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
