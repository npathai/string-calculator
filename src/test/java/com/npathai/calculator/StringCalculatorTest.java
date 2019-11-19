package com.npathai.calculator;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class StringCalculatorTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    private StringCalculator calculator;

    @Before
    public void createCalculator() {
        calculator = new StringCalculator();
    }

    @Test
    public void sumOfEmptyStringIsZero() {
        addAndAssert("", 0);
    }

    @Test
    @Parameters({"4", "5", "6"})
    public void sumOfSingleNumberIsNumberItself(int singleNumber) {
        addAndAssert(String.valueOf(singleNumber), singleNumber);
    }

    @Test
    @Parameters(method = "listOfTwoCommaSeparatedNumbers")
    public void returnsSumOfTwoCommaSeparatedNumbers(String input, int expectedSum) {
        addAndAssert(input, expectedSum);
    }

    public Object[] listOfTwoCommaSeparatedNumbers() {
        return new Object[] {
                new Object[] {"1,2", 3},
                new Object[] {"5,2", 7}
        };
    }

    @Test
    @Parameters(method = "listOfArbitraryDelimiterSeparatedNumbers")
    public void returnsSumOfArbitraryDelimiterSeparatedNumbers(String input, int expectedSum) {
        addAndAssert(input, expectedSum);
    }

    public Object[] listOfArbitraryDelimiterSeparatedNumbers() {
        return new Object[] {
                new Object[] {"1,2,3,4", 10},
                new Object[] {"1\n2\n3\n4\n5", 15}
        };
    }

    @Test
    @Parameters(method = "listOfNumbersSeparatedByNewLineAndComma")
    public void supportsNewLineAndCommaAsDelimiters(String input, int expectedSum) {
        addAndAssert(input, expectedSum);
    }

    public Object[] listOfNumbersSeparatedByNewLineAndComma() {
        return new Object[] {
                new Object[] {"1,2\n3\n4", 10},
                new Object[] {"1\n2,3,4,5", 15},
                new Object[] {"1\n2\n3\n4\n6", 16}
        };
    }

    @Test
    @Parameters(method = "listOfNumbersWithCustomSeparatorCharacter")
    public void supportsOptionallyChangingSeparatorToCustomValue(String input, int expectedSum) {
        addAndAssert(input, expectedSum);
    }

    private void addAndAssert(String input, int expectedSum) {
        assertThat(calculator.add(input), is(expectedSum));
    }

    public Object[] listOfNumbersWithCustomSeparatorCharacter() {
        return new Object[] {
                new Object[] {"//;\n1;2", 3},
                new Object[] {"//#\n3#2", 5},
                new Object[] {"//*\n3*2", 5}
        };
    }

    @Test
    public void negativesAreNotAllowed() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage(formatNegativesNotAllowedMessage(-2, -3));

        calculator.add("1,-2,-3");
    }

    private String formatNegativesNotAllowedMessage(int... negatives) {
        return "negatives not allowed: " + Arrays.stream(negatives)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }

    @Test
    @Parameters(method = "listOfNumbersWithSomeValuesGreaterThan1000")
    public void filtersNumbersStrictlyGreaterThan1000(String input, int expectedSum) {
        addAndAssert(input, expectedSum);
    }

    public Object[] listOfNumbersWithSomeValuesGreaterThan1000() {
        return new Object[] {
                new Object[] {"6,1001,5,1002,2001,1000", 1011},
                new Object[] {"1000,1001,0", 1000},
                new Object[] {"1001,1002,2001", 0},
        };
    }

    @Test
    @Parameters(method = "listOfNumbersWithArbitraryLengthSeparators")
    public void supportsArbitraryLengthSeparator(String input, int expectedSum) {
        addAndAssert(input, expectedSum);
    }

    public Object[] listOfNumbersWithArbitraryLengthSeparators() {
        return new Object[] {
                new Object[] {"//[***]\n1***2***4", 7},
                new Object[] {"//[~~~]\n1~~~2~~~4", 7},
                new Object[] {"//[~#!]\n1~#!2~#!4", 7}
        };
    }

    @Test
    @Parameters(method = "listOfNumbersWithMultipleSingleLengthCustomSeparators")
    public void supportsMultipleSingleLengthCustomSeparators(String input, int expectedSum) {
        addAndAssert(input, expectedSum);
    }

    public Object[] listOfNumbersWithMultipleSingleLengthCustomSeparators() {
        return new Object[] {
                new Object[] {"//[%][^]\n1^2%3^4", 10},
                new Object[] {"//[$][&]\n1&2$3&4", 10},
        };
    }

    @Test
    @Parameters(method = "listOfNumbersWithMultipleLongerLengthCustomSeparators")
    public void supportsMultipleLongerLengthCustomSeparators(String input, int expectedSum) {
        addAndAssert(input, expectedSum);
    }

    public Object[] listOfNumbersWithMultipleLongerLengthCustomSeparators() {
        return new Object[] {
                new Object[] {"//[foo][bar][baz]\n1foo2bar3baz4", 10},
                new Object[] {"//[fizz][buzz]\n1fizz2buzz3fizz4", 10},
        };
    }
}
