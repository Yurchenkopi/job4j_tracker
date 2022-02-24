package ru.job4j.array;

public class PositiveOrNegative {
    public static boolean check(int[] data) {
        int neg = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 0) {
                neg++;
            }
        }
        return neg % 2 != 0;
    }
}