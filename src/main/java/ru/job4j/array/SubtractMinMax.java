package ru.job4j.array;

public class SubtractMinMax {
    public static int calculate(int[] ints) {
        return max(ints) - min(ints);
    }

    private static int max(int[] ints) {
        int buf = ints[0];
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] > buf) {
                buf = ints[i];
            }
        }
        return buf;
    }

    private static int min(int[] ints) {
        int buf = ints[0];
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] < buf) {
                buf = ints[i];
            }
        }
        return buf;
    }

}
