package autoer.ui.actions;

import autoer.ui.model.ExtendedKeyEvent;

public class KeyPress extends Actions {

  private String text;
  private String description;
  private int modifier;

  public KeyPress(String text, int modifier, String description) {
    this.text = text;
    this.modifier = modifier;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean run() {
    for (Character character : text.toCharArray()) {
      String key = String.valueOf(Character.toUpperCase(character));
      int keyCode = ExtendedKeyEvent.getKeyCode(key);
      robot.keyPress(keyCode);
      robot.keyRelease(keyCode);
    }
    return true;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getModifier() {
    return modifier;
  }

  public void setModifier(int modifier) {
    this.modifier = modifier;
  }
}
