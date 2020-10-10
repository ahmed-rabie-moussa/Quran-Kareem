package com.rabie.qurankareem.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;


import com.rabie.qurankareem.models.Audio;
import com.rabie.qurankareem.models.Chapter;
import com.rabie.qurankareem.models.ChapterAndTranslatedName;
import com.rabie.qurankareem.models.TranslatedName;
import com.rabie.qurankareem.models.Translation;
import com.rabie.qurankareem.models.Verse;
import com.rabie.qurankareem.models.Word;

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertVerses (List<Verse> verseList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertWords (List<Word> wordList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertAudio (List<Audio> wordList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertTranslation (List<Translation> translationList);



}
