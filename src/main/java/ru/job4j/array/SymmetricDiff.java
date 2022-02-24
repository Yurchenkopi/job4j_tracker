package ru.job4j.array;

import java.util.Arrays;

public class SymmetricDiff {
    public static int[] diff(int[] left, int[] right) {
        int length = left.length + right.length;
        int[] tempRsl = Arrays.copyOf(left, length);
        System.arraycopy(right, 0, tempRsl, left.length, right.length);
        int size = length;
        int leftSize = left.length;
        for (int i = 0; i < leftSize; i++) {
            int buf = tempRsl[i];
            for (int j = leftSize; j < size; j++) {
                if (buf == tempRsl[j]) {
                    System.arraycopy(tempRsl, j + 1, tempRsl, j, tempRsl.length - 1 - j);
                    System.arraycopy(tempRsl, i + 1, tempRsl, i, tempRsl.length - 1 - i);
                    size -= 2;
                    leftSize--;
                    i--;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOf(tempRsl, size)));
        return Arrays.copyOf(tempRsl, size);
    }
}

