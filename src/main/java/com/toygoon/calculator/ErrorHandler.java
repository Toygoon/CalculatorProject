/* Calculator in JavaFX
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package com.toygoon.calculator;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ErrorHandler extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Anti-Aliasing 적용
        System.setProperty("prism.lcdtext", "false");
        // 시스템 폰트로 D2Coding 적용
        Font.loadFont(getClass().getResourceAsStream("/res/D2Coding-Ver1.3.2-20180524.ttf"), 10);

        // FXMLLoader를 통해 Layout의 FXML을 불러옴
        FXMLLoader fxmlLoader = new FXMLLoader(CalcMain.class.getResource("calculator.fxml"));
        // Scene을 통해 Window 생성
        Scene scene = new Scene(fxmlLoader.load(), 300, 100);

        // Scene의 각종 정보 설정 (타이틀, 스타일, 사이즈 재조정 가능여부, 현재 Scene)
        primaryStage.setTitle("Error");
        primaryStage.initStyle(StageStyle.UTILITY);
        //stage.setResizable(false);
        primaryStage.setScene(scene);

        // launch() 호출 시 작동할 show()
        primaryStage.show();
    }
}
