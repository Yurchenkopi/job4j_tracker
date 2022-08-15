package ru.job4j.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Concordance {
    public static Map<Character, List<Integer>> collectCharacters(String s) {
        Map<Character, List<Integer>> rsl = new HashMap<>();
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            char currCh = s.charAt(i);
            if (currCh != ' ') {
                rsl.putIfAbsent(currCh, new ArrayList<>());
                rsl.get(currCh).add(index);
                index++;
            }
        }
        return rsl;
    }
}