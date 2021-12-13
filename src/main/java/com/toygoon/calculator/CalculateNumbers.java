package com.toygoon.calculator;

public class CalculateNumbers {
    // 연산자 목록
    private static final String[] OPERANDS = {"PLUS", "MINUS", "MULTIPLE", "DIVIDE", "MOD"};
    // 특수 함수 목록
    private static final String[] ALL_FUNCTIONS = {"SIN", "COS", "TAN", "FACTORIAL", "SQRT"};
    // 특수 함수 목록이면서, 단항 연산자인 연산자
    private static final String[] SINGLE_FUNCTIONS = {"SIN", "COS", "TAN", "FACTORIAL", "SQRT"};

    /* 연산자를 구분하는 메서드 */
    public static boolean isOperands(String input) {
        for(String s : OPERANDS) {
            if(input.equals(s))
                return true;
        }

        return false;
    }

    /* 특수 함수를 구분하는 메서드 */
    public static boolean isFunctions(String input) {
        for(String s : ALL_FUNCTIONS) {
            if(input.equals(s))
                return true;
        }

        return false;
    }

    /* 특수 함수 중, 단항 연산자를 확인하는 메서드 */
    public static boolean isSingleFunctions(String input) {
        for(String s : SINGLE_FUNCTIONS) {
            if(input.equals(s))
                return true;
        }

        return false;
    }

    /* 팩토리얼 계산 메서드 */
    public static String getFactorial(String input) {
        String result = null;

        // 실수인 경우와 정수인 경우를 구분
        if(input.contains(".")) {
            double d = Double.parseDouble(input), res = 1;

            for(double i=1; i<=d; i++)
                res *= i;

            result = Double.toString(res);
        } else {
            int a = Integer.parseInt(input), res = 1;

            for(int i=1; i<=a; i++)
                res *= i;

            result = Integer.toString(res);
        }

        return result;
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

    /* FXML의 연산자 값을 실제 기호로 변환하는 메서드 */
    public static String getFunctExpr(String num, String data) {
        switch(data) {
            case "SIN":
                return "sin(" + num + ") = ";
            case "COS":
                return "cos(" + num + ") = ";
            case "TAN":
                return "tan(" + num + ") = ";
            case "FACTORIAL":
                return num + "! = ";
            case "SQRT":
                return "sqrt(" + num + ") = ";
        }

        return null;
    }

    public static String calcFunctResults(String first, String second, String funct) {
        // 단항 연산자 구분
        if(isSingleFunctions(funct)) {
            second = "0";
        }

        // 첫 번째 피연산자, 두 번째 피연산자의 값을 Double로 캐스팅
        double a = Double.parseDouble(first);
        double b = Double.parseDouble(second);
        // 결과를 저장할 변수
        double result = 0;

        /* 단항 연산자 연산 */
        switch(funct) {
            case "SIN" -> result = Math.sin(a);
            case "COS" -> result = Math.cos(a);
            case "TAN" -> result = Math.tan(a);
            case "FACTORIAL" -> {
                return getFactorial(first);
            }
            case "SQRT" -> result = Math.sqrt(a);
        }

        // Int와 Double을 구분하여 String 형식으로 저장
        if((int) result == result)
            return String.valueOf((int) result);
        else
            return String.valueOf(result);
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

        // 0으로는 나눌 수 없음
        if(operand.equals("DIVIDE") && (a == 0 || b == 0)) {
            return "0";
        }

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
