package ru.job4j.oop;

public class Student {
    public void music() {
        System.out.println("tra-ta-taa");
    }

    public void song() {
        System.out.println("I believe I can fly");
    }

    public static void main(String[] args) {
        Student petya = new Student();
        for (int i = 0; i < 3; i++) {
            petya.music();
            petya.song();
        }
    }
}
