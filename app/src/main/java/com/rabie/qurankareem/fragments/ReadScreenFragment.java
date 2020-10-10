package com.rabie.qurankareem.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabie.qurankareem.R;
import com.rabie.qurankareem.models.Chapter;
import com.rabie.qurankareem.models.ChapterAndTranslatedName;
import com.rabie.qurankareem.models.ChapterViewModel;
import com.rabie.qurankareem.models.ChaptersList;
import com.rabie.qurankareem.networking.APIClient;
import com.rabie.qurankareem.networking.APIInterface;
import com.rabie.qurankareem.networking.RESTApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ReadScreenFragment extends Fragment implements ObservableScrollViewCallbacks {
    ArrayList<Chapter> fetchedChapters;
    ChapterViewModel chapterViewModel;
    private static ReadScreenFragment readScreenFragment = new ReadScreenFragment();

    public static Fragment getInstance() {
        return readScreenFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);
        chapterViewModel = new ViewModelProvider(this).get(ChapterViewModel.class);
//        chapterViewModel.getChaptersFromApi(getContext());
//        chapterViewModel.chapterListMutableLiveData.observe(getActivity(), new Observer<ChapterAndTranslatedName>() {
//            @Override
//            public void onChanged(ChapterAndTranslatedName chaptersList) {
////                FragmentManager fragmentManager = getParentFragmentManager();
////                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
////                SuraFragment suraFragment = new SuraFragment(chaptersList.getChapter());
////                fragmentTransaction.add(R.id.fragment_sura,suraFragment);
////                fragmentTransaction.commit();
//            }
//        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }


    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getActivity().getActionBar();
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }
}
