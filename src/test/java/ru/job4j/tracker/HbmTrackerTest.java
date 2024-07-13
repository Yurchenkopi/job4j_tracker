package ru.job4j.tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HbmTrackerTest {

    @AfterEach
    public void wipeTable() {
        try (var tracker = new HbmTracker()) {
            if (!tracker.findAll().isEmpty()) {
                tracker.findAll()
                        .forEach(i -> tracker.delete(i.getId()));
            }
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (var tracker = new HbmTracker()) {
            var item = tracker.add(new Item("test Item"));
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenEditItemSuccessfully() {
        try (var tracker = new HbmTracker()) {
            var item = tracker.add(new Item("test Item"));
            tracker.replace(item.getId(), new Item("Edited Item"));
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo("Edited Item");
        }
    }

    @Test
    public void whenDeleteItemSuccessfully() {
        try (var tracker = new HbmTracker()) {
            Item item = tracker.add(new Item("New item"));
            tracker.delete(item.getId());
            assertThat(tracker.findById(item.getId())).isNull();
        }
    }

    @Test
    public void whenAddSeveralItemsAndThenFindItAll() {
        try (var tracker = new HbmTracker()) {
            Item first = tracker.add(new Item("First"));
            Item second = tracker.add(new Item("Second"));
            Item third = tracker.add(new Item("Third"));
            assertThat(tracker.findAll()).isEqualTo(List.of(first, second, third));
        }
    }

    @Test
    public void whenAddSeveralItemsAndThenFindByName() {
        try (var tracker = new HbmTracker()) {
            Item first = tracker.add(new Item("New Test Item"));
            Item second = tracker.add(new Item("New Test Item"));
            Item third = tracker.add(new Item("New Test Item"));
            assertThat(tracker.findByName("New Test Item")).isEqualTo(List.of(first, second, third));
        }
    }
}