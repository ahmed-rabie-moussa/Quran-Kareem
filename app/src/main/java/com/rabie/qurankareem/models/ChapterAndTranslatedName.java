package com.rabie.qurankareem.models;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ChapterAndTranslatedName {

    @Embedded
    public Chapter chapter;
    @Relation(
            parentColumn = "id",
            entityColumn = "chapter_id"
    )
    public TranslatedName translatedName;

}
