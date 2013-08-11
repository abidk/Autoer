package autoer.ui.model;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ModifiersCellRenderer extends JLabel implements ListCellRenderer {

  private static final long serialVersionUID = 1L;

  public ModifiersCellRenderer() {
    setOpaque(true);
    setHorizontalAlignment(CENTER);
    setVerticalAlignment(CENTER);
  }

  /*
   * This method finds the image and text corresponding to the selected value
   * and returns the label, set up to display the text and image.
   */
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    // Get the selected index. (The index param isn't
    // always valid, so just use the value.)
    String string = (String) value;

    if (isSelected) {
      setBackground(list.getSelectionBackground());
      setForeground(list.getSelectionForeground());
    } else {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }
    setText(string);
    setFont(list.getFont());
    return this;
  }
}
