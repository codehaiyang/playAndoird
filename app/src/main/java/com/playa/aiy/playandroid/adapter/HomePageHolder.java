package com.playa.aiy.playandroid.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.playa.aiy.playandroid.R;

import org.w3c.dom.Text;

import butterknife.BindView;

public class HomePageHolder extends BaseViewHolder {

    @BindView(R.id.tv_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_tag)
    TextView mTvTag;
    @BindView(R.id.iv_collect)
    ImageView mIvCollect;

    public HomePageHolder(View view) {
        super(view);
    }
}
