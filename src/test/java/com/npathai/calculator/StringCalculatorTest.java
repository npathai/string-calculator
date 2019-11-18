package com.npathai.calculator;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringCalculatorTest {

    @Test
    public void additionReturnsZeroWhenPassedEmptyString() {
        StringCalculator calculator = new StringCalculator();
        int sum = calculator.add("");
        assertThat(sum, is(0));
    }

    @Test
    public void sumOfSingleNumberIsNumberItself() {
        StringCalculator calculator = new StringCalculator();

        int sum = calculator.add("4");
        assertThat(sum, is(4));
        sum = calculator.add("5");
        assertThat(sum, is(5));
        sum = calculator.add("6");
        assertThat(sum, is(6));
    }

    private class StringCalculator {

        public int add(String input) {
            if (input.isEmpty()) {
                return 0;
            }
            return Integer.parseInt(input);
        }
    }
}
