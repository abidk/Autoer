package autoer.ui.actions;

import java.awt.event.InputEvent;

public class MouseClick extends Actions {

  public static int button1 = InputEvent.BUTTON1_MASK;
  public static int button2 = InputEvent.BUTTON2_MASK;
  public static int button3 = InputEvent.BUTTON3_MASK;

  private int buttonType;
  private String description;

  public MouseClick(MouseClick mouseClick) {
    this.buttonType = mouseClick.getButtonType();
    this.description = mouseClick.getDescription();
  }

  public MouseClick(int buttonType, String description) {
    this.buttonType = buttonType;
    this.description = description;
  }

  public boolean run() {
    robot.mousePress(buttonType);
    robot.mouseRelease(buttonType);
    return true;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getButtonType() {
    return buttonType;
  }

  public void setButtonType(int buttonType) {
    this.buttonType = buttonType;
  }
}
