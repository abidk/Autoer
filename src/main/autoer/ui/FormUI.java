package autoer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;

import autoer.Application;
import autoer.ui.actions.StartAction;

import com.jeta.forms.components.label.JETALabel;
import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

public class FormUI extends JFrame {

  private static final long serialVersionUID = 1L;

  public static final String FORM_FILE = "forms/Runescape.jfrm";
  public static final int DELAY_TAB = 0;
  public static final int MOUSE_MOVE_TAB = 1;
  public static final int MOUSE_CLICK_TAB = 2;
  public static final int CHECK_COLOUR_TAB = 3;
  public static final int PRESS_KEY_TAB = 4;

  private Application autoer;

  // Tabs
  private DelayTab delayTab;
  private MouseMoveTab mouseMoveTab;
  private MouseClickTab mouseClickTab;
  private CheckColourTab colourTab;
  private KeyPressTab keyPressTab;

  // components
  private FormPanel form;
  private JMenuItem newMacroItem;
  private JMenuItem loadMacroItem;
  private JMenuItem saveMacroItem;
  private JMenuItem exitItem;
  private JMenuItem aboutItem;
  private JTabbedPane formTabs;
  private JButton startButton;
  private JButton stopButton;
  private JButton moveUpButton;
  private JButton moveDownButton;
  private JButton copyButton;
  private JButton pasteButton;
  private JTextArea statusTextField;
  private JScrollPane actionScrollPanel;
  private JButton removeButton;
  private JButton removeAllButton;
  private JETALabel mousePositionLabel;
  private JETALabel mouseTextColourLabel;
  private JPanel mouseColourColourPanel;
  private JProgressBar progressBar;
  private JLabel progressLabel;

  private Action doNothing = new AbstractAction() {
    private static final long serialVersionUID = 1L;

    public void actionPerformed(ActionEvent e) {
      switch (getformTabs().getSelectedIndex()) {
      case DELAY_TAB:
        break;

      case MOUSE_MOVE_TAB:
        getMouseMoveTab().setPoints();
        break;

      case MOUSE_CLICK_TAB:
        break;

      case CHECK_COLOUR_TAB:
        getCheckColourTab().setPoints();
        break;

      case PRESS_KEY_TAB:
        break;

      }
    }
  };

  private Action changeColour = new AbstractAction() {
    private static final long serialVersionUID = 1L;

    public void actionPerformed(ActionEvent e) {
      switch (getformTabs().getSelectedIndex()) {
      case DELAY_TAB:
        break;

      case MOUSE_MOVE_TAB:
        break;

      case MOUSE_CLICK_TAB:
        break;

      case CHECK_COLOUR_TAB:
        getCheckColourTab().setColour();
        break;

      case PRESS_KEY_TAB:
        break;

      }
    }
  };

