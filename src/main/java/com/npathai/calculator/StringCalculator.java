package com.npathai.calculator;

public class StringCalculator {

    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }
        String[] summands = input.split(",");
        if (summands.length == 1) {
            return Integer.parseInt(summands[0]);
        } else {
            return Integer.parseInt(summands[0]) + Integer.parseInt(summands[1]);
        }
    }
}
