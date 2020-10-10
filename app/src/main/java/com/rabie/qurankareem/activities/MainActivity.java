package com.rabie.qurankareem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.rabie.qurankareem.R;
import com.rabie.qurankareem.adapters.MainScreenPagerAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();

    }


    public void setUpView (){
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MainScreenPagerAdapter mainScreenPagerAdapter = new MainScreenPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainScreenPagerAdapter);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.bottomNavigation);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(android.R.color.darker_gray),getResources().getColor(android.R.color.white));
        tabLayout.getTabAt(0).setIcon(getDrawable(R.drawable.ic_home));
        tabLayout.getTabAt(0).setText("قراءة");
        tabLayout.getTabAt(1).setIcon(getDrawable(R.drawable.ic_planner));
        tabLayout.getTabAt(1).setText("خطط");
        tabLayout.getTabAt(2).setIcon(getDrawable(R.drawable.ic_bookmark));
        tabLayout.getTabAt(2).setText("السجل");
        int [][] states = new int[][]{new int[]{android.R.attr.state_selected},new int[]{}};
        int [] colors = {getResources().getColor(android.R.color.white),
                getResources().getColor(android.R.color.darker_gray)};
        tabLayout.setTabIconTint(new ColorStateList(states,colors));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //ToolBar Settings
        Toolbar appBar = findViewById(R.id.app_bar);
        TextView appBarTitle = findViewById(R.id.app_bar_title);
        setActionBar(appBar);
        getActionBar().setDisplayShowTitleEnabled(false);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0)
                    appBarTitle.setText("القرآن الكريم");
                if (tabLayout.getSelectedTabPosition() == 1)
                    appBarTitle.setText("المخطط");
                if (tabLayout.getSelectedTabPosition() == 2)
                    appBarTitle.setText("السجل");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }





}