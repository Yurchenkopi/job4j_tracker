package ru.job4j.list;

import java.util.*;

public class Alphabet {
    public static String reformat(String s) {
        List<Character> ch = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            ch.add(s.charAt(i));
        }
        Collections.sort(ch);
        for (char sym : ch) {
            str.append(sym);
        }
        return str.toString();
    }
}
