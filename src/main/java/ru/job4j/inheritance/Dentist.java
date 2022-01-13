package ru.job4j.inheritance;

public class Dentist extends Doctor {
    private boolean isOrthodontist;

    public Dentist(String name, String surname, String education, String birthday, int experience, int timeOfVisit, boolean isOrthodontist) {
        super(name, surname, education, birthday, experience, timeOfVisit);
        this.isOrthodontist = isOrthodontist;
    }

    public boolean isPatientStrong(Patient patient) {
        return true;
    }
}
