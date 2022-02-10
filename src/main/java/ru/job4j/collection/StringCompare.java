package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rsl = 0;
        int minLength;
        boolean isEqual = false;
        boolean rightLessLeft = false;
        boolean finRsl = false;
        char[] leftArray = new char[left.length()];
        char[] rightArray = new char[right.length()];
        for (int i = 0; i < left.length(); i++) {
            leftArray[i] = left.charAt(i);
        }
        for (int i = 0; i < right.length(); i++) {
            rightArray[i] = right.charAt(i);
        }
        if (left.length() <= right.length()) {
            minLength = left.length();
            isEqual = left.length() == right.length();
        } else {
            minLength = right.length();
            rightLessLeft = true;
        }
        for (int i = 0; i < minLength; i++) {
            int tempRsl = Character.compare(leftArray[i], rightArray[i]);
            if (tempRsl != 0) {
                rsl = tempRsl;
                finRsl = true;
                break;
            }
        }
        if (!finRsl) {
            if (isEqual) {
                rsl = 0;
            } else if (rightLessLeft) {
                rsl = left.charAt(minLength);
            } else {
                rsl = -right.charAt(minLength);
            }
        }
        return rsl;
    }
}