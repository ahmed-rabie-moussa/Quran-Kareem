package com.rabie.qurankareem.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rabie.qurankareem.R;

public class BookmarksScreenFragment extends Fragment {

    private static BookmarksScreenFragment bookmarksScreenFragment = new BookmarksScreenFragment();
    public static Fragment getInstance(){
        return bookmarksScreenFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}
