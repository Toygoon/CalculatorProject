package com.toygoon.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import static com.toygoon.calculator.CalculateNumbers.*;

public class CalcController {
    private String operand = "";
    private String prevNum = "";
    private boolean isOperating = false;
    private boolean isDotPressed = false;
    private int listTop = 0;

    @FXML
    private TextField field_calc;

    @FXML
    private ListView<String> list_history;

    @FXML
    public void numHandler(ActionEvent event) {
        // FXML의 userData를 전달받는 Node, String
        Node node = (Node) event.getSource();
        String data = (String) node.getUserData();

        if(data.equals("CLEAR")) {
            operand = "";
            prevNum = "";
            if(isOperating) {
                list_history.getItems().remove(listTop);
            }
            isOperating = false;
            field_calc.setText("");
        } else if(data.equals("EQUAL")) {
            if(isOperating) {
                String tmp = list_history.getItems().get(listTop);
                String result = calcResults(prevNum, field_calc.getText(), operand);
                list_history.getItems().remove(listTop);
                list_history.getItems().add(listTop, tmp + field_calc.getText() + " = " + result);
                field_calc.setText(result);

                isOperating = false;
                listTop++;
            }
        } else if(data.equals("DOT")) {
            if(!isDotPressed) {
                field_calc.setText(field_calc.getText() + ".");
                isDotPressed = true;
            }
        } else if(isOperands(data)) {
            if(isOperating) {
                if(prevNum.equals("")) {
                    operand = data;
                } else {
                    String tmp = list_history.getItems().get(listTop);
                    String result = calcResults(prevNum, field_calc.getText(), operand);
                    list_history.getItems().remove(listTop);
                    list_history.getItems().add(listTop, tmp + field_calc.getText() + " = " + result);
                    field_calc.setText(result);

                    isOperating = false;
                    listTop++;
                }
            } else {
                prevNum = field_calc.getText();
                operand = data;
                field_calc.setText("");
                isOperating = true;
                list_history.getItems().add(listTop, prevNum + " " + getOperands(operand));
            }

        } else {
            field_calc.setText(field_calc.getText() + data);
        }
        list_history.scrollTo(listTop);
    }
}
