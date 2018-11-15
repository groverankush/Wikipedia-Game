package com.ankushgrover.letswiki.data.model;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Ankush Grover on 12-11-2018.
 */
public class CompleteArticle {
    private String title;
    private String imageUrl;
    private String completeText;
    private Word[] words;
    private HashSet<Integer> missingWordIndexes;
    private HashMap<Integer, Word> userWords;

    public CompleteArticle(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCompleteText() {
        return completeText;
    }

    public void setCompleteText(String completeText) {
        this.completeText = completeText;
    }

    public Word[] getWords() {
        return words;
    }

    public void setWords(Word[] words) {
        this.words = words;
    }

    public HashMap<Integer, Word> getUserWords() {
        return userWords;
    }

    public void setUserWords(HashMap userWords) {
        this.userWords = userWords;
    }

    public HashSet<Integer> getMissingWordIndexes() {
        return missingWordIndexes;
    }

    public void setMissingWordIndexes(HashSet<Integer> missingWordIndexes) {
        this.missingWordIndexes = missingWordIndexes;
    }
}
