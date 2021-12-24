/* Calculator in JavaFX
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package com.toygoon.calculator;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertController {
    /* 에러를 출력하는 메서드 */
    public static void showError(String msg) {
        // Alert 클래스 사용
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setResizable(false);
        // 타이틀, 헤더 지정 후 파라미터로 메시지를 받아 출력
        alert.setTitle("Error");
        alert.setHeaderText("Calculation Error");
        alert.setContentText(msg);

        // alert
        alert.showAndWait();
    }

    /* 프로그램 종료를 묻는 메서드 */
    public static void confirmExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setResizable(false);
        alert.setTitle("Close");
        alert.setHeaderText("Closing confirmation");
        alert.setContentText("Do you want to close calculator?");

        Optional<ButtonType> obt = alert.showAndWait();
        ButtonType bt = obt.orElse(ButtonType.CANCEL);

        if(bt == ButtonType.OK) {
            System.out.println("OK");
        } else {
            System.out.println("cancel");
        }
    }
}
