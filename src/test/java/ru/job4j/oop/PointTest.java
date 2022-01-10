package ru.job4j.oop;

import org.junit.Test;

import org.junit.Assert;

public class PointTest {
    @Test
    public void when00to20then2() {
        double expected = 2;
        Point a = new Point(0, 0);
        Point b = new Point(2, 0);
        double out = a.distance(b);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void when00to40then4() {
        double expected = 4;
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        double out = a.distance(b);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void when04to20thenSQRT20() {
        double expected = 4.47;
        Point a = new Point(0, 4);
        Point b = new Point(2, 0);
        double out = a.distance(b);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void when43to86then5() {
        double expected = 5;
        Point a = new Point(4, 3);
        Point b = new Point(8, 6);
        double out = a.distance(b);
        Assert.assertEquals(expected, out, 0.01);
    }
}