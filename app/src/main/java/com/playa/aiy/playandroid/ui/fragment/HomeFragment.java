package com.playa.aiy.playandroid.ui.fragment;


import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.adapter.HomePageAdapter;
import com.playa.aiy.playandroid.base.BaseFragment;
import com.playa.aiy.playandroid.contract.HomeContract;
import com.playa.aiy.playandroid.data.BenarBean;
import com.playa.aiy.playandroid.data.HomePageArticleBean;
import com.playa.aiy.playandroid.presenter.HomePagePresenter;
import com.playa.aiy.playandroid.utils.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 首页fragment
 * @author aiy
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.rl_content)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView rv;

    private HomePagePresenter presenter;
    private LinearLayout bannerView;
    private Banner banner;
    private HomePageAdapter mAdapter;

    private List<HomePageArticleBean.DatasBean> articleList;
    private List<String> linkList;
    private List<String> imageList;
    private List<String> titleList;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        setRefresh();
        articleList = new ArrayList<>();
        imageList = new ArrayList<>();
        titleList = new ArrayList<>();
        linkList = new ArrayList<>();
        presenter = new HomePagePresenter(this);
        presenter.getHomepageDataList(0);
        presenter.getBanner();
        mAdapter = new HomePageAdapter(R.layout.item_homepage, articleList);
        mAdapter.addHeaderView(bannerView);
        rv.setAdapter(mAdapter);
    }

    @Override
    protected void initUi() {
        super.initUi();
        showLoading();
        //初始化recyclerView
        rv.setLayoutManager(new LinearLayoutManager(context));
        //初始化轮播图
        bannerView = (LinearLayout) getLayoutInflater().inflate(R.layout.view_banner, null);
        banner = bannerView.findViewById(R.id.banner);
        bannerView.removeView(banner);
        bannerView.addView(banner);
    }

    /**
     * 设置SmartRefreshLayout刷新加载
     */
    private void setRefresh() {
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            presenter.autoRefresh();
            refreshLayout.finishRefresh(1000);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            presenter.loadMore();
            refreshLayout.finishLoadMore(1000);
        });
    }

    @Override
    public void getHomePageListOk(HomePageArticleBean data, boolean isRefresh) {
        Log.d(TAG, "getHomePageListOk: " + data.toString());
        //showNormal();
        if(isRefresh){
            articleList = data.getDatas();
            mAdapter.replaceData(articleList);
        }else {
            articleList.addAll(data.getDatas());
            mAdapter.addData(articleList);
        }
    }

    @Override
    public void getHomeListErr(String info) {
        Log.d(TAG, "getHomeListErr: " + info);
    }

    @Override
    public void getBannerListOk(List<BenarBean> benarBean) {
        Log.d(TAG, "getBannerListOk: " + benarBean.toString());
        imageList.clear();
        titleList.clear();
        linkList.clear();
        for (BenarBean benar:benarBean) {
            imageList.add(benar.getImagePath());
            titleList.add(benar.getTitle());
            linkList.add(benar.getUrl());
        }
        //设置banner加载轮播图配置
        banner.setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setImages(imageList)
                .setBannerAnimation(Transformer.Accordion)
                .setBannerTitles(titleList)
                .isAutoPlay(true)
                .setDelayTime(5000)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .start();

    }

    @Override
    public void getBannerListErr(String info) {
        Log.d(TAG, "getBannerListErr: " + info);
    }
}
