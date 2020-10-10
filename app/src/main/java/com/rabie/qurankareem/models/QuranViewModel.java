package com.rabie.qurankareem.models;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabie.qurankareem.database.ChaptersDao;
import com.rabie.qurankareem.database.QuranDatabase;
import com.rabie.qurankareem.networking.APIClient;
import com.rabie.qurankareem.networking.APIInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;

public class QuranViewModel extends ViewModel {
    public MutableLiveData<List<ChapterWithVerses>> quranMutableLiveData = new MutableLiveData<List<ChapterWithVerses>>();
    public MutableLiveData<Boolean> quranCompleted = new MutableLiveData<Boolean>();


    public Observable<VersesList> getChapterVerses(String chapterID){
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        return apiInterface.fetchChapterVerses(chapterID);
    }

    public Single insertVersesOnRoom (List<Verse> verses, Context context){
       return QuranDatabase.getInstance(context).chaptersDao().insertVerses(verses);
    }

    public Single insertWordsOnRoom (List<Word> words, Context context){
        return QuranDatabase.getInstance(context).chaptersDao().insertWords(words);
    }

    public Single insertAudioOnRoom (List<Audio> audioList, Context context){
        return QuranDatabase.getInstance(context).chaptersDao().insertAudio(audioList);
    }

    public Single insertTranslationOnRoom (List<Translation> translations, Context context){
        return QuranDatabase.getInstance(context).chaptersDao().insertTranslation(translations);
    }

}
