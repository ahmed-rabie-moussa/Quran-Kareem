//package com.rabie.qurankareem.fragments;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.viewpager.widget.ViewPager;
//
//import com.google.android.material.tabs.TabLayout;
//import com.rabie.qurankareem.R;
//import com.rabie.qurankareem.adapters.MainScreenPagerAdapter;
//
//public class ChooserFragment extends Fragment {
//    MainScreenPagerAdapter mainScreenPagerAdapter;
//    ViewPager viewPager;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_read, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        mainScreenPagerAdapter = new MainScreenPagerAdapter(getChildFragmentManager());
//        viewPager = view.findViewById(R.id.pager);
//        viewPager.setAdapter(mainScreenPagerAdapter);
//        TabLayout tabLayout = view.findViewById(R.id.bottomNavigation);
//        tabLayout.setupWithViewPager(viewPager);
//
//    }
//}