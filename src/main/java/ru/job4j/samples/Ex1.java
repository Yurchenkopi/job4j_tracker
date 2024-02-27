package ru.job4j.samples;

import java.io.FileReader;
import java.io.IOException;

public class Ex1 {
    public static void main(String[] args) {
        try (var resource = new FileReader("pom.xml")) {
            throw new RuntimeException("Runtime Exception");
        } catch (IOException e) {
            System.out.println("IO Exception");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
