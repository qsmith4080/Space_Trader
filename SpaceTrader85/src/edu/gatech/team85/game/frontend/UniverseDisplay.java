package edu.gatech.team85.game.frontend;

import edu.gatech.team85.game.backend.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class UniverseDisplay implements UIScreen {

    private JPanel mainPanel;
    private JPanel universeDisplayPanel;
    private JPanel informationPanel;
    private GameWindow parentWindow;
    private Region currentRegion;
    private List<Region> regions = Universe.getInstance().getRegions();
    private JLabel currentLocationLabel;
    private Player player;

    public UniverseDisplay(GameWindow parent) {
        parentWindow = parent;
        universeDisplayPanel = new JPanel();
        informationPanel = new JPanel();
        player = Game.getPlayerCharacter();
        currentRegion = player.getCurrentLocationRegion();

        informationPanel.add(new JLabel("Universe"));
        currentLocationLabel = new JLabel("You are currently in " + currentRegion.getName());
        informationPanel.add(currentLocationLabel);
        informationPanel.add(new JLabel("Current fuel: " + Game.getPlayerShip().getCurrentFuel()));
        JButton backButton = new JButton("Back to " + currentRegion.getName());
        informationPanel.add(backButton);
        backButton.addActionListener(e -> returnToCurrentRegion());


        // add buttons for regions
        for (Region region : Universe.getInstance().getRegions()) {
            JButton regionButton = new JButton(region.getName());
            regionButton.addActionListener(e -> travel(region));
            universeDisplayPanel.add(regionButton);

            // add label
            universeDisplayPanel.add(new JLabel(String.format(
                    "Distance: %.2f, Fuel cost: %d, Tech Level: %s", getDistance(region),
                    Game.getPlayerShip().fuelCost(region), region.getTechLevel())));
        }

        GridLayout regionsLayout = new GridLayout(12, 2);
        regionsLayout.setHgap(5);
        regionsLayout.setVgap(5);
        universeDisplayPanel.setLayout(regionsLayout);
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
        universeDisplayPanel.updateUI();
        informationPanel.updateUI();

        mainPanel = new JPanel();
        mainPanel.add(informationPanel, BorderLayout.NORTH);
        mainPanel.add(universeDisplayPanel, BorderLayout.CENTER);
        mainPanel.repaint();
        mainPanel.setVisible(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void travel(Region destination) {
        Ship playerShip = Game.getPlayerShip();
        // check for sufficient fuel
        if (!playerShip.canTravelTo(destination)) {
            // Display a pop-up when the player has insufficient fuel.
            String errorMessage = "Travel to " + destination
                    + " requires  "
                    + playerShip.fuelCost(destination) + " fuel units.";
            JOptionPane.showMessageDialog(new JFrame(),
                    errorMessage,
                    "Insufficient Fuel",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        if (destination == currentRegion) {
            currentLocationLabel.setText("You are already in " + currentRegion.getName());
            currentLocationLabel.setForeground(Color.red);
        } else {
            playerShip.travel(destination);
            parentWindow.setWindowPanel(new RegionsDisplay(parentWindow));
        }
    }

    private void returnToCurrentRegion() {
        parentWindow.setWindowPanel(new RegionsDisplay(parentWindow));
    }

    private double getDistance(Region region) {
        return currentRegion.distanceFrom(region);
    }
}
