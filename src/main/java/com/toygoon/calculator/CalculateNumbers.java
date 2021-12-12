package com.toygoon.calculator;

public class CalculateNumbers {
    // 연산자 목록
    private static final String[] OPERANDS = {"PLUS", "MINUS", "MULTIPLE", "DIVIDE", "MOD", "NEGATIVE"};

    /* 연산자를 구분하는 메서드 */
    public static boolean isOperands(String input) {
        for(String s : OPERANDS) {
            if(input.equals(s))
                return true;
        }

        return false;
    }

    /* FXML의 연산자 값을 실제 기호로 변환하는 메서드 */
    public static String getOperands(String input) {
        return switch (input) {
            case "PLUS" -> "+";
            case "MINUS" -> "-";
            case "MULTIPLE" -> "×";
            case "DIVIDE" -> "÷";
            case "MOD" -> "%";
            default -> null;
        };
    }

    /* 연산을 수행하는 메서드 */
    public static String calcResults(String first, String second, String operand) {
        // 피연산자 중 하나라도 존재하지 않으면, 둘 중 하나의 값을 반환
        if(first.equals("") || second.equals(""))
            return first + second;

        // 첫 번째 피연산자, 두 번째 피연산자의 값을 Double로 캐스팅
        double a = Double.parseDouble(first);
        double b = Double.parseDouble(second);
        // 결과를 저장할 변수
        double result = 0;

        // 연산자에 따라 연산 방식을 달리함
        switch(operand) {
            case "PLUS" -> result = a+b;
            case "MINUS" -> result = a-b;
            case "MULTIPLE" -> result = a*b;
            case "DIVIDE" -> result = a/b;
            case "MOD" -> result = a%b;
            default -> result = 0;
        }

        // Int와 Double을 구분하여 String 형식으로 저장
        if((int) result == result)
            return String.valueOf((int) result);
        else
            return String.valueOf(result);

    }

}
