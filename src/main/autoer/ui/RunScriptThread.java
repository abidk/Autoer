package autoer.ui;

import java.util.List;

import autoer.ui.actions.Actions;
import autoer.ui.actions.CheckColour;

public class RunScriptThread extends Thread {

  private List<Actions> actions;
  private boolean running = true;
  private FormUI formUI;
  private int counter;

  public RunScriptThread(List<Actions> actions, FormUI formUI) {
    this.actions = actions;
    this.formUI = formUI;
  }

  public void run() {
    formUI.addStatusText("Started");
    while (running) {
      counter=0;
      execute(actions);
      if( running ) {
        formUI.addStatusText("Looping...");        
      }
    }
  }
  

  private void execute(List<Actions> actions) {
    for (Actions action : actions) {
      if( !running ) {
        break;
      }
      if (action.getClass().equals(CheckColour.class)) {
        CheckColour checkColour = (CheckColour) action;
        execute(checkColour.getActions());
      }
      formUI.addStatusText(action.getDescription());
      formUI.setProgressBarValue(counter++);
      action.run();
    }
  }

  public void stopScript() {
    running = false;
    formUI.addStatusText("Stopped");
  }
}
