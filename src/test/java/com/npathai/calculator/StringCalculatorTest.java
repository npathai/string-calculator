package com.npathai.calculator;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class StringCalculatorTest {

    private StringCalculator calculator;

    @Before
    public void createCalculator() {
        calculator = new StringCalculator();
    }

    @Test
    public void additionReturnsZeroWhenPassedEmptyString() {
        int sum = calculator.add("");
        assertThat(sum, is(0));
    }

    @Test
    public void sumOfSingleNumberIsNumberItself() {
        int sum = calculator.add("4");
        assertThat(sum, is(4));
        sum = calculator.add("5");
        assertThat(sum, is(5));
        sum = calculator.add("6");
        assertThat(sum, is(6));
    }

    @Test
    public void returnsSumOfTwoCommaSeparatedNumbers() {
        int sum = calculator.add("1,2");
        assertThat(sum, is(3));
        sum = calculator.add("5,2");
        assertThat(sum, is(7));
    }

    @Test
    public void returnsSumOfArbitraryDelimiterSeparatedNumbers() {
        int sum = calculator.add("1,2,3,4");
        assertThat(sum, is(10));
        sum = calculator.add("1\n2\n3\n4\n5");
        assertThat(sum, is(15));
    }

    @Test
    public void supportsNewLineAndCommaAsDelimiters() {
        int sum = calculator.add("1,2\n3\n4");
        assertThat(sum, is(10));
        sum = calculator.add("1\n2,3,4,5");
        assertThat(sum, is(15));
        sum = calculator.add("1\n2\n3\n4\n6");
        assertThat(sum, is(16));
    }

    @Test
    public void supportsOptionallyChangingSeparatorToCustomValue() {
        int sum = calculator.add("//;\n1;2");
        assertThat(sum, is(3));
        sum = calculator.add("//#\n3#2");
        assertThat(sum, is(5));
    }

}
