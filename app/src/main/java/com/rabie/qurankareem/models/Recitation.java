package com.rabie.qurankareem.models;

public class Recitation {
    private int id;
    private String style;
    private String reciter_name_eng;
    private String reciter_name_translated;

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
