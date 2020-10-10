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

    public ChapterAndTranslatedName() {
    }

    public ChapterAndTranslatedName(Chapter chapter, TranslatedName translatedName) {
        this.chapter = chapter;
        this.translatedName = translatedName;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public TranslatedName getTranslatedName() {
        return translatedName;
    }

    public void setTranslatedName(TranslatedName translatedName) {
        this.translatedName = translatedName;
    }
}
