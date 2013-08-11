package autoer.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import autoer.Application;
import autoer.ui.actions.KeyPress;
import autoer.ui.model.ModifiersCellRenderer;

import com.jeta.forms.components.panel.FormPanel;

public class KeyPressTab {

  private KeyPress keyPress;
  private FormUI form;
  private Application autoer;
  private JTextField keyStrokesTextField;
  private JTextArea descriptionTextField;
  private JButton addButton;
  private JButton clearButton;
  private JButton saveButton;
  private JComboBox modifiersCombo;

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

  public KeyPressTab(FormUI formUI, FormPanel form, Application autoer) {
    this.autoer = autoer;
    this.form = formUI;
    fetchUIComponents(form);
    init();
  }

  private void init() {
    addButton.addActionListener(buttonListener);
    clearButton.addActionListener(buttonListener);
    saveButton.addActionListener(buttonListener);
    modifiersCombo.setRenderer(new ModifiersCellRenderer());
  }

  private void fetchUIComponents(FormPanel form) {
    keyStrokesTextField = (JTextField) form
        .getComponentByName("KeyPress.keysTextField");
    modifiersCombo = (JComboBox) form
        .getComponentByName("KeyPress.modifiersCombo");
    descriptionTextField = (JTextArea) form
        .getComponentByName("KeyPress.descriptionTextArea");
    addButton = (JButton) form.getComponentByName("KeyPress.addButton");
    clearButton = (JButton) form.getComponentByName("KeyPress.clearButton");
    saveButton = (JButton) form.getComponentByName("KeyPress.saveButton");
  }

  public void loadAction(KeyPress keyPress) {
    this.keyPress = keyPress;
    keyStrokesTextField.setText(keyPress.getText());
    descriptionTextField.setText(keyPress.getDescription());
  }

  public void saveAction() {
    if (isValid()) {
      keyPress.setText(keyStrokesTextField.getText());
      keyPress.setDescription(descriptionTextField.getText());
      autoer.updateTree();
    } else {
      JOptionPane.showMessageDialog(null, "Error!");
    }
  }

  private void addAction() {
    String text = keyStrokesTextField.getText();
    String description = descriptionTextField.getText();
    // int modifier = (Integer)modifiersCombo.getSelectedItem();
    if (isValid()) {
      autoer.addValue(new KeyPress(text, 0, description));
      clearFields();
    } else {
      JOptionPane.showMessageDialog(null, "Error!");
    }
  }

  private void clearFields() {
    keyStrokesTextField.setText("");
    descriptionTextField.setText("");
  }

  public boolean isValid() {
    if (descriptionTextField.getText().trim().equals("")
        || keyStrokesTextField.getText().trim().equals("")) {
      return false;
    }

    return true;
  }
}
