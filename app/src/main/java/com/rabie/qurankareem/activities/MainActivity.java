package com.rabie.qurankareem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.rabie.qurankareem.R;
import com.rabie.qurankareem.adapters.MainScreenPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MainScreenPagerAdapter mainScreenPagerAdapter = new MainScreenPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainScreenPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.bottomNavigation);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(getDrawable(R.drawable.ic_home));
        tabLayout.getTabAt(0).setText("قراءة");
        tabLayout.getTabAt(1).setIcon(getDrawable(R.drawable.ic_planner));
        tabLayout.getTabAt(1).setText("خطط");
        tabLayout.getTabAt(2).setIcon(getDrawable(R.drawable.ic_bookmark));
        tabLayout.getTabAt(2).setText("السجل");
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

}
