package ru.job4j.tracker.inheritance;

public class Dentist extends Doctor{
    private boolean isOrthodontist;

    public Dentist (boolean isOrthodontist) {
        super();
        this.isOrthodontist = isOrthodontist;
    }

    public boolean isPatientStrong(Patient patient){
        return true;
    }
}
