package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int a) {
        return x - a;
    }

    public double divide(int a) {
        return (double) a / x;
    }

    public double sumAllOperation(int a) {
        Calculator calculator = new Calculator();
        return sum(a) + calculator.multiply(a) + minus(a) + calculator.divide(a);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(sum(10));
        System.out.println(calculator.multiply(10));
        System.out.println(minus(10));
        System.out.println(calculator.divide(10));
        System.out.println(calculator.sumAllOperation(10));
    }
}
