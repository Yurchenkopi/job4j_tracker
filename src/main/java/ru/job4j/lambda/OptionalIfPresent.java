package ru.job4j.lambda;

import java.util.Optional;

public class OptionalIfPresent {

    public static void ifPresent(int[] data) {
        max(data).ifPresent(s -> System.out.println("Max: " + s));
    }

    private static int maxAr(int[] data) {
        int buf = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > buf) {
                buf = data[i];
            }
        }
        return buf;
    }

    private static Optional<Integer> max(int[] data) {
        return data.length != 0 ? Optional.of(maxAr(data)) : Optional.empty();
    }
}