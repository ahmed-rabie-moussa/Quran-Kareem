package com.rabie.qurankareem.networking;

import com.rabie.qurankareem.models.ChaptersList;
import com.rabie.qurankareem.models.Verse;
import com.rabie.qurankareem.models.VersesList;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {
    @GET("/api/v3/chapters/")
    Observable<ChaptersList> fetchChapters();

    @GET("/api/v3/chapters/{chapter_id}/verses?recitation=1&translations=21")
    Observable<VersesList> fetchChapterVerses(@Path("chapter_id") String chapterId);
}
