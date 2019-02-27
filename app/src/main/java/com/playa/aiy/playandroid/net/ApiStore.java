package com.playa.aiy.playandroid.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit 工具类
 */
public class ApiStore {


    private static Retrofit retrofit;

    public static String baseUrl = "";

    public static <T> T createApi(Class<T> service){
        return retrofit.create(service);
    }

    /**
     * 创建retrofit客户端
     */
    private static void createProy(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();

//        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return null;
//                    }
//                })
//                .addInterceptor(new );
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
