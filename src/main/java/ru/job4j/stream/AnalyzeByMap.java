package ru.job4j.stream;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyzeByMap {

    public static double averageScore(List<Pupil> pupils) {
        return pupils.stream().flatMap(pupil -> pupil.getSubjects().stream())
                .mapToInt(Subject::getScore)
                .average()
                .orElse(0D);
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        return pupils.stream().flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors
                        .groupingBy(
                                Subject::getName,
                                LinkedHashMap::new,
                                Collectors.averagingDouble(Subject::getScore))).entrySet().stream()
                .map(entry -> new Label(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        return pupils.stream().map(pupil -> new Label(pupil.getName(), pupil.getSubjects().stream()
                        .mapToInt(Subject::getScore)
                        .average()
                        .orElse(0D)))
                .collect(Collectors.toList());
    }

    public static Label bestStudent(List<Pupil> pupils) {
        return pupils.stream().map(pupil -> new Label(pupil.getName(), pupil.getSubjects().stream()
                        .mapToInt(Subject::getScore)
                        .sum()))
                .max(Comparator.comparingDouble(Label::getScore))
                .orElse(null);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        return pupils.stream().flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors
                        .groupingBy(Subject::getName,
                                Collectors.summingDouble(Subject::getScore))).entrySet().stream()
                .map(entry -> new Label(entry.getKey(), entry.getValue()))
                .max(Comparator.comparingDouble(Label::getScore))
                .orElse(null);
    }
}