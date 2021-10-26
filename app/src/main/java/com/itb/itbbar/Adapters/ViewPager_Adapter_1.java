package com.itb.itbbar.Adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.itb.itbbar.R;

import java.util.Timer;
import java.util.TimerTask;

public class ViewPager_Adapter_1 extends PagerAdapter {

    private Context context;
    private TypedArray list;

    public Timer swipeTimer;
    final Handler handler = new Handler();


    public ViewPager_Adapter_1(Context context, TypedArray list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.length();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewpager_1_layout, container, false);

        addDataToView(view, position);
        container.addView(view);

        return view;
    }

    private void addDataToView(View view, int position) {

        ImageView imageView = view.findViewById(R.id.viewPager_1_background);
        imageView.setImageDrawable(list.getDrawable(position));

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    public void setTimer(final ViewPager myPager, int time) {
        final int size = list.length();

        final Runnable Update = new Runnable() {
            int NUM_PAGES = size;
            int currentPage = 0;

            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                myPager.setCurrentItem(currentPage++, true);
            }
        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, time * 1000);
    }
}
