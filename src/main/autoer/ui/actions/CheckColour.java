package autoer.ui.actions;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class CheckColour extends Actions {

  private Point point;
  private Color color;
  private String description;

  private ArrayList<Actions> actions = new ArrayList<Actions>();

  public CheckColour(CheckColour checkColour) {
    this.point = checkColour.getPoint();
    this.color = checkColour.getColor();
    this.description = checkColour.getDescription();
  }

  public CheckColour(Point point, Color color, String description) {
    this.point = point;
    this.color = color;
    this.description = description;
  }

  public boolean run() {
    Color pixelColor = robot.getPixelColor((int) point.getX(), (int) point.getY());

    if (pixelColor.equals(color)) {
      for (Actions action : actions) {
        action.run();
      }
    }
    return true;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Point getPoint() {
    return point;
  }

  public void setPoint(Point point) {
    this.point = point;
  }

  public boolean addAction(Actions action) {
    return actions.add(action);
  }

  public void removeAction(Actions action) {
    if (action.getClass().equals(CheckColour.class)) {
      CheckColour checkColour = (CheckColour) action;
      checkColour.removeAction(action);
    } else {
      actions.remove(action);
    }
  }

  public ArrayList<Actions> getActions() {
    return actions;
  }

  public void setActions(ArrayList<Actions> actions) {
    this.actions = actions;
  }
}
