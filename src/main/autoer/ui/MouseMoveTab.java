package autoer.ui;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

import autoer.Application;
import autoer.ui.actions.MouseMove;

import com.jeta.forms.components.panel.FormPanel;

public class MouseMoveTab {

  private MouseMove move;
  private FormUI form;
  private Application autoer;
  private JTextArea descriptionTextField;
  private JButton addButton;
  private JButton clearButton;
  private JButton saveButton;
  private JSpinner xSpinner;
  private JSpinner ySpinner;
  private JCheckBox smoothCheckBox;

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

  public MouseMoveTab(FormUI formUI, FormPanel form, Application autoer) {
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
    xSpinner = (JSpinner) form.getComponentByName("MouseMove.xSpinner");
    ySpinner = (JSpinner) form.getComponentByName("MouseMove.ySpinner");
    smoothCheckBox = (JCheckBox) form
        .getComponentByName("MouseMove.smoothCheckBox");
    descriptionTextField = (JTextArea) form
        .getComponentByName("MouseMove.descriptionTextArea");
    addButton = (JButton) form.getComponentByName("MouseMove.addButton");
    clearButton = (JButton) form.getComponentByName("MouseMove.clearButton");
    saveButton = (JButton) form.getComponentByName("MouseMove.saveButton");
  }

  public void loadAction(MouseMove move) {
    this.move = move;

    xSpinner.setValue((int) move.getEndPoint().getX());
    ySpinner.setValue((int) move.getEndPoint().getY());
    smoothCheckBox.setSelected(move.isSmooth());
    descriptionTextField.setText(move.getDescription());
  }

  public void saveAction() {
    if (isValid()) {

      Integer x = (Integer) xSpinner.getValue();
      Integer y = (Integer) ySpinner.getValue();

      move.setEndPoint(new Point(x.intValue(), y.intValue()));
      move.setSmooth(smoothCheckBox.isSelected());
      move.setDescription(descriptionTextField.getText());
    } else {
      JOptionPane.showMessageDialog(null, "Error!");
    }
  }

  private void addAction() {
    Integer x = (Integer) xSpinner.getValue();
    Integer y = (Integer) ySpinner.getValue();
    boolean smooth = smoothCheckBox.isSelected();
    String description = descriptionTextField.getText();

    if (isValid()) {
      autoer.addValue(new MouseMove(new Point(x.intValue(), y.intValue()),
          smooth, description));
      clearFields();
      autoer.updateTree();
    } else {
      JOptionPane.showMessageDialog(null, "Error!");
    }

  }

  private void clearFields() {
    xSpinner.setValue(0);
    ySpinner.setValue(0);
    smoothCheckBox.setSelected(false);
    descriptionTextField.setText("");
  }

  public void setPoints() {
    PointerInfo mouseInfo = MouseInfo.getPointerInfo();
    Point point = mouseInfo.getLocation();

    xSpinner.setValue((int) point.getX());
    ySpinner.setValue((int) point.getY());
  }

  public boolean isValid() {
    if (descriptionTextField.getText().trim().equals("")) {
      return false;
    }

    return true;
  }
}
