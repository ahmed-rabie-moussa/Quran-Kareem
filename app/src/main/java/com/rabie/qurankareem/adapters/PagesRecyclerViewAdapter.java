package com.rabie.qurankareem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.rabie.qurankareem.R;
import com.rabie.qurankareem.models.VerseWithInfo;


import java.util.ArrayList;
import java.util.List;


public class PagesRecyclerViewAdapter extends RecyclerView.Adapter<PagesRecyclerViewAdapter.PagesViewHolder> {
    List<List<VerseWithInfo>> verseWithInfoList = new ArrayList<List<VerseWithInfo>>() {
    };

    public PagesRecyclerViewAdapter() {

    }

    public void setPages(List<List<VerseWithInfo>> verseWithInfoList) {
        for (int i = 0; i < verseWithInfoList.size(); i++) {
            if (verseWithInfoList.get(i).size() != 0)
                this.verseWithInfoList.add(verseWithInfoList.get(i));
        }

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_page_list_item, parent, false);
        PagesViewHolder pageViewHolder = new PagesViewHolder(view);
        return pageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PagesViewHolder holder, int position) {
        holder.pageNameNumberTextView.setText("Page " + (position + 1));
        if (verseWithInfoList.get(position).size() == 0) {
            holder.pageFirstVersTextView.setText(" ");
            holder.pageFirstWordsTextView.setText(" ");
        } else {
            holder.pageFirstVersTextView.setText(verseWithInfoList.get(position).get(0).verse.getVerseKey());
            holder.pageFirstWordsTextView.setText(verseWithInfoList.get(position).get(0).verse.getTextSimple());
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return verseWithInfoList.size();
    }


}
