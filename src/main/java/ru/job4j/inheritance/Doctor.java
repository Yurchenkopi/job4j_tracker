package ru.job4j.inheritance;

public class Doctor extends Profession {

    private int experience;
    private int timeOfVisit;

    public Doctor(String name, String surname, String education, String birthday, int experience, int timeOfVisit) {
        super(name, surname, education, birthday);
        this.experience = experience;
        this.timeOfVisit = timeOfVisit;
    }

    public int expKoef() {
        return (this.experience < 3) ? 100 : 300;
    }

    public int costOfVisit(int timeOfVisit) {
        return timeOfVisit * this.expKoef();

    }

}
