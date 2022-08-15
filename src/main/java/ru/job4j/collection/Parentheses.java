package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;

public class Parentheses {
    public static boolean valid(char[] data) {
        List<Character> chars = new ArrayList<>();
        boolean rsl = false;
        for (Character ch : data) {
           chars.add(ch);
        }
        if (chars.size() % 2 == 0 && chars.get(0) == '(') {
            int i = 0;
            int j = 0;
            for (Character ch : chars) {
                if (ch == '(') {
                    i++;
                } else if (ch == ')') {
                    j++;
                }
            }
            System.out.println("i = " + i + "; j = " + j);
            rsl = i == j;
        }
        return rsl;
    }
}

