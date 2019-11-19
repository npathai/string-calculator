package com.npathai.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    private static final String DEFAULT_SEPARATORS = "[,\\n]";

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
        return Arrays.stream(splitArguments(input))
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

    private String[] splitArguments(String input) {
        String separator = DEFAULT_SEPARATORS;

        if (containsCustomSeparator(input)) {
            String[] tokens = input.split("\\n");
            separator = tokens[0].substring(2);

            Pattern regex = Pattern.compile("\\[(.*?)\\]");
            Matcher matcher = regex.matcher(separator);
            List<String> separators = new ArrayList<>();
            while (matcher.find()) {
                separators.add(matcher.group(1));
            }

            if (separators.size() == 1) {
                separator = Pattern.quote(separators.get(0));
            } else if (separators.size() > 1) {

                separator = String.format("[%s]", separators.stream()
                        .map(each -> each.charAt(0))
                        .map(String::valueOf)
                        .collect(Collectors.joining(",")));
            } else {
                separator = Pattern.quote(separator);
            }
            input = tokens[1];
        }
        return input.split(separator);
    }

    private boolean containsCustomSeparator(String input) {
        return input.startsWith("//");
    }
}
