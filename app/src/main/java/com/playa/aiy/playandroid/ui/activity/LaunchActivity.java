package com.playa.aiy.playandroid.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.base.BaseActivity;
import com.playa.aiy.playandroid.utils.JumpUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * app启动页
 */
public class LaunchActivity extends BaseActivity {

    private static final String TAG = "LaunchActivity";

    @BindView(R.id.iv_logo)
    ImageView mIvLogn;

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        JumpUtils.overlay(LaunchActivity.this, MainActivity.class);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lunch;
    }
}
