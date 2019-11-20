package com.npathai.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Internal utility class used to split arguments provided to calculator
 */
class ArgumentSplitter {

    private final String defaultSeparatorString;

    ArgumentSplitter(char... defaultSeparators) {
        StringJoiner joiner = new StringJoiner(",");
        for (char separator : defaultSeparators) {
            joiner.add(String.valueOf(separator));
        }
        defaultSeparatorString = String.format("[%s]", joiner.toString());
    }

    String[] split(String input) {
        if (containsCustomSeparator(input)) {
            return splitWithCustomSeparator(input);
        }
        return input.split(defaultSeparatorString);
    }

    private String[] splitWithCustomSeparator(String input) {
        String[] tokens = input.split("\\n");
        String separator = tokens[0].substring(2);

        List<String> multiSeparators = extractMultiSeparators(separator);

        String formattedSeparator;
        if (!multiSeparators.isEmpty()) {
            formattedSeparator = String.format("%s", multiSeparators.stream()
                    .map(Pattern::quote)
                    .collect(Collectors.joining("|")));
        } else {
            formattedSeparator = Pattern.quote(separator);
        }
        return tokens[1].split(formattedSeparator);
    }

    private List<String> extractMultiSeparators(String separator) {
        Pattern regex = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = regex.matcher(separator);
        List<String> separators = new ArrayList<>();
        while (matcher.find()) {
            separators.add(matcher.group(1));
        }
        return separators;
    }

    private boolean containsCustomSeparator(String input) {
        return input.startsWith("//");
    }
}
