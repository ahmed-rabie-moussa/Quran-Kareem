package com.rabie.qurankareem.networking;

public interface RESTApi {
    public static final String baseUrl = "http://api.quran.com:3000";
    public  static final String recitations = "/api/v3/options/recitations?language=9";
    public static final String translations = "/api/v3/options/translations";
    public static final String languages = "/api/v3/options/translations";
    public  static final String verses = "chapters/{chapter_id}/verses?recitation=1&translations=21&language=en&text_type=words";
}
