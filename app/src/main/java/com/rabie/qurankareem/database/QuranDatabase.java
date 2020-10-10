package com.rabie.qurankareem.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rabie.qurankareem.models.Audio;
import com.rabie.qurankareem.models.Chapter;
import com.rabie.qurankareem.models.TranslatedName;
import com.rabie.qurankareem.models.Translation;
import com.rabie.qurankareem.models.Verse;
import com.rabie.qurankareem.models.Word;
import com.rabie.qurankareem.utils.MyCustomTypeConverter;

@Database(entities = {Chapter.class, TranslatedName.class, Verse.class, Word.class, Audio.class, Translation.class}, version = 1)
@TypeConverters({MyCustomTypeConverter.class})
public abstract class QuranDatabase extends RoomDatabase {
    private static QuranDatabase instance;

    public abstract ChaptersDao chaptersDao();

    public static synchronized QuranDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), QuranDatabase.class, "quran.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
