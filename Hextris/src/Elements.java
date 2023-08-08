import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.Random;
import java.util.Spliterator;

public class Elements{
    
    private double X = 200;
    private double Y = 0;
    private static final int side = 20;
    private int element;
    private boolean canGoRight = true;
    private boolean canGoLeft = true;
    private boolean canGoDown = true;
    private boolean canRotate = true;
    private int currentXRight = 0;
    private int CurrentXLeft;
    private int currentY = 0;
    private double centerX;
    private double centerY;
    private double[][] coorArray;
    private int correctRotation;
    private static final int booleanX = 15;
    private  static final int booleanY = 21;
    public static int[][] grid = new int[booleanX][booleanY];
    public static int[][] temp = new int[booleanX][booleanY];
    private static int score = 0;
    public static boolean wasFull = false;
    public static int filledCount = 0;
    private ArrayList<Polygon> polygons;

    public static boolean WasFull() {
        return wasFull;
    }

    public static void setWasFull(boolean wasFull) {
        Elements.wasFull = wasFull;
    }

    public static int getFilledCount() {
        return filledCount;
    }

    public static void setFilledCount(int filledCount) {
        Elements.filledCount = filledCount;
    }

    public void setCorrectRotation(int correctRotation) {
        this.correctRotation = correctRotation;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public int getCurrentXLeft() {
        return CurrentXLeft;
    }

    public void setCurrentXLeft(int CurrentXLeft) {
        this.CurrentXLeft = CurrentXLeft;
    }

    public int getCurrentXRight() {
        return currentXRight;
    }

    public void setCurrentXRight(int currentXRight) {
        this.currentXRight = currentXRight;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public boolean CanGoRight(){
        return canGoRight;
    }

    public void setCanGoRight(boolean canGoRight){
        this.canGoRight = canGoRight;
    }

    public boolean CanGoLeft() {
        return canGoLeft;
    }

    public void setCanGoLeft(boolean canGoLeft){
        this.canGoLeft = canGoLeft;
    }

    public boolean CanGoDown() {
        return canGoDown;
    }

    public void setCanGoDown(boolean canGoDown){
        this.canGoDown = canGoDown;
    }

    public boolean CanRotate() {
        return canRotate;
    }

    public void setCanRotate(boolean canRotate) {
        this.canRotate = canRotate;
    }

    private static ArrayList<Integer> toDisappear;

    public Elements() {
        for (int i = 0; i < booleanY; i++)
            grid[0][i] = 1;
        for (int i = 0; i < booleanX; i++)
            grid[i][booleanY - 1] = 1;
        for (int i = 0; i < booleanY; i++)
            grid[booleanX - 1][i] = 1;

        this.correctRotation = 0;

        polygons = new ArrayList<Polygon>();
        element = new Random().nextInt(8);
        switch(element) {
            case 0:
                setCurrentXRight(7);
                setCurrentXLeft(5);
                setCurrentY(0);
                element1();
                break;
            case 1:
                setCurrentXRight(7);
                setCurrentXLeft(6);
                setCurrentY(2);
                element2();
                break;
            case 2:
                setCurrentXRight(6);
                setCurrentXLeft(5);
                setCurrentY(2);
                element3();
                break;
            case 3:
                setCurrentXRight(7);
                setCurrentXLeft(6);
                setCurrentY(3);
                element4();
                break;
            case 4:
                setCurrentXRight(7);
                setCurrentXLeft(6);
                setCurrentY(3);
                element5();
                break;
            case 5:
                setCurrentXRight(6);
                setCurrentXLeft(5);
                setCurrentY(3);
                element6();
                break;
            case 6:
                setCurrentXRight(6);
                setCurrentXLeft(6);
                setCurrentY(3);
                element7();
                break;
            case 7:
                setCurrentXRight(7);
                setCurrentXLeft(5);
                setCurrentY(2);
                element8();
                break;

        }
    }

    public void setX(double newX){
        X = newX;
    }

    public double getX(){
        return X;
    }

    public void setY(double newY){
        Y = newY;
    }

    public double getY(){
        return Y;
    }

    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }

    public void gameOver(Group root){
        GraphicsSettings.setNewShape(false);
        Rectangle r = new Rectangle(100,250,300,200);
        r.setFill(Color.BLACK);
        root.getChildren().add(r);
        Text text = new Text(105,330,"Game Over! :(");
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        Text text2 = new Text(105,390,"Score : " + score);
        text2.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        text2.setFill(Color.WHITE);
        root.getChildren().add(text);
        root.getChildren().add(text2);
    }

    public static void printPermanentHexagons(Group root){
        for (int i = 490; i <= 493; i++) {
            Line line = new Line(i, 0, i, 750);
            line.setStroke(Color.CRIMSON);
            root.getChildren().add(line);
        }
        for (int i = 0; i < booleanX - 1; i++) {
            for (int j = 0; j < booleanY - 1; j++) {
                Shapes hex = new Shapes();
                if (grid[i][j] != 0){
                    hex.setPosX(((i) * 30) + side);

                    if(i % 2 == 0)
                        hex.setPosY((((j) * Math.sqrt(3) * 20) + (Math.sqrt(3) * 20) / 2));
                    else
                        hex.setPosY((((j) * Math.sqrt(3) * 20 + Math.sqrt(300)) + (Math.sqrt(3) * 20) / 2));

                    hex.hexagon(hex.getPosX(), hex.getPosY(), side);
                    hex.elementsHexagon(grid[i][j]);
                    root.getChildren().add(hex.getPolygon());
                }
            }
        }
        for(int j = 0; j < booleanY; j++){
            Shapes hex = new Shapes();
            if(grid[0][j] == 1) {
                hex.setPosX(20);
                hex.setPosY((j * Math.sqrt(3) * side) + (Math.sqrt(3) * side) / 2);
                hex.hexagon(hex.getPosX(), hex.getPosY(), side);
                root.getChildren().add(hex.getPolygon());
            }
        }
        for (int i = 0; i < booleanX ; i++) {
            Shapes hex = new Shapes();
            if(grid[i][booleanY - 1] == 1){
                if(i % 2 == 0)
                    hex.setPosY(((booleanY - 1)  * Math.sqrt(3) * side )  + (Math.sqrt(3) * side)/2);
                else
                    hex.setPosY(((booleanY - 1)  * Math.sqrt(3) * side  - Math.sqrt(300))  + (Math.sqrt(3) * side)/2);
                hex.setPosX(((i) * 30) + side);
                hex.hexagon(hex.getPosX(), hex.getPosY(), side);
                root.getChildren().add(hex.getPolygon());
            }
        }
        for (int i = 0; i < booleanY - 1; i++) {
            Shapes hex = new Shapes();
            if(grid[booleanX-1][i] == 1){
                hex.setPosX(22 * side);
                hex.setPosY((i * Math.sqrt(3) * side) + (Math.sqrt(3) * side)/2);
                hex.hexagon(hex.getPosX(), hex.getPosY(), side);
                root.getChildren().add(hex.getPolygon());
            }

        }
    }

    public static void score(){
        toDisappear = new ArrayList<Integer>();
        for (int i = booleanY - 2; i >= 0 ; i--) {
            setFilledCount(0);
            for (int j = 1; j < booleanX - 1; j++) {
                if(grid[j][i] != 0)
                    setFilledCount(getFilledCount() + 1);
            }
            if(getFilledCount() == 13) {
                setWasFull(true);
                score += 10;
                toDisappear.add(i);
            }
            //System.out.println(score);
        }
    }
 
    public static void rowDisappearance(){
        for (int i = 0; i < booleanY; i++) {
            for (int j = 0; j < booleanX; j++) {
                temp[j][i] = grid[j][i];
            }
        }
        if(WasFull()){
            for(int i = 0; i < toDisappear.size(); i++) {
                for (int j = toDisappear.get(i); j > 0  ; j--) {
                    for (int k = 1; k < booleanX - 1; k++) {
                        temp[k][j] = temp[k][j - 1];
                    }
                }
            }
        }
    }

    public static void printUpdatedScene(Group root){
        for (int i = 0; i < booleanY - 1; i++) {
            for (int j = 1; j < booleanX - 1; j++) {
                grid[j][i] = temp[j][i];
            }
        }
        root.getChildren().clear();
        printPermanentHexagons(root);
        GraphicsSettings.setNewShape(true);
    }

    public void moveDown(Group root) {
        Y++;
        for (Polygon p : getPolygons()) {
            for (int i = 0, k = 10; i < booleanX; i++, k+=10) {
                for (int j = 0; j < booleanY; j++) {
                    Shapes hex = new Shapes();
                    if (grid[i][j] != 0) {
                        if (i % 2 == 0)
                            hex.setPosY(((j * Math.sqrt(3) * side )) - (Math.sqrt(3) * side));
                        else
                            hex.setPosY(((j * Math.sqrt(3) * side - Math.sqrt(300)) - Math.sqrt(3) * side));
                        hex.setPosX(((i) * 30) + side);
                        hex.hexagon(hex.getPosX(), hex.getPosY(), side);
                        Shape s = Shape.intersect(hex.getPolygon(), p);
                        if (s.contains(hex.getPosX(), hex.getPosY())) {
                            GraphicsSettings.setNewShape(true);
                            if(hex.getPosY() <= Math.sqrt(3) * side * 2 && hex.getPosY() >= 0) {
                                gameOver(root);
                            }
                            setCanGoDown(false);
                            setCanRotate(false);
                            setCanGoLeft(false);
                            setCanGoRight(false);
                        }
                    }
                }
            }
            p.setLayoutY(p.getLayoutY() + Math.sqrt(3) * side);
        }
    }

    public void moveRight(){
        X++;
        for(Polygon p : getPolygons()) {
            for (int i = 0, k = 10; i < booleanX; i++, k += 10) {
                for (int j = 0; j < booleanY; j++) {
                    Shapes hex = new Shapes();
                    if (grid[i][j] != 0) {
                        if (i % 2 == 0)
                            hex.setPosY(((j * Math.sqrt(3) * side - Math.sqrt(3) * side)));
                        else
                            hex.setPosY(((j * Math.sqrt(3) * side - Math.sqrt(300)) - Math.sqrt(3) * side));
                        hex.setPosX((i * 2 * side) - k - 30);
                        hex.hexagon(hex.getPosX(), hex.getPosY(), side);
                        Shape s = Shape.intersect(hex.getPolygon(), p);
                        if (s.contains(hex.getPosX(), hex.getPosY()))
                            setCanGoRight(false);
                    }
                }
            }
            setCanGoLeft(true);
            p.setLayoutX(p.getLayoutX() + 30);
        }
        if (X % 2 == 1) {
            for (Polygon p : getPolygons()) {
                p.setLayoutY(p.getLayoutY() - (Math.sqrt(3) * side) / 2);
            }
        }
        else {
            for (Polygon p : getPolygons()) {
                p.setLayoutY(p.getLayoutY() + (Math.sqrt(3) * side) / 2);
            }
        }
    }
    public void fillArray(){
        int y,x;
        for (Polygon p : getPolygons()) {
            x = (int)Math.round(p.getBoundsInParent().getMaxX() + p.getBoundsInParent().getMinX())/2;
            x = (x-20)/30;
            y = (int)Math.round(p.getBoundsInParent().getMaxY() + p.getBoundsInParent().getMinY()) / 2;
            y = (int)((y-(Math.sqrt(3)*20)/2) / (side * Math.sqrt(3))) + 1;
            grid[x][y] = element + 1;
        }

    }
    public void moveLeft(){
        X--;
        for(Polygon p : getPolygons()){
            for (int i = 0, k = 10; i < booleanX; i++, k+=10) {
                for (int j = 0; j < booleanY; j++) {
                    Shapes hex = new Shapes();
                    if (grid[i][j] != 0) {
                        if (i % 2 == 0)
                            hex.setPosY(((j * Math.sqrt(3) * side - Math.sqrt(3) * side)));
                        else
                            hex.setPosY(((j * Math.sqrt(3) * side - Math.sqrt(300)) - Math.sqrt(3) * side));
                        hex.setPosX((i * 2 * side) - k + 90);
                        hex.hexagon(hex.getPosX(), hex.getPosY(), side);
                        Shape s = Shape.intersect(hex.getPolygon(), p);
                        if (s.contains(hex.getPosX(), hex.getPosY())) {
                            setCanGoLeft(false);
                        }
                    }
                }
            }
            setCanGoRight(true);
            p.setLayoutX(p.getLayoutX() - 30);
        }
        if(X % 2 == 0) {
            for (Polygon p: getPolygons()) {
                p.setLayoutY(p.getLayoutY() - (Math.sqrt(3) * side) / 2);
            }
        }
        else {
            for (Polygon p: getPolygons()) {
                p.setLayoutY(p.getLayoutY() + (Math.sqrt(3) * side) / 2);
            }
        }
    }

    public void moveDownFast(){
        Y++;
        for (Polygon p : getPolygons()) {
            for (int i = 0; i < booleanX; i++) {
                for (int j = 0; j < booleanY; j++) {
                    Shapes hex = new Shapes();
                    if (grid[i][j] != 0) {
                        if (i % 2 == 0)
                            hex.setPosY(((j * Math.sqrt(3) * side - Math.sqrt(3) * side)));
                        else
                            hex.setPosY(((j * Math.sqrt(3) * side - Math.sqrt(300)) - Math.sqrt(3) * side));
                        hex.setPosX(((i) * 30) + side);
                        hex.hexagon(hex.getPosX(), hex.getPosY(), side);
                        Shape s = Shape.intersect(hex.getPolygon(), p);
                        if (s.contains(hex.getPosX(), hex.getPosY())) {
                            setCanGoDown(false);
                            GraphicsSettings.setNewShape(true);
                        }
                    }
                }
            }
            p.setLayoutY(p.getLayoutY() + Math.sqrt(3) * side);
        }
    }

    public void rotate() {
        boolean rotated = true;
        for (Polygon p : getPolygons()) {
            p.getTransforms().add(new Rotate(60.0, getCenterX(), getCenterY()));
            for (int i = 0, k = 10; i < booleanX; i++, k+=10) {
                for (int j = 0; j < booleanY; j++) {
                    Shapes hex = new Shapes();
                    if (grid[i][j] != 0) {
                        hex.setPosY((j * Math.sqrt(3) * side) + (Math.sqrt(3) * side)/2);
                        hex.setPosX(((i) * 30) + side);
                        hex.hexagon(hex.getPosX(), hex.getPosY(), side);
                        Shape s = Shape.intersect(hex.getPolygon(), p);
                        if (s.contains(hex.getPosX(), hex.getPosY())) {
                            rotated = false;
                        }
                        else {
                            setCanGoLeft(true);
                            setCanGoRight(true);
                        }
                    }
                }
            }
            p.getTransforms().add(new Rotate(-60.0, getCenterX(), getCenterY()));
            if(rotated)
                correctRotation++;
        }
        if(correctRotation == 4){
            int i = 0;
            for (Polygon p : getPolygons()) {
                p.getTransforms().add(new Rotate(60.0, getCenterX(), getCenterY()));
                coorArray[i][0] = (p.getBoundsInParent().getMaxX() + p.getBoundsInParent().getMinX()) / 2;
                coorArray[i][1] = (p.getBoundsInParent().getMaxY() + p.getBoundsInParent().getMinY()) / 2;
                i++;
            }
        }
    }



    public void element1 (){
        Shapes hex = new Shapes();
        coorArray = new double[4][2];
        hex.hexagon(X, Y + Math.sqrt(300),side);
        setCenterX(X);
        setCenterY((Y + Math.sqrt(300)));
        coorArray[0][0] = X;
        coorArray[0][1] = Y;
        hex.elementsHexagon(1);
        polygons.add(hex.getPolygon());

        Shapes hex2 = new Shapes();
        hex2.hexagon(X - 30,  Y + (2 * Math.sqrt(300)), side);
        hex2.elementsHexagon(1);
        coorArray[1][0] = X - 30;
        coorArray[1][1] = Y + (1 * Math.sqrt(300));
        polygons.add(hex2.getPolygon());

        Shapes hex3 = new Shapes();
        hex3.hexagon(X + 30,  Y + (2 * Math.sqrt(300)), side);
        hex3.elementsHexagon(1);
        coorArray[2][0] = X + 30;
        coorArray[2][1] = Y + (1 * Math.sqrt(300));
        polygons.add(hex3.getPolygon());

        Shapes hex4 = new Shapes();
        hex4.hexagon(X,  Y + (3 *  Math.sqrt(300)), side);
        hex4.elementsHexagon(1);
        coorArray[3][0] = X;
        coorArray[3][1] = Y + (2 *  Math.sqrt(300));
        polygons.add(hex4.getPolygon());

    }


    public void element2(){
        Shapes hex = new Shapes();
        coorArray = new double[4][2];
        hex.hexagon(X, Y + Math.sqrt(300),side);
        setCenterX(X);
        setCenterY((Y + Math.sqrt(300)));
        hex.elementsHexagon(2);
        coorArray[0][0] = X;
        coorArray[0][1] = Y + Math.sqrt(300);
        polygons.add(hex.getPolygon());

        Shapes hex2 = new Shapes();
        hex2.hexagon(X , Y + (3 * Math.sqrt(300)), side);
        hex2.elementsHexagon(2);
        coorArray[1][0] = X;
        coorArray[1][1] = Y + (3 * Math.sqrt(300));
        polygons.add(hex2.getPolygon());

        Shapes hex3 = new Shapes();
        hex3.hexagon(X ,  Y + (5 * Math.sqrt(300)), side);
        hex3.elementsHexagon(2);
        coorArray[2][0] = X;
        coorArray[2][1] = Y + (5 * Math.sqrt(300));
        polygons.add(hex3.getPolygon());

        Shapes hex4 = new Shapes();
        hex4.hexagon(X + 30,  Y + (2 *  Math.sqrt(300)), side);
        hex4.elementsHexagon(2);
        coorArray[3][0] = X + 30;
        coorArray[3][1] = Y + (2 *  Math.sqrt(300));
        polygons.add(hex4.getPolygon());


    }

    public void element3(){
        Shapes hex = new Shapes();
        coorArray = new double[4][2];
        setCenterX(X);
        setCenterY((Y + Math.sqrt(300)));
        hex.hexagon(X, Y + Math.sqrt(300),side);
        hex.elementsHexagon(3);
        coorArray[0][0] = X;
        coorArray[0][1] = Y ;
        polygons.add(hex.getPolygon());

        Shapes hex2 = new Shapes();
        hex2.hexagon(X , Y + (3 * Math.sqrt(300)), side);
        hex2.elementsHexagon(3);
        coorArray[1][0] = X;
        coorArray[1][1] = Y + (2 * Math.sqrt(300));
        polygons.add(hex2.getPolygon());

        Shapes hex3 = new Shapes();
        hex3.hexagon(X ,  Y + (5 * Math.sqrt(300)), side);
        hex3.elementsHexagon(3);
        coorArray[2][0] = X;
        coorArray[2][1] = Y + (4 * Math.sqrt(300));
        polygons.add(hex3.getPolygon());

        Shapes hex4 = new Shapes();
        hex4.hexagon(X - 30,  Y + (2 *  Math.sqrt(300)), side);
        hex4.elementsHexagon(3);
        coorArray[3][0] = X - 30;
        coorArray[3][1] = Y + (1 * Math.sqrt(300));
        polygons.add(hex4.getPolygon());
    }

    public void element4(){
        Shapes hex = new Shapes();
        coorArray = new double[4][2];
        setCenterX(X);
        setCenterY((Y + Math.sqrt(300)));
        hex.hexagon(X, Y + Math.sqrt(300),side);
        hex.elementsHexagon(4);
        coorArray[0][0] = X;
        coorArray[0][1] = Y;
        polygons.add(hex.getPolygon());

        Shapes hex2 = new Shapes();
        hex2.hexagon(X , Y + (3 * Math.sqrt(300)), side);
        hex2.elementsHexagon(4);
        coorArray[1][0] = X;
        coorArray[1][1] = Y + (2 * Math.sqrt(300));
        polygons.add(hex2.getPolygon());

        Shapes hex3 = new Shapes();
        hex3.hexagon(X ,  Y + (5 * Math.sqrt(300)), side);
        hex3.elementsHexagon(4);
        coorArray[2][0] = X;
        coorArray[2][1] = Y + (4 * Math.sqrt(300));
        polygons.add(hex3.getPolygon());

        Shapes hex4 = new Shapes();
        hex4.hexagon(X + 30,  Y + (6 *  Math.sqrt(300)), side);
        hex4.elementsHexagon(4);
        coorArray[3][0] = X + 30;
        coorArray[3][1] = Y + (4 * Math.sqrt(300));
        polygons.add(hex4.getPolygon());
    }

    public void element5(){
        Shapes hex = new Shapes();
        coorArray = new double[4][2];
        setCenterX(X);
        setCenterY((Y + Math.sqrt(300)));
        hex.hexagon(X, Y + Math.sqrt(300),side);
        hex.elementsHexagon(5);
        coorArray[0][0] = X;
        coorArray[0][1] = Y;
        polygons.add(hex.getPolygon());

        Shapes hex2 = new Shapes();
        hex2.hexagon(X , Y + (3 * Math.sqrt(300)), side);
        hex2.elementsHexagon(5);
        coorArray[1][0] = X;
        coorArray[1][1] = Y + (2 * Math.sqrt(300));
        polygons.add(hex2.getPolygon());

        Shapes hex3 = new Shapes();
        hex3.hexagon(X + 30,  Y + (4 * Math.sqrt(300)), side);
        hex3.elementsHexagon(5);
        coorArray[2][0] = X + 30;
        coorArray[2][1] = Y + (3 * Math.sqrt(300));
        polygons.add(hex3.getPolygon());

        Shapes hex4 = new Shapes();
        hex4.hexagon(X + 30,  Y + (6 *  Math.sqrt(300)), side);
        hex4.elementsHexagon(5);
        coorArray[3][0] = X + 30;
        coorArray[3][1] = Y + (5 * Math.sqrt(300));
        polygons.add(hex4.getPolygon());
    }

    public void element6(){
        Shapes hex = new Shapes();
        coorArray = new double[4][2];
        setCenterX(X);
        setCenterY((Y + Math.sqrt(300)));
        hex.hexagon(X, Y + Math.sqrt(300),side);
        hex.elementsHexagon(6);
        coorArray[0][0] = X;
        coorArray[0][1] = Y + Math.sqrt(300);
        polygons.add(hex.getPolygon());

        Shapes hex2 = new Shapes();
        hex2.hexagon(X , Y + (3 * Math.sqrt(300)), side);
        hex2.elementsHexagon(6);
        coorArray[1][0] = X;
        coorArray[1][1] = Y + (3 * Math.sqrt(300));
        polygons.add(hex2.getPolygon());

        Shapes hex3 = new Shapes();
        hex3.hexagon(X - 30 ,  Y + (4 * Math.sqrt(300)), side);
        hex3.elementsHexagon(6);
        coorArray[2][0] = X - 30;
        coorArray[2][1] = Y + (4 * Math.sqrt(300));
        polygons.add(hex3.getPolygon());

        Shapes hex4 = new Shapes();
        hex4.hexagon(X - 30,  Y + (6 *  Math.sqrt(300)), side);
        hex4.elementsHexagon(6);
        coorArray[3][0] = X - 30;
        coorArray[3][1] = Y + (6 * Math.sqrt(300));
        polygons.add(hex4.getPolygon());
    }

    public void element7(){
        Shapes hex = new Shapes();
        coorArray = new double[4][2];
        setCenterX(X);
        setCenterY((Y + Math.sqrt(300)));
        hex.hexagon(X, Y + Math.sqrt(300),side);
        hex.elementsHexagon(7);
        coorArray[0][0] = X;
        coorArray[0][1] = Y + Math.sqrt(300);
        polygons.add(hex.getPolygon());

        Shapes hex2 = new Shapes();
        hex2.hexagon(X , Y + (3 * Math.sqrt(300)), side);
        hex2.elementsHexagon(7);
        coorArray[1][0] = X;
        coorArray[1][1] = Y + (2 * Math.sqrt(300));
        polygons.add(hex2.getPolygon());

        Shapes hex3 = new Shapes();
        hex3.hexagon(X ,  Y + (5 * Math.sqrt(300)), side);
        hex3.elementsHexagon(7);
        coorArray[2][0] = X;
        coorArray[2][1] = Y + (4 * Math.sqrt(300));
        polygons.add(hex3.getPolygon());

        Shapes hex4 = new Shapes();
        hex4.hexagon(X ,  Y + (7 *  Math.sqrt(300)), side);
        hex4.elementsHexagon(7);
        coorArray[3][0] = X;
        coorArray[3][1] = Y + (6 * Math.sqrt(300));
        polygons.add(hex4.getPolygon());
    }

    public void element8(){
        Shapes hex = new Shapes();
        coorArray = new double[4][2];
        setCenterX(X);
        setCenterY((Y + Math.sqrt(300)));
        hex.hexagon(X, Y + Math.sqrt(300),side);
        hex.elementsHexagon(8);
        coorArray[0][0] = X;
        coorArray[0][1] = Y;
        polygons.add(hex.getPolygon());

        Shapes hex2 = new Shapes();
        hex2.hexagon(X , Y + (3 * Math.sqrt(300)), side);
        hex2.elementsHexagon(8);
        coorArray[1][0] = X;
        coorArray[1][1] = Y + (2 * Math.sqrt(300));
        polygons.add(hex2.getPolygon());

        Shapes hex3 = new Shapes();
        hex3.hexagon(X + 30,  Y + (4 * Math.sqrt(300)), side);
        hex3.elementsHexagon(8);
        coorArray[2][0] = X + 30;
        coorArray[2][1] = Y + (3 * Math.sqrt(300));
        polygons.add(hex3.getPolygon());

        Shapes hex4 = new Shapes();
        hex4.hexagon(X - 30,  Y + (4 *  Math.sqrt(300)), side);
        hex4.elementsHexagon(8);
        coorArray[3][0] = X - 30;
        coorArray[3][1] = Y + (3 * Math.sqrt(300));
        polygons.add(hex4.getPolygon());
    }
}
