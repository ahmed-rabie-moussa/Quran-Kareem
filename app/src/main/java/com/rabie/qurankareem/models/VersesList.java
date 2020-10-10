package com.rabie.qurankareem.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VersesList {

    @SerializedName("verses")
    private List<Verse> verses;

    public VersesList(List<Verse> verses) {
        this.verses = verses;
    }

    public List<Verse> getVerses() {
        return verses;
    }

    public void setVerses(List<Verse> verses) {
        this.verses = verses;
    }
}
