package org.mikmin.Cup_Pong;

public class HitBox {
    private double Xposition;
    private double Yposition;
    private double[] Xrange;
    private double[] Yrange;
    private boolean scoring;
    private Cup cup;

    public HitBox(int xposition, Cup cup, boolean scoring, double[] yrange, int yposition, double[] xrange) {
        Xposition = xposition;
        this.cup = null;
        this.scoring = false;
        Yrange = yrange;
        Yposition = yposition;
        Xrange = xrange;
    }

    public HitBox(double xposition, double yposition, double[] xrange, double[] yrange, boolean scoring, Cup cup) {
        Xposition = xposition;
        Yposition = yposition;
        Xrange = xrange;
        Yrange = yrange;
        this.scoring = true;
        this.cup = cup;
    }


    public boolean Iscolliding(double ballFinalX, double ballFinalY, double radius){
        double left = Xposition + Xrange[0];
        double right = Xposition + Xrange[1];

        double top = Yposition + Yrange[0];
        double bottom = Yposition + Yrange[1];

        double closestX = Math.max(left, Math.min(ballFinalX, right));

        double closestY = Math.max(top, Math.min(ballFinalY, bottom));

        double distanceX = ballFinalX - closestX;
        double distanceY = ballFinalY - closestY;

        return distanceX * distanceX
                + distanceY * distanceY <= radius * radius;
    }

    public boolean isScoring(){
        return scoring;
    }

    public Cup getCup() {
        return cup;
    }
}
