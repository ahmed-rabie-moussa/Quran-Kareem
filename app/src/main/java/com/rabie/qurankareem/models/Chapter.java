package com.rabie.qurankareem.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.SkipCallbackExecutor;

public class Chapter implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("chapter_number")
    private int chapter_number;
    @SerializedName("bismillah_pre")
    private boolean bismillah_pre;
    @SerializedName("revelation_order")
    private int revelation_order;
    @SerializedName("revelation_place")
    private String revelation_place;
    @SerializedName("name_complex")
    private String name_complex;
    @SerializedName("name_arabic")
    private String name_arabic;
    @SerializedName("name_simple")
    private String name_simple;
    @SerializedName("verses_count")
    private int verses_count;
    @SerializedName("pages")
    private List<Integer> pages;
    @SerializedName("translated_name")
    private TranslatedName translated_name;


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

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public TranslatedName getTranslated_name() {
        return translated_name;
    }

    public void setTranslated_name(TranslatedName translated_name) {
        this.translated_name = translated_name;
    }


    public Chapter(int id, int chapter_number, boolean bismillah_pre, int revelation_order, String revelation_place, String name_complex, String name_arabic, String name_simple, int verses_count, List<Integer> pages, TranslatedName translated_name) {
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


    public class TranslatedName {
        @SerializedName("language_name")
        String language_name;
        @SerializedName("name")
        String name;


        public TranslatedName(String language_name, String name) {
            this.language_name = language_name;
            this.name = name;
        }

        public String getLanguage_name() {
            return language_name;
        }

        public void setLanguage_name(String language_name) {
            this.language_name = language_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}
