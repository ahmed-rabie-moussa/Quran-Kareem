package com.rabie.qurankareem.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChaptersList {

    @SerializedName("chapters")
    List<Chapter> chapters;


    public ChaptersList(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }


}
