package autoer.ui.actions;

import java.awt.AWTException;
import java.awt.Robot;

public abstract class Actions {

  public Robot robot;

  public Actions() {
    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }
  
  public abstract boolean run();

  public abstract String getDescription();
}
