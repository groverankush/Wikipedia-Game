package com.ankushgrover.letswiki.ui.wiki.wordsDilaog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ankushgrover.letswiki.data.model.Word;

import java.util.ArrayList;

/**
 * Created by Ankush Grover on 16-11-2018.
 */
public class LeftWordsAdapter extends RecyclerView.Adapter<LeftWordsAdapter.WordsHolder> {

    private ArrayList<Word> words;
    private OnItemClickListener listener;

    public LeftWordsAdapter(ArrayList<Word> words, OnItemClickListener listener) {

        this.words = words;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WordsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WordsHolder(LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_selectable_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WordsHolder wordsHolder, int i) {
        wordsHolder.wordTv.setText(words.get(i).getWord());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class WordsHolder extends RecyclerView.ViewHolder {

        TextView wordTv;


        WordsHolder(@NonNull View itemView) {
            super(itemView);

            wordTv = itemView.findViewById(android.R.id.text1);

            itemView.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));

        }
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }
}
