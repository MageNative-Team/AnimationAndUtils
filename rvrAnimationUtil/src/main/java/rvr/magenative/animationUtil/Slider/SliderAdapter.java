package rvr.magenative.animationUtil.Slider;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rvr.magenative.animationUtil.R;

public class SliderAdapter extends PagerAdapter
{

    private Context context;
    private ArrayList bannerSliderList;
    private static final String TAG = "SliderAdapter";


    public SliderAdapter(Context context, ArrayList bannerSliderList) {
        this.context = context;
        this.bannerSliderList = bannerSliderList;
    }

    @Override
    public int getCount() {
        return bannerSliderList!=null?bannerSliderList.size():0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        View recyclerLayout =  LayoutInflater.from(container.getContext()).inflate(R.layout.view_banner_slider,container,false);
        ImageView bannerImage = recyclerLayout.findViewById(R.id.bannerImage);
        Log.i(TAG, "instantiateItem: "+"Inflated");
        Picasso.get().load(String.valueOf(bannerSliderList.get(position))).into(bannerImage);
        container.addView(recyclerLayout);
        return recyclerLayout;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }


}
