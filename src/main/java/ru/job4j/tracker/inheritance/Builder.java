package ru.job4j.tracker.inheritance;

public class Builder extends Engineer {
    private boolean universalWorker;

    public Builder(boolean universalWorker) {
        super();
        this.universalWorker = universalWorker;
    }

    public int buldingTime(House house) {
     return 365;
    }
}
