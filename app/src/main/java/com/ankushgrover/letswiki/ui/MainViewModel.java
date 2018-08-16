package com.ankushgrover.letswiki.ui;

import android.arch.lifecycle.ViewModel;

/**
 * Created by Ankush Grover(ankushgrover02@gmail.com) on 16/8/18.
 */
public class MainViewModel extends ViewModel {
    private boolean isDifficult;

    public boolean isDifficult() {
        return isDifficult;
    }

    public void setDifficult(boolean difficult) {
        isDifficult = difficult;
    }
}
