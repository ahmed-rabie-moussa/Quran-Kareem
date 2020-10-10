package com.rabie.qurankareem.models;

import java.util.List;

public class VerseWithInfo {

    private Verse verse;
    private List<Word> wordList;
    private List<Translation> translationList;
    private Audio audio;

    public VerseWithInfo(Verse verse, List<Word> wordList, List<Translation> translationList, Audio audio) {
        this.verse = verse;
        this.wordList = wordList;
        this.translationList = translationList;
        this.audio = audio;
    }

    public Verse getVerse() {
        return verse;
    }

    public void setVerse(Verse verse) {
        this.verse = verse;
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    public List<Translation> getTranslationList() {
        return translationList;
    }

    public void setTranslationList(List<Translation> translationList) {
        this.translationList = translationList;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }
}
