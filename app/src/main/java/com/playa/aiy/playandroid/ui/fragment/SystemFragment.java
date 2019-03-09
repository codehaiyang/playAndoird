package com.playa.aiy.playandroid.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.base.BaseFragment;
import com.playa.aiy.playandroid.contract.SystemContract;
import com.playa.aiy.playandroid.data.SystemBean;
import com.playa.aiy.playandroid.presenter.SystemPagePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

/**
 * 体系fragment
 */
public class SystemFragment extends BaseFragment implements SystemContract.View {

    @BindView(R.id.srl_system)
    SmartRefreshLayout mSrfSystem;
    @BindView(R.id.rv_system)
    RecyclerView mRvSystem;
    private SystemPagePresenter presenter;

    private List<SystemBean> mSystemBeanList = new ArrayList<>();

    @Override
    protected void initUi() {
        //设置刷新组件
        setRefresh();
        //设置recyclerview
        mRvSystem.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initData() {
        presenter = new SystemPagePresenter(this);
        presenter.getSystemList();
    }

    /**
     * 设置刷新
     */
    private void setRefresh() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_system;
    }


    /**
     * 体系列表数据获取成功
     * @param systemBeanList
     */
    @Override
    public void getSystemListOk(List<SystemBean> systemBeanList) {
        mSystemBeanList = systemBeanList;
    }

    /**
     * 体系列表数据获取失败
     * @param msg
     */
    @Override
    public void getSystemListErr(String msg) {
        Log.d(TAG, "getSystemListErr: " + msg);
    }
}
