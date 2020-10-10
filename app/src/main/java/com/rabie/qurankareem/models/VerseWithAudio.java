package com.rabie.qurankareem.models;

import androidx.room.Embedded;
import androidx.room.Relation;

public class VerseWithAudio {
    @Embedded
    private Verse verse;
    @Relation(
            parentColumn = "id",
            entityColumn = "verse_id"
    )
    private Audio audio;

    public VerseWithAudio(Verse verse, Audio audio) {
        this.verse = verse;
        this.audio = audio;
    }

    public Verse getVerse() {
        return verse;
    }

    public void setVerse(Verse verse) {
        this.verse = verse;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }
}
