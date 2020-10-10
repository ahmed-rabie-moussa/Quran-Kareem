//package com.rabie.qurankareem.utils;
//
//import com.rabie.qurankareem.models.Chapter;
//import com.rabie.qurankareem.models.ChapterAndTranslatedName;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ModelsConverter {
//
//    public static List<Chapter> fromRoomChapterToChapter (List<ChapterAndTranslatedName> chapterAndTranslatedNameList){
//        ArrayList<Chapter> chapterArrayList = new ArrayList<>();
//        for (ChapterAndTranslatedName chapterAndTranslatedName: chapterAndTranslatedNameList){
//            Chapter chapter = new Chapter(
//                    chapterAndTranslatedName.chapter.getId(),
//                    chapterAndTranslatedName.chapter.getChapter_number(),
//                    chapterAndTranslatedName.chapter.isBismillah_pre(),
//                    chapterAndTranslatedName.chapter.getRevelation_order(),
//                    chapterAndTranslatedName.chapter.getRevelation_place(),
//                    chapterAndTranslatedName.chapter.getName_complex(),
//                    chapterAndTranslatedName.chapter.getName_arabic(),
//                    chapterAndTranslatedName.chapter.getName_simple(),
//                    chapterAndTranslatedName.chapter.getVerses_count(),
//                    chapterAndTranslatedName.chapter.getPages(),
//                    chapterAndTranslatedName.chapter.getTranslated_name()
//            );
//            chapter.getTranslated_name().setChapter_id(chapterAndTranslatedName.chapter.getId());
//            chapter.getTranslated_name().setName(chapterAndTranslatedName.translatedName.getName());
//            chapter.getTranslated_name().setLanguageName(chapterAndTranslatedName.translatedName.getLanguageName());
//            chapterArrayList.add(chapter);
//        }
//        return chapterArrayList;
//    }
//
////Returned Example
////    {
////        "id": 1,
////            "chapter_number": 1,
////            "bismillah_pre": false,
////            "revelation_order": 5,
////            "revelation_place": "makkah",
////            "name_complex": "Al-Fātiĥah",
////            "name_arabic": "الفاتحة",
////            "name_simple": "Al-Fatihah",
////            "verses_count": 7,
////            "pages": [
////        1,
////                1
////      ],
////        "translated_name": {
////        "language_name": "english",
////                "name": "The Opener"
////    }
////    }
//
//    public static List<ChapterAndTranslatedName> fromChaptertoRoomChapter(List<Chapter> chapterList){
//        ArrayList<ChapterAndTranslatedName> chapterAndTranslatedNameList = new ArrayList<ChapterAndTranslatedName>();
//        for (Chapter chapter : chapterList){
//            ChapterAndTranslatedName chapterAndTranslatedName = new ChapterAndTranslatedName();
//            chapterAndTranslatedName.chapter.setId(chapter.getId());
//            chapterAndTranslatedName.chapter.setChapter_number(chapter.getChapter_number());
//            chapterAndTranslatedName.chapter.setBismillah_pre(chapter.isBismillah_pre());
//            chapterAndTranslatedName.chapter.setRevelation_order(chapter.getRevelation_order());
//            chapterAndTranslatedName.chapter.setRevelation_place(chapter.getRevelation_place());
//            chapterAndTranslatedName.chapter.setName_complex(chapter.getName_complex());
//            chapterAndTranslatedName.chapter.setName_arabic(chapter.getName_arabic());
//            chapterAndTranslatedName.chapter.setName_simple(chapter.getName_simple());
//            chapterAndTranslatedName.chapter.setVerses_count(chapter.getVerses_count());
//            chapterAndTranslatedName.chapter.setPages(chapter.getPages());
//            chapterAndTranslatedName.chapter.setTranslated_name(chapter.getTranslated_name());
//            chapterAndTranslatedName.translatedName.setChapter_id(chapter.getId());
//            chapterAndTranslatedName.translatedName.setLanguageName(chapter.getTranslated_name().getLanguageName());
//            chapterAndTranslatedName.translatedName.setName(chapter.getTranslated_name().getName());
//            chapterAndTranslatedNameList.add(chapterAndTranslatedName);
//        }
//        return chapterAndTranslatedNameList;
//    }
//}
