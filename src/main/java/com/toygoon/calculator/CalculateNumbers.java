package com.toygoon.calculator;

import java.util.Stack;

public class CalculateNumbers {
    private static final String[] OPERANDS = {"PLUS", "MINUS", "MULTIPLE", "DIVIDE", "MOD", "NEGATIVE", "EQUAL", "CLEAR"};

    public static boolean isOperands(String input) {
        for(String s : OPERANDS) {
            if(input.equals(s))
                return true;
        }

        return false;
    }

    public static String getOperands(String input) {
        return switch (input) {
            case "PLUS" -> "+";
            case "MINUS" -> "-";
            case "MULTIPLE" -> "×";
            case "DIVIDE" -> "÷";
            case "MOD" -> "%";
            case "EQUAL" -> "=";
            default -> null;
        };
    }

    public static String calcResults(String first, String second, String operand) {
        double a = Double.parseDouble(first);
        double b = Double.parseDouble(second);
        double result = 0;

        switch(operand) {
            case "PLUS" -> result = a+b;
            case "MINUS" -> result = a-b;
            case "MULTIPLE" -> result = a*b;
            case "DIVIDE" -> result = a/b;
            case "MOD" -> result = a%b;
            default -> result = 0;
        }

        if((int) result == result)
            return String.valueOf((int) result);
        else
            return String.valueOf(result);

    }

}
