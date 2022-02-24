package ru.job4j.array;

import java.util.Arrays;

public class Split {
    public static char[][] split(char[] str, char c) {
        char[][] result = new char[str.length / 2][];
        int commonCount = 1;
        int index = 0;
        int bufLength = str.length;
        result[commonCount - 1] = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            if (str[i] == c) {
                result[commonCount - 1] = Arrays.copyOf(result[commonCount - 1], index);
                bufLength = bufLength - result[commonCount - 1].length - 1;
                commonCount++;
                index = 0;
                result[commonCount - 1] = new char[bufLength];
                continue;
            }
            result[commonCount - 1][index] = str[i];
            index++;
        }
        result[commonCount - 1] = Arrays.copyOf(result[commonCount - 1], index);
        return Arrays.copyOf(result, commonCount);
    }
}