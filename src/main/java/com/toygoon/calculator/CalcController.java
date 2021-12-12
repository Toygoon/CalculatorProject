package com.toygoon.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.Stack;

import static com.toygoon.calculator.CalculateNumbers.*;

public class CalcController {
    //private String inputText = "";
    private String operand = "";
    private String prevNum = "";
    private String fullExpr = "";
    private boolean isOperating = false;
    private boolean isPending = false;
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
            fullExpr = "";
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
    /*
        if(data.equals("CLEAR")) {
            field_calc.setText("");
            prevNum = "";
            operand = "";
            if(!(list_history.getItems().get(listTop).equals("")))
                list_history.getItems().remove(listTop--);
        } else if(isOperands(data)) {
            if(isOperating) {
                switch(operand) {

                }
            } else {
                prevNum = field_calc.getText();
                operand = data;
                field_calc.setText("");
                list_history.getItems().add(listTop, prevNum + " " + getOperands(operand));
                isOperating = true;
            }
        } else {
            field_calc.setText(field_calc.getText() + data);
        }
        */
        /*
        if(data.equals("CLEAR")) {
            inputText = "";
            operand = "";
            prevNum = "";
            fullExpr = "";
            if(isOperating)
                list_history.getItems().remove(listTop);
            isOperating = false;
        } else if (isOperands(data)) {
            fullExpr += inputText + " " + getOperands(data) + " ";
            if(isOperating) {
                list_history.getItems().remove(listTop);
                inputText = calcResults(prevNum, inputText, operand);
                fullExpr += "= " + inputText;
                isDone = true;
                isOperating = false;
            } else {
                isOperating = true;
                operand = data;
                prevNum = inputText;
                inputText = "";
            }
            if(!(fullExpr.equals("")))
                list_history.getItems().add(listTop, fullExpr);
        } else {
            inputText += data;
        }

        if(isDone) {
            fullExpr = "";
            listTop++;
            isDone = false;
        }
        list_history.scrollTo(list_history.getItems().size());
        field_calc.setText(inputText);

         */
    }
}
