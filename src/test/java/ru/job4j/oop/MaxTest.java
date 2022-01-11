package ru.job4j.oop;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaxTest {

    @Test
    public void whenMax1to3then3() {
        int first = 1;
        int second = 3;
        int expected = 3;
        int rsl = Max.max(first, second);
        assertEquals(expected, rsl);
    }

    @Test
    public void whenMax1to0to10then10() {
        int first = 1;
        int second = 0;
        int third = 10;
        int expected = 10;
        int rsl = Max.max(first, second, third);
        assertEquals(expected, rsl);
    }

    @Test
    public void whenMax1to3to0toMinus10then3() {
        int first = 1;
        int second = 3;
        int third = 0;
        int fourth = -10;
        int expected = 3;
        int rsl = Max.max(first, second, third, fourth);
        assertEquals(expected, rsl);
    }
}