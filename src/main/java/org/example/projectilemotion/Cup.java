package org.example.projectilemotion;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Cup extends Group {
    private final double width = 100;
    private final double height = 120;
    private final double wall = 10;


    private HitBox[] hitBoxes;

    public Cup (double x, double y){
        setLayoutX(x);
        setLayoutY(y);

        Polygon mainPartOfCup = new Polygon(0.0, 10.0, width, 10.0, width - 15, height, 15.0, height);

        mainPartOfCup.setFill(Color.RED);
        mainPartOfCup.setStroke(Color.DARKRED);
        mainPartOfCup.setStrokeWidth(2);

        Rectangle stripe = new Rectangle(8, 30, width - 16, 12);

        stripe.setFill(Color.HOTPINK);
        stripe.setOpacity(0.5);

        Ellipse rim = new Ellipse(width / 2, 10, width / 2 + 4, 12);
        rim.setFill(Color.WHITE);


        Ellipse opening = new Ellipse(width / 2, 10, width / 2 - wall, 7);
        opening.setFill(Color.rgb(128, 0, 32));

        getChildren().addAll(mainPartOfCup, stripe, rim, opening);

        hitBoxes = new HitBox[3];

        // Left wall
        hitBoxes[0] = new HitBox(
                x, y,
                new double[]{0, wall},
                new double[]{10, height},
                false, this
        );

        // Right wall
        hitBoxes[1] = new HitBox(
                x, y,
                new double[]{width - wall, width},
                new double[]{10, height},
                false, this
        );

        // Scoring area inside the cup
        hitBoxes[2] = new HitBox(
                x, y,
                new double[]{wall + 10, width - wall - 10},
                new double[]{20, 30},
                true, this
        );

    }

    public HitBox[] getHitBoxes() {
        return hitBoxes;
    }


}
