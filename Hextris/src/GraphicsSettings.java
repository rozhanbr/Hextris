import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javafx.scene.shape.Polygon;
import java.util.ArrayList;

public class GraphicsSettings extends Application {
    private final int height = 775;
    private final int width = 670;

    private final int side = 20;
    private double[][] coordArray = new double[4][2];

    private static boolean newShape = true;
    public boolean gameOver = false;
    public int count;

    public static boolean isNewShape() {
        return newShape;
    }

    public static void setNewShape(boolean newShape) {
        GraphicsSettings.newShape = newShape;
    }

    public GraphicsSettings(){
        count = 0;
     }
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Elements e = new Elements();
        e.printPermanentHexagons(root);

        Timeline time2 = new Timeline(new KeyFrame(new Duration(800),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (isNewShape()) {
                            Elements element2 = new Elements();
                            for (Polygon p : element2.getPolygons()) {
                                root.getChildren().add(p);
                            }
                            setNewShape(false);
                            scene.setOnKeyPressed(key -> {
                                if (key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.D) {
                                    if (element2.CanGoRight() && element2.CanGoDown()) {
                                        if (element2.getX() % 2 == 0)
                                            element2.setCurrentY(element2.getCurrentY() - 1);
                                        else
                                            element2.setCurrentY(element2.getCurrentY() + 1);
                                        element2.setCurrentXLeft(element2.getCurrentXLeft() + 1);
                                        element2.setCurrentXRight(element2.getCurrentXRight() + 1);
                                        element2.moveRight();
                                    }
                                }
                                if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.A) {
                                    if (element2.CanGoLeft() && element2.CanGoDown()) {
                                        if (element2.getX() % 2 == 0)
                                            element2.setCurrentY(element2.getCurrentY() - 1);
                                        else
                                            element2.setCurrentY(element2.getCurrentY() + 1);
                                        element2.setCurrentXLeft(element2.getCurrentXLeft() - 1);
                                        element2.setCurrentXRight(element2.getCurrentXRight() - 1);
                                        element2.moveLeft();
                                    }
                                }
                                if (key.getCode() == KeyCode.DOWN || key.getCode() == KeyCode.S) {
                                    if (element2.CanGoDown()) {
                                        setNewShape(false);
                                        element2.setCurrentY(element2.getCurrentY() + 1);
                                        element2.moveDownFast();
                                    }
                                }

                                if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP) {
                                    element2.setCorrectRotation(0);
                                    if (element2.CanGoDown() && element2.CanRotate()) {
                                        element2.rotate();
                                    }
                                }

                            });
                            Timeline time3 = new Timeline(new KeyFrame(new Duration(400),
                                    new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            if (element2.CanGoDown()) {
                                                count++;
                                                setNewShape(false);
                                                element2.setCurrentY(element2.getCurrentY() + 1);
                                                element2.moveDown(root);
                                            }
                                            if(!element2.CanGoDown()){
                                                element2.fillArray();
                                            }
                                            if(!element2.CanGoDown() && !Elements.wasFull) {
                                                element2.fillArray();
                                                Elements.score();
                                                if(Elements.WasFull()) {
                                                    Elements.rowDisappearance();
                                                    Elements.printUpdatedScene(root);
                                                    Elements.setFilledCount(0);
                                                }
                                            }
                                        }
                                    }));
                            time3.setCycleCount(Timeline.INDEFINITE);
                            time3.play();
                        }
                        }
                }));
        time2.setCycleCount(Timeline.INDEFINITE);
        time2.play();
            scene.setFill(Color.BLACK);
            stage.setTitle("Hextris Game");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setHeight(height);
            stage.setWidth(width);
            stage.show();

    }
}
