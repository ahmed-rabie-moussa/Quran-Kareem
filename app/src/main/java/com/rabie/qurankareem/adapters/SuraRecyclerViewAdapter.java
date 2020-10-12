package com.rabie.qurankareem.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;

import com.rabie.qurankareem.R;
import com.rabie.qurankareem.activities.ElQuranActivity;
import com.rabie.qurankareem.activities.MainActivity;
import com.rabie.qurankareem.database.QuranDatabase;
import com.rabie.qurankareem.models.Chapter;
import com.rabie.qurankareem.models.ChapterAndTranslatedName;
import com.rabie.qurankareem.models.ChapterInfo;
import com.rabie.qurankareem.models.ChapterViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SuraRecyclerViewAdapter extends RecyclerView.Adapter<SuraRecyclerViewAdapter.SuraViewHolder> {
    List<ChapterAndTranslatedName> chapters;

    public void setChapters(List<ChapterAndTranslatedName> chapters) {
        this.chapters = chapters;
    }

    public SuraRecyclerViewAdapter(List<ChapterAndTranslatedName> chapters) {
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public SuraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_sura_list_item, parent, false);
        SuraViewHolder suraViewHolder = new SuraViewHolder(view);
        return suraViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SuraViewHolder holder, int position) {
        holder.suraNumberTextView.setText("" + chapters.get(position).chapter.getChapter_number());
        holder.engSuraNameTextView.setText(chapters.get(position).chapter.getName_simple());
        holder.translatedNameTextView.setText(chapters.get(position).translatedName.getName());
        holder.suraNameTextView.setText(chapters.get(position).chapter.getName_arabic());
        if (chapters.get(position).chapter.getRevelation_place().equals("makkah")) {
            holder.revelationPlaceImageView.setImageResource(R.drawable.ic_kaaba_mecca);
        } else holder.revelationPlaceImageView.setImageResource(R.drawable.ic_madinah);
    }

    public static class SuraViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView suraNumberTextView;
        TextView engSuraNameTextView;
        TextView suraNameTextView;
        TextView translatedNameTextView;
        ImageView revelationPlaceImageView;
        Context context;

        public SuraViewHolder(@NonNull View itemView) {
            super(itemView);
            suraNameTextView = itemView.findViewById(R.id.suraNameTextView);
            engSuraNameTextView = itemView.findViewById(R.id.engSuraNameTextView);
            suraNumberTextView = itemView.findViewById(R.id.suraNumberTextView);
            translatedNameTextView = itemView.findViewById(R.id.translatedNameTextView);
            revelationPlaceImageView = itemView.findViewById(R.id.revelationPlaceImageView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ElQuranActivity.class);
            intent.putExtra("chapter", getAdapterPosition() + 1);
            context.startActivity(intent);
        }


        @Override
        public boolean onLongClick(View v) {
            ChapterViewModel chapterViewModel = new ViewModelProvider(ViewModelStore::new).get(ChapterViewModel.class);
            chapterViewModel.getChapterInfoFromApi(String.valueOf(getAdapterPosition() + 1))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<ChapterInfo>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(ChapterInfo chapterInfo) {
                            chapterViewModel.getChapterfromBD(chapterInfo.getChapterInfo().getChapterId(), v.getContext()).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread()).subscribe(chapterAndTranslatedName -> {

                                popUpChapterInfo(chapterInfo.getChapterInfo(), chapterAndTranslatedName, v.getContext());
                            }, e -> {
                            });

                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println(e.getMessage());
                        }
                    });
            return true;
        }

        public void popUpChapterInfo(ChapterInfo chapterInfo, ChapterAndTranslatedName chapter, Context context) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_chapter_info, null);

            TextView textview = (TextView) view.findViewById(R.id.dialogSuraArabicName);
            textview.setText(chapter.chapter.getName_arabic());
            TextView textview1 = (TextView) view.findViewById(R.id.dialogSuraEngName);
            textview1.setText(chapter.translatedName.getName());
            TextView textview2 = (TextView) view.findViewById(R.id.dialogSuraNumber);
            textview2.setText(String.valueOf(chapter.chapter.getChapter_number()));
            TextView textview3 = (TextView) view.findViewById(R.id.dialogSuraRevelationPlace);
            textview3.setText(chapter.chapter.getRevelation_place());
            TextView textview4 = (TextView) view.findViewById(R.id.dialogSuraRevelationOrder);
            textview4.setText(String.valueOf(chapter.chapter.getRevelation_order()));
            TextView textview5 = (TextView) view.findViewById(R.id.dialogSuraShortText);
            textview5.setText(chapterInfo.getShortText());
            TextView textview6 = (TextView) view.findViewById(R.id.dialogSuraText);
            textview6.setText(Html.fromHtml(chapterInfo.getText()));

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            // alertDialog.setTitle(c);
            alertDialog.setView(view);
            alertDialog.setPositiveButton("Return", null);
            AlertDialog alert = alertDialog.create();
            alert.show();
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }


}
