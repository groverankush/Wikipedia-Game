package com.ankushgrover.letswiki.data.model;

import android.text.TextUtils;

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

    public CompleteArticle(String title, String imageUrl, String completeText, Word[] words, HashSet<Integer> missingWordIndexes, HashMap<Integer, Word> userWords) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.completeText = completeText;
        this.words = words;
        this.missingWordIndexes = missingWordIndexes;
        this.userWords = userWords;
    }

    public static CompleteArticle getEmptyInstance() {
        return new CompleteArticle("", "", "", null, null, null);
    }

    public boolean isEmpty() {
        return (TextUtils.isEmpty(title)
                && TextUtils.isEmpty(imageUrl)
                && TextUtils.isEmpty(completeText)
                && words == null
                && missingWordIndexes == null
                && userWords == null);
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
