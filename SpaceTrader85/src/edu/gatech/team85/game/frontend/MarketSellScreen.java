package edu.gatech.team85.game.frontend;

import edu.gatech.team85.game.backend.Commodity;
import edu.gatech.team85.game.backend.Game;
import edu.gatech.team85.game.backend.Market;
import edu.gatech.team85.game.backend.Ship;

import javax.swing.*;
import java.awt.*;

public class MarketSellScreen implements UIScreen {
    private JPanel sellPanel;
    private GameWindow parent;
    private Market buyer;


    public MarketSellScreen(GameWindow parent, Market buyer) {
        this.parent = parent;
        this.buyer = buyer;

        JPanel page = new JPanel();
        JButton returnToRegionButton = new JButton("Back to Region Screen");
        returnToRegionButton.addActionListener(e -> backToRegion());
        page.add(returnToRegionButton);
        page.add(new JLabel("Money: " + Game.getPlayerCharacter().getMoney()));
        page.add(new JLabel("Cargo Space: " + Game.getPlayerShip().getUnusedCargoSpace()));

        page.add(generateSellListings(buyer));

        page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));

        this.sellPanel = page;
    }

    // creates a panel containing the items the player can sell
    private JScrollPane generateSellListings(Market market) {
        JPanel listingsContainer = new JPanel();

        // add buttons for items from inventory that can be sold
        for (Commodity item : Game.getPlayerShip().itemTypesInInventory()) {

            // Skip items where the market price at this location is zero
            if (!Game.getPlayerShip().canSellCargo(item)) {
                continue;
            }
            // Skip any item types that this region would not pay for
            if (buyer.priceOfItem(item) == 0) {
                continue;
            }

            String itemName = item.toString();
            int itemPrice = market.priceOfItem(item);
            String itemDescription = "Sell " + itemName + " [" + itemPrice + " Credits]";

            JButton purchaseButton = new JButton(itemDescription);
            purchaseButton.addActionListener(e -> makeSale(item));
            listingsContainer.add(purchaseButton);
        }

        // add buttons for items from inventory that this region would not pay for
        // these buttons give an error pop-up instead of actually selling for zero credits
        for (Commodity item : Game.getPlayerShip().itemTypesInInventory()) {

            // Skip any item types that can't be sold
            if (!Game.getPlayerShip().canSellCargo(item)) {
                continue;
            }
            // This time, skip items where the price is not zero
            if (buyer.priceOfItem(item) != 0) {
                continue;
            }

            String itemName = item.toString();
            int itemPrice = market.priceOfItem(item);
            String itemDescription = "Sell " + itemName + " [" + itemPrice + " Credits]";

            JButton purchaseButton = new JButton(itemDescription);
            purchaseButton.addActionListener(e -> {
                // Pop-up indicating that this item cannot be sold here

                String errorMessage = item + " cannot be sold in this "
                        + Game.getPlayerCharacter().getCurrentLocationRegion().getTechLevel()
                        .toString().toLowerCase()
                        + " region because the market price of this item in this location is"
                        + " zero credits.";
                JOptionPane.showMessageDialog(new JFrame(),
                        errorMessage,
                        "Cannot Make Sale",
                        JOptionPane.ERROR_MESSAGE);
            });
            listingsContainer.add(purchaseButton);
        }

        listingsContainer.setLayout(new BoxLayout(listingsContainer, BoxLayout.Y_AXIS));

        // create scrollable pane
        JScrollPane scrollPane = new JScrollPane(listingsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setMinimumSize(new Dimension(400, 400));
        scrollPane.getViewport().setPreferredSize(new Dimension(400, 400));

        return scrollPane;
    }

    private void makeSale(Commodity item) {
        Ship ship = Game.getPlayerShip();
        if (!ship.canSellCargo(item)) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "You cannot sell this item to this market.",
                    "Cannot Make Sale",
                    JOptionPane.ERROR_MESSAGE);
        }

        ship.sellCargo(item, buyer);
        // Reload the whole page because the available listings may have changed
        parent.setWindowPanel(new MarketSellScreen(parent, buyer));
    }



    public JPanel getMainPanel() {
        return sellPanel;
    }

    private void backToRegion() {
        parent.setWindowPanel(new RegionsDisplay(parent));
    }
}
