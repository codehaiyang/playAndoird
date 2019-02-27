package com.playa.aiy.playandroid.net;

import com.playa.aiy.playandroid.data.HomePageArticleBean;
import com.playa.aiy.playandroid.net.response.BaseResp;

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

}
