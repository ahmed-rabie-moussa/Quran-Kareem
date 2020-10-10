package com.rabie.qurankareem.models;

import java.util.List;

public class ChapterWithVerses {

    private Chapter chapter;
    private List<Verse> chapterVerseList;

    public ChapterWithVerses(Chapter chapter, List<Verse> chapterVerseList) {
        this.chapter = chapter;
        this.chapterVerseList = chapterVerseList;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public List<Verse> getChapterVerseList() {
        return chapterVerseList;
    }

    public void setChapterVerseList(List<Verse> chapterVerseList) {
        this.chapterVerseList = chapterVerseList;
    }
}
