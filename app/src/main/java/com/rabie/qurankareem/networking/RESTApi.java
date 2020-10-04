package com.rabie.qurankareem.networking;

public class RESTApi {
    public static String recitations = "http://api.quran.com:3000/api/v3/options/recitations";
    public static String chapters = "http://api.quran.com:3000/api/v3/chapters";
    public static String verses = "http://api.quran.com:3000/api/v3/chapters/{chapter_id}/verses?recitation=1&translations=21&language=en&text_type=words";
}
