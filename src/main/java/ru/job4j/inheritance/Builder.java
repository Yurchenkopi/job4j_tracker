package ru.job4j.inheritance;

public class Builder extends Engineer {
    private boolean universalWorker;

    public Builder(String name, String surname, String education, String birthday, int experience, boolean universalWorker) {
        super(name, surname, education, birthday, experience);
        this.universalWorker = universalWorker;
    }

    public int buldingTime(House house) {
     return 365;
    }
}
