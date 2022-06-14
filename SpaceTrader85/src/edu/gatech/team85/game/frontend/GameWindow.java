package edu.gatech.team85.game.frontend;

import javax.swing.*;
import java.awt.*;

/**
 * The application window that contains the game's UI
 *
 * Replaces contents of window when player travels between regions
 */
public class GameWindow {
    private JFrame window;

    public GameWindow() {
        window = new JFrame("Space Trader");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new FlowLayout());

        // This placeholder is replaced by a menu when setWindowPanel is called
        window.add(new JLabel("Loading game..."));

        // Display the window
        window.setSize(500, 500);
        window.setVisible(true);
    }

    /**
     * Changes to a different 'page' of UI by replacing the current contents
     * of the game window with a different panel
     * @param screen an object that provides a JFrame containing one "page" of the UI
     */
    public void setWindowPanel(UIScreen screen) {
        JPanel panel = screen.getMainPanel();
        window.getContentPane().removeAll();
        window.add(panel);
        window.repaint();
        window.setVisible(true);
        panel.updateUI();
    }
}
