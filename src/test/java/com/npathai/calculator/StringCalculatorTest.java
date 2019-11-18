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
    

    private class StringCalculator {

        public int add(String input) {
            return 0;
        }
    }
}
