module com.toygoon.calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.pixelduke.fxskins;

    opens com.toygoon.calculator to javafx.fxml;
    exports com.toygoon.calculator;
}