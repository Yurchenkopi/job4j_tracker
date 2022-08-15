package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class Article {
    public static boolean generateBy(String origin, String line) {
        Map<String, Integer> data = new HashMap<>();
        boolean rsl = true;
        origin = deleteSym(origin);
        for (String s : origin.split(" ")) {
            data.computeIfPresent(s, (k, v) -> v + 1);
            data.putIfAbsent(s, 1);
        }
        for (String s : data.keySet()) {
            System.out.println(s + " : " + data.get(s) + ";");
        }
        for (String s : line.split(" ")) {
            if (data.containsKey(s)) {
                if (data.get(s) == 0) {
                    rsl = false;
                    break;
                }
                data.computeIfPresent(s, (k, v) -> v - 1);
            } else {
                rsl = false;
                break;
            }
            System.out.println(s + " : " + data.get(s) + ";");
        }
        return rsl;
    }

    public static String deleteSym(String origin) {
        StringBuilder str = new StringBuilder(origin);
        String temp = "";
        System.out.println(origin);
        for (int j = 33; j <= 47; j++) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == j) {
                    if (i == str.length() - 1) {
                        temp = str.substring(0, str.length() - 1);
                        str = new StringBuilder(temp);
                    } else {
                        str.delete(i, i + 1);
                    }
                }
            }
        }
        for (int j = 58; j <= 63; j++) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == j) {
                    if (i == str.length() - 1) {
                        temp = str.substring(0, str.length() - 1);
                        str = new StringBuilder(temp);
                    } else {
                        str.delete(i, i + 1);
                    }
                }
        }

        }
        return str.toString();
    }

    public static void main(String[] args) {
        String str = "Мой дядя самых честных правил, "
                + "Когда не в шутку занемог, "
                + "Он уважать себя заставил "
                + "И лучше выдумать не мог. "
                + "Его пример другим наука; "
                + "Но, боже мой, какая скука "
                + "С больным сидеть и день и ночь, "
                + "Не отходя ни шагу прочь! "
                + "Какое низкое коварство "
                + "Полуживого забавлять, "
                + "Ему подушки поправлять, "
                + "Печально подносить лекарство, "
                + "Вздыхать и думать про себя: "
                + "Когда же черт возьмет тебя!";
        System.out.println(deleteSym(str));
        System.out.println(generateBy(str, "Когда же"));
    }
}
