package ru.job4j.ex;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class FactTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenLessThan0ThenFinish() {
        new Fact().calc(-1);
    }

    @Test
    public void when5Then120() {
        int fact = new Fact().calc(5);
        assertThat(fact, is(120));
    }
}