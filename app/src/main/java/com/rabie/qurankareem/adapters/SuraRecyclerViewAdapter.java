package com.rabie.qurankareem.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;

import com.rabie.qurankareem.R;
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

    public static class SuraViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView suraNumberTextView;
        TextView engSuraNameTextView;
        TextView suraNameTextView;
        TextView translatedNameTextView;
        ImageView revelationPlaceImageView;

        public SuraViewHolder(@NonNull View itemView) {
            super(itemView);
            suraNameTextView = itemView.findViewById(R.id.suraNameTextView);
            engSuraNameTextView = itemView.findViewById(R.id.engSuraNameTextView);
            suraNumberTextView = itemView.findViewById(R.id.suraNumberTextView);
            translatedNameTextView = itemView.findViewById(R.id.translatedNameTextView);
            revelationPlaceImageView = itemView.findViewById(R.id.revelationPlaceImageView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            System.out.println("OnClick" + getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            ChapterViewModel chapterViewModel= new ViewModelProvider(ViewModelStore::new).get(ChapterViewModel.class);
            chapterViewModel.getChapterInfoFromApi(suraNumberTextView.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<ChapterInfo>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(ChapterInfo chapterInfo) {
                            System.out.println(chapterInfo.getChapterInfo().getText());
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println(e.getMessage());
                        }
                    });
            return true;
        }
    }

    @NonNull
    @Override
    public SuraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_sura_list_item,parent,false);
        SuraViewHolder suraViewHolder = new SuraViewHolder(view);
        return suraViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SuraViewHolder holder, int position) {
        holder.suraNumberTextView.setText("" +chapters.get(position).chapter.getChapter_number());
        holder.engSuraNameTextView.setText(chapters.get(position).chapter.getName_simple());
        holder.translatedNameTextView.setText(chapters.get(position).translatedName.getName());
        holder.suraNameTextView.setText(chapters.get(position).chapter.getName_arabic());
        if (chapters.get(position).chapter.getRevelation_place().equals("makkah")){
            holder.revelationPlaceImageView.setImageResource(R.drawable.ic_kaaba_mecca);
        }
        else holder.revelationPlaceImageView.setImageResource(R.drawable.ic_madinah);
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
