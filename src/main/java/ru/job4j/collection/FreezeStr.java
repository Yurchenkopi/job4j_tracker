package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        Map<Character, Integer> data = new HashMap<>();
        boolean rsl = true;
        for (int i = 0; i < left.length(); i++) {
            data.computeIfPresent(left.charAt(i), (k, v) -> v + 1);
            data.putIfAbsent(left.charAt(i), 1);
        }
        for (int i = 0; i < right.length(); i++) {
            data.computeIfPresent(right.charAt(i), (k, v) -> v - 1);
        }
        if (left.length() == right.length()) {
            for (Character ch : data.keySet()) {
                if (data.get(ch) != 0) {
                    rsl = false;
                }
            }
        } else {
            rsl = false;
        }

        return rsl;
    }
}