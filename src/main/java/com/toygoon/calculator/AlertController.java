/* Calculator in JavaFX
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package com.toygoon.calculator;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

import static javafx.scene.control.ButtonBar.ButtonData.*;

public class AlertController {
    /* 에러를 출력하는 메서드 */
    public static void showError(String msg) {
        // Alert 클래스 사용
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // 크기 조정 비활성화
        alert.setResizable(false);
        // 타이틀, 헤더 지정 후 파라미터로 메시지를 받아 출력
        alert.setTitle("Error");
        alert.setHeaderText("Calculation Error");
        alert.setContentText(msg);

        // 확인 버튼 설정
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setText("OK");

        // alert
        alert.showAndWait();
    }

    /* 프로그램 종료를 묻는 메서드 */
    public static void confirmExit(WindowEvent event) {
        // Alert 클래스 사용
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // 크기 조정 비활성화
        alert.setResizable(false);
        // Dialog의 세부 정보 설정
        alert.setTitle("Close");
        alert.setHeaderText("Closing confirmation");
        alert.setContentText("Do you want to close calculator?");

        // 확인, 취소 버튼을 지정
        ButtonType okButton = new ButtonType("OK", YES);
        ButtonType cancelButton = new ButtonType("Cancel", CANCEL_CLOSE);

        // alert Instance에 버튼을 추가
        alert.getButtonTypes().setAll(okButton, cancelButton);
        // ButtonData != YES 일 경우 계속
        alert.showAndWait().ifPresent(type -> {
            if (type.getButtonData() != YES) {
                event.consume();
            }
        });
    }
}
