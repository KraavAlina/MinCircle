package alghoritm;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

    public final static Integer WIDTH = 1000;
    public final static Integer HEIGHT = 600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Smallest-circle problem");
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        RandomPoints randomPoints = new RandomPoints();
        randomPoints.drawPoints(gc);
        BuildMinCoverCircle method = new BuildMinCoverCircle(randomPoints.getPoints(),gc);
        method.drawCircle(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
