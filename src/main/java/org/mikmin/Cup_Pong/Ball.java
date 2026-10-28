package org.mikmin.Cup_Pong;

import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Ball {
    // the visual node of the ball
    private Circle circle;
    // the starting x value
    private int startingX;
    // starting y value
    private int startingY;
    // the inputted velocity, this is one of the two values that the user will input
    private double velocity;
    // internal value for calculations
    private double XVelocity;
    // internal value for calculations
    private double YVelocity;
    // inputted angle in degrees, the other value the user will input
    private double angle;
    // unused boolean that is free if you want to use it
    private boolean isOverlapping;
    // the gravity value of the simulation, feel free to adjust to your liking
    private final static double YAccel = 98;
    // the grouping of transition animations, which plays them in order when .play() is called
    private static SequentialTransition path = new SequentialTransition();
    // internal value for calculations, this is the current final  X position of the ball at the end of the animation
    private double simX;
    // ditto for Y
    private double simY;

    // primary constructor, likely the one that will be used in the actually simulation as velocity and angle are set
    public Ball(int startingX, int startingY) {
        // circle is created at 0,0 then moved to the starting positions. this is to prevent issues with how translationTransition works
        circle = new Circle(0,0,10);
        circle.setTranslateX(startingX);
        circle.setTranslateY(startingY);
        this.startingX = startingX;
        this.startingY = startingY;
        this.velocity = 0;
        this.XVelocity = 0;
        this.YVelocity = 0;
        this.angle = 0;
        this.isOverlapping = false;
        // default final position is the same as starting
        this.simX = startingX;
        this.simY = startingY;
    }

    // secondary constructor, mostly used for testing
    public Ball(int startingX, int startingY, double velocity, double angle) {
        circle = new Circle(0,0,10);
        circle.setTranslateX(startingX);
        circle.setTranslateY(startingY);
        this.startingX = startingX;
        this.startingY = startingY;
        // running the set methods for each respective value, which also sets the X and Y velocities
        setVelocity(velocity);
        setAngle(angle);
        this.isOverlapping = false;
        this.simX = startingX;
        this.simY = startingY;
    }

    // method used for that the sliders can change the velocity of the ball,
    public void setVelocity(double velocity) {
        this.velocity = velocity;
        // Math.cos() and .sin() are read using radians, so i convert our angle from degrees to radians for the velocity calculation.
        // however we should stick to using degrees for the user as they are much simpler to understand
        this.XVelocity = velocity * Math.cos(Math.toRadians(angle));
        // this is negative as within java the positive Y axis is pointing down, so i reverse it to match the standard quadrant used in most mathematics
        this.YVelocity = -velocity * Math.sin(Math.toRadians(angle));
    }

    // ditto for angle
    public void setAngle(double angle) {
        // if statements to make sure the input angle is within 360 degrees
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
        // ditto from line 74
        this.YVelocity = -velocity * Math.sin(Math.toRadians(angle));
    }

    // this is the big breadwinner method, that generates the next step of the animation when run
    public void nextPosition() {
        // the time elapsed for both the animation and the physics calculation, recommend a smaller time scale for smoother anims
        double time = 0.1;
        // the final Y velocity, to replace the internal y velocity so it stays consistent between iterations
        double finalYVelocity = YVelocity + YAccel * time;

        // the final Y position of the animation, calculated using yf = yi + v(t) + 1/2(a)t^2
        double finalY =  (simY
                                + YVelocity * time
                                + 0.5 * YAccel * time * time);
        // ditto for x
       double finalX =  (simX
                                + XVelocity * time);

       // the translation animation, set to linear so that animation is smooth
        TranslateTransition tt = new TranslateTransition(new Duration(time * 1000),circle);
        tt.setToX(finalX);
        tt.setToY(finalY);
        tt.setInterpolator(Interpolator.LINEAR);

        // adds the animation to the sequence
        path.getChildren().add(tt);

        // changes the initial position and velocity to the final for future iterations
        YVelocity = finalYVelocity;
        simY = finalY;
        simX = finalX;
    }

    // plays the sequential animation (path)
    public void play() {
        path.play();
    }


    // setters/getters
    public void setOverlapping(boolean overlapping) {
        isOverlapping = overlapping;
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

    public static SequentialTransition getPath() {
        return path;
    }

    public double getSimX() {
        return simX;
    }

    public double getSimY() {
        return simY;
    }

    public Circle getCircle() {
        return circle;
    }
}
