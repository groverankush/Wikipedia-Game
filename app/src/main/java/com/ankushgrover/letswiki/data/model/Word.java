package com.ankushgrover.letswiki.data.model;

/**
 * Created by Ankush Grover on 13-11-2018.
 */
public class Word {
    private int startIndex;
    private int endIndex;
    private String word;

    public Word(int startIndex, int endIndex, String word) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.word = word;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
