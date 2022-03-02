package ru.job4j.lambda;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MathFuncTest {

    MathFunc function = new MathFunc();

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSQRFunctionThenSQRResults() {
        List<Double> result = function.diapason(1, 4, x -> x * x + 2 * x + 1);
        List<Double> expected = Arrays.asList(4D, 9D, 16D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenPOWFunctionThenPOWResults() {
        List<Double> result = function.diapason(0, 4, x -> Math.pow(3, x));
        List<Double> expected = Arrays.asList(1D, 3D, 9D, 27D);
        assertThat(result, is(expected));
    }
}