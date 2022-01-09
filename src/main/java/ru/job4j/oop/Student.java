package ru.job4j.oop;

public class Student {
    public void music() {
        System.out.println("tra-ta-taa");
    }

    public void song(String song) {
        System.out.println(song);
    }

    public static void main(String[] args) {
        Student petya = new Student();
        String song = "I believe I can fly";
        for (int i = 0; i < 3; i++) {
            petya.music();
            petya.song(song);
        }
    }
}
