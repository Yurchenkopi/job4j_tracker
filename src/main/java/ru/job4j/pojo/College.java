package ru.job4j.pojo;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Ivanov Julio Ivanovich");
        student.setGroup("MT11-111");
        student.setAdmisDate("2011-07-07");
        System.out.println(student.getFio() + " was admitted to " + student.getGroup() + " on " + student.getAdmisDate() + ".");
    }
}