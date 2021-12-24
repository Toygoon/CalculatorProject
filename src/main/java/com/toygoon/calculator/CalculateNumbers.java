/* Calculator in JavaFX
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package com.toygoon.calculator;

import static com.toygoon.calculator.AlertController.*;

public class CalculateNumbers {
    // 연산자 목록
    private static final String[] OPERANDS = {"PLUS", "MINUS", "MULTIPLE", "DIVIDE", "MOD", "AND", "OR", "NOR", "XOR", "SHIFTLEFT", "SHIFTRIGHT"};
    // 특수 함수 목록
    private static final String[] ALL_FUNCTIONS = {"SIN", "COS", "TAN", "FACTORIAL", "SQRT", "SECANT", "COSECANT", "COTANGENT", "COMMONLOG", "NATURALLOG", "COMPLEMENT", "LOG2"
                                                    ,"HEXA", "BINARY", "POWER"};


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

    /* 팩토리얼 계산 메서드 */
    public static String getFactorial(String input) {
        String result = null;

        // 실수인 경우와 정수인 경우를 구분
        if(input.contains(".")) {
            double d = Double.parseDouble(input), res = 1;

            for(double i=1; i<=d; i++)
                res *= i;

            result = Double.toString(res);
        } else if(Integer.parseInt(input) == 0) {
            // 0과의 곱인 경우 0을 반환, 나머지 경우는 1부터 계산함
            result = "0";
        } else {
            int a = Integer.parseInt(input), res = 1;

            for (int i = 1; i <= a; i++)
                res *= i;

            result = Integer.toString(res);
        }

        // 범위를 넘어간 경우, 오류 출력
        // 입력 값은 0이 아니지만, 출력 값은 0인 경우 무한임
        if(!(input.equals("0")) && result.equals("0")) {
            showError("Factorial of " + input + " is not a number.");
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
            case "AND" -> "&";
            case "OR" -> "|";
            case "XOR" -> "^";
            case "SHIFTLEFT" -> "<<";
            case "SHIFTRIGHT" -> ">>";
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
            case "SECANT":
                return "sec(" + num + ") = ";
            case "COSECANT":
                return "csc(" + num + ") = ";
            case "COTANGENT":
                return "cot(" + num + ") = ";
            case "COMMONLOG":
                return "log(" + num + ") = ";
            case "NATURALLOG":
                return "ln(" + num + ") = ";
            case "HEXA":
                return "hex(" + num + ") = ";
            case "BINARY":
                return "bin(" + num + ") = ";
            case "POWER":
                return "square(" + num + ") = ";
        }

        return null;
    }

    public static String calcFunctResults(String first, String funct) {
        // 피연산자의 값을 Double로 캐스팅
        double a = Double.parseDouble(first);
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
            case "SECANT" -> result = 1 / Math.sin(a);
            case "COSECANT" -> result = 1 / Math.cos(a);
            case "COTANGENT" -> result = 1 / Math.tan(a);
            case "COMMONLOG" -> result = Math.log10(a);
            case "NATURALLOG" -> result = Math.log(a);
            case "COMPLEMENT" -> {
                return Integer.toString(~(Integer.parseInt(first)));
            }
            case "HEXA" -> {
                return Integer.toHexString(Integer.parseInt(first));
            }
            case "BINARY" -> {
                return Integer.toBinaryString(Integer.parseInt(first));
            }
            case "POWER" -> result = Math.pow(a, 2);
        }

        // 결과가 무한인 경우, 예외 처리
        if(Double.isInfinite(result)) {
            showError("Calculation from " + first + "is infinity value.");
            return "0";
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
            showError("Can't divide with 0.");
            return "0";
        }

        // 연산자에 따라 연산 방식을 달리함
        switch(operand) {
            case "PLUS" -> result = a+b;
            case "MINUS" -> result = a-b;
            case "MULTIPLE" -> result = a*b;
            case "DIVIDE" -> result = a/b;
            case "MOD" -> result = a%b;
            case "AND" -> {
                return Integer.toString(Integer.parseInt(first) & Integer.parseInt(second));
            }
            case "OR" -> {
                return Integer.toString(Integer.parseInt(first) | Integer.parseInt(second));
            }
            case "XOR" -> {
                return Integer.toString(Integer.parseInt(first) ^ Integer.parseInt(second));
            }
            case "SHIFTLEFT" -> {
                return Integer.toString(Integer.parseInt(first) << Integer.parseInt(second));
            }
            case "SHIFTRIGHT" -> {
                return Integer.toString(Integer.parseInt(first) >> Integer.parseInt(second));
            }
            default -> result = 0;
        }

        // Int와 Double을 구분하여 String 형식으로 저장
        if((int) result == result)
            return String.valueOf((int) result);
        else
            return String.valueOf(result);

    }

}
