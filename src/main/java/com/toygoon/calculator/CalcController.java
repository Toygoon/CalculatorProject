package com.toygoon.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import static com.toygoon.calculator.CalculateNumbers.*;

public class CalcController {
    // 연산자로 지정될 변수
    private String operand = "";
    // 이전의 피연산자로 지정될 변수
    private String prevNum = "";
    // 피연산자와 연산자가 한 번씩 입력된 후, 연산 중의 상태를 나타내는 변수
    private boolean isOperating = false;
    // 소숫점은 한 번만 입력되어야 하므로, 이를 확인하는 변수
    private boolean isDotPressed = false;
    // 연산 기록을 기록하는 List에서 사용될 Top 값
    private int listTop = 0;

    // 계산 결과가 나오는 TextField
    @FXML
    private TextField field_calc;

    // 계산 결과를 기록하여 저장하는 ListView, Generic은 String 형태
    @FXML
    private ListView<String> list_history;

    /* 각 버튼을 입력받아 상황에 따라 계산하는 메서드 */
    @FXML
    public void numHandler(ActionEvent event) {
        // FXML의 userData를 전달받는 Node, String
        Node node = (Node) event.getSource();
        String data = (String) node.getUserData();

        if(data.equals("CLEAR")) {
            /* C를 눌렀을 경우 */

            // 각 요소 초기화
            operand = "";
            prevNum = "";
            // 연산 중인 자료가 있다면, 초기화 하고 리스트에서 삭제
            if(isOperating) {
                list_history.getItems().remove(listTop);
            }
            isOperating = false;
            // 리스트에 있는 내용 삭제
            field_calc.setText("");
        } else if(data.equals("EQUAL")) {
            /* =를 눌렀을 경우 */
            // 만약 연산 중이라면, 연산 결과를 출력하는 작업 수행
            if(isOperating) {
                // (list_history용) 이전까지의 연산 결과를 저장하는 값
                String tmp = list_history.getItems().get(listTop);
                // 결과를 result 변수에 저장
                String result = calcResults(prevNum, field_calc.getText(), operand);
                // 이전의 연산 중인 식을 목록에서 수정하기 위해, 삭제 후 새로 추가
                list_history.getItems().remove(listTop);
                list_history.getItems().add(listTop, tmp + field_calc.getText() + " = " + result);

                // 연산 결과를 저장
                field_calc.setText(result);

                // 연산이 한 번 끝났으므로, isOperating 변수를 false로 설정하여 연산 중인 상태를 종료
                isOperating = false;
                // listTop을 증가시켜 새로운 리스트 순번을 이용하게 함
                listTop++;
            }
        } else if(data.equals("DOT")) {
            /* .을 눌렀을 경우 */
            // 소숫점이 여러 개 존재 할 수 없으므로, isDotPressed 변수를 이용하여 확인
            if(!isDotPressed) {
                // 만약 isDotPressed가 false라면, 소숫점을 입력
                field_calc.setText(field_calc.getText() + ".");
                // 입력 이후 isDotPressed 변수를 초기화
                isDotPressed = true;
            }
        } else if(isOperands(data)) {
            /* 연산자를 입력 받은 경우 */
            // 이미 연산 중인 연산자가 존재하고, 새로운 값을 입력 받은 후에는 이전 연산을 완료하도록 함
            if(isOperating) {
                if(field_calc.getText().equals("")) {
                    // 이전에 입력한 피연산자가 없다면, 연산자를 수정하는 작업만 함
                    operand = data;
                } else {
                    // 이전에 입력한 피연산자가 존재하면, 이전의 연산을 마무리함
                    // 위의 EQUAL-isOperating 작업과 일부 동일
                    String tmp = list_history.getItems().get(listTop);
                    String result = calcResults(prevNum, field_calc.getText(), operand);
                    list_history.getItems().remove(listTop);
                    list_history.getItems().add(listTop, tmp + field_calc.getText() + " = " + result);

                    // 이전 연산의 결과를 새로운 연산 결과 식으로 만드는 작업
                    // field_calc는 초기화
                    field_calc.setText("");
                    // 새로운 연산 작업 수행
                    listTop++;
                    // 피연산자는 이전의 연산 작업 결과
                    prevNum = result;
                    // 새로운 연산자는 현재 입력 중인 연산자
                    operand = data;
                    // 이는 새로운 연산 작업이 수행 중인 상태
                    isOperating = true;
                    // 목록에 추가
                    list_history.getItems().add(listTop, prevNum + " " + getOperands(operand));
                }
            } else {
                if(!(field_calc.getText().equals(""))) {
                    // 그렇지 않은 경우, 이전의 피연산자와 추가하는 작업
                    prevNum = field_calc.getText();
                    // 연산자를 받아옴
                    operand = data;
                    // 새로운 피연산자를 받아오기 위해, field를 비움
                    field_calc.setText("");
                    // 연산 중인 것으로 지정
                    isOperating = true;
                    // 현재까지의 기록을 저장
                    list_history.getItems().add(listTop, prevNum + " " + getOperands(operand));
                }
            }
        } else {
            /* 숫자를 입력 받았을 경우 */
            // 이전에 입력된 값과 버튼이 눌린 값을 합침
            field_calc.setText(field_calc.getText() + data);
        }
        // 자동 스크롤을 위한 방식
        list_history.scrollTo(listTop);
    }

    /* Backspace 구현 메서드 */
    @FXML
    public void bsHandler() {
        // 현재의 값을 가져옴
        String prv = field_calc.getText();
        // 현재 입력되어 있는 값의 length
        int length = prv.length();

        // length가 0이면 return
        if(length == 0)
            return;

        // StringBuilder를 이용하여 맨 마지막의 글자를 지워줌
        field_calc.setText(new StringBuilder(prv).deleteCharAt(length-1).toString());
    }
}
