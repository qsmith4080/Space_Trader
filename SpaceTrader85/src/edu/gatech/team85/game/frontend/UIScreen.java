package edu.gatech.team85.game.frontend;

import javax.swing.*;

/* Common interface for each separate "screen" of the UI
 * Any class that provides a "main panel" can be passed to the GameWindow object to display
 * a page.
 */
public interface UIScreen {
    JPanel getMainPanel();
}
