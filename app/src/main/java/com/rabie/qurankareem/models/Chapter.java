package com.rabie.qurankareem.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "chapters")
public class Chapter implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "chapter_number")
    @SerializedName("chapter_number")
    private int chapter_number;

    @ColumnInfo(name = "bismillah_pre")
    @SerializedName("bismillah_pre")
    private boolean bismillah_pre;

    @ColumnInfo(name = "revelation_order")
    @SerializedName("revelation_order")
    private int revelation_order;

    @ColumnInfo(name = "revelation_place")
    @SerializedName("revelation_place")
    private String revelation_place;

    @ColumnInfo(name = "name_complex")
    @SerializedName("name_complex")
    private String name_complex;

    @ColumnInfo(name = "name_arabic")
    @SerializedName("name_arabic")
    private String name_arabic;

    @ColumnInfo(name = "name_simple")
    @SerializedName("name_simple")
    private String name_simple;

    @ColumnInfo(name = "verses_count")
    @SerializedName("verses_count")
    private int verses_count;

    @ColumnInfo(name = "pages")
    @SerializedName("pages")
    private List<Integer> pages;
    @Ignore
    @SerializedName("translated_name")
    private TranslatedName translated_name;
    @Ignore
    public Chapter() {
    }

    public Chapter(int id, int chapter_number, boolean bismillah_pre, int revelation_order, String revelation_place, String name_complex, String name_arabic, String name_simple, int verses_count, List<Integer> pages) {
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
    }

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

}
