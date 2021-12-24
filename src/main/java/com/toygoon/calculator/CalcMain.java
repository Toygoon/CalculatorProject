/* Calculator in JavaFX
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package com.toygoon.calculator;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import static com.toygoon.calculator.AlertController.*;

public class CalcMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Anti-Aliasing 적용
        System.setProperty("prism.lcdtext", "false");
        // 시스템 폰트로 D2Coding 적용
        Font.loadFont(getClass().getResourceAsStream("/res/D2Coding-Ver1.3.2-20180524.ttf"), 10);

        // FXMLLoader를 통해 Layout의 FXML을 불러옴
        FXMLLoader fxmlLoader = new FXMLLoader(CalcMain.class.getResource("calculator.fxml"));

        // Scene을 통해 Window 생성
        Scene scene = new Scene(fxmlLoader.load(), 640, 800);

        // Scene의 각종 정보 설정 (타이틀, 스타일, 사이즈 재조정 가능여부, 현재 Scene)
        stage.setTitle("Calculator");
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        // Icon을 설정
        stage.getIcons().add(new Image("file:resources/icons/21283780921537355429-512.png"));
        // 종료 확인을 설정
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                confirmExit(event);
            }
        });

        // 현재의 Scene을 설정
        stage.setScene(scene);

        // launch() 호출 시 작동할 show()
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
