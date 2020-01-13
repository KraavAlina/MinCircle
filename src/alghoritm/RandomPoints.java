package alghoritm;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static alghoritm.Main.HEIGHT;
import static alghoritm.Main.WIDTH;

public class RandomPoints {
    private List<Point2D> points;

    public RandomPoints() {
        Random random = new Random();
        int size = random.nextInt(80 - 19) + 20;
        points = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            points.add(new Point2D(random.nextInt(WIDTH - 700) + 350, random.nextInt(HEIGHT - HEIGHT/2) + 150));
        }
    }

    public List<Point2D> getPoints() {
        return points;
    }

    public void drawPoints(GraphicsContext gc) {
        gc.setLineWidth(2);
        for (Point2D point : points) {
            gc.strokeOval(point.getX() - 0.5, point.getY() - 0.5, 1, 1);
        }
    }

    public void writePoints() {
        for (Point2D point : points){
            System.out.println("x: " + point.getX() + ", y: " + point.getY());
        }
    }
}
