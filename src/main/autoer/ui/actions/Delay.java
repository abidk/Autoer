package autoer.ui.actions;

public class Delay extends Actions {

  private int delay;
  private String description;

  public Delay(Delay delay) {
    this.delay = delay.getDelay();
    this.description = delay.getDescription();
  }

  public Delay(int delay, String description) {
    this.delay = delay;
    this.description = description;
  }

  public boolean run() {
    robot.delay(delay);
    return true;
  }

  public int getDelay() {
    return delay;
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}