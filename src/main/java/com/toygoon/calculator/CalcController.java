package com.toygoon.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CalcController {
    @FXML
    private TextField numfield;

    @FXML
    public void numHandler(ActionEvent event) {
        Node node = (Node) event.getSource();
        String data = (String) node.getUserData();

        numfield.setText(data);
    }
}
