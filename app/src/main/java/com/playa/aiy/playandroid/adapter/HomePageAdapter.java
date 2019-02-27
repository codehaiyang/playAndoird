package com.playa.aiy.playandroid.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.data.HomePageArticleBean;

import java.util.List;

public class HomePageAdapter extends BaseQuickAdapter<HomePageArticleBean.DatasBean,HomePageHolder> {


    public HomePageAdapter(int layoutResId, @Nullable List<HomePageArticleBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomePageHolder helper, HomePageArticleBean.DatasBean item) {
        helper.getView(R.id.tv_tag).setVisibility(View.GONE);
        if (!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_content,item.getTitle());
        }
        if (!TextUtils.isEmpty(item.getAuthor())){
            helper.setText(R.id.tv_author,item.getAuthor());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())){
            helper.setText(R.id.tv_time,item.getNiceDate());
        }
        if (!TextUtils.isEmpty(item.getChapterName())){
            helper.setText(R.id.tv_type,item.getSuperChapterName() + "/" + item.getChapterName());
        }
    }
}
