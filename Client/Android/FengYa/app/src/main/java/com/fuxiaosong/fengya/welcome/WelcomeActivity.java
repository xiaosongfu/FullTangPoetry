package com.fuxiaosong.fengya.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.fuxiaosong.fengya.R;
import com.fuxiaosong.fengya.poet.PoetActivity;

/**
 * Created by fuxiaosong on 16/8/30.
 */
public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //2.5s后跳转到显示诗人的 Activity : PoetActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this , PoetActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        } , 2500);
    }
}
