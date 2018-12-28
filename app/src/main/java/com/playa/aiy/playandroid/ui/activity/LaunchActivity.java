package com.playa.aiy.playandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.playa.aiy.playandroid.R;

/**
 * app启动页
 */
public class LaunchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.anin_launch_enter, 0);
            finish();
        }, 2000);
    }
}
