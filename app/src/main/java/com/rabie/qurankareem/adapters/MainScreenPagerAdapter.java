package com.rabie.qurankareem.adapters;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rabie.qurankareem.fragments.BookmarksScreenFragment;
import com.rabie.qurankareem.fragments.PlannerScreenFragment;
import com.rabie.qurankareem.fragments.ReadScreenFragment;

public class MainScreenPagerAdapter extends FragmentPagerAdapter {

    public MainScreenPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        if (i == 0) return new ReadScreenFragment();
        else if (i == 1) return new PlannerScreenFragment();
        else if (i == 2) return new BookmarksScreenFragment();
        else return new ReadScreenFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "Read";
        else if (position == 1) return "Planner";
        else if (position == 2) return "Bookmarks";
        else return null;
    }

}
