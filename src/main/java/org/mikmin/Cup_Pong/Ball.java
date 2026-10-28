package org.mikmin.Cup_Pong;

import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Ball {
    private Circle circle;
    private int startingX;
    private int startingY;
    private double velocity;
    private double XVelocity;
    private double YVelocity;
    private double angle;
    private boolean isOverlapping;
    private final static double YAccel = 98;
    private static SequentialTransition path = new SequentialTransition();
    private double simX;
    private double simY;

    public Ball(int startingX, int startingY) {
        circle = new Circle(0,0,10);
        this.startingX = startingX;
        this.startingY = startingY;
        this.velocity = 0;
        this.XVelocity = 0;
        this.YVelocity = 0;
        this.angle = 0;
        this.isOverlapping = false;
        this.simX = startingX;
        this.simY = startingY;
    }

    public Ball(int startingX, int startingY, double velocity, double angle) {
        circle = new Circle(0,0,10);
        this.startingX = startingX;
        this.startingY = startingY;
        setVelocity(velocity);
        setAngle(angle);
        this.isOverlapping = false;
        this.simX = startingX;
        this.simY = startingY;
        circle.setTranslateX(startingX);
        circle.setTranslateY(startingY);
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
        this.XVelocity = velocity * Math.cos(Math.toRadians(angle));
        this.YVelocity = -velocity * Math.sin(Math.toRadians(angle));
    }

    public void setAngle(double angle) {
        if (angle < 360 && angle >= 0) {
            this.angle = angle;
        }   else if (angle >= 360) {
            while (angle >= 360) {
                angle -= 360;
            }
            this.angle = angle;
        } else {
            while (angle < 0) {
                angle += 360;
            }
            this.angle = angle;
        }

        this.XVelocity = velocity * Math.cos(Math.toRadians(angle));
        this.YVelocity = -velocity * Math.sin(Math.toRadians(angle));
    }

    public void setOverlapping(boolean overlapping) {
        isOverlapping = overlapping;
    }

    public void nextPosition() {
        double time = 0.1;
        double finalYVelocity = YVelocity + YAccel * time;
        double finalY =  (simY
                                + YVelocity * time
                                + 0.5 * YAccel * time * time);
       double finalX =  (simX
                                + XVelocity * time);

        TranslateTransition tt = new TranslateTransition(new Duration(time * 1000),circle);
       tt.setToX(finalX);
        tt.setToY(finalY);
        tt.setInterpolator(Interpolator.LINEAR);

        path.getChildren().add(tt);

        YVelocity = finalYVelocity;
        simY = finalY;
    simX = finalX;
    }

    public void play() {
        path.play();
    }

    public int getStartingX() {
        return startingX;
    }

    public int getStartingY() {
        return startingY;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getXVelocity() {
        return XVelocity;
    }

    public double getYVelocity() {
        return YVelocity;
    }

    public double getAngle() {
        return angle;
    }

    public boolean isOverlapping() {
        return isOverlapping;
    }

    public Circle getCircle() {
        return circle;
    }
}
