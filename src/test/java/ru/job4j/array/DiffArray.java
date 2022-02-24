package ru.job4j.array;

import java.util.Arrays;

public class DiffArray {
    public static int[] diff(int[] left, int[] right) {
        int[] rsl = left;
        int size = left.length;
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < right.length; j++) {
                if (left[i] == right[j]) {
                    System.arraycopy(rsl, i + 1, rsl, i, left.length - i - 1);
                    size--;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(rsl));
        return Arrays.copyOf(rsl, size);
    }
}