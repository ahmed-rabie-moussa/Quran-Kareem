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
import com.rabie.qurankareem.adapters.SuraRecyclerViewAdapter;
import com.rabie.qurankareem.models.Chapter;
import com.rabie.qurankareem.models.ChapterAndTranslatedName;

import java.util.List;


public class SuraFragment extends Fragment {
    List<ChapterAndTranslatedName> chapters;

    public SuraFragment(List<ChapterAndTranslatedName> chapters) {
        this.chapters = chapters;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sura, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.suraRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SuraRecyclerViewAdapter(chapters));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}
