package com.ankushgrover.letswiki.ui.wiki.spans;

import android.support.annotation.NonNull;
import android.text.style.ClickableSpan;
import android.view.View;

import com.ankushgrover.letswiki.data.model.Word;

/**
 * Created by Ankush Grover on 15-11-2018.
 */
public class ClickSpan extends ClickableSpan {

    private Word word;

    public ClickSpan(Word word) {

        this.word = word;
    }

    @Override
    public void onClick(@NonNull View widget) {

    }

}
