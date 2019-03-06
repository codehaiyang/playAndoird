package com.playa.aiy.playandroid.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.base.BaseActivity;
import com.playa.aiy.playandroid.ui.fragment.HomeFragment;
import com.playa.aiy.playandroid.ui.fragment.SystemFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 *
 * @author aiy
 */
public class MainActivity extends BaseActivity{

    @BindView(R.id.toolbar_common)
    Toolbar toolbar;

    private static final String TAG = "MainActivity";
    private ArrayList<Fragment> fragmentList;


    @Override
    protected void initData() {
        initFragment();
        selectFragment(0);
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new SystemFragment());
    }

    /**
     * 设置默认选择的fragment 默认第一个
     * @param index
     */
    private void selectFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = fragmentList.get(index);
        if(!currentFragment.isAdded()){
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.frame_layout,currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
