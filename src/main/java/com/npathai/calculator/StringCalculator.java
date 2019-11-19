package com.npathai.calculator;

import java.util.Arrays;

public class StringCalculator {

    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }
        return Arrays.stream(splitArguments(input))
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private String[] splitArguments(String input) {
        String separator = "[,\\n]";

        if (input.startsWith("//")) {
            String[] tokens = input.split("\\n");
            separator = tokens[0].substring(2);
            input = tokens[1];
        }
        return input.split(separator);
    }
}
