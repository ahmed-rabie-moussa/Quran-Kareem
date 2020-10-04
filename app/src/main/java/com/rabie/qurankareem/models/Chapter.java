package com.rabie.qurankareem.models;

import java.util.Map;

public class Chapter {
    private int chapter_number;
    private boolean bismillah_pre;
    private int revelation_order;
    private String revelation_place;
    private String name_complex;
    private String name_arabic;
    private String name_simple;
    private int verses_count;
    private Map [] pages;                    // try to figure out.
    private Map translated_name;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChapter_number() {
        return chapter_number;
    }

    public void setChapter_number(int chapter_number) {
        this.chapter_number = chapter_number;
    }

    public boolean isBismillah_pre() {
        return bismillah_pre;
    }

    public void setBismillah_pre(boolean bismillah_pre) {
        this.bismillah_pre = bismillah_pre;
    }

    public int getRevelation_order() {
        return revelation_order;
    }

    public void setRevelation_order(int revelation_order) {
        this.revelation_order = revelation_order;
    }

    public String getRevelation_place() {
        return revelation_place;
    }

    public void setRevelation_place(String revelation_place) {
        this.revelation_place = revelation_place;
    }

    public String getName_complex() {
        return name_complex;
    }

    public void setName_complex(String name_complex) {
        this.name_complex = name_complex;
    }

    public String getName_arabic() {
        return name_arabic;
    }

    public void setName_arabic(String name_arabic) {
        this.name_arabic = name_arabic;
    }

    public String getName_simple() {
        return name_simple;
    }

    public void setName_simple(String name_simple) {
        this.name_simple = name_simple;
    }

    public int getVerses_count() {
        return verses_count;
    }

    public void setVerses_count(int verses_count) {
        this.verses_count = verses_count;
    }

    public Map[] getPages() {
        return pages;
    }

    public void setPages(Map[] pages) {
        this.pages = pages;
    }

    public Map getTranslated_name() {
        return translated_name;
    }

    public void setTranslated_name(Map translated_name) {
        this.translated_name = translated_name;
    }



    public Chapter(int id, int chapter_number, boolean bismillah_pre, int revelation_order, String revelation_place, String name_complex, String name_arabic, String name_simple, int verses_count, Map[] pages, Map translated_name) {
        this.id = id;
        this.chapter_number = chapter_number;
        this.bismillah_pre = bismillah_pre;
        this.revelation_order = revelation_order;
        this.revelation_place = revelation_place;
        this.name_complex = name_complex;
        this.name_arabic = name_arabic;
        this.name_simple = name_simple;
        this.verses_count = verses_count;
        this.pages = pages;
        this.translated_name = translated_name;
    }




}
