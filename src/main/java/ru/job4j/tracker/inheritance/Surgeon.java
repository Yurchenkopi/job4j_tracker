package ru.job4j.tracker.inheritance;

public class Surgeon extends Doctor {

    private int numsOfOperations;

    public Surgeon(int numsOfOperations) {
        super();
        this.numsOfOperations = numsOfOperations;
    }

    public boolean operationSuccess(Patient patient) {
        return true;
    }

}
