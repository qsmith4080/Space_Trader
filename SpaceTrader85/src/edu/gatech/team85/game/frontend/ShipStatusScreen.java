package edu.gatech.team85.game.frontend;

import edu.gatech.team85.game.backend.Ship;

import javax.swing.*;

public class ShipStatusScreen implements UIScreen {

    private JPanel panel;

    public ShipStatusScreen(GameWindow parent, UIScreen caller, Ship ship) {
        panel = new JPanel();
        panel.add(new JLabel("Ship type: " + ship.getShipType()));
        panel.add(new JLabel(" ")); // spacer
        panel.add(new JLabel("Cargo Bay Size: " + ship.getMaxCargoSpace()));
        panel.add(new JLabel("Unused cargo space: " + ship.getUnusedCargoSpace()));
        panel.add(new JLabel(" ")); // spacer
        panel.add(new JLabel("Fuel Capacity: " + ship.getFuelCapacity()));
        panel.add(new JLabel("Current Fuel: " + ship.getCurrentFuel()));
        panel.add(new JLabel(" ")); // spacer
        panel.add(new JLabel("Max Health Points: " + ship.getMaxHealth()));
        panel.add(new JLabel("Current  Health Points: " + ship.getCurrentHealth()));
        panel.add(new JLabel(" ")); // spacer
        JButton returnButton = new JButton("Back");
        // button to return to previous screen
        returnButton.addActionListener(e -> {
            parent.setWindowPanel(caller);
        });
        panel.add(returnButton);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }


    @Override
    public JPanel getMainPanel() {
        return panel;
    }
}
