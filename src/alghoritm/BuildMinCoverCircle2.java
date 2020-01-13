package alghoritm;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class BuildMinCoverCircle2 {
    private List<Point2D> points;
    private Point2D centerCircle;
    private Double radius;
    private GraphicsContext gc;

    public BuildMinCoverCircle2(List<Point2D> points, GraphicsContext gc) {
        this.gc = gc;
        this.points = new ArrayList<>(points);
        minDisk();
        System.out.println("Center: (" + centerCircle.getX() + ", " + centerCircle.getY() + ")");
        System.out.println("Radius: " + radius);
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
            if (radius < Utils.distanceBetweenPoints(points.get(i), centerCircle))
                minDiskWithPoint(points.get(i), i);
        }

    }

    private void minDiskWithPoint(Point2D point, int index) {
        centerCircle = Utils.part(points.get(0), point);
        radius = Utils.distanceBetweenPoints(centerCircle, point);
        for (int i = 1; i < index; i++) {
            if (radius < Utils.distanceBetweenPoints(points.get(i), centerCircle))
                minDiskWithTwoPoint(point, points.get(i), i);
        }
    }

    private void minDiskWithTwoPoint(Point2D point1, Point2D point2, int index) {
        centerCircle = Utils.part(point1, point2);
        radius = Utils.distanceBetweenPoints(centerCircle, point1);
        for (int i = 0; i < index; i++) {
            if (radius < Utils.distanceBetweenPoints(points.get(i), centerCircle))
                minDiskWithThreePoint(points.get(i), point1, point2);
        }
    }

    private void minDiskWithThreePoint(Point2D point1, Point2D point2, Point2D point3) {
        if (Utils.onLine(point1, point2, point3)) {
            Point2D p1 = Utils.minPoint(Utils.minPoint(point1, point2), point3);
            Point2D p2 = Utils.maxPoint(Utils.maxPoint(point1, point2), point3);
            centerCircle = Utils.part(p1, p2);
            radius = Utils.distanceBetweenPoints(p1, p2) / 2;
            return;
        }

        double A = point2.getX() - point1.getX();
        double B = point2.getY() - point1.getY();
        double C = point3.getX() - point1.getX();
        double D = point3.getY() - point1.getY();
        double E = A * (point1.getX() + point2.getX()) + B * (point1.getY() + point2.getY());
        double F = C * (point1.getX() + point3.getX()) + D * (point1.getY() + point3.getY());
        double G = 2 * (A * (point3.getY() - point2.getY()) - B * (point3.getX() - point2.getX()));
        double x = (D * E - B * F) / G;
        double y = (A * F - C * E) / G;

        centerCircle = new Point2D(x, y);
        radius = Utils.distanceBetweenPoints(centerCircle, point1);
    }
}
