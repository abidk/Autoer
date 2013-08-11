package autoer.ui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

import autoer.Application;
import autoer.ui.actions.CheckColour;

import com.jeta.forms.components.colors.JETAColorWell;
import com.jeta.forms.components.panel.FormPanel;

public class CheckColourTab {

  private CheckColour colour;
  private FormUI form;
  private Application autoer;
  private JTextArea descriptionTextField;
  private JButton addButton;
  private JButton clearButton;
  private JButton saveButton;
  private JSpinner xSpinner;
  private JSpinner ySpinner;
  private JETAColorWell colorCheckBox;

  private ActionListener buttonListener = new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
      if (evt.getSource() == addButton) {
        addAction();
      } else if (evt.getSource() == clearButton) {
        clearFields();
      } else if (evt.getSource() == saveButton) {
        saveAction();
      }
    }
  };

  public CheckColourTab(FormUI formUI, FormPanel form, Application autoer) {
    this.autoer = autoer;
    this.form = formUI;
    fetchUIComponents(form);
    init();
  }

  private void init() {
    addButton.addActionListener(buttonListener);
    clearButton.addActionListener(buttonListener);
    saveButton.addActionListener(buttonListener);
  }

  private void fetchUIComponents(FormPanel form) {
    xSpinner = (JSpinner) form.getComponentByName("GetColour.xSpinner");
    ySpinner = (JSpinner) form.getComponentByName("GetColour.ySpinner");
    colorCheckBox = (JETAColorWell) form.getComponentByName("GetColour.colourColorWell");
    descriptionTextField = (JTextArea) form.getComponentByName("GetColour.descriptionTextArea");
    addButton = (JButton) form.getComponentByName("GetColour.addButton");
    clearButton = (JButton) form.getComponentByName("GetColour.clearButton");
    saveButton = (JButton) form.getComponentByName("GetColour.saveButton");
  }

  public void loadAction(CheckColour colour) {
    this.colour = colour;
    xSpinner.setValue((int) colour.getPoint().getX());
    ySpinner.setValue((int) colour.getPoint().getY());
    colorCheckBox.setColor(colour.getColor());
    descriptionTextField.setText(colour.getDescription());
  }

  public void saveAction() {
    if (isValid()) {
      colour.setPoint(new Point((Integer) xSpinner.getValue(), (Integer) ySpinner.getValue()));
      colour.setColor(colorCheckBox.getColor());
      colour.setDescription(descriptionTextField.getText());
      autoer.updateTree();
    }

  }

  private void addAction() {
    int x = (Integer) xSpinner.getValue();
    int y = (Integer) ySpinner.getValue();
    Color color = colorCheckBox.getColor();
    String description = descriptionTextField.getText();
    if (isValid()) {
      autoer.addValue(new CheckColour(new Point(x, y), color, description));
      clearFields();
    } else {
      JOptionPane.showMessageDialog(null, "Error!");
    }
  }

  private void clearFields() {
    xSpinner.setValue(0);
    ySpinner.setValue(0);
    colorCheckBox.setColor(Color.BLACK);
    descriptionTextField.setText("");
  }

  public void setPoints() {
    PointerInfo mouseInfo = MouseInfo.getPointerInfo();
    Point point = mouseInfo.getLocation();

    xSpinner.setValue((int) point.getX());
    ySpinner.setValue((int) point.getY());
  }

  public void setColour() {
    Robot robot;
    try {
      robot = new Robot();
      PointerInfo mouseInfo = MouseInfo.getPointerInfo();
      Point point = mouseInfo.getLocation();
      Color color = robot.getPixelColor((short) point.getX(), (short) point.getY());
      colorCheckBox.setColor(color);
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

  public boolean isValid() {
    if (descriptionTextField.getText().trim().equals("")) {
      return false;
    }

    return true;
  }
}
