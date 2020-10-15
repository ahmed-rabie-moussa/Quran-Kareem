package com.rabie.qurankareem.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecitationsList {

    @SerializedName("recitations")
    private List<Recitation> recitationList;

    public RecitationsList(List<Recitation> recitationList) {
        this.recitationList = recitationList;
    }

    public List<Recitation> getRecitationList() {
        return recitationList;
    }

    public void setRecitationList(List<Recitation> recitationList) {
        this.recitationList = recitationList;
    }
}
