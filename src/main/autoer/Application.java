package autoer;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import autoer.ui.FormUI;
import autoer.ui.RunScriptThread;
import autoer.ui.actions.Actions;
import autoer.ui.actions.CheckColour;
import autoer.ui.actions.Delay;
import autoer.ui.actions.KeyPress;
import autoer.ui.actions.MouseClick;
import autoer.ui.actions.MouseMove;
import autoer.ui.actions.StartAction;
import autoer.ui.model.ActionTreeModel;

import com.jeta.forms.gui.common.FormException;

public class Application {

  public static final int SCREEN_WIDTH = 700;
  public static final int SCREEN_HEIGHT = 500;
  // public static final Dimension SCREEN_SIZE =
  // Toolkit.getDefaultToolkit().getScreenSize();

  private FormUI formUI;
  private StartAction startAction = new StartAction("NewProject");
  private ActionTreeModel actionsTree = new ActionTreeModel();
  private RunScriptThread runScriptThread = null;
  private Actions selectedItem = null;

  public Application() {
  }

  public void init() throws FormException {
    actionsTree.addModel(startAction);
    // new Splash(500);
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      // UIManager
      // .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception e) {
    }
    formUI = new FormUI(this);
    actionsTree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent e) {
        editSelected();
      }

    });
    formUI.setTitle("Autoer");
    formUI.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    formUI.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    // formUI.setLocation(screenWidth - (formUI.getWidth() / 2), screenHeight -
    // (formUI.getHeight() / 2));
    formUI.setLocationRelativeTo(null);
    formUI.setVisible(true);
    updateMouseStatus();
  }

  public void updateMouseStatus() {
    Thread thread = new Thread(new Runnable() {
      public void run() {
        Robot robot = null;
        try {
          robot = new Robot();
          while (true) {
            PointerInfo mouseInfo = MouseInfo.getPointerInfo();
            Point point = mouseInfo.getLocation();

            Color color = robot.getPixelColor((short) point.getX(), (short) point.getY());
            formUI.setStatusBar(point, color);
            Thread.sleep(40);
          }
        } catch (AWTException e1) {
          e1.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    thread.start();
  }

  public int getRandomDelay(int max) {
    Random random = new Random();
    return random.nextInt(max);
  }

  public void addValue(Actions action) {
    Actions selectedItem = actionsTree.getSelectedItem();
    if (selectedItem.getClass().equals(StartAction.class)) {
      if (startAction.addAction(action)) {
        formUI.increaseProgressBarValue();
        updateTree();
      }
    } else if (selectedItem.getClass().equals(CheckColour.class)) {
      CheckColour checkAction = (CheckColour) selectedItem;
      if (checkAction.addAction(action)) {
        formUI.increaseProgressBarValue();
        updateTree();
      }
    }
  }

  public void removeSelected() {
    if (startAction.removeAction(actionsTree.getSelectedItem())) {
      formUI.decreaseProgressBarValue();
      updateTree();
    }
  }

  public void editSelected() {
    Actions action = actionsTree.getSelectedItem();

    if (action != null) {
      if (action.getClass().equals(Delay.class)) {
        formUI.getDelayTab().loadAction((Delay) action);
        formUI.getformTabs().setSelectedIndex(FormUI.DELAY_TAB);
      }

      if (action.getClass().equals(MouseMove.class)) {
        formUI.getMouseMoveTab().loadAction((MouseMove) action);
        formUI.getformTabs().setSelectedIndex(FormUI.MOUSE_MOVE_TAB);
      }

      if (action.getClass().equals(MouseClick.class)) {
        formUI.getMouseClickTab().loadAction((MouseClick) action);
        formUI.getformTabs().setSelectedIndex(FormUI.MOUSE_CLICK_TAB);
      }

      if (action.getClass().equals(CheckColour.class)) {
        formUI.getCheckColourTab().loadAction((CheckColour) action);
        formUI.getformTabs().setSelectedIndex(FormUI.CHECK_COLOUR_TAB);
      }

      if (action.getClass().equals(KeyPress.class)) {
        formUI.getKeyPressTab().loadAction((KeyPress) action);
        formUI.getformTabs().setSelectedIndex(FormUI.PRESS_KEY_TAB);
      }
    }
  }

  public ActionTreeModel getActionTree() {
    return actionsTree;
  }

  public void removeAll() {
    startAction.removeAllActions();
    updateTree();
  }

  public void moveSelectedUp() {
    Actions currentSelection = actionsTree.getSelectedItem();
    startAction.moveUp(currentSelection);
    updateTree();
  }

  public void moveSelectedDown() {
    Actions currentSelection = actionsTree.getSelectedItem();
    startAction.moveDown(currentSelection);
    updateTree();
  }

  public void updateTree() {
    actionsTree.addModel(startAction);
  }

  public void runScript() {
    runScriptThread = new RunScriptThread(startAction.getActions(), formUI);
    runScriptThread.start();
  }

  public void stopScript() {
    runScriptThread.stopScript();
  }

  public void copy() {
    selectedItem = actionsTree.getSelectedItem();
    removeSelected();
  }

  public void paste() {
    if (selectedItem != null) {
      Actions newAction = selectedItem;
      addValue(newAction);
    }
    selectedItem = null;
  }

  public void setStartAction(StartAction startAction) {
    this.startAction = startAction;
    updateTree();
  }

  public StartAction getStartAction() {
    return startAction;
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Application application = new Application();
          application.init();
        } catch (FormException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
