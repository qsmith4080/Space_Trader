package edu.gatech.team85.game.frontend;

import edu.gatech.team85.game.backend.*;

import javax.swing.*;

public class RefuelMenu implements UIScreen {
    private GameWindow parent;
    private JPanel fuelScreen;
    private TechLevel techLevel;
    private double fuelCost; // cost of fuel in the current region

    public RefuelMenu(GameWindow parent, TechLevel techLevel) {
        this.parent = parent;
        this.techLevel = techLevel;
        this.fuelScreen = new JPanel();
        if (Constants.FUEL_COST.containsKey(techLevel)) {
            this.fuelCost = Constants.FUEL_COST.get(techLevel);
            fuelScreen.add(new JLabel("Money: " + Game.getPlayerCharacter().getMoney()));
            fuelScreen.add(new JLabel("Fuel capacity: "
                    + Game.getPlayerShip().getFuelCapacity()));
            fuelScreen.add(new JLabel("Current Fuel: "
                    + Game.getPlayerShip().getCurrentFuel()));


            JButton buy1 = new JButton("Buy 1 Fuel Unit [Cost: " + price(1) + " credits]");
            buy1.addActionListener(e -> purchaseFuel(1));
            fuelScreen.add(buy1);

            JButton buy10 = new JButton("Buy 10 Fuel Units [Cost: " + price(10) + " credits]");
            buy10.addActionListener(e -> purchaseFuel(10));
            fuelScreen.add(buy10);

            JButton buy50 = new JButton("Buy 50 Fuel Units [Cost: " + price(50) + " credits]");
            buy50.addActionListener(e -> purchaseFuel(50));
            fuelScreen.add(buy50);


        } else {
            fuelScreen.add(new JLabel(techLevel + " regions cannot sell starship fuel"));
        }
        // Button to return to Region menu
        JButton returnToRegionButton = new JButton("Back to Region Screen");
        returnToRegionButton.addActionListener(e ->
                parent.setWindowPanel(new RegionsDisplay(parent)));
        this.fuelScreen.add(returnToRegionButton);
        this.fuelScreen.setLayout(new BoxLayout(this.fuelScreen, BoxLayout.Y_AXIS));
    }

    public JPanel getMainPanel() {
        return fuelScreen;
    }


    private int price(int fuelUnits) {
        return (int) Math.ceil(fuelUnits * this.fuelCost);
    }
    private boolean canAfford(int fuelUnits) {
        if (price(fuelUnits) > Game.getPlayerCharacter().getMoney()) {
            // player cannot afford fuel
            return false;
        }
        return true;
    }
    private boolean hasCapacityFor(int fuelUnits) {
        Ship playerShip = Game.getPlayerShip();
        if (fuelUnits > (playerShip.getFuelCapacity() - playerShip.getCurrentFuel())) {
            return false;
        }
        return true;
    }

    private void purchaseFuel(int fuelUnits) {
        if (!canAfford(fuelUnits)) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "You cannot afford this quantity of fuel.",
                    "Cannot Buy Fuel",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!hasCapacityFor(fuelUnits)) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Your fuel tank does not have enough capacity for this quantity of fuel.",
                    "Cannot buy fuel",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            Game.getPlayerShip().addFuel(fuelUnits);
            Player player = Game.getPlayerCharacter();
            player.setMoney(player.getMoney() - price(fuelUnits));
            // Refresh page
            parent.setWindowPanel(new RefuelMenu(parent, this.techLevel));
        }

    }


}
