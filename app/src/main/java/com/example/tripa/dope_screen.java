package com.example.tripa;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class dope_screen extends AppCompatActivity {

    ViewPager  mslideViewPager;
    LinearLayout mDotlayout;
    Button backbtn,nextbtn,skipbtn;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dope_screen);

        backbtn = findViewById(R.id.Backbtn);
        nextbtn = findViewById(R.id.Nextbtn);
        skipbtn = findViewById(R.id.Skipbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (getitem(0) > 0){

                    mslideViewPager.setCurrentItem(getitem(-1),true);

                }


            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getitem(0) < 3)
                    mslideViewPager.setCurrentItem(getitem(1),true);
                else {

                    Intent i = new Intent(dope_screen.this,Login.class);
                    startActivity(i);
                    finish();

                }


            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(dope_screen.this,Login.class);
                startActivity(i);
                finish();

            }
        });

        mslideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotlayout = (LinearLayout) findViewById(R.id.Indicator_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);

        mslideViewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mslideViewPager.addOnPageChangeListener(viewListener);


    }

    @SuppressLint("NewApi")
    public void setUpindicator(int position) {

        dots = new TextView[4];
        mDotlayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotlayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position > 0){
                backbtn.setVisibility(View.VISIBLE);
            }else {
                backbtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getitem(int i){

        return mslideViewPager.getCurrentItem() + i;
    }
}
