package com.playa.aiy.playandroid.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.data.SystemBean;
import java.util.List;

/**
 * 体系页面适配器
 * @author aiy
 */
public class SystemPageAdapter extends BaseQuickAdapter<SystemBean, BaseViewHolder> {


    public SystemPageAdapter(int layoutResId, @Nullable List<SystemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemBean item) {
        helper.setText(R.id.tv_sys_title,item.getName());
    }

}
