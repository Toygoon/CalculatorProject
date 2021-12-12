package com.toygoon.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Stack;

import static com.toygoon.calculator.CalculateNumbers.*;

public class CalcController {
    private String inputText = "";
    private String operand = "";
    private String prevNum = "";
    private String fullExpr = "";
    private boolean isOperating = false;
    private boolean isDone = false;
    private int listTop = 0;

    @FXML
    private TextField field_calc;

    @FXML
    private ListView<String> list_history;

    @FXML
    public void numHandler(ActionEvent event) {
        Node node = (Node) event.getSource();
        String data = (String) node.getUserData();

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
                fullExpr += inputText;
                isDone = true;
                isOperating = false;
            } else {
                isOperating = true;
                operand = data;
                prevNum = inputText;
                inputText = "";
            }
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
    }
}
