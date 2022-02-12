package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] splitArr1 = o1.split("/");
        String[] splitArr2 = o2.split("/");
        for (int i = 0; i < Math.min(splitArr1.length, splitArr2.length); i++) {
            if (!splitArr1[i].equals(splitArr2[i]) && i == 0) {
                return o2.compareTo(o1);
            } else if (!splitArr1[i].equals(splitArr2[i])) {
                return o1.compareTo(o2);
            }
        }
        return Integer.compare(splitArr1.length, splitArr2.length);
    }
}

