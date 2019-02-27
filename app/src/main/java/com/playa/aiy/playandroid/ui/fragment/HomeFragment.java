package com.playa.aiy.playandroid.ui.fragment;


import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.adapter.HomePageAdapter;
import com.playa.aiy.playandroid.base.BaseFragment;
import com.playa.aiy.playandroid.contract.HomeContract;
import com.playa.aiy.playandroid.data.HomePageArticleBean;
import com.playa.aiy.playandroid.presenter.HomePagePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
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

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        setRefresh();
        articleList = new ArrayList<>();
        presenter.getHomepageDataList(0);
        mAdapter = new HomePageAdapter(R.layout.item_homepage, articleList);
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
        banner.removeView(banner);
        banner.addView(banner);
    }

    /**
     * 设置SmartRefreshLayout刷新加载
     */
    private void setRefresh() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.autoRefresh();
                refreshLayout.finishRefresh(1000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.loadMore();
                refreshLayout.finishLoadMore(1000);
            }
        });
    }

    @Override
    public void getHomePageListOk(HomePageArticleBean data, boolean isRefresh) {
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

    }
}
