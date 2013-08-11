package autoer.ui.model;

import java.awt.Color;
import java.awt.Point;

public class Format {

  private Format() {
  }

  public static Point deserializeStringPoint(String text) {
    String[] points = text.split(",");
    return new Point(Integer.valueOf(points[0]), Integer.valueOf(points[1]));
  }

  public static String serializePointString(Point point) {
    return (int) point.getX() + "," + (int) point.getY();
  }

  public static Color deserializeStringColor(String text) {
    String[] points = text.split(",");
    return new Color(Integer.valueOf(points[0]), Integer.valueOf(points[1]), Integer.valueOf(points[2]));
  }

  public static String serializeColorString(Color color) {
    return color.getRed() + "," + color.getGreen() + "," + color.getBlue();
  }
}
