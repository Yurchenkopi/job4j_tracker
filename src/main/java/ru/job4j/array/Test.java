package ru.job4j.array;

public class Test {
    public static void main(String[] args) {
        int num = 1234;
        int sum = 0;
        for (int i = 0; i < String.valueOf(num).length(); i++) {
             sum += String.valueOf(num).charAt(i) - '0';
        }
        System.out.println(sum);
    }

}
