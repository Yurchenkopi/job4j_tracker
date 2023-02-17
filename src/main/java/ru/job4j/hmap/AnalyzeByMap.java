package ru.job4j.hmap;

import java.util.*;

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
        Map<Subject, Integer> data = new LinkedHashMap<>();
        List<Label> rsl = new ArrayList<>();
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                data.computeIfPresent(s, (k, v) -> v + s.score());
                data.putIfAbsent(s, s.score());
            }
        }
        for (Subject  s : data.keySet()) {
            rsl.add(new Label(
                    s.name(),
                    (double) data.get(s) / pupils.size()
                    ));
        }
        return rsl;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> rsl = new LinkedList<>();
        for (Pupil p : pupils) {
            int sum = 0;
            for (Subject s : p.subjects()) {
                sum += s.score();
            }
            rsl.add(new Label(
                    p.name(),
                    sum
            ));
        }
        rsl.sort(Comparator.naturalOrder());
        return rsl.get(rsl.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<Subject, Integer> data = new LinkedHashMap<>();
        List<Label> rsl = new ArrayList<>();
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                data.computeIfPresent(s, (k, v) -> v + s.score());
                data.putIfAbsent(s, s.score());
            }
        }
        for (Subject  s : data.keySet()) {
            rsl.add(new Label(
                    s.name(),
                    data.get(s)
            ));
        }
        rsl.sort(Comparator.naturalOrder());
        return rsl.get(rsl.size() - 1);
    }

    public record Subject(String name, int score) {
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Subject subject = (Subject) o;
            return Objects.equals(name, subject.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
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
