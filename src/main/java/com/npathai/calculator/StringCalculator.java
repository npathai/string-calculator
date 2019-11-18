package com.npathai.calculator;

import java.util.Arrays;

public class StringCalculator {

    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }
        return Arrays.stream(input.split("[,\\n]"))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
