package com.rabie.qurankareem.networking;

import com.rabie.qurankareem.models.ChapterInfo;
import com.rabie.qurankareem.models.ChaptersList;

import com.rabie.qurankareem.models.RecitationsList;
import com.rabie.qurankareem.models.VersesList;


import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/api/v3/chapters/")
    Observable<ChaptersList> fetchChapters();

    @GET("/api/v3/chapters/{chapter_id}/verses?translations=21")
    Observable<VersesList> fetchChapterVerses(@Path("chapter_id") String chapterId, @Query("recitation") int recitation);

    @GET("/api/v3/chapters/{chapter_id}/info")
    Single<ChapterInfo> fetchChapterInfo(@Path("chapter_id") String chapterId);

    @GET("/api/v3/options/recitations?language=9")
    Single<RecitationsList> fetchRecitations();


}
