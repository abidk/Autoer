package autoer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class Splash extends JWindow {

  private static final long serialVersionUID = 1L;

  public Splash(int duration) {
    JPanel content = (JPanel) getContentPane();
    content.setBackground(Color.GRAY);

    // Set the window's bounds, centering the window
    int width = 480;
    int height = 120;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width - width) / 2;
    int y = (screen.height - height) / 2;
    setBounds(x, y, width, height);

    JLabel lab = new JLabel("Autoer", JLabel.CENTER);
    lab.setFont(new Font("Sans-Serif", Font.BOLD, 32));
    lab.setForeground(Color.WHITE);
    content.add(lab, BorderLayout.CENTER);

    // Display it
    setVisible(true);

    // Wait a little while, maybe while loading resources
    try {
      Thread.sleep(duration);
    } catch (Exception e) {
    }

    setVisible(false);
    dispose();
  }
}