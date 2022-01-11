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
    public void when04to20then4dot47() {
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

    @Test
    public void when4310to8610then7dot07() {
        double expected = 7.07;
        Point a = new Point(4, 3, 10);
        Point b = new Point(8, 6, 5);
        double out = a.distance3d(b);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void when431to825then5dot74() {
        double expected = 5.74;
        Point a = new Point(4, 3, 1);
        Point b = new Point(8, 2, 5);
        double out = a.distance3d(b);
        Assert.assertEquals(expected, out, 0.01);
    }
}