package edu.gatech.team85.game.frontend;

import edu.gatech.team85.game.backend.Game;
import edu.gatech.team85.game.backend.Player;
import edu.gatech.team85.game.backend.Region;

import javax.swing.*;
import java.awt.*;

public class RegionsDisplay implements UIScreen {

    private JPanel regionsDisplayPanel;
    private GameWindow parentWindow;

    public RegionsDisplay(GameWindow parent) {
        parentWindow = parent;
        Player player = Game.getPlayerCharacter();
        Region currentRegion = player.getCurrentLocationRegion();
        regionsDisplayPanel = new JPanel();

        regionsDisplayPanel.add(new JLabel(currentRegion.getName()));
        regionsDisplayPanel.add(new JLabel("You traveled to " + currentRegion.getName()));
        regionsDisplayPanel.add(new JLabel("Location: (" + currentRegion.getxCoordinate()
                + ", " + currentRegion.getyCoordinate() + ")"));
        regionsDisplayPanel.add(new JLabel("Tech Level: " + currentRegion.getTechLevel()));

        JButton travelButton = new JButton("Travel");
        travelButton.addActionListener(e -> travel());
        regionsDisplayPanel.add(travelButton);

        JButton shipStatusButton = new JButton("View Ship Status");
        shipStatusButton.addActionListener(e -> {
            // go to market buy screen
            parentWindow.setWindowPanel(
                    new ShipStatusScreen(parent, this, Game.getPlayerShip()));
        });
        regionsDisplayPanel.add(shipStatusButton);

        JButton buyButton = new JButton("Buy Goods");
        buyButton.addActionListener(e -> {
            // go to market buy screen
            parentWindow.setWindowPanel(new MarketBuyScreen(parentWindow, currentRegion));
        });
        regionsDisplayPanel.add(buyButton);

        JButton sellButton = new JButton("Sell Goods");
        sellButton.addActionListener(e -> {
            parentWindow.setWindowPanel(new MarketSellScreen(parentWindow, currentRegion));
        });
        regionsDisplayPanel.add(sellButton);

        regionsDisplayPanel.setLayout(new BoxLayout(regionsDisplayPanel, BoxLayout.Y_AXIS));

        JButton refuelButton = new JButton("Buy Fuel");
        refuelButton.addActionListener(e -> parent.setWindowPanel(
                new RefuelMenu(parent, currentRegion.getTechLevel())));
        regionsDisplayPanel.add(refuelButton);
    }

    private void travel() {

        parentWindow.setWindowPanel(new UniverseDisplay(parentWindow));
    }

    public JPanel getMainPanel() {
        return regionsDisplayPanel;
    }

}
