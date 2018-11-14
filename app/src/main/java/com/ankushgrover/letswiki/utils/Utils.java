package com.ankushgrover.letswiki.utils;

import com.ankushgrover.letswiki.data.model.Word;

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
            words[i] = new Word(startIndex, startIndex + s[i].length(), s[i]);
            startIndex += s[i].length() + 1;
        }
        return words;
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
