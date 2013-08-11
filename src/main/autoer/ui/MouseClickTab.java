package autoer.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import autoer.Application;
import autoer.ui.actions.MouseClick;

import com.jeta.forms.components.panel.FormPanel;

public class MouseClickTab {

  private MouseClick click;
  private FormUI form;
  private Application autoer;
  private JTextArea descriptionTextField;
  private JButton addButton;
  private JButton clearButton;
  private JButton saveButton;
  private JComboBox mouseButtonComboBox;

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

  public MouseClickTab(FormUI formUI, FormPanel form, Application autoer) {
    this.autoer = autoer;
    this.form = formUI;
    fetchUIComponents(form);
    init();
  }

  private void init() {
    mouseButtonComboBox.addItem("Left Click");
    mouseButtonComboBox.addItem("Middle Click");
    mouseButtonComboBox.addItem("Right Click");

    addButton.addActionListener(buttonListener);
    clearButton.addActionListener(buttonListener);
    saveButton.addActionListener(buttonListener);
  }

  private void fetchUIComponents(FormPanel form) {
    mouseButtonComboBox = (JComboBox) form
        .getComponentByName("MouseClick.mouseButtonComboBox");
    descriptionTextField = (JTextArea) form
        .getComponentByName("MouseClick.descriptionTextArea");
    addButton = (JButton) form.getComponentByName("MouseClick.addButton");
    saveButton = (JButton) form.getComponentByName("MouseClick.saveButton");
    clearButton = (JButton) form.getComponentByName("MouseClick.clearButton");
  }

  public void loadAction(MouseClick click) {
    this.click = click;
    int type = click.getButtonType();

    if (type == MouseClick.button1) {
      mouseButtonComboBox.setSelectedItem("Left Click");
    } else if (type == MouseClick.button2) {
      mouseButtonComboBox.setSelectedItem("Middle Click");
    } else if (type == MouseClick.button3) {
      mouseButtonComboBox.setSelectedItem("Right Click");
    }

    descriptionTextField.setText(click.getDescription());
  }

  public void saveAction() {
    if (isValid()) {
      String selectedItem = (String) mouseButtonComboBox.getSelectedItem();
      int buttonType = -1;

      if (selectedItem.equals("Left Click")) {
        buttonType = MouseClick.button1;
      } else if (selectedItem.equals("Middle Click")) {
        buttonType = MouseClick.button2;
      } else if (selectedItem.equals("Right Click")) {
        buttonType = MouseClick.button3;
      }

      click.setButtonType(buttonType);
      click.setDescription(descriptionTextField.getText());
      autoer.updateTree();
    }
  }

  private void addAction() {
    if (isValid()) {
      String selectedItem = (String) mouseButtonComboBox.getSelectedItem();
      int buttonType = -1;

      if (selectedItem.equals("Left Click")) {
        buttonType = MouseClick.button1;
      } else if (selectedItem.equals("Middle Click")) {
        buttonType = MouseClick.button2;
      } else if (selectedItem.equals("Right Click")) {
        buttonType = MouseClick.button3;
      }

      String description = descriptionTextField.getText();

      autoer.addValue(new MouseClick(buttonType, description));
      clearFields();
    } else {
      JOptionPane.showMessageDialog(null, "Error!");
    }
  }

  private void clearFields() {
    mouseButtonComboBox.setSelectedIndex(0);
    descriptionTextField.setText("");
  }

  public boolean isValid() {
    if (descriptionTextField.getText().trim().equals("")) {
      return false;
    }

    return true;
  }
}
