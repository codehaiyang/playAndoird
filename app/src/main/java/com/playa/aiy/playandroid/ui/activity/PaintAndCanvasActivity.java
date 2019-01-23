package com.playa.aiy.playandroid.ui.activity;

import android.widget.FrameLayout;

import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.base.BaseActivity;
import com.playa.aiy.playandroid.weight.MyView;

import butterknife.BindView;

public class PaintAndCanvasActivity extends BaseActivity {

    @BindView(R.id.root)
    FrameLayout mRoot;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mRoot.addView(new MyView(PaintAndCanvasActivity.this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.paint_and_canvas_activity;
    }

}
