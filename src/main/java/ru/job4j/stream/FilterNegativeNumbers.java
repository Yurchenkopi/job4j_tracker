package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterNegativeNumbers {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(160, -3, -10, -4, 4, 0, 12, 45, 76, -100, 150);
        List<Integer> positive = numbers.stream().filter(num -> num > 0).collect(Collectors.toList());
        positive.forEach(System.out::println);
    }
}