package com.ankushgrover.letswiki.ui.wiki.spans;

import android.support.annotation.NonNull;
import android.text.style.ClickableSpan;
import android.view.View;

import com.ankushgrover.letswiki.data.model.Word;

/**
 * Created by Ankush Grover on 15-11-2018.
 */
public class ClickSpan extends ClickableSpan {

    // The word which is added to the span.
    private Word word;
    private OnWordClickListener listener;

    public ClickSpan(@NonNull Word word, @NonNull OnWordClickListener listener) {

        this.word = word;
        this.listener = listener;
    }

    @Override
    public void onClick(@NonNull View widget) {
        listener.onWordClick(word);
    }

    public interface OnWordClickListener {
        void onWordClick(Word word);
    }

}
