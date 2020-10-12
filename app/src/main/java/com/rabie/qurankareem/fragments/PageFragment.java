package com.rabie.qurankareem.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rabie.qurankareem.R;
import com.rabie.qurankareem.adapters.PagesRecyclerViewAdapter;
import com.rabie.qurankareem.adapters.SuraRecyclerViewAdapter;
import com.rabie.qurankareem.database.QuranDatabase;
import com.rabie.qurankareem.models.Chapter;
import com.rabie.qurankareem.models.Page;
import com.rabie.qurankareem.models.VerseWithInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class PageFragment extends Fragment implements LifecycleOwner {
    int numberOfPages;
    private LifecycleRegistry lifecycleRegistry;

    public PageFragment(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pagesRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        PagesRecyclerViewAdapter adapter = new PagesRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        //load Data
        new UseDataBaseAsync(recyclerView).execute();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    public class UseDataBaseAsync extends AsyncTask<Void, Void, List<List<VerseWithInfo>>> {

        RecyclerView recyclerView;

        UseDataBaseAsync(RecyclerView view) {
            this.recyclerView = view;
        }

        @Override
        protected List<List<VerseWithInfo>> doInBackground(Void... voids) {
            ArrayList<List<VerseWithInfo>> pages = new ArrayList<List<VerseWithInfo>>();
            for (int i = 1; i <= 604; i++) {
                List<VerseWithInfo> verses = QuranDatabase.getInstance(getContext()).chaptersDao().getVersesListFromPage(i);
                pages.add(verses);
            }
            return pages;
        }

        @Override
        protected void onPostExecute(List<List<VerseWithInfo>> lists) {
            super.onPostExecute(lists);
            PagesRecyclerViewAdapter pagesRecyclerViewAdapter = new PagesRecyclerViewAdapter();
            pagesRecyclerViewAdapter.setPages(lists);
            recyclerView.setAdapter(pagesRecyclerViewAdapter);
        }
    }

}
