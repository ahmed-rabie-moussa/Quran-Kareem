package com.rabie.qurankareem.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabie.qurankareem.database.QuranDatabase;
import com.rabie.qurankareem.networking.APIClient;
import com.rabie.qurankareem.networking.APIInterface;
import com.rabie.qurankareem.utils.InternetConnection;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class ChapterViewModel extends ViewModel {
    public MutableLiveData<List<ChapterAndTranslatedName>> chapterListMutableLiveData = new MutableLiveData<List<ChapterAndTranslatedName>>();



    public Observable<ChaptersList> getChaptersFromApi(){
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        return apiInterface.fetchChapters();
    }


    public Single<List<Long>> putChaptersToRoom(List<Chapter> chapterList, Context context){
        return QuranDatabase.getInstance(context).chaptersDao().insertChapter(chapterList);
    }


    public Single<List<Long>> putTranslatedChapterNameToRoom(List<TranslatedName> translatedNames, Context context){
        return QuranDatabase.getInstance(context).chaptersDao().insertTranslatedChapterName(translatedNames);
    }

    public Single<List<ChapterAndTranslatedName>> getChaptersFromDB(Context context){
        return QuranDatabase.getInstance(context).chaptersDao().getChapters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ChapterInfo> getChapterInfoFromApi(String chapter_id) {
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        return apiInterface.fetchChapterInfo(chapter_id);
    }

    public Single<ChapterAndTranslatedName> getChapterfromBD(int chapter_id, Context context) {
        return QuranDatabase.getInstance(context).chaptersDao().getChapter(chapter_id);
    }

    public Single<RecitationsList> getRecitationsFromApi() {
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        return apiInterface.fetchRecitations();
    }

    public Single<List<Long>> putRecitationsToRoom(List<Recitation> recitations, Context context) {
        return QuranDatabase.getInstance(context).chaptersDao().insertRecitations(recitations);
    }

}
