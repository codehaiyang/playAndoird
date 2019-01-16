package com.playa.aiy.playandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.net.PayApi;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PayDemoActivity extends AppCompatActivity {

    private static final String TAG = "PayDemoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity);
        Button ali = findViewById(R.id.alipay);
        ali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Runnable pay =  new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new GsonBuilder()
                                //配置你的Gson
                                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                                .create();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.119.166:8080/")
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .build();
                        PayApi payApi = retrofit.create(PayApi.class);
                        Call<ResponseBody> call = payApi.getOrderAliPay();
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Log.d(TAG, "onResponse: " + response);
                                try {
                                    String body = response.body().string();
                                    Log.d(TAG, "onResponse: " + body);
                                    JsonObject parse = (JsonObject) new JsonParser().parse(body);
                                    String data = (String) parse.get("data").getAsString();
                                    PayTask payTask = new PayTask(PayDemoActivity.this);
                                    Map<String, String> stringStringMap = payTask.payV2(data, true);
                                    Log.d(TAG, "onResponse: " + stringStringMap );
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.d(TAG, "onResponse: " + t.getMessage());
                            }
                        });
                    }
                };
                Thread payThread = new Thread(pay);
                payThread.start();
            }
        });
    }
}
