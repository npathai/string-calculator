package com.npathai.calculator;

import java.util.Arrays;

public class StringCalculator {

    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }
        String[] summands = input.split(",");
        if (summands.length == 1) {
            return Integer.parseInt(summands[0]);
        } else {
            return Arrays.stream(summands).mapToInt(Integer::parseInt).sum();
        }
    }
}
