package ru.job4j.array;

import java.util.Arrays;

public class Anomaly {
    public static int[][] found(int[] data, int up, int down) {
        int[][] result = new int[2][];
        int count = 0;
        for (int i = 0; i < 2; i++) {
            int index = 0;
            result[i] = new int[data.length];
            for (int j = 0; j < data.length; j++) {
                if (data[j] >= up && i == 0) {
                    result[i][index] = j;
                    index++;
                } else if (data[j] <= down && i == 1) {
                    result[i][index] = j;
                    index++;
                }
            }
            count = index != 0 ? count + 1 : count;
            result[i] = index == 1 ? Arrays.copyOf(result[i], index + 1) : Arrays.copyOf(result[i], index);
            if (index == 1) {
                result[i][1] = result[i][0];
            }
        }
        return Arrays.copyOf(result, count);
    }
}
