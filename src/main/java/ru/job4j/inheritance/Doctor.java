package ru.job4j.inheritance;

public class Doctor extends Profession {

    private int experience;
    private int priceOfVisit;
    private int timeOfVisit;

    public Doctor() {

    }

    public Doctor(int experience, int priceOfVisit, int timeOfVisit) {
        super();
        this.experience = experience;
        this.priceOfVisit = priceOfVisit;
        this.timeOfVisit = timeOfVisit;
    }

    public int expKoef() {
        return (this.experience < 3) ? 1 : 2;
    }

    public int costOfVisit(int timeOfVisit) {
        return timeOfVisit * this.priceOfVisit * this.expKoef();

    }

}
