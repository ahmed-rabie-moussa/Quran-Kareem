package com.rabie.qurankareem.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rabie.qurankareem.R;
import com.rabie.qurankareem.adapters.PagesRecyclerViewAdapter;
import com.rabie.qurankareem.adapters.SuraRecyclerViewAdapter;
import com.rabie.qurankareem.models.Chapter;
import com.rabie.qurankareem.models.Page;

import java.util.List;


public class PageFragment extends Fragment {
    List<Page> pages;

    public PageFragment(List<Page> pages) {
        this.pages = pages;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sura, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pagesRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new PagesRecyclerViewAdapter(pages));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}
