package com.rabie.qurankareem.models;

public class Language {
    private int id;
    private String name;
    private String iso_code;
    private String native_name;
    private String direction;

    public Language(int id, String name, String iso_code, String native_name, String direction) {
        this.id = id;
        this.name = name;
        this.iso_code = iso_code;
        this.native_name = native_name;
        this.direction = direction;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public String getNative_name() {
        return native_name;
    }

    public void setNative_name(String native_name) {
        this.native_name = native_name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }



}
