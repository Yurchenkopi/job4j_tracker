package ru.job4j.oop;

public class Cat {

    private String food;
    private String name;

    public void show() {
        System.out.println(this.name + " ate " + this.food + ".");
    }

    public void eat(String meat) {
        this.food = meat;
    }

    public void giveNick(String nick) {
        this.name = nick;
    }

    public static void main(String[] args) {
        Cat gav = new Cat();
        Cat black = new Cat();
        gav.giveNick("Gav");
        gav.eat("kotleta");
        gav.show();
        black.giveNick("Blacky");
        black.eat("fish");
        black.show();
    }
}
