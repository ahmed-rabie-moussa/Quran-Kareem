package com.rabie.qurankareem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rabie.qurankareem.R;
import com.rabie.qurankareem.database.QuranDatabase;
import com.rabie.qurankareem.models.ChapterAndTranslatedName;
import com.rabie.qurankareem.models.Verse;
import com.rabie.qurankareem.models.VerseWithInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ElQuranActivity extends AppCompatActivity {
    int mOffset;
    ChapterAndTranslatedName chapterAndTranslatedName;
    TextView suraTextName;
    TextView suraTextNameEng;
    TextView suraText;
    ImageView basmallahView;
    List<VerseWithInfo> verseList;
    String suraAyatText;
    ArrayList<Integer> versesStart;
    ArrayList<Integer> versesEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_el_quran);
        suraTextName = findViewById(R.id.quranSuraTextName);
        suraTextNameEng = findViewById(R.id.quranSuraTextNameEng);
        suraText = findViewById(R.id.quranSuraText);
        basmallahView = findViewById(R.id.basmalahView);
        int chapterNumber = getIntent().getIntExtra("chapter", 1);
        loadData(chapterNumber);

        suraText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mOffset = suraText.getOffsetForPosition(event.getX(), event.getY());
                    int ayaPosition = selectTextStartAndEnd(mOffset);
                    SpannableString text = colorSpans(suraAyatText, versesStart.get(ayaPosition), versesEnd.get(ayaPosition));
                    suraText.setText(text);
//                    mTxtWord.setText(findWordForRightHanded(mTxt.getText().toString(), mOffset));
                }
                return false;
            }
        });
    }

    public void loadData(int chapterNumber) {
        QuranDatabase.getInstance(getApplicationContext()).chaptersDao().getChapter(chapterNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ChapterAndTranslatedName>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ChapterAndTranslatedName chapterAndTranslatedName) {
                        suraTextName.setText(chapterAndTranslatedName.chapter.getName_arabic());
                        suraTextNameEng.setText(chapterAndTranslatedName.translatedName.getName());
                        if (chapterAndTranslatedName.chapter.isBismillah_pre())
                            basmallahView.setVisibility(View.VISIBLE);
                        else basmallahView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        QuranDatabase.getInstance(getApplicationContext()).chaptersDao().getVersesListOfChapter(chapterNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<VerseWithInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<VerseWithInfo> verseWithInfos) {
                        verseList = verseWithInfos;
                        prepareSuraText(verseWithInfos);
                        String text = suraAyatText;
                        suraText.setText(text);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private String findWordForRightHanded(String str, int offset) { // when you touch ' ', this method returns left word.
        if (str.length() == offset) {
            offset--; // without this code, you will get exception when touching end of the text
        }

        if (str.charAt(offset) == ' ') {
            offset--;
        }
        int startIndex = offset;
        int endIndex = offset;

        try {
            while (str.charAt(startIndex) != ' ' && str.charAt(startIndex) != '\n') {
                startIndex--;
            }
        } catch (StringIndexOutOfBoundsException e) {
            startIndex = 0;
        }

        try {
            while (str.charAt(endIndex) != ' ' && str.charAt(endIndex) != '\n') {
                endIndex++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            endIndex = str.length();
        }

        // without this code, you will get 'here!' instead of 'here'
        // if you use only english, just check whether this is alphabet,
        // but 'I' use korean, so i use below algorithm to get clean word.
        char last = str.charAt(endIndex - 1);
        if (last == ',' || last == '.' ||
                last == '!' || last == '?' ||
                last == ':' || last == ';') {
            endIndex--;
        }

        return str.substring(startIndex, endIndex);
    }

    public SpannableString colorSpans(String textSource, int start, int end) {
        SpannableString wordtoSpan = new SpannableString(textSource);
        wordtoSpan.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.Tan)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return wordtoSpan;
//        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/qalammajeed.ttf");
//        textView.setTypeface(typeFace);
//        textView.setText(wordtoSpan);
    }

    public int selectTextStartAndEnd(int position) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < verseList.size(); i++) {
            if (position >= versesStart.get(i) && position <= versesEnd.get(i)) {
                return i;
            }
        }
        return 0;
    }

    public void prepareSuraText(List<VerseWithInfo> verseList) {
        String suraText = "";
        String suraSeparator = " ۞ ";
        versesStart = new ArrayList<>();
        versesEnd = new ArrayList<>();
        for (int i = 0; i < verseList.size(); i++) {
            versesStart.add(suraText.length());
            suraText += verseList.get(i).verse.getTextMadani();
            if (!(i == verseList.size() - 1))
                suraText += suraSeparator;
            versesEnd.add(suraText.length());
        }
        suraAyatText = suraText;
    }

}