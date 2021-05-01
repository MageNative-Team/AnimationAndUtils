package rvr.magenative.animationUtil.Slider;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rvr.magenative.animationUtil.R;

public class FrameSlider {


    private Activity activity;
    private static final String TAG = "FrameSlider";
    public FrameSlider(Activity activity) {
        this.activity = activity;
    }

    public void createSlider(FrameLayout parent, ArrayList list,int width,int height){



        Log.i(TAG, "createSlider: "+"Function Start");
        View root = LayoutInflater.from(activity).inflate(R.layout.frame_banner_layout,parent,false);
        LinearLayout layoutDots = root.findViewById(R.id.sliderDots);
        ViewPager pager = root.findViewById(R.id.bannerViewPager);
        root.setLayoutParams(new ViewGroup.LayoutParams(width,height));
        pager.setAdapter(new SliderAdapter(activity,list));
        setBannerSlider(layoutDots,pager,list.size());
        parent.addView(root);

        Log.i(TAG, "createSlider: "+"Function END");
    }




    private void setBannerSlider(LinearLayout layoutDots,ViewPager pager, final int size)
    {
        layoutDots.removeAllViews();
        Log.e( "setBanerSlider: ",size+"" );
        ImageView[] imageView = new ImageView[size];
        for (int i = 0; i < size; i++) {
            imageView[i] = new ImageView(activity);
            imageView[i].setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.non_active_box));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            layoutDots.addView(imageView[i], params);
        }

        imageView[0].setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.active_box));



        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {
                for (int i=0;i<size;i++)
                {
                    imageView[i].setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.non_active_box));
                }
                imageView[position].setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.active_box));
                if (position==size)
                {
                    pager.setCurrentItem(0,true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {

                if (pager.getCurrentItem() == size-1) {
                    pager.setCurrentItem(0, true);
                } else {
                    pager.setCurrentItem((pager.getCurrentItem() + 1),
                            true);

                }
            }
        };

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 4000, 4000);


    }



}
