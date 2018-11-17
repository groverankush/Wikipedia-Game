package com.ankushgrover.letswiki.ui.wiki.wordsDilaog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.data.model.Word;
import com.ankushgrover.letswiki.utils.Utils;
import com.ankushgrover.letswiki.viewmodel.GameViewModel;

import java.util.ArrayList;


public class LeftWordsDialogFragment extends DialogFragment {


    private static final String KEY_CLICKED_INDEX = "clickedIndex";
    private GameViewModel model;

    public static LeftWordsDialogFragment newInstance(int clickedIndex) {
        LeftWordsDialogFragment fragment = new LeftWordsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_CLICKED_INDEX, clickedIndex);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        model = ViewModelProviders.of(getActivity()).get(GameViewModel.class);


        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_words, null, false);
        initView(view);


        return new AlertDialog.Builder(getActivity()).setView(view).create();

    }

    private void initView(View view) {

        ArrayList<Word> words = Utils.getMissingWords(model.article);
        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        LeftWordsAdapter adapter = new LeftWordsAdapter(words, position -> {
            model.article.getValue().getUserWords().put(getArguments().getInt(KEY_CLICKED_INDEX), words.get(position));
            model.updateArticle();
            dismiss();
        });

        recycler.setAdapter(adapter);

    }

}
