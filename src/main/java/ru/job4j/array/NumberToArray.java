package ru.job4j.array;

import java.util.Arrays;

public class NumberToArray {
    public static int[] resolve(int number) {
        int size = 0;
        int tmp = number;
        while (tmp != 0) {
            tmp = tmp / 10;
            size++;
        }
        int[] data = new int[size];
        int i = 0;
        while (number != 0) {
            data[i] = number % 10;
            number = number / 10;
            i++;
        }
        return data;
    }
}