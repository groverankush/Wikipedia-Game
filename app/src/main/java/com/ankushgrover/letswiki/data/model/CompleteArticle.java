package com.ankushgrover.letswiki.data.model;

import java.util.ArrayList;

/**
 * Created by Ankush Grover on 12-11-2018.
 */
public class CompleteArticle {
    private String title;
    private String imageUrl;
    private String completeText;
    private ArrayList<String> words;
    private ArrayList<String> userWords;

    public CompleteArticle(String title) {
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

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public ArrayList<String> getUserWords() {
        return userWords;
    }

    public void setUserWords(ArrayList<String> userWords) {
        this.userWords = userWords;
    }
}
