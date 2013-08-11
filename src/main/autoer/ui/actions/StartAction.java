package autoer.ui.actions;

import java.util.ArrayList;

public class StartAction extends Actions {

  private ArrayList<Actions> actions = new ArrayList<Actions>();
  private boolean itemRemoved = false;
  private String projectName;

  public StartAction(String projectName) {
    this.projectName = projectName;
  }

  public String getDescription() {
    return projectName;
  }

  public boolean run() {
    for (Actions action : actions) {
      action.run();
    }
    return true;
  }

  public boolean addAction(Actions action) {
    return actions.add(action);
  }

  public boolean removeAction(Actions action) {
    itemRemoved = false;
    remove(actions, action);
    return itemRemoved;
  }

  private void remove(ArrayList<Actions> actions, Object object) {
    for (Actions action : actions) {
      itemRemoved = actions.remove(object);
      if (itemRemoved) {
        return;
      }
      if (action.getClass().equals(CheckColour.class)) {
        CheckColour checkColour = (CheckColour) action;
        remove(checkColour.getActions(), object);
      }
    }
  }

  public void removeAllActions() {
    actions.clear();
  }

  public ArrayList<Actions> getActions() {
    return actions;
  }

  public void setActions(ArrayList<Actions> actions) {
    this.actions = actions;
  }

  public void moveUp(Actions action) {
    ArrayList<Actions> actionList = findAction(actions, action);

    if (actionList != null) {
      int elementPosition = actionList.indexOf(action);
      if (elementPosition > 0) {
        actionList.remove(elementPosition);
        actionList.add(elementPosition - 1, action);
      }
    }

  }

  public void moveDown(Actions action) {
    ArrayList<Actions> actionList = findAction(actions, action);

    if (actionList != null) {
      int elementPosition = actionList.indexOf(action);
      if (elementPosition >= 0 && elementPosition < actionList.size() - 1) {
        actionList.remove(elementPosition);
        actionList.add(elementPosition + 1, action);
      }
    }
  }

  private ArrayList<Actions> found = null;

  private ArrayList<Actions> findAction(ArrayList<Actions> actions, Actions target) {
    for (Actions action : actions) {
      if (action.equals(target)) {
        found = actions;
        break;
      }
      if (action.getClass().equals(CheckColour.class)) {
        CheckColour checkColour = (CheckColour) action;
        findAction(checkColour.getActions(), target);
      }
    }
    return found;
  }
}
