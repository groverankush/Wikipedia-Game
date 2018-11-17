package com.ankushgrover.letswiki.utils;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.data.model.CompleteArticle;
import com.ankushgrover.letswiki.data.model.Word;
import com.ankushgrover.letswiki.ui.wiki.spans.ClickSpan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Ankush Grover on 14-11-2018.
 */
public class Utils {

    private Utils() {
    }

    public static Word[] splitWords(String paragraph) {

        String s[] = paragraph.split(" ");
        Word words[] = new Word[s.length];
        int startIndex = 0;
        for (int i = 0; i < s.length; i++) {
            words[i] = new Word(startIndex, startIndex + s[i].length(), i, s[i]);
            startIndex += s[i].length() + 1;
        }
        return words;
    }

    public static SpannableString makeResultParagraph(Context context, CompleteArticle article) {

        SpannableStringBuilder builder = new SpannableStringBuilder();

        for (Word word : article.getWords()) {
            SpannableString string = new SpannableString(word.getWord());
            if (article.getMissingWordIndexes().contains(word.getWordIndex()))
                string.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, android.R.color.holo_green_dark)), 0, word.getWord().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(string);
            builder.append(" ");
        }
        return SpannableString.valueOf(builder);
    }

    public static SpannableString makeParagraph(Context context, CompleteArticle article, ClickSpan.OnWordClickListener listener) {

        Word[] words = Arrays.copyOf(article.getWords(), article.getWords().length);
        HashSet<Integer> missingWords = article.getMissingWordIndexes();
        HashMap<Integer, Word> userWords = article.getUserWords();


        // Create raw paragraph
        int startIndex = 0;
        for (int i = 0; i < words.length; i++) {

            String temp = words[i].getWord();
            if (missingWords.contains(words[i].getWordIndex()) && !userWords.containsKey(i)) {
                temp = getDash();
                words[i] = new Word(startIndex, startIndex + temp.length(), i, temp, Word.SpecialCase.MISSING);
            } else if (missingWords.contains(words[i].getWordIndex()) && userWords.containsKey(i)) {
                temp = userWords.get(i).getWord();
                words[i] = new Word(startIndex, startIndex + temp.length(), i, temp, Word.SpecialCase.FILLED);
            }
            startIndex += temp.length() + 1;
        }


        // Add spans
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (Word word : words) {
            SpannableString string = new SpannableString(word.getWord());
            if (word.getSpecialCase() == Word.SpecialCase.MISSING) {
                string.setSpan(new ClickSpan(word, listener), 0, word.getWord().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (word.getSpecialCase() == Word.SpecialCase.FILLED) {
                string.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorAccent)), 0, word.getWord().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            builder.append(string);
            builder.append(" ");
        }


        return SpannableString.valueOf(builder);


    }

/*
    public static SpannableString makeParagraph(Context context, Word[] words, HashSet<Integer> missingWords, HashMap<Integer, Word> userWords, ClickSpan.OnWordClickListener listener) {


        // Create raw paragraph
        int startIndex = 0;
        for (int i = 0; i < words.length; i++) {

            String temp = words[i].getWord();
            if (missingWords.contains(words[i].getWordIndex()) && !userWords.containsKey(i)) {
                temp = getDash();
                words[i] = new Word(startIndex, startIndex + temp.length(), i, temp, Word.SpecialCase.MISSING);
            } else if (missingWords.contains(words[i].getWordIndex()) && userWords.containsKey(i)) {
                temp = userWords.get(i).getWord();
                words[i] = new Word(startIndex, startIndex + temp.length(), i, temp, Word.SpecialCase.FILLED);
            }
            startIndex += temp.length() + 1;
        }


        // Add spans
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (Word word : words) {
            SpannableString string = new SpannableString(word.getWord());
            if (word.getSpecialCase() == Word.SpecialCase.MISSING) {
                string.setSpan(new ClickSpan(word, listener), 0, word.getWord().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (word.getSpecialCase() == Word.SpecialCase.FILLED) {
                string.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorAccent)), 0, word.getWord().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            builder.append(string);
            builder.append(" ");
        }


        return SpannableString.valueOf(builder);
    }
*/


    private static String getDash() {
        return "__________";
    }


    public static ArrayList<Word> getMissingWords(LiveData<CompleteArticle> data) {
        CompleteArticle article = data.getValue();
        assert article != null;
        ArrayList<Word> words = new ArrayList<>();
        HashSet<Integer> usedWordIndexes = new HashSet<>();
        for (Word word : article.getUserWords().values()) {
            usedWordIndexes.add(word.getWordIndex());
        }

        for (Integer index : article.getMissingWordIndexes()) {
            if (!usedWordIndexes.contains(index))
                words.add(article.getWords()[index]);
        }

        return words;
    }

    public static int calculateScore(CompleteArticle article) {
        int score = 0;
        for (Integer index : article.getUserWords().keySet()) {
            if (article.getWords()[index].equals(article.getUserWords().get(index)))
                ++score;
        }
        return score;
    }


    /*private static String getDashes(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append("_");
        }
        return builder.toString();
    }*/



/*    public void fun(){


        StringBuilder paraBuilder = new StringBuilder(paragraph.trim());
        paraBuilder.append("\n");
        int index = 0;
        int startIndex = 0;

        StringBuilder builder = new StringBuilder();
        while (index < paraBuilder.length()) {
            if (paraBuilder.charAt(index) == ' ') {
                words.add(new Word(startIndex, index - 1, builder.toString()));

                while (index < paraBuilder.length() && paraBuilder.charAt(++index) != ' ') {
                    builder.append(index);
                }

            } else {
                builder.append(paraBuilder.charAt(index++));
            }
        }
    }*/
}
