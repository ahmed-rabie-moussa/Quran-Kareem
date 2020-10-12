package com.rabie.qurankareem.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.rabie.qurankareem.database.QuranDatabase;
import com.rabie.qurankareem.models.Chapter;
import com.rabie.qurankareem.models.ChapterAndTranslatedName;
import com.rabie.qurankareem.models.ChapterViewModel;
import com.rabie.qurankareem.models.ChaptersList;
import com.rabie.qurankareem.models.Verse;
import com.rabie.qurankareem.models.VerseWithInfo;
import com.rabie.qurankareem.models.Word;
import com.rabie.qurankareem.networking.APIClient;
import com.rabie.qurankareem.networking.APIInterface;
import com.rabie.qurankareem.networking.RESTApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ReadScreenFragment extends Fragment implements ObservableScrollViewCallbacks {
    ArrayList<Chapter> fetchedChapters;
    ChapterViewModel chapterViewModel;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
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
        TextView suraTextView = view.findViewById(R.id.suraTouchedTextView);
        TextView pageTextView = view.findViewById(R.id.pageTouchedTextView);
        TextView juzTextView = view.findViewById(R.id.juzTouchedTextView);
        TextView rubTextView = view.findViewById(R.id.rubTouchedTextView);
        getSuraFragment();
        suraTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSuraFragment();
                suraTextView.setTextColor(getResources().getColor(R.color.Black));
                pageTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
                juzTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
                rubTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
            }
        });
        pageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPageFragment();
                suraTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
                pageTextView.setTextColor(getResources().getColor(R.color.Black));
                juzTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
                rubTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
            }
        });
        juzTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJuzFragment();
                suraTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
                pageTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
                juzTextView.setTextColor(getResources().getColor(R.color.Black));
                rubTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
            }
        });
        rubTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRubFragment();
                suraTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
                pageTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
                juzTextView.setTextColor(getResources().getColor(R.color.LightSlateGray));
                rubTextView.setTextColor(getResources().getColor(R.color.Black));
            }
        });


        return view;
    }

    public void getSuraFragment() {
        QuranDatabase.getInstance(getContext()).chaptersDao().getChapters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ChapterAndTranslatedName>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<ChapterAndTranslatedName> chapterAndTranslatedNames) {
                        FragmentManager fragmentManager = getParentFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        SuraFragment suraFragment = new SuraFragment(chapterAndTranslatedNames);
                        fragmentTransaction.replace(R.id.fragment_category, suraFragment);
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void getPageFragment() {
        QuranDatabase.getInstance(getContext()).chaptersDao().getTotalPages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        FragmentManager fragmentManager = getParentFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        PageFragment pageFragment = new PageFragment(integer);
                        fragmentTransaction.replace(R.id.fragment_category, pageFragment);
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    public void getJuzFragment() {


    }

    public void getRubFragment() {

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
