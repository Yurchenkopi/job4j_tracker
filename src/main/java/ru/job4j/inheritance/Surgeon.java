package ru.job4j.inheritance;

public class Surgeon extends Doctor {

    private int numsOfOperations;

    public Surgeon(String name, String surname, String education, String birthday, int experience, int priceOfVisit, int timeOfVisit, int numsOfOperations) {
        super(name, surname, education, birthday, experience, priceOfVisit, timeOfVisit);
        this.numsOfOperations = numsOfOperations;
    }

    public boolean operationSuccess(Patient patient) {
        return true;
    }

}
