package com.rabie.qurankareem.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "recitations")
public class Recitation implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "style")
    @SerializedName("style")
    private String style;

    @ColumnInfo(name = "reciter_name_eng")
    @SerializedName("reciter_name_eng")
    private String reciter_name_eng;

    @ColumnInfo(name = "reciter_name_translated")
    @SerializedName("reciter_name_translated")
    private String reciter_name_translated;

    @Ignore
    public Recitation() {
    }

    public Recitation(int id, String style, String reciter_name_eng, String reciter_name_translated) {
        this.id = id;
        this.style = style;
        this.reciter_name_eng = reciter_name_eng;
        this.reciter_name_translated = reciter_name_translated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getReciter_name_eng() {
        return reciter_name_eng;
    }

    public void setReciter_name_eng(String reciter_name_eng) {
        this.reciter_name_eng = reciter_name_eng;
    }

    public String getReciter_name_translated() {
        return reciter_name_translated;
    }

    public void setReciter_name_translated(String reciter_name_translated) {
        this.reciter_name_translated = reciter_name_translated;
    }
}
