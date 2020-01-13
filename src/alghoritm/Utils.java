package alghoritm;

import javafx.geometry.Point2D;

public class Utils {
    static double eps = 1e-7;

    public static Point2D part(Point2D a, Point2D b) {
        return new Point2D((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
    }

    public static Double distanceBetweenPoints(Point2D point1, Point2D point2) {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
    }

    /*
    public static Double areaTriangle(Point2D p1, Point2D p2, Point2D p3) {
        return 0.5 * (p1.getX() * p2.getY() + p2.getX() * p3.getY() + p3.getX() * p1.getY() -
                p1.getY() * p2.getX() - p2.getY() * p3.getX() - p3.getY() * p1.getX());
    }
*/
    public static Double areaTriangle(Point2D p1, Point2D p2, Point2D p3) {
        return 0.5 * ((p1.getX() - p3.getX()) * (p2.getY()-p3.getY()) - (p1.getY()-p3.getY())*(p2.getX()-p3.getX()));
    }

    public static Boolean equalsDouble(Double a, Double b) {
        return Math.abs(a - b) < eps;
    }

    public static Boolean onLine(Point2D p1, Point2D p2, Point2D p3) {
        return equalsDouble(areaTriangle(p1, p2, p3), 0.0);
    }

    public static Integer compare(Point2D p1, Point2D p2) {
        if (p1.getX() < p2.getX() || (equalsDouble(p1.getX(), p2.getX()) && p1.getY() < p2.getY()))
            return -1;
        if (p1.getX() > p2.getX() || (equalsDouble(p1.getX(), p2.getX()) && p1.getY() > p2.getY()))
            return 1;
        return 0;
    }

    public static Boolean compareLess(Point2D p1, Point2D p2) {
        return p1.getX() < p2.getX() || (equalsDouble(p1.getX(), p2.getX()) && p1.getY() < p2.getY());
    }

    public static Point2D minPoint(Point2D p1, Point2D p2) {
        return compareLess(p1, p2) ? p1 : p2;
    }

    public static Point2D maxPoint(Point2D p1, Point2D p2) {
        return compareLess(p1, p2) ? p2 : p1;
    }
}

