package com.rabie.qurankareem.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rabie.qurankareem.R;
import com.rabie.qurankareem.models.Page;

import java.util.List;

public class PagesRecyclerViewAdapter extends RecyclerView.Adapter<PagesRecyclerViewAdapter.PagesViewHolder> {
    List<Page> pages;
    public void setChapters(List<Page> pages) {
        this.pages = pages;
    }



    public PagesRecyclerViewAdapter(List<Page> pages) {
        this.pages = pages;
    }

    public static class PagesViewHolder extends RecyclerView.ViewHolder {

        TextView pageNameNumberTextView;
        TextView pageFirstVersTextView;
        TextView pageFirstWordsTextView;


        public PagesViewHolder(@NonNull View itemView) {
            super(itemView);
            pageNameNumberTextView = itemView.findViewById(R.id.pageNumberTextView);
            pageFirstVersTextView = itemView.findViewById(R.id.pageFirstVerseTextView);
            pageFirstWordsTextView = itemView.findViewById(R.id.pageFirstWordsTextView);

        }


    }

    @NonNull
    @Override
    public PagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_page_list_item,parent,false);
        PagesViewHolder pageViewHolder = new PagesViewHolder(view);
        return pageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PagesViewHolder holder, int position) {
//        holder.suraNumberTextView.setText("" +chapters.get(position).getChapter_number());
//        holder.engSuraNameTextView.setText(chapters.get(position).getName_simple());
//        holder.translatedNameTextView.setText(chapters.get(position).getTranslated_name().getName());

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }


}
