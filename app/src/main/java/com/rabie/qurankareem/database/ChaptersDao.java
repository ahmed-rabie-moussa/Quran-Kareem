package com.rabie.qurankareem.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;


import com.rabie.qurankareem.models.Chapter;
import com.rabie.qurankareem.models.ChapterAndTranslatedName;
import com.rabie.qurankareem.models.TranslatedName;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;


@Dao
public interface ChaptersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertChapter (List<Chapter> chapters);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertTranslatedChapterName (List<TranslatedName> translatedNames);

    @Transaction
    @Query("SELECT * FROM chapters")
    Single<List<ChapterAndTranslatedName>> getChapters ();


}
