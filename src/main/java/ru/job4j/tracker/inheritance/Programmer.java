package ru.job4j.tracker.inheritance;

public class Programmer extends Engineer {
    private String[] progrLang;

    public Programmer(String[] progrLang) {
        super();
        this.progrLang = progrLang;
    }

    public boolean isKnownLang(String str) {
        boolean rsl = false;
        for (int i = 0; i < this.progrLang.length; i++) {
            if (this.progrLang[i].equals(str)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }
}
