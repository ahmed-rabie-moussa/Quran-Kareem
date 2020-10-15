package com.rabie.qurankareem.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rabie.qurankareem.R;
import com.rabie.qurankareem.database.QuranDatabase;
import com.rabie.qurankareem.models.ChapterAndTranslatedName;
import com.rabie.qurankareem.models.ChapterViewModel;
import com.rabie.qurankareem.models.QuranViewModel;
import com.rabie.qurankareem.models.Verse;
import com.rabie.qurankareem.models.VerseWithInfo;
import com.rabie.qurankareem.models.VersesList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ElQuranActivity extends AppCompatActivity {
    int mOffset = 1;
    int chapterNumber;
    Menu menu;
    int pausePosition = 0;
    ChapterAndTranslatedName chapterAndTranslatedNameX;
    TextView suraTextName;
    TextView suraTextNameEng;
    TextView suraText;
    ImageView basmallahView;
    List<VerseWithInfo> verseList;
    String suraAyatText;
    ArrayList<Integer> versesStart;
    ArrayList<Integer> versesEnd;
    BottomAppBar bottomAppBar;
    FloatingActionButton floatingActionButton;
    MenuItem stop;
    MediaPlayer mediaPlayer = new MediaPlayer();
    String url;
    int recitation;
    SharedPreferences sharedPreferences;
    QuranViewModel quranViewModel = new ViewModelProvider(ViewModelStore::new).get(QuranViewModel.class);
    List<Verse> versesAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_el_quran);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sharedPreferences = getSharedPreferences("Quran Kareem", Context.MODE_PRIVATE);
        recitation = sharedPreferences.getInt("recitation", 4);


        floatingActionButton = findViewById(R.id.floatinActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stop.setVisible(!stop.isVisible());
                if (stop.isVisible()) {
                    startQuran();
                    floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_pause_24, getApplicationContext().getTheme()));
                    bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                } else {
                    pauseQuran();
                    floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_play_arrow_24, getApplicationContext().getTheme()));
                    bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);

                }
            }
        });


        suraTextName = findViewById(R.id.quranSuraTextName);
        suraTextNameEng = findViewById(R.id.quranSuraTextNameEng);
        suraText = findViewById(R.id.quranSuraText);
        basmallahView = findViewById(R.id.basmalahView);
        chapterNumber = getIntent().getIntExtra("chapter", 1);
        loadData(chapterNumber);

        suraText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mOffset = suraText.getOffsetForPosition(event.getX(), event.getY());
                    mOffset = selectTextStartAndEnd(mOffset) + 1;
                    SpannableString text = colorSpans(suraAyatText, versesStart.get(mOffset - 1), versesEnd.get(mOffset - 1));
                    suraText.setText(text);
                    pausePosition = 0;
                    floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_play_arrow_24, getApplicationContext().getTheme()));
                    bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                    stop.setVisible(false);
                    stopQuran();
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
                        chapterAndTranslatedNameX = chapterAndTranslatedName;
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

    private String findWordForRightHanded(String str, int offset) {
        if (str.length() == offset) {
            offset--;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.bottom_app_bar, menu);
        this.menu = menu;
        stop = menu.findItem(R.id.stop);
        stop.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_play_arrow_24, getApplicationContext().getTheme()));
                bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                item.setVisible(false);
                stopQuran();
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                System.out.println("Settings");
                break;
            case R.id.stop:
                System.out.println("Stop");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void startQuran() {
        if (pausePosition == 0) {
            quranViewModel.getChapterVerses(String.valueOf(chapterAndTranslatedNameX.chapter.getId()), recitation)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<VersesList>() {
                        @Override
                        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@io.reactivex.annotations.NonNull VersesList versesList) {
                            versesAPI = versesList.getVerses();
                        }

                        @Override
                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            playQuran(mOffset - 1, 0);
                        }
                    });
        } else playQuran(mOffset - 1, pausePosition);
    }

    private void playQuran(int position, int pausePosition) {
        final int[] pos = {position};
        mOffset = position + 1;

        if (position < versesAPI.size()) {
            try {
                SpannableString text = colorSpans(suraAyatText, versesStart.get(position), versesEnd.get(position));
                suraText.setText(text);
                mediaPlayer.reset();
                mediaPlayer.setDataSource(verseList.get(position).audio.getUrl());
                mediaPlayer.prepare();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        playQuran(++pos[0], 0);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.seekTo(pausePosition);
            pausePosition = 0;
            mediaPlayer.start();
        }
    }


    private void pauseQuran() {
        pausePosition = mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
    }

    private void stopQuran() {
        pausePosition = 0;
        mediaPlayer.stop();
    }

}