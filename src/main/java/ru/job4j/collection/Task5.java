package ru.job4j.collection;

import java.util.*;

public class Task5 {
    public static List<Integer> multiAssign(List<Task> tasks) {
            Map<Integer, List<Integer>> data = new HashMap<>();
            List<Integer> temp = new ArrayList<>();
            for (Task t : tasks) {
                data.computeIfPresent(t.assignId(), (k, v) -> {
                    v.add(t.taskId());
                    return v;
                });
                data.computeIfAbsent(t.assignId(), k -> {
                    List<Integer> d = new ArrayList<>();
                    d.add(t.taskId());
                    return d;
                });
                for (Integer id : data.keySet()) {
                    if (data.get(id).size() > 1) {
                        temp.add(id);
                    }
                }
            }
        return temp;
    }

    public record Task(Integer taskId, Integer assignId) {
    }
}
