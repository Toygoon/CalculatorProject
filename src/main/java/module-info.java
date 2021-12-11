module com.toygoon.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.toygoon.calculator to javafx.fxml;
    exports com.toygoon.calculator;
}