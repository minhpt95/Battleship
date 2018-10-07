package minhpt;

import java.awt.*;

public class Utils {
    private Utils() {

    }

    //check distance of minhpt.Ship must be equal 3
    public static double distanceBetweenPoints(Point from, Point to) {
        double x1 = from.getX();
        double y1 = from.getY();
        double x2 = to.getX();
        double y2 = to.getY();

        double result = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)) + 1;

        return result;
    }


    // create between point from start point and end point
    public static boolean isPointBetween(Point point, Position position) {
        Point from = position.getFrom();
        Point to = position.getTo();

        return from.getY() <= point.getY()
                && to.getY() >= point.getY()
                && from.getX() <= point.getX()
                && to.getX() >= point.getX();
    }
}
