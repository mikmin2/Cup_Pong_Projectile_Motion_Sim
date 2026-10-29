package org.mikmin.Cup_Pong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


// git repository: https://github.com/mikmin2/Cup_Pong_Projectile_Motion_Sim
// trello link: https://trello.com/b/58tcbji0/cup-pong-java-project
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Ball testBall = new Ball(100, 500, 320, 60);


        Cup[] cups = {
                new Cup(450, 400),
                new Cup(570, 300)
        };


        HitBox[] allHitBoxes =
                new HitBox[cups.length * 3];

        int index = 0;

        for (Cup cup : cups) {
            for (HitBox hitbox : cup.getHitBoxes()) {
                allHitBoxes[index] = hitbox;
                index++;
            }
        }
        Button playButton = new Button("Fire!");

        playButton.setLayoutX(20);
        playButton.setLayoutY(20);


        Pane pane = new Pane();


        pane.getChildren().addAll(cups);
        pane.getChildren().addAll(
                testBall.getCircle(),
                playButton
        );

        // Fire button action
        playButton.setOnAction(e -> {

            // Prevent firing again during this shot
            playButton.setDisable(true);

            boolean collision = false;
            boolean scoringEnabled = false;

            int steps = 0;
            int maxSteps = 200;

            double radius = testBall.getCircle().getRadius();


            while (!collision && steps < maxSteps) {


                testBall.nextPosition();
                steps++;


                double ballX = testBall.getSimX();
                double ballY = testBall.getSimY();


                for (HitBox hitbox : allHitBoxes) {

                    if (hitbox.Iscolliding(
                            ballX,
                            ballY,
                            radius)) {


                        if (hitbox.isScoring()) {

                            scoringEnabled = true;

                            System.out.println(
                                    "Scoring hitbox reached!"
                            );

                        } else {

                            System.out.println(
                                    "Ball hit a cup wall!"
                            );
                        }


                        collision = true;
                        break;
                    }
                }


                if (ballY + radius >= 690) {

                    collision = true;

                    System.out.println(
                            "Ball hit the ground!"
                    );
                }


                if (ballX - radius > 700
                        || ballX + radius < 0) {

                    collision = true;

                    System.out.println(
                            "Ball left the screen!"
                    );
                }
            }


            if (scoringEnabled) {

                System.out.println(
                        "Scoring enabled for this shot!"
                );
            }


            testBall.play();
        });


        Scene scene = new Scene(pane, 700, 700);

        stage.setTitle("Cup Pong");
        stage.setScene(scene);
        stage.show();

    }
}
