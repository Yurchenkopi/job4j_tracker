package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String splitStr1 = o1.split("/")[0];
        String splitStr2 = o2.split("/")[0];
        return splitStr1.compareTo(splitStr2) != 0 ? o2.compareTo(o1) : o1.compareTo(o2);
    }
}

