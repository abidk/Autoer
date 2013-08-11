package autoer.ui.actions;

import java.awt.MouseInfo;
import java.awt.Point;

public class MouseMove extends Actions {

  private Point endPoint;
  private String description;
  private boolean smooth;

  public MouseMove(MouseMove mouseMove) {
    this.endPoint = mouseMove.getEndPoint();
    this.smooth = mouseMove.isSmooth();
    this.description = mouseMove.getDescription();
  }

  public MouseMove(Point endPoint, boolean smooth, String description) {
    this.endPoint = endPoint;
    this.smooth = smooth;
    this.description = description;
  }

  public boolean run() {
    if (!smooth) {
      robot.mouseMove((int) endPoint.getX(), (int) endPoint.getY());
    } else {
      Point currentMousePosition = MouseInfo.getPointerInfo().getLocation();

      int startX = (int) currentMousePosition.getX();
      int startY = (int) currentMousePosition.getY();
      int endX = (int) endPoint.getX();
      int endY = (int) endPoint.getY();

      if (startX > endX) {
        startX = (int) endPoint.getX();
        endX = (int) currentMousePosition.getX();
      }

      if (startY > endY) {
        startY = (int) endPoint.getY();
        endY = (int) currentMousePosition.getY();
      }

      // int gcd = calculateTheGCD( (endX - startX), (endY - startY) );
      // int gcdX =(endX - startX)/gcd;
      // int gcdY =(endY - startY)/gcd;

      // if( (endX - startX) == 0 || (endY - startY) == 0) {
      // return true;
      // }

      // System.out.println(gcd + " " + startX+ " " + startY );
      while (startX < endX || startY < endY) {
        if (startX < endX) {
          startX++;
        }

        if (startY < endY) {
          startY++;
        }

        robot.delay(5);
        robot.mouseMove(startX, startY);
      }
    }
    return true;
  }

  // work out the GCD
  public int calculateTheGCD(int gcd1, int gcd2) {

    while (gcd1 != gcd2) {
      if (gcd1 > gcd2) {
        gcd1 -= gcd2;
      } else {
        gcd2 -= gcd1;
      }
    }

    return gcd1;
  }

  public Point getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(Point endPoint) {
    this.endPoint = endPoint;
  }

  public boolean isSmooth() {
    return smooth;
  }

  public void setSmooth(boolean smooth) {
    this.smooth = smooth;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
