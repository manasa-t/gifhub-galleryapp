package com.manasa.sampleapp.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.manasa.sampleapp.R;
import com.manasa.sampleapp.galleryhome.GalleryHomeActivity;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_TIME_OUT = 3000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, GalleryHomeActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
