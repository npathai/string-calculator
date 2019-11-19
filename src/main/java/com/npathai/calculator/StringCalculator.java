package com.npathai.calculator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
    private static final String DEFAULT_SEPARATORS = "[,\\n]";

    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }

        List<Integer> arguments = Arrays.stream(splitArguments(input))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        checkNoNegatives(arguments);

        return arguments.stream().mapToInt(Integer::intValue).sum();
    }

    private static void checkNoNegatives(List<Integer> arguments) {
        List<String> negatives = arguments.stream().filter(val -> val < 0)
                .map(String::valueOf)
                .collect(Collectors.toList());

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " + String.join(",", negatives));
        }
    }

    private String[] splitArguments(String input) {
        String separator = DEFAULT_SEPARATORS;

        if (input.startsWith("//")) {
            String[] tokens = input.split("\\n");
            separator = tokens[0].substring(2);
            input = tokens[1];
        }
        return input.split(separator);
    }
}
