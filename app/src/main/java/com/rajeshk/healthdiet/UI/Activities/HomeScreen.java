package com.rajeshk.healthdiet.UI.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rajeshk.healthdiet.R;
import com.rajeshk.healthdiet.UI.Adapters.Horizontal_adapter;
import com.rajeshk.healthdiet.UI.Adapters.SlidingImage_Adapter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeScreen extends AppCompatActivity {
    @Bind(R.id.ll_dots)
    LinearLayout ll_dots;
    private TextView[] dots;
    int page_position = 0;
    @Bind(R.id.reviewpager)
    ViewPager myPager;
    private static final Integer[] IMAGES = {R.drawable.hair, R.drawable.health_tips, R.drawable.men_workout, R.drawable.yoga_logo};

    @Bind(R.id.scroll_1)
    RecyclerView scroll_1;
    @Bind(R.id.scroll_2)
    RecyclerView scroll_2;
    @Bind(R.id.scroll_3)
    RecyclerView scroll_3;
    SlidingImage_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        adapter  = new SlidingImage_Adapter(HomeScreen.this,IMAGES);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        scroll_1.setLayoutManager(mLayoutManager);
        scroll_2.setLayoutManager(mLayoutManager1);
        scroll_3.setLayoutManager(mLayoutManager2);
        Horizontal_adapter horizontal_adapter = new Horizontal_adapter(HomeScreen.this,IMAGES);
        scroll_1.setAdapter(horizontal_adapter);
        scroll_2.setAdapter(horizontal_adapter);
        scroll_3.setAdapter(horizontal_adapter);
        init();
        addBottomDots(0);

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == IMAGES.length) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                myPager.setCurrentItem(page_position, true);
            }
        };

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 100, 3000);
    }


    private void initViews(){

    }

    private void init(){

        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);
        myPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void addBottomDots(int currentPage) {
        dots = new TextView[IMAGES.length];
        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(Color.parseColor("#000000"));
            ll_dots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#FF0000"));
    }
}