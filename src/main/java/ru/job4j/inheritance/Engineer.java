package ru.job4j.inheritance;

public class Engineer extends Profession {
    private int experience;

    public Engineer(String name, String surname, String education, String birthday, int experience) {
        super(name, surname, education, birthday);
        this.experience = experience;
    }

    public boolean isGoodEngineer(int experience) {
        return this.experience > 5;
    }
}
