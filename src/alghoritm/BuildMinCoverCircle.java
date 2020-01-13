package alghoritm;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class BuildMinCoverCircle {
    private List<Point2D> points;
    private Point2D centerCircle;
    private Double radius;
    private GraphicsContext gc;

    public BuildMinCoverCircle(List<Point2D> points, GraphicsContext gc) {
        this.gc = gc;
        this.points = new ArrayList<>(points);
        minDisk();
        System.out.println("Center: (" + centerCircle.getX() + ", " + centerCircle.getY() + ")");
        System.out.println("Radius: " + radius);
//        System.out.println("Min x: " + (centerCircle.getX() - radius) + "; Max x: " + (centerCircle.getX() + radius));
//        System.out.println("Min y: " + (centerCircle.getY() - radius) + "; Max y: " + (centerCircle.getY() + radius));
    }

    public void drawCircle (GraphicsContext gc){
        double d = radius*2;
        gc.strokeOval(centerCircle.getX()-d/2, centerCircle.getY()-d/2, d, d);
        gc.setStroke(Color.RED);
        gc.strokeOval(centerCircle.getX() - 1.5, centerCircle.getY() - 1.5, 3, 3);
        gc.setStroke(Color.BLACK);
    }

    private void minDisk() {
        centerCircle = Utils.part(points.get(0), points.get(1));
        radius = Utils.distanceBetweenPoints(centerCircle, points.get(0));
        for (int i = 2; i < points.size(); i++) {
            //System.out.println("Center: (" + centerCircle.getX() + ", " + centerCircle.getY() + ")");
            //System.out.println("Radius1: " + radius + " Distance1: " + Utils.distanceBetweenPoints(points.get(i), centerCircle));
            //drawCircle(gc);
            if (radius < Utils.distanceBetweenPoints(points.get(i), centerCircle))
                minDiskWithPoint(points.get(i), i);
        }

    }

    private void minDiskWithPoint(Point2D point, int index) {
        centerCircle = Utils.part(points.get(0), point);
        radius = Utils.distanceBetweenPoints(centerCircle, point);
        for (int i = 1; i < index; i++) {
            //System.out.println("Center: (" + centerCircle.getX() + ", " + centerCircle.getY() + ")");
            //System.out.println("Radius2: " + radius + " Distance2: " + Utils.distanceBetweenPoints(points.get(i), centerCircle));
            //drawCircle(gc);
            if (radius < Utils.distanceBetweenPoints(points.get(i), centerCircle))
                minDiskWithTwoPoint(point, points.get(i), i);
        }
    }

    private void minDiskWithTwoPoint(Point2D point1, Point2D point2, int index) {
        centerCircle = Utils.part(point1, point2);
        radius = Utils.distanceBetweenPoints(centerCircle, point1);
        for (int i = 0; i < index; i++) {
            //System.out.println("Center: (" + centerCircle.getX() + ", " + centerCircle.getY() + ")");
            //System.out.println("Radius3: " + radius + " Distance3: " + Utils.distanceBetweenPoints(points.get(i), centerCircle));
            //drawCircle(gc);
            if (radius < Utils.distanceBetweenPoints(points.get(i), centerCircle))
                minDiskWithThreePoint(points.get(i), point1, point2);
        }
    }

    private void minDiskWithThreePoint(Point2D point1, Point2D point2, Point2D point3) {
        /*if (Utils.onLine(point1, point2, point3)) {
            Point2D p1 = Utils.minPoint(Utils.minPoint(point1, point2), point3);
            Point2D p2 = Utils.maxPoint(Utils.maxPoint(point1, point2), point3);
            centerCircle = Utils.part(p1, p2);
            radius = Utils.distanceBetweenPoints(p1, p2) / 2;
            return;
        }
        */
        /*
        Point2D mP1P2 = Utils.part(point1, point2);
        Point2D mP1P3 = Utils.part(point1, point3);
        double a1 = point1.getX() - point2.getX();
        double b1 = point1.getY() - point2.getY();
        double c1 = -a1 * mP1P2.getX() - b1 * mP1P2.getY();
        double a2 = point1.getX() - point3.getX();
        double b2 = point1.getY() - point3.getY();
        double c2 = -a2 * mP1P3.getX() - b2 * mP1P3.getY();
        double x, y;
        x = (b2 * c1 - b1 * c2) / (a2 * b1 - a1 * b2);
        y = -(a1 * x + c1) / b1;
         */
/*
        double A = point2.getX() - point1.getX();
        double B = point2.getY() - point1.getY();
        double C = point3.getX() - point1.getX();
        double D = point3.getY() - point1.getY();
        double E = A * (point1.getX() + point2.getX()) + B * (point1.getY() + point2.getY());
        double F = C * (point1.getX() + point3.getX()) + D * (point1.getY() + point3.getY());
        double G = 2 * (A * (point3.getY() - point2.getY()) - B * (point3.getX() - point2.getX()));
        double x = (D * E - B * F) / G;
        double y = (A * F - C * E) / G;

 *//*
        double ma = (point2.getY()-point1.getY())/(point2.getX()-point1.getX());
        double mb = (point3.getY()-point2.getY())/(point3.getX()-point2.getX());
        double x = (ma*mb*(point1.getY()-point3.getY())+mb*(point1.getX()+point2.getY())-ma*(point2.getX()+point3.getX()))/(2*(mb-ma));
        double y = -(1/ma)*(x-(point1.getX()+point2.getX())/2) + (point1.getY()+point2.getY())/2;
*/

        double a = point2.getX()*point2.getX()+point2.getY()*point2.getY()-point3.getX()*point3.getX()-point3.getY()*point3.getY();
        double b = point3.getX()*point3.getX()+point3.getY()*point3.getY()-point1.getX()*point1.getX()-point1.getY()*point1.getY();
        double c = point1.getX()*point1.getX()+point1.getY()*point1.getY()-point2.getX()*point2.getX()-point2.getY()*point2.getY();
        double d = point1.getX()*(point2.getY()-point3.getY())+point2.getX()*(point3.getY()-point1.getY())+point3.getX()*(point1.getY()-point2.getY());
        double x = -0.5*(point1.getY()*a+point2.getY()*b+point3.getY()*c)/d;
        double y =  0.5*(point1.getX()*a+point2.getX()*b+point3.getX()*c)/d;

        centerCircle = new Point2D(x, y);
        radius = Utils.distanceBetweenPoints(centerCircle, point1);
        //System.out.println("Radius4: "+ radius);
/*
        gc.setStroke(Color.GREEN);
        gc.strokeOval(point1.getX() - 1.5, point1.getY() - 1.5, 3, 3);
        gc.strokeOval(point2.getX() - 1.5, point2.getY() - 1.5, 3, 3);
        gc.strokeOval(point3.getX() - 1.5, point3.getY() - 1.5, 3, 3);
        gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
        gc.strokeLine(point2.getX(), point2.getY(), point3.getX(), point3.getY());
        gc.strokeLine(point1.getX(), point1.getY(), point3.getX(), point3.getY());
        drawCircle(gc);
        gc.setStroke(Color.BLACK);
*/
    }
}
