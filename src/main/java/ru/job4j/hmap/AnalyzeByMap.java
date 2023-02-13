package ru.job4j.hmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnalyzeByMap {

    public static double averageScore(List<Pupil> pupils) {
        int capacity = 0;
        int sum = 0;
        for (Pupil p : pupils) {
            capacity += p.subjects().size();
            for (Subject s : p.subjects()) {
                sum += s.score();
            }
        }
        return (double) sum / capacity;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> rsl = new ArrayList<>();
        for (Pupil p : pupils) {
            int sum = 0;
            for (Subject s : p.subjects()) {
                sum += s.score();
            }
            rsl.add(new Label(
                    p.name(),
                    (double) sum / p.subjects().size()
            ));
        }
        return rsl;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        return List.of();
    }

    public static Label bestStudent(List<Pupil> pupils) {
        return null;
    }

    public static Label bestSubject(List<Pupil> pupils) {
        return null;
    }

    public record Subject(String name, int score) {
    }

    public record Pupil(String name, List<Subject> subjects) {
    }

    public record Label(String name, double score) implements Comparable<Label> {
        @Override
        public int compareTo(Label o) {
            return Double.compare(this.score, o.score);
        }
    }

}
