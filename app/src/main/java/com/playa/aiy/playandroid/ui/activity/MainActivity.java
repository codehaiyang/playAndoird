package com.playa.aiy.playandroid.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.base.BaseActivity;
import com.playa.aiy.playandroid.ui.fragment.HomeFragment;
import com.playa.aiy.playandroid.ui.fragment.MineFragment;
import com.playa.aiy.playandroid.ui.fragment.ProjectFragment;
import com.playa.aiy.playandroid.ui.fragment.SystemFragment;
import com.playa.aiy.playandroid.ui.fragment.WechatFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 首页activity
 * @author aiy
 */
public class MainActivity extends BaseActivity{

    @BindView(R.id.toolbar_common)
    Toolbar toolbar;
    @BindView(R.id.navigation_buttom)
    BottomNavigationView mBtvHome;

    private static final String TAG = "MainActivity";
    private ArrayList<Fragment> fragmentList;
    private int lastIndex;

    @Override
    protected void initView() {
        //初始化fragment列表
        initFragment();
        //默认显示第一个fragment
        selectFragment(0);
        //底部导航选择
        mBtvHome.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void initData() {
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new SystemFragment());
        fragmentList.add(new WechatFragment());
        fragmentList.add(new ProjectFragment());
        fragmentList.add(new MineFragment());
    }

    /**
     * 设置默认选择的fragment 默认第一个
     * @param index
     */
    private void selectFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = fragmentList.get(index);
        Fragment lastFragment = fragmentList.get(lastIndex);
        lastIndex = index;
        ft.hide(lastFragment);
        if(!currentFragment.isAdded()){
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.frame_layout,currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.item_home:
                    selectFragment(0);
                    return true;
                case R.id.item_hierarchy:
                    selectFragment(1);
                    return true;
                case R.id.time_wechat:
                    selectFragment(2);
                    return true;
                case R.id.item_project:
                    selectFragment(3);
                    return true;
                case R.id.item_mine:
                    selectFragment(4);
                    return true;

                default:
                    break;
            }
            return false;
        }
    };

    /**
     * 初始化toolbar
     */
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
