package com.rabie.qurankareem.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabie.qurankareem.networking.APIClient;
import com.rabie.qurankareem.networking.APIInterface;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;


import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;


public class ChapterViewModel extends ViewModel {
    public MutableLiveData<ChaptersList> chapterListMutableLiveData = new MutableLiveData<ChaptersList>();

    public void getChapters(){
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Single<ChaptersList> single = apiInterface.fetchChapters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(o -> chapterListMutableLiveData.setValue(o), e -> Log.i("Toto", e.getMessage()));

    }
}
