package com.example.animationlibarary;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import rvr.magenative.animationUtil.AnimationUtil;
import rvr.magenative.animationUtil.IntentAnimation;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,TwstaActivity.class));
                AnimationUtil.activityIntentAnimation(MainActivity.this, IntentAnimation.FLIP_RIGHT);
            }
        },1000);


    }


}