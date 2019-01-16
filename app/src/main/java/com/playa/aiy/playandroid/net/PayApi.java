package com.playa.aiy.playandroid.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PayApi {

    @GET("pay/newOrderAliPay")
    Call<ResponseBody> getOrderAliPay();
}
