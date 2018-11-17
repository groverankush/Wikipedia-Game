package com.ankushgrover.letswiki.viewmodel;

import android.arch.lifecycle.ViewModel;

/**
 * Created by Ankush Grover(ankushgrover02@gmail.com) on 16/8/18.
 */
public class MainViewModel extends ViewModel {
    private boolean isDifficult;
    private int gameScore;

    public boolean isDifficult() {
        return isDifficult;
    }

    public void setDifficult(boolean difficult) {
        isDifficult = difficult;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }
}
