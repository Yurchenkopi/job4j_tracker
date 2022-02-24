package ru.job4j.array;

import java.util.Arrays;

public class OrArray {
    public static int[] or(int[] left, int[] right) {
        int length = left.length + right.length;
        int[] tempRsl = Arrays.copyOf(left, length);
        System.arraycopy(right, 0, tempRsl, left.length, right.length);
        int size = length;
        for (int i = 0; i < left.length; i++) {
            int buf = tempRsl[i];
            for (int j = left.length; j < size; j++) {
                if (buf == tempRsl[j]) {
                    System.arraycopy(tempRsl, j + 1, tempRsl, j, tempRsl.length - 1 - j);
                    size--;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOf(tempRsl, size)));
        return Arrays.copyOf(tempRsl, size);
    }
}
