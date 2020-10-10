package com.rabie.qurankareem.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabie.qurankareem.networking.APIClient;
import com.rabie.qurankareem.networking.APIInterface;

import java.util.List;

import io.reactivex.Observable;
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

}
