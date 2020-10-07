package com.rabie.qurankareem.networking;

import com.rabie.qurankareem.models.ChaptersList;


import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("/api/v3/chapters/")
    Single<ChaptersList> fetchChapters();
}
