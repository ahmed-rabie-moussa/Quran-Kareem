package com.rabie.qurankareem.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rabie.qurankareem.R;
import com.rabie.qurankareem.models.Chapter;

import java.util.List;

public class SuraRecyclerViewAdapter extends RecyclerView.Adapter<SuraRecyclerViewAdapter.SuraViewHolder> {
    List<Chapter> chapters;
    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }



    public SuraRecyclerViewAdapter(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public static class SuraViewHolder extends RecyclerView.ViewHolder {

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
        holder.suraNumberTextView.setText("" +chapters.get(position).getChapter_number());
        holder.engSuraNameTextView.setText(chapters.get(position).getName_simple());
        holder.translatedNameTextView.setText(chapters.get(position).getTranslated_name().getName());
        holder.suraNameTextView.setText(chapters.get(position).getName_arabic());
        System.out.println(chapters.get(position).getName_arabic());
        if (chapters.get(position).getRevelation_place().equals("makkah")){
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
