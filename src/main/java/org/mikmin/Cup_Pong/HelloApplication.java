package org.mikmin.Cup_Pong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Ball testBall = new Ball(350,350,100,90);


        for (int i = 0; i < 100; i++) {
            testBall.nextPosition();
        }
        Button playButton = new Button("Fire!");

        playButton.setOnAction(e -> {
            testBall.play();
        });

        Pane pane = new Pane(testBall.getCircle(), playButton);
        Scene scene = new Scene(pane, 700, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
