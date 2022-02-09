package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rsl = -1;
        int index = -1;
        char[] leftArray = new char[left.length()];
        char[] rightArray = new char[right.length()];
        for (int i = 0; i < left.length(); i++) {
            leftArray[i] = left.charAt(i);
        }
        for (int i = 0; i < right.length(); i++) {
            rightArray[i] = right.charAt(i);
        }
        if (left.length() < right.length()) {
            for (int i = 0; i < left.length(); i++) {
                int tempRsl = Character.compare(leftArray[i], rightArray[i]);
                if (tempRsl != 0) {
                    rsl = tempRsl;
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < right.length(); i++) {
                int tempRsl = Character.compare(leftArray[i], rightArray[i]);
                if (tempRsl != 0) {
                    rsl = tempRsl;
                    index = i;
                    break;
                }
            }
        }
        if (left.length() == right.length() && index == -1) {
            rsl = 0;
        } else if (left.length() < right.length() && index == -1) {
            rsl = -right.charAt(index + 1);
        } else if (index == -1) {
            rsl = left.charAt(index + 1);
        }
        return rsl;
    }
}