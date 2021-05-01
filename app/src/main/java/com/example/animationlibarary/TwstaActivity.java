package com.example.animationlibarary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

import rvr.magenative.animationUtil.CircleImageView;
import rvr.magenative.animationUtil.Slider.FrameSlider;

public class TwstaActivity extends AppCompatActivity {

//    FrameLayout bannerFrame;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twsta);





        circleImageView = findViewById(R.id.sss);
        circleImageView.setBackgroundResource(R.drawable.test_image);


//        bannerFrame = findViewById(R.id.bannerFrame);
//        ArrayList<String> list = new ArrayList<>();
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/frymire.png");
//
//        new FrameSlider(this).createSlider(bannerFrame,list, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//


    }
}
