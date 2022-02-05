package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Tracker  {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    private int indexOf(int id) {
        int rsl = -1;
        int index = 0;
        for (Item item : items) {
            if (item.getId() == id) {
                rsl = index;
                break;
            }
            index++;
        }
        return rsl;
    }

    public List<Item> findAll() {
        return items;
    }

    public List<Item> findByName(String key) {
        List<Item> itemTemp = new ArrayList<>();
        for (Item item : items) {
            if (key.equals(item.getName())) {
                itemTemp.add(item);
            }
        }
        return itemTemp;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            item.setId(id);
            items.set(index, item);
        }
        return index != -1;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        if (index != -1) {
            items.remove(index);
        }
        return index != -1;
    }
}