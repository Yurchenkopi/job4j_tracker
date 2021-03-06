package ru.job4j.collection;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JobTest {
    @Test
    public void whenCompatorByDescNameAndDescPrority() {
        Comparator<Job> sortDescByNameAndDescByPriority = new JobDescByName().thenComparing(new JobAscByPriority());
        int rsl = sortDescByNameAndDescByPriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByAscNameAndAscPrority() {
        Comparator<Job> sortAscByNameAndAscByPriority = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = sortAscByNameAndAscByPriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByAscNameAndDescPrority() {
        Comparator<Job> sortAscByNameAndDescByPriority = new JobAscByName().thenComparing(new JobDescByPriority());
        List<Job> tasks = Arrays.asList(
                new Job("Impl task", 0),
                new Job("Fix bug", 4),
                new Job("Fix bug", 1),
                new Job("Fix bug", 3)
        );
        List<Job> expected = Arrays.asList(
                new Job("Fix bug", 4),
                new Job("Fix bug", 3),
                new Job("Fix bug", 1),
                new Job("Impl task", 0)
        );
        List<Job> rsl = new ArrayList<>();
        Collections.sort(tasks, sortAscByNameAndDescByPriority);
        assertThat(tasks, is(expected));
    }

    @Test
    public void whenCompatorByDescNameAndAscPrority() {
        Comparator<Job> sortDescByNameAndAscByPriority = new JobDescByName().thenComparing(new JobAscByPriority());
        List<Job> tasks = Arrays.asList(
                new Job("Fix bug", 4),
                new Job("Fix bug", 3),
                new Job("Fix bug", 1),
                new Job("Impl task", 0)
        );
        List<Job> expected = Arrays.asList(
                new Job("Impl task", 0),
                new Job("Fix bug", 1),
                new Job("Fix bug", 3),
                new Job("Fix bug", 4)
        );
        List<Job> rsl = new ArrayList<>();
        Collections.sort(tasks, sortDescByNameAndAscByPriority);
        assertThat(tasks, is(expected));
    }

    @Test
    public void whenCompatorByAscName() {
        List<Job> tasks = Arrays.asList(
                new Job("Fix bug", 3),
                new Job("Make bug", 0),
                new Job("Have a rest", 1),
                new Job("Impl task", 2)
        );
        List<Job> expected = Arrays.asList(
                new Job("Fix bug", 3),
                new Job("Have a rest", 1),
                new Job("Impl task", 2),
                new Job("Make bug", 0)
                );
        List<Job> rsl = new ArrayList<>();
        Collections.sort(tasks, new JobAscByName());
        assertThat(tasks, is(expected));
    }

    @Test
    public void whenCompatorByDescPrior() {
        List<Job> tasks = Arrays.asList(
                new Job("Fix bug", 3),
                new Job("Make bug", 0),
                new Job("Have a rest", 1),
                new Job("Impl task", 2)
        );
        List<Job> expected = Arrays.asList(
                new Job("Fix bug", 3),
                new Job("Impl task", 2),
                new Job("Have a rest", 1),
                new Job("Make bug", 0)
        );
        List<Job> rsl = new ArrayList<>();
        Collections.sort(tasks, new JobDescByPriority());
        assertThat(tasks, is(expected));
    }
}