package com.npathai.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringCalculator {
    private final ArgumentSplitter argumentSplitter = new ArgumentSplitter(',', '\n');

    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }

        List<Integer> arguments = filterArguments(
                checkNoNegatives(parseArguments(input)),
                (arg) -> arg <= 1000);

        return arguments.stream().mapToInt(Integer::intValue).sum();
    }

    private static List<Integer> filterArguments(List<Integer> arguments, Predicate<Integer> filter) {
        return arguments.stream().filter(filter)
                .collect(Collectors.toList());
    }

    private List<Integer> parseArguments(String input) {
        return Arrays.stream(argumentSplitter.split(input))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<Integer> checkNoNegatives(List<Integer> arguments) {
        List<Integer> negatives = arguments.stream()
                .filter(val -> val < 0)
                .collect(Collectors.toList());

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " + negatives.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")));
        }

        return arguments;
    }
}
