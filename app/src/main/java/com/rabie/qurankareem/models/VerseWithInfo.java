package com.rabie.qurankareem.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class VerseWithInfo {
@Embedded public Verse verse;
    @Relation(
            parentColumn = "verse_key",
            entityColumn = "verse_key"
    )
    public List<Word> wordList;

    @Relation(
            parentColumn = "id",
            entityColumn = "verse_id"
    )
    public List<Translation> translationList;

    @Relation(
            parentColumn = "id",
            entityColumn = "verse_id"
    )
    public Audio audio;


}
