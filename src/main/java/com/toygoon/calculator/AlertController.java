/* Calculator in JavaFX
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package com.toygoon.calculator;

import javafx.scene.control.Alert;
import javafx.scene.text.Font;

public class AlertController {
    public AlertController() {
        // Anti-Aliasing 적용
        System.setProperty("prism.lcdtext", "false");
        // 시스템 폰트로 D2Coding 적용
        Font.loadFont(getClass().getResourceAsStream("/res/D2Coding-Ver1.3.2-20180524.ttf"), 10);
    }

    public static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Calculation Error");
        alert.setContentText(msg);

        alert.showAndWait();
    }

    public static void confirmExit() {

    }
}
