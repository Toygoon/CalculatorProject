package com.toygoon.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CalcMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CalcMain.class.getResource("calculator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 900);
        stage.setTitle("Calculator");
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
