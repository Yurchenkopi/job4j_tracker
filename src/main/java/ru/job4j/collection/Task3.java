package ru.job4j.collection;

import java.util.*;

public class Task3 {
    public static List<Integer> extractDuplicates(List<Integer> left, List<Integer> right) {
        Map<Integer, Integer> data = new HashMap<>();
        List<Integer> temp = new ArrayList<>();
        for (Integer i : left) {
            data.put(i, 0);
        }
        for (Integer i : right) {
            data.computeIfPresent(i, (k, v) -> v = 1);
        }
        for (Integer i : data.keySet()) {
            int ii = data.get(i);
            if (ii == 1) {
                temp.add(i);
            }
        }
        return temp;
    }
}
