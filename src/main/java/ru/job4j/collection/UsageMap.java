package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("1111@ya.ru", "Julio Gonsalez");
        map.put("2222@ya.ru", "Ivan Petrov");
        map.put("3333@ya.ru", "Vasya Pupkin");
        map.put("4444@ya.ru", "Tihon Petrov");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " = " + value);
        }

    }
}
