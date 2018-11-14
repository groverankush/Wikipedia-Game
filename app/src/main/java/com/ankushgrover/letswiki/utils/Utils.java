package com.ankushgrover.letswiki.utils;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import com.ankushgrover.letswiki.data.model.Word;
import com.ankushgrover.letswiki.ui.wiki.spans.ClickSpan;

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

    public static SpannableString makeParagraph(Word[] words, HashSet<Integer> missingWords, HashSet<Integer> userWords, boolean isAnswer) {

        SpannableStringBuilder paraBuilder = new SpannableStringBuilder();

        for (Word word : words) {
            SpannableString string;
            if (missingWords.contains(word.getWordIndex()) && !userWords.contains(word.getWordIndex())) {
                string = new SpannableString(getDashes(word.getWord().length()));
                string.setSpan(new ClickSpan(word), word.getStartIndex(), word.getEndIndex(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (missingWords.contains(word.getWordIndex()) && userWords.contains(word.getWordIndex())) {
                // TODO LOGIC BUSTED! DO SOMETHING
            }
        }

        return null;
    }

    private static String getDashes(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append("_");
        }
        return builder.toString();
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
