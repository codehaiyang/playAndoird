package com.playa.aiy.playandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.base.BaseActivity;
import com.playa.aiy.playandroid.contract.MainContract;
import com.playa.aiy.playandroid.ui.fragment.HomeFragment;

import butterknife.BindView;

/**
 *
 * @author aiy
 */
public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.fbtn_up)
    FloatingActionButton fbtnUp;
    @BindView(R.id.btn_ntv)
    BottomNavigationView btnNtv;


    Fragment[] mFragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new Fragment[5];
        mFragments[0] = HomeFragment.getInstance();
        mFragments[1] = HomeFragment.getInstance();
        mFragments[2] = HomeFragment.getInstance();
        mFragments[3] = HomeFragment.getInstance();
        mFragments[4] = HomeFragment.getInstance();
        loadFragment(R.id.fl_content,0,mFragments);
    }

    /**
     * 加载fragment
     * @param fl_content
     * @param fragments
     */
    private void loadFragment(int fl_content, int showFragmentId, Fragment[] fragments) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.length;i++){
            Fragment fragment = fragments[i];
            transaction.add(fl_content,fragment,fragment.getClass().getName());
            if(i != showFragmentId){
                transaction.hide(fragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutIds() {
        return R.layout.activity_main;
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showErrorMsg() {

    }

    @Override
    public void reLoad() {

    }
}