  private WindowListener windowListener = new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
      exit();
    }
  };

  private final ActionListener actionListener = new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
      if (evt.getSource() == newMacroItem) {
        autoer.removeAll();
      } else if (evt.getSource() == loadMacroItem) {
        loadScript();
      } else if (evt.getSource() == saveMacroItem) {
        saveScript();
      } else if (evt.getSource() == exitItem) {
        exit();
      } else if (evt.getSource() == aboutItem) {
        JOptionPane.showMessageDialog(null, "Written by Abid.");
      } else if (evt.getSource() == removeButton) {
        autoer.removeSelected();
      } else if (evt.getSource() == removeAllButton) {
        autoer.removeAll();
      } else if (evt.getSource() == moveUpButton) {
        autoer.moveSelectedUp();
      } else if (evt.getSource() == moveDownButton) {
        autoer.moveSelectedDown();
      } else if (evt.getSource() == copyButton) {
        autoer.copy();
      } else if (evt.getSource() == pasteButton) {
        autoer.paste();
      } else if (evt.getSource() == startButton) {
        setButtonsEnabled(false);
        autoer.runScript();

      } else if (evt.getSource() == stopButton) {
        setButtonsEnabled(true);
        autoer.stopScript();
      }
    }
  };

  public FormUI(Application autoer) throws FormException {
    this.autoer = autoer;
    this.form = loadForm();
    fetchUIComponents(form);
    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(form, BorderLayout.CENTER);
    this.setJMenuBar(createFileMenu());
    this.addWindowListener(windowListener);

    // add some buttom mappins
    this.getformTabs().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), "doSomething");
    this.getformTabs().getActionMap().put("doSomething", doNothing);
    this.getformTabs().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "changeColour");
    this.getformTabs().getActionMap().put("changeColour", changeColour);
    init();
  }

  private JMenuBar createFileMenu() {
    // Create file menu
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");

    newMacroItem = new JMenuItem("New Macro");
    newMacroItem.setEnabled(true);
    newMacroItem.addActionListener(actionListener);

    loadMacroItem = new JMenuItem("Load Macro");
    loadMacroItem.setEnabled(true);
    loadMacroItem.addActionListener(actionListener);

    saveMacroItem = new JMenuItem("Save Macro");
    saveMacroItem.setEnabled(true);
    saveMacroItem.addActionListener(actionListener);

    exitItem = new JMenuItem("Exit");
    exitItem.setEnabled(true);
    exitItem.addActionListener(actionListener);

    // Add to menu
    fileMenu.add(newMacroItem);
    fileMenu.add(loadMacroItem);
    fileMenu.add(saveMacroItem);
    fileMenu.addSeparator();
    fileMenu.add(exitItem);

    JMenu helpMenu = new JMenu("Help");
    aboutItem = new JMenuItem("About");
    aboutItem.setEnabled(true);
    aboutItem.addActionListener(actionListener);
    helpMenu.add(aboutItem);

    menuBar.add(fileMenu);
    menuBar.add(helpMenu);

    return menuBar;
  }

  private void init() {
    // formTabs.addChangeListener( changeListener );
    actionScrollPanel.setViewportView(autoer.getActionTree());
    delayTab = new DelayTab(this, form, autoer);
    mouseClickTab = new MouseClickTab(this, form, autoer);
    mouseMoveTab = new MouseMoveTab(this, form, autoer);
    colourTab = new CheckColourTab(this, form, autoer);
    keyPressTab = new KeyPressTab(this, form, autoer);
    startButton.addActionListener(actionListener);
    stopButton.addActionListener(actionListener);
    removeButton.addActionListener(actionListener);
    removeAllButton.addActionListener(actionListener);
    copyButton.addActionListener(actionListener);
    pasteButton.addActionListener(actionListener);

    URL upUrl = this.getClass().getResource("forms/up.png");
    moveUpButton.setText("");
    moveUpButton.setIcon(new ImageIcon(upUrl));
    moveUpButton.addActionListener(actionListener);

    URL downUrl = this.getClass().getResource("forms/down.png");
    moveDownButton.setText("");
    moveDownButton.setIcon(new ImageIcon(downUrl));
    moveDownButton.addActionListener(actionListener);
    setButtonsEnabled(true);

    mousePositionLabel.setText("");
    mouseTextColourLabel.setText("");

    mouseColourColourPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    statusTextField.setEditable(false);
    progressBar.setMinimum(0);
    progressBar.setMaximum(0);
    progressBar.setValue(0);
    progressLabel.setText("0\\0");
  }

  private void setButtonsEnabled(boolean bool) {
    startButton.setEnabled(bool);
    stopButton.setEnabled(!bool);
  }

  private void fetchUIComponents(FormPanel form) {
    actionScrollPanel = (JScrollPane) form.getComponentByName("actionScrollPanel");
    formTabs = (JTabbedPane) form.getComponentByName("commandsTab");
    startButton = (JButton) form.getComponentByName("startButton");
    stopButton = (JButton) form.getComponentByName("stopButton");
    statusTextField = (JTextArea) form.getComponentByName("statusTextArea");
    copyButton = (JButton) form.getComponentByName("copyButton");
    pasteButton = (JButton) form.getComponentByName("pasteButton");
    removeButton = (JButton) form.getComponentByName("removeButton");
    removeAllButton = (JButton) form.getComponentByName("removeAllButton");
    mousePositionLabel = (JETALabel) form.getComponentByName("mousePositionLabel");
    mouseTextColourLabel = (JETALabel) form.getComponentByName("mouseTextColourLabel");
    mouseColourColourPanel = (JPanel) form.getComponentByName("mouseColourColourPanel");

    progressBar = (JProgressBar) form.getComponentByName("progressBar");
    progressLabel = (JLabel) form.getComponentByName("progressLabel");
    moveUpButton = (JButton) form.getComponentByName("moveUpButton");
    moveDownButton = (JButton) form.getComponentByName("moveDownButton");
  }

  public void addStatusText(String input) {
    statusTextField.append(input + "\n");
    statusTextField.setCaretPosition(statusTextField.getText().length());
  }

  public void addStatusText(int input) {
    statusTextField.append(input + "\n");
    statusTextField.setCaretPosition(statusTextField.getText().length());
  }

  public void addStatusText(boolean input) {
    statusTextField.append(input + "\n");
    statusTextField.setCaretPosition(statusTextField.getText().length());
  }

  public void clearStatusText() {
    statusTextField.setText(null);
  }

  public void exit() {
    int answer = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", null,
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    if (answer == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
  }

  private FormPanel loadForm() throws FormException {
    InputStream formIn = null;
    try {
      formIn = this.getClass().getResourceAsStream(FORM_FILE);
      return new FormPanel(formIn);
    } finally {
      if (formIn != null) {
        try {
          formIn.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  public void setStatusBar(Point location, Color colour) {
    mousePositionLabel.setText("X:" + (short) location.getX() + " Y:" + (short) location.getY());
    mouseTextColourLabel.setText("RGB: " + colour.getRed() + "," + colour.getGreen() + "," + colour.getBlue());
    mouseColourColourPanel.setBackground(colour);
  }

  public CheckColourTab getCheckColourTab() {
    return colourTab;
  }

  public void setColourTab(CheckColourTab colourTab) {
    this.colourTab = colourTab;
  }

  public DelayTab getDelayTab() {
    return delayTab;
  }

  public void setDelayTab(DelayTab delayTab) {
    this.delayTab = delayTab;
  }

  public MouseClickTab getMouseClickTab() {
    return mouseClickTab;
  }

  public void setMouseClickTab(MouseClickTab mouseClickTab) {
    this.mouseClickTab = mouseClickTab;
  }

  public MouseMoveTab getMouseMoveTab() {
    return mouseMoveTab;
  }

  public void setMouseMoveTab(MouseMoveTab mouseMoveTab) {
    this.mouseMoveTab = mouseMoveTab;
  }

  public KeyPressTab getKeyPressTab() {
    return keyPressTab;
  }

  public void setKeyPressTab(KeyPressTab keyPressTab) {
    this.keyPressTab = keyPressTab;
  }

  public JTabbedPane getformTabs() {
    return formTabs;
  }

  public void increaseProgressBarValue() {
    progressBar.setMaximum(progressBar.getMaximum() + 1);
    setProgressLabelText();
  }

  public void decreaseProgressBarValue() {
    progressBar.setMaximum(progressBar.getMaximum() - 1);
    setProgressLabelText();
  }

  public void setProgressBarValue(int i) {
    progressBar.setValue(i);
    setProgressLabelText();
  }

  public void setProgressLabelText() {
    int max = progressBar.getMaximum();
    int currentValue = progressBar.getValue();
    progressLabel.setText(currentValue + "\\" + max);
  }

  public void loadScript() {
    JFileChooser fc = new JFileChooser();

    fc.setFileFilter(new SRTFileFilter());
    int returnVal = fc.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();

      InputStream testDataIn = null;
      try {
        IBindingFactory bindingFactory = BindingDirectory.getFactory(StartAction.class);
        IMarshallingContext marshallingContext = bindingFactory.createMarshallingContext();
        marshallingContext.setIndent(2);

        IUnmarshallingContext unmarshallingContext = bindingFactory.createUnmarshallingContext();

        testDataIn = new FileInputStream(file);
        StartAction application = (StartAction) unmarshallingContext.unmarshalDocument(testDataIn, null);
        autoer.setStartAction(application);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (testDataIn != null) {
          try {
            testDataIn.close();
          } catch (IOException e) {
          }
        }
      }
    }
  }

  private void saveScript() {
    JFileChooser fc = new JFileChooser();
    fc.setFileFilter(new SRTFileFilter());
    int returnVal = fc.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      StartAction data = autoer.getStartAction();

      InputStream testDataIn = null;
      try {
        IBindingFactory bindingFactory = BindingDirectory.getFactory(StartAction.class);
        IMarshallingContext marshallingContext = bindingFactory.createMarshallingContext();
        marshallingContext.setIndent(2);

        FileOutputStream fileOutputStream = null;
        if (file.getName().endsWith(".srt")) {
          fileOutputStream = new FileOutputStream(file);
        } else {
          fileOutputStream = new FileOutputStream(file + ".srt");
        }

        marshallingContext.marshalDocument(data, "UTF-8", null, fileOutputStream);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (testDataIn != null) {
          try {
            testDataIn.close();
          } catch (IOException e) {
          }
        }
      }
    }

  }

}