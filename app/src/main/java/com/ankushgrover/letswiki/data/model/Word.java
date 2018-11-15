package com.ankushgrover.letswiki.data.model;

/**
 * Created by Ankush Grover on 13-11-2018.
 */
public class Word {
    public SpecialCase getSpecialCase() {
        return specialCase;
    }

    public void setSpecialCase(SpecialCase specialCase) {
        this.specialCase = specialCase;
    }

    public enum SpecialCase {
        MISSING, FILLED, NONE
    }

    private int startIndex;
    private int endIndex;
    private int wordIndex;
    private String word;
    private SpecialCase specialCase;

    public Word(int startIndex, int endIndex, int wordIndex, String word) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.wordIndex = wordIndex;
        this.word = word;
        specialCase = SpecialCase.NONE;
    }

    public Word(int startIndex, int endIndex, int wordIndex, String word, SpecialCase specialCase) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.wordIndex = wordIndex;
        this.word = word;
        this.specialCase = specialCase;
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

    public int getWordIndex() {
        return wordIndex;
    }

    public void setWordIndex(int wordIndex) {
        this.wordIndex = wordIndex;
    }
}
