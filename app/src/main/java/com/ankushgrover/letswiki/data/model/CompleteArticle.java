package com.ankushgrover.letswiki.data.model;

import java.util.ArrayList;
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
    // UNUSED
    private ArrayList<String> userWords;
    private String wikiText;

    public CompleteArticle(String title, String imageUrl) {
        this.title = title;
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

    public ArrayList<String> getUserWords() {
        return userWords;
    }

    public void setUserWords(ArrayList<String> userWords) {
        this.userWords = userWords;
    }

    public String getWikiText() {
        return wikiText;
    }

    public void setWikiText(String wikiText) {
        this.wikiText = wikiText;
    }

    public HashSet<Integer> getMissingWordIndexes() {
        return missingWordIndexes;
    }

    public void setMissingWordIndexes(HashSet<Integer> missingWordIndexes) {
        this.missingWordIndexes = missingWordIndexes;
    }
}
