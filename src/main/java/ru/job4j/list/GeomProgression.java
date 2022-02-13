package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class GeomProgression {
    public static int generateAndSum(int first, int denominator, int count) {
        List<Integer> geomPr = new ArrayList<>(count);
        int rsl = 0;
        for (int i = 1; i <= count; i++) {
            geomPr.add(first * (int) Math.pow(denominator, i - 1));
        }
        for (int num : geomPr) {
            rsl += num;
        }
        return rsl;
    }

    public static void printInfo(int first, int denominator, int count) {
        List<Integer> geomPr = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            geomPr.add(first * (int) Math.pow(denominator, i - 1));
        }
        int index = 0;
        for (int num : geomPr) {
            System.out.println("Элемент с индексом [" + index + "] - " + num);
            index++;
        }
        System.out.println("Сумма всех элементов равняется: " + generateAndSum(first, denominator, count));
    }

    public static void main(String[] args) {
        printInfo(1, 2, 4);
    }
}
