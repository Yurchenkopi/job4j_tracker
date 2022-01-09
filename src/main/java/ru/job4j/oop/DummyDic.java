package ru.job4j.oop;

public class DummyDic {
    public String engToRus(String eng) {
        String translation;
        if (eng.equals("Dog")) {
            translation = "Собака";
        } else if (eng.equals("Cat")) {
            translation = "Кошка";
        } else {
            translation = "Неизвестное слово";
        }
        return translation;
    }

    public static void main(String[] args) {
        DummyDic dictionary = new DummyDic();
        String[] data = { "Dog", "Cat", "Frog" };
        for (String eng : data) {
            System.out.println(dictionary.engToRus(eng) + ": " + eng);
        }
    }
}
