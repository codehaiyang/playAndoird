package com.playa.aiy.playandroid.ui.fragment;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.base.BaseFragment;
import com.playa.aiy.playandroid.net.PayApi;
import com.playa.aiy.playandroid.net.entity.BaseResponse;
import com.playa.aiy.playandroid.ui.activity.MainActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author aiy
 */
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.rl_content)
    SmartRefreshLayout mRlContent;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    protected Activity mActivity;

    LinearLayoutManager mLinearLayoutManager;

    private Banner banner;

    public static HomeFragment getInstance(){
        return new HomeFragment();
    }

    @Override
    protected void initView() {
        rvList.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
