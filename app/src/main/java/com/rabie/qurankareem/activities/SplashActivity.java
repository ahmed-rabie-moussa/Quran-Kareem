package com.rabie.qurankareem.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.rabie.qurankareem.R;
import com.rabie.qurankareem.models.Chapter;
import com.rabie.qurankareem.models.ChapterAndTranslatedName;
import com.rabie.qurankareem.models.ChapterViewModel;
import com.rabie.qurankareem.models.ChapterWithVerses;
import com.rabie.qurankareem.models.ChaptersList;
import com.rabie.qurankareem.models.QuranViewModel;
import com.rabie.qurankareem.models.TranslatedName;
import com.rabie.qurankareem.models.Translation;
import com.rabie.qurankareem.models.Verse;
import com.rabie.qurankareem.models.VersesList;
import com.rabie.qurankareem.models.Word;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashActivity extends Activity implements LifecycleOwner {

    private LifecycleRegistry lifecycleRegistry;
    private ArrayList<ChapterWithVerses> chapterWithVersesArrayList = new ArrayList<>();
    QuranViewModel quranViewModel = new ViewModelProvider(ViewModelStore::new).get(QuranViewModel.class);
    ChapterViewModel chapterViewModel = new ViewModelProvider(ViewModelStore::new).get(ChapterViewModel.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
        getChaptersFromApiToRoom();


    }

    public void getChaptersFromApiToRoom() {
        chapterViewModel.getChaptersFromApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChaptersList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChaptersList chaptersList) {
                        ArrayList<ChapterAndTranslatedName> chapterAndTranslatedNameArrayList = new ArrayList<>();
                        ArrayList<Chapter> chapterArrayList = new ArrayList<Chapter>();
                        ArrayList<TranslatedName> translatedNameArrayList = new ArrayList<TranslatedName>();
                        for (int i = 0; i < chaptersList.getChapters().size(); i++) {
                            Chapter chapter = chaptersList.getChapters().get(i);
                            TranslatedName translatedName = new TranslatedName();
                            translatedName.setLanguageName(chapter.getTranslated_name().getLanguageName());
                            translatedName.setName(chapter.getTranslated_name().getName());
                            translatedName.setChapter_id(chapter.getId());
                            ChapterAndTranslatedName chapterAndTranslatedName = new ChapterAndTranslatedName();
                            chapterAndTranslatedName.setChapter(chapter);
                            chapterAndTranslatedName.setTranslatedName(translatedName);
                            chapterArrayList.add(chapter);
                            translatedNameArrayList.add(translatedName);
                            chapterAndTranslatedNameArrayList.add(chapterAndTranslatedName);
                        }
                        chapterViewModel.putChaptersToRoom(chapterArrayList, getApplicationContext())
                                .subscribeOn(Schedulers.io())
                                .subscribe(result -> {
                                }, error -> Log.i("SplashActivity", error.getMessage()));
                        chapterViewModel.putTranslatedChapterNameToRoom(translatedNameArrayList, getApplicationContext())
                                .subscribeOn(Schedulers.io())
                                .subscribe(result -> {
                                }, error -> Log.i("SplashActivity", error.getMessage()));
                        chapterViewModel.chapterListMutableLiveData.setValue(chapterAndTranslatedNameArrayList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("SplashActivity", "Data from Api and Putted To DB");
                    }
                });
        chapterViewModel.chapterListMutableLiveData.observe(this::getLifecycle, new androidx.lifecycle.Observer<List<ChapterAndTranslatedName>>() {
            @Override
            public void onChanged(List<ChapterAndTranslatedName> chapterAndTranslatedNames) {
                Observable<VersesList> observable = Observable.fromIterable(chapterAndTranslatedNames)
                        .flatMap(new Function<ChapterAndTranslatedName, ObservableSource<VersesList>>() {
                            @Override
                            public ObservableSource<VersesList> apply(ChapterAndTranslatedName chapterAndTranslatedName) throws Exception {
                                return getChaptersVersesFromApi(chapterAndTranslatedName.getChapter());
                            }
                        });
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                observable.subscribe(new Observer<VersesList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VersesList versesList) {
                        int position = versesList.getVerses().get(0).getChapterId();
                        ChapterWithVerses chapterWithVerses = new ChapterWithVerses(chapterAndTranslatedNames.get(position - 1).getChapter(), versesList.getVerses());
                        chapterWithVersesArrayList.add(chapterWithVerses);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Completed");
                        quranViewModel.quranMutableLiveData.setValue(reorderVersesInQuran(chapterWithVersesArrayList));
                    }
                });

            }
        });
quranViewModel.quranMutableLiveData.observe(this::getLifecycle, new androidx.lifecycle.Observer<List<ChapterWithVerses>>() {
    @Override
    public void onChanged(List<ChapterWithVerses> chaptersWithVerses) {

    }
});




    }

    public Observable<VersesList> getChaptersVersesFromApi(Chapter chapter) {
        return quranViewModel.getChapterVerses(Integer.toString(chapter.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public ArrayList<ChapterWithVerses> reorderVersesInQuran (ArrayList<ChapterWithVerses> chaptersWithVerses){
        ArrayList<ChapterWithVerses> modified = new ArrayList<>();
        for (ChapterWithVerses chapterWithVerses: chaptersWithVerses){
            for(Verse verse: chapterWithVerses.getChapterVerseList()){
                verse.getAudio().setVerse_id(verse.getId());
                for (Translation translation:verse.getTranslations()){
                    translation.setVerse_id(verse.getId());
                }
                for(Word word :verse.getWords()){
                    word.setAudio_url(word.getAudio().getUrl());
                }
            }
            modified.add(chapterWithVerses);
        }
        return modified;
    }


    @Override
    public void onStart() {
        super.onStart();
        lifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }


}
