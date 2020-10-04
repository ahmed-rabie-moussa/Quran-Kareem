package com.rabie.qurankareem.models;

public class Verse {
    private int id;
    private int verse_number;
    private int chapter_id;
    private String text_madani;
    private String text_indopak;
    private String text_simple;
    private int juz_number;
    private int hizb_number;
    private int rub_number;
    private boolean sajdah;
    private int sajdah_number;
    private int page_number;
    private Word []words;
    public Verse(int id, int verse_number, int chapter_id, String text_madani, String text_indopak, String text_simple, int juz_number, int hizb_number, int rub_number, boolean sajdah, int sajdah_number, int page_number, Word[] words) {
        this.id = id;
        this.verse_number = verse_number;
        this.chapter_id = chapter_id;
        this.text_madani = text_madani;
        this.text_indopak = text_indopak;
        this.text_simple = text_simple;
        this.juz_number = juz_number;
        this.hizb_number = hizb_number;
        this.rub_number = rub_number;
        this.sajdah = sajdah;
        this.sajdah_number = sajdah_number;
        this.page_number = page_number;
        this.words = words;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVerse_number() {
        return verse_number;
    }

    public void setVerse_number(int verse_number) {
        this.verse_number = verse_number;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getText_madani() {
        return text_madani;
    }

    public void setText_madani(String text_madani) {
        this.text_madani = text_madani;
    }

    public String getText_indopak() {
        return text_indopak;
    }

    public void setText_indopak(String text_indopak) {
        this.text_indopak = text_indopak;
    }

    public String getText_simple() {
        return text_simple;
    }

    public void setText_simple(String text_simple) {
        this.text_simple = text_simple;
    }

    public int getJuz_number() {
        return juz_number;
    }

    public void setJuz_number(int juz_number) {
        this.juz_number = juz_number;
    }

    public int getHizb_number() {
        return hizb_number;
    }

    public void setHizb_number(int hizb_number) {
        this.hizb_number = hizb_number;
    }

    public int getRub_number() {
        return rub_number;
    }

    public void setRub_number(int rub_number) {
        this.rub_number = rub_number;
    }

    public boolean isSajdah() {
        return sajdah;
    }

    public void setSajdah(boolean sajdah) {
        this.sajdah = sajdah;
    }

    public int getSajdah_number() {
        return sajdah_number;
    }

    public void setSajdah_number(int sajdah_number) {
        this.sajdah_number = sajdah_number;
    }

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }

    public Word[] getWords() {
        return words;
    }

    public void setWords(Word[] words) {
        this.words = words;
    }

}
