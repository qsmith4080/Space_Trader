package edu.gatech.team85.game.frontend;

import edu.gatech.team85.game.backend.Commodity;
import edu.gatech.team85.game.backend.Game;
import edu.gatech.team85.game.backend.Market;
import edu.gatech.team85.game.backend.Ship;

import javax.swing.*;
import java.awt.*;

public class MarketBuyScreen implements UIScreen {
    private JPanel buyPanel;
    private GameWindow parent;
    private Market seller;
    private JLabel moneyLabel;
    private JLabel spaceLabel;

    public MarketBuyScreen(GameWindow parent, Market seller) {
        this.parent = parent;
        this.seller = seller;

        JPanel page = new JPanel();
        JButton returnToRegionButton = new JButton("Back to Region Screen");
        returnToRegionButton.addActionListener(e -> backToRegion());
        page.add(returnToRegionButton);
        this.moneyLabel = new JLabel("Money: ");
        this.spaceLabel = new JLabel("Cargo Space: ");
        page.add(moneyLabel);
        page.add(spaceLabel);

        page.add(generateBuyListings(seller));

        page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));

        this.buyPanel = page;
        this.updateMoneyAndSpaceLabels();
    }

    // creates a panel containing the listings of items that can be bought
    private JScrollPane generateBuyListings(Market market) {
        JPanel listingsContainer = new JPanel();

        for (Commodity item : market.itemsForSale()) {
            String itemName = item.toString();
            int itemPrice = market.priceOfItem(item);
            String itemDescription = "Buy " + itemName + " [" + itemPrice + " Credits]";

            JButton purchaseButton = new JButton(itemDescription);
            purchaseButton.addActionListener(e -> makePurchase(item));
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

    private void makePurchase(Commodity item) {
        Ship ship = Game.getPlayerShip();
        if (!ship.canBuyCargo(item, this.seller)) {
            // Pop-up informing the player that they cannot afford this item
            String errorMessage = "You cannot buy this item";

            if (Game.getPlayerCharacter().getMoney() < seller.priceOfItem(item)) {
                errorMessage = "You cannot afford this item.";
            } else if (Game.getPlayerShip().getUnusedCargoSpace() < item.getSize()) {
                errorMessage = "You do not have enough cargo space available to store this item."
                        + "\nIt requires " + item.getSize() + " unit(s) of cargo space.";
            }

            JOptionPane.showMessageDialog(new JFrame(),
                    errorMessage,
                    "Cannot Make Purchase",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }
        ship.buyCargo(item, seller);
        updateMoneyAndSpaceLabels();


        System.out.println("Purchase " + item);
    }

    private void updateMoneyAndSpaceLabels() {
        int money = Game.getPlayerCharacter().getMoney();
        String moneyString = "Money: " + money + " Credits";
        this.moneyLabel.setText(moneyString);

        int space = Game.getPlayerShip().getUnusedCargoSpace();
        String spaceString = "Unused Cargo Space: " + space;
        this.spaceLabel.setText(spaceString);
    }

    public JPanel getMainPanel() {
        return buyPanel;
    }

    private void backToRegion() {
        parent.setWindowPanel(new RegionsDisplay(parent));
    }
}
