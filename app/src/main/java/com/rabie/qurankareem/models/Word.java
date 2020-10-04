package com.rabie.qurankareem.models;

public class Word {
    private int id;
    private int position;
    private String text_madani;
    private String text_indopak;
    private String text_simple;
    private String class_name;
    private int line_number;
    private int page_number;
    private String code;
    private String code_v3;
    private String char_type;
    private String audio;

    public Word(int id, int position, String text_madani, String text_indopak, String text_simple, String class_name, int line_number, int page_number, String code, String code_v3, String char_type, String audio) {
        this.id = id;
        this.position = position;
        this.text_madani = text_madani;
        this.text_indopak = text_indopak;
        this.text_simple = text_simple;
        this.class_name = class_name;
        this.line_number = line_number;
        this.page_number = page_number;
        this.code = code;
        this.code_v3 = code_v3;
        this.char_type = char_type;
        this.audio = audio;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getLine_number() {
        return line_number;
    }

    public void setLine_number(int line_number) {
        this.line_number = line_number;
    }

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode_v3() {
        return code_v3;
    }

    public void setCode_v3(String code_v3) {
        this.code_v3 = code_v3;
    }

    public String getChar_type() {
        return char_type;
    }

    public void setChar_type(String char_type) {
        this.char_type = char_type;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }


}
