package ru.job4j.stream;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class OptionalFlatMap {
    public static Optional<Integer> flatMap(List<String> strings) {
        return strings.stream()
                .filter(f -> f.endsWith(".java"))
                .findFirst()
                .flatMap(s -> Optional.of(s.length()));
    }
}