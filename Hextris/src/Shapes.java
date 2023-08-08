
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Shapes extends Polygon {
    private double posX = 40;
    private double posY = 30;
    private double ARR_Y = 30;
    private double ARR_X = 40;

    private Polygon hexagon;

    public Shapes(){
    }
  public void hexagon(double x, double y, double a){
      hexagon = new Polygon();
      hexagon.getPoints().addAll( x + a / 2, y + a * Math.sqrt(3)/2,
              x + a, y,
              x + a / 2, y - a * Math.sqrt(3)/2,
              x - a / 2, y - a * Math.sqrt(3)/2,
              x - a, y,
              x - a / 2, y + a * Math.sqrt(3)/2
      );
      hexagon.setFill(Color.YELLOW);
      hexagon.setStrokeWidth(4);
      hexagon.setStroke(Color.PURPLE);
  }

    public void setPosX(double newPosX){
        posX = newPosX;
    }
    public void setPosY(double newPosY){
        posY = newPosY;
    }
    public double getPosX(){
        return posX;
    }
    public double getPosY(){
        return posY;
    }

    public Polygon getPolygon() {
        return hexagon;
    }

    public void elementsHexagon(int element){
        switch (element){
            case 1:
                hexagon.setFill(Color.BLUEVIOLET);
                hexagon.setStrokeWidth(4);
                hexagon.setStroke(Color.PURPLE);
                break;
            case 2:
                hexagon.setFill(Color.LAWNGREEN);
                hexagon.setStrokeWidth(4);
                hexagon.setStroke(Color.PURPLE);
                break;
            case 3 :
                hexagon.setFill(Color.AQUA);
                hexagon.setStrokeWidth(4);
                hexagon.setStroke(Color.PURPLE);
                break;
            case 4:
                hexagon.setFill(Color.LIGHTSALMON);
                hexagon.setStrokeWidth(4);
                hexagon.setStroke(Color.PURPLE);
                break;
            case 5 :
                hexagon.setFill(Color.PLUM);
                hexagon.setStrokeWidth(4);
                hexagon.setStroke(Color.PURPLE);
                break;
            case 6 :
                hexagon.setFill(Color.PINK);
                hexagon.setStrokeWidth(4);
                hexagon.setStroke(Color.PURPLE);
                break;
            case 7 :
                hexagon.setFill(Color.FUCHSIA);
                hexagon.setStrokeWidth(4);
                hexagon.setStroke(Color.PURPLE);
                break;
            case 8 :
                hexagon.setFill(Color.RED);
                hexagon.setStrokeWidth(4);
                hexagon.setStroke(Color.PURPLE);
                break;
            case 9 :
                hexagon.setFill(Color.LIME);
                hexagon.setStrokeWidth(4);
                hexagon.setStroke(Color.PURPLE);
                break;

        }
    }

}