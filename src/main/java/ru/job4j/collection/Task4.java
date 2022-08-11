package ru.job4j.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task4 {
    public static List<Integer> extractUnique(List<Integer> left, List<Integer> right) {
        Map<Integer, Integer> data = new HashMap<>();
        List<Integer> temp = new ArrayList<>();
        for (Integer i : left) {
            data.put(i, 0);
        }
        for (Integer i : right) {
            data.computeIfPresent(i, (k, v) -> v = 1);
            data.computeIfAbsent(i, k -> 0);
        }
        for (Integer i : data.keySet()) {
            int ii = data.get(i);
            if (ii == 0) {
                temp.add(i);
            }
        }
        return temp;
    }
}
