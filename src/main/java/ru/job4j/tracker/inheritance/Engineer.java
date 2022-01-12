package ru.job4j.tracker.inheritance;

public class Engineer {
    private int experience;

    public Engineer() {

    }

    public Engineer(int experience) {
        super();
        this.experience = experience;
    }

    public boolean isGoodEngineer(int experience) {
        return this.experience > 5;
    }
}
