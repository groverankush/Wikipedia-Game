package com.ankushgrover.letswiki.utils;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.data.model.Word;
import com.ankushgrover.letswiki.ui.wiki.spans.ClickSpan;

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

    public static SpannableString makeParagraph(Word[] words, HashSet<Integer> missingWords, HashMap<Integer, Word> userWords, ClickSpan.OnWordClickListener listener) {


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
            if (word.getSpecialCase() == Word.SpecialCase.MISSING)
                string.setSpan(new ClickSpan(word, listener), 0, word.getWord().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            else if (word.getSpecialCase() == Word.SpecialCase.FILLED) {
                string.setSpan(new ForegroundColorSpan(R.attr.colorAccent) , 0, word.getWord().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            builder.append(string);
            builder.append(" ");
        }


        return SpannableString.valueOf(builder);
    }

    /*private static String getDashes(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append("_");
        }
        return builder.toString();
    }*/

    private static String getDash() {
        return "_____";
    }

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
