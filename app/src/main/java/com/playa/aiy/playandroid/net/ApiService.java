package com.playa.aiy.playandroid.net;

import com.playa.aiy.playandroid.data.BenarBean;
import com.playa.aiy.playandroid.data.HomePageArticleBean;
import com.playa.aiy.playandroid.data.SystemBean;
import com.playa.aiy.playandroid.net.response.BaseResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * retrofit api 接口类
 */
public interface ApiService {

    /**
     * 主页数据
     * @param num
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseResp<HomePageArticleBean>> getArticleList(@Path("page") int num);

    /**
     * banner数据
     * @return
     */
    @GET("banner/json")
    Observable<BaseResp<List<BenarBean>>> getBannerList();

    /**
     * 体系数据
     * @return
     */
    @GET("tree/json")
    Observable<BaseResp<List<SystemBean>>> getSystemList();

}
