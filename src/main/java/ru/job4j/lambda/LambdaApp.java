package ru.job4j.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LambdaApp {

    public static void main(String[] args) {

        BiFunction<Integer, Integer, Integer> func = action(1);
        BiFunction<Integer, Integer, Integer> func2 = action1(1);
        int a = func.apply(6, 5);
        int b = func2.apply(func.apply(6, 5), 5);
        System.out.println(b);          // 11

        int c = action(2).apply(8, 2);
        System.out.println(c);          // 6
    }

    private static BiFunction<Integer, Integer, Integer> action(int number) {
        switch (number) {
            case 1:
                return (x, y) -> x + y;
            case 2:
                return (x, y) -> x - y;
            case 3:
                return (x, y) -> x * y;
            default:
                return (x, y) -> 0;
        }
    }

    private static BiFunction<Integer, Integer, Integer> action1(int number) {
        switch (number) {
            case 1:
                return (x, y) -> x * y;
            default:
                return (x, y) -> 0;
        }
    }
}