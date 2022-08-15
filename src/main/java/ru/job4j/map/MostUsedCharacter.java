package ru.job4j.map;

import java.util.*;

public class MostUsedCharacter {
    public static char getMaxCount(String str) {
        Map<Character, Integer> sym = new TreeMap<>();
        char maxNumKey = ' ';
        int maxNumVal = 0;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char currChar = str.charAt(i);
            if (currChar != ' ' && sym.containsKey(currChar)) {
                sym.computeIfPresent(currChar, (key, value) -> value = value + 1);
            } else if (currChar != ' ') {
                sym.computeIfAbsent(currChar, key -> 1);
            }
        }
        for (char ch : sym.keySet()) {
            maxNumKey = maxNumVal < sym.get(ch) ? ch : maxNumKey;
            maxNumVal = maxNumVal < sym.get(ch) ? sym.get(ch) : maxNumVal;
            System.out.println(ch + "--->" + sym.get(ch));
        }
        return maxNumKey;
    }

    public static void main(String[] args) {

        System.out.println("Наибольшое количество раз встречается символ -->" + getMaxCount("Мама мыла раму"));
    }
}