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

        // this is just testing,attempting to get the animation to work correctly
        Ball testBall = new Ball(350,350,100,90);

        // a for loop that generates 100 lines of the animation, which is a bit overkill but works, the amount of times/ how many anims are generated can easily be changed
        for (int i = 0; i < 100; i++) {
            testBall.nextPosition();
        }

        //test button
        Button playButton = new Button("Fire!");

        playButton.setOnAction(e -> {
            // the .play() method should only be run once the full animation is generated
            testBall.play();
        });

        // basic javaFX structure, feel free to change to a fxml format
        Pane pane = new Pane(testBall.getCircle(), playButton);
        Scene scene = new Scene(pane, 700, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
