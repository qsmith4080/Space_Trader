package edu.gatech.team85.game.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// represents the player's ship
public class Ship {
    private String shipType; // the name of this ship type
    private int cargoSpace; //maximum number of units of cargo that can be stored
    // quantity of used cargo space is calculated from inventory
    private int fuelCapacity; // size of the ship's fuel tank
    private int maxHealth; //maximum health of the ship

    /* Unused space available for storing cargo
     * value is automatically updated when items are bought or sold
     */
    private int unusedCargoSpace;
    private int currentFuel;
    private int health; // current level of health, always equal to or less than maxHealth

    /* Holds the quantity of each commodity type in the player's inventory
     * Entries should always be positive integers; if the player has none of a certain commodity,
     * its entry should be removed from the map
     */
    private Map<Commodity, Integer> inventory;

    /* this constructor is private because ships should be made via the factory methods for each
        type of ship
     */
    private Ship(String typeName, int maxCargo, int maxFuel, int maxHealth) {
        this.shipType = typeName;
        this.cargoSpace = maxCargo;
        this.fuelCapacity = maxFuel;
        this.maxHealth = maxHealth;

        this.unusedCargoSpace = maxCargo;
        this.currentFuel = this.fuelCapacity;
        this.health = this.maxHealth;

        inventory = new HashMap<>(); //initialize empty inventory
    }

    // public constructor enforces using one of possible values of the ShipType enum
    public String getShipType() {
        return shipType;
    }

    // private constructor
    public Ship(ShipType type) {
        this(type.getName(), type.getMaxCargo(), type.getMaxFuel(), type.getMaxHealth());
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return health;
    }

    public int getMaxCargoSpace() {
        return cargoSpace;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public int getCurrentFuel() {
        return currentFuel;
    }

    public void addFuel(int fuelUnits) {
        if ((currentFuel + fuelUnits) > fuelCapacity) {
            throw new IllegalArgumentException("Insufficient fuel capacity");
        }
        currentFuel += fuelUnits;
    }

    public int fuelCost(Region destination) {
        Region currentRegion = Game.getPlayerCharacter().getCurrentLocationRegion();
        double distance = currentRegion.distanceFrom(destination);
        return fuelCost(distance);
    }

    public int fuelCost(double distance) {
        double cost = distance / 10;
        // players with higher pilot skill have lower fuel costs
        double pilotSkill = Game.getPlayerCharacter().getPilotSkill();
        //a player with 16 pilot skill points pays only 20% the normal fuel cost
        double skillMultiplier = 1 - (pilotSkill * 0.05);
        cost = cost * skillMultiplier;
        return (int) cost;
    }

    public Boolean canTravelTo(Region destination) {
        return currentFuel >= fuelCost(destination);
    }

    public void travel(Region destination) {
        if (!canTravelTo(destination)) {
            return;
        }
        if (canTravelTo(destination)) {
            Region currentRegion = Game.getPlayerCharacter().getCurrentLocationRegion();
            int fuelCost = fuelCost(currentRegion.distanceFrom(destination));
            currentFuel = currentFuel - fuelCost;
            // update player location
            Game.getPlayerCharacter().setCurrentLocationRegion(destination);
        } else {
            throw new UnsupportedOperationException("Insufficient Fuel");
        }
    }

    public int getUnusedCargoSpace() {
        return unusedCargoSpace;
    }

    private void updateUnusedCargoSpace() {
        int unusedSpace = this.cargoSpace;
        for (Commodity itemType : inventory.keySet()) {
            int quantity = inventory.get(itemType);
            int size = quantity * itemType.getSize();
            unusedSpace = unusedSpace - size;
        }
        this.unusedCargoSpace = unusedSpace;
    }

    public boolean canBuyCargo(Commodity item, Market seller) {
        // check that the seller can sell this item type
        if (!seller.itemsForSale().contains(item)) {
            return false;
        }
        // check that the player can afford this item
        int cost = seller.priceOfItem(item);
        if (cost > Game.getPlayerCharacter().getMoney()) {
            return false;
        }
        // check that the ship has enough available cargo space to fit this item
        int unusedSpaceAfterPurchase = getUnusedCargoSpace() - item.getSize();
        if (unusedSpaceAfterPurchase < 0) {
            return false;
        }
        return true;
    }

    public void buyCargo(Commodity item, Market seller) {
        if (!canBuyCargo(item, seller)) {
            throw new IllegalArgumentException(
                    "Cannot purchase " + item + " from this seller");
        }
        // remove money from player
        Player playerCharacter = Game.getPlayerCharacter();
        playerCharacter.setMoney(playerCharacter.getMoney() - seller.priceOfItem(item));

        // add item to inventory
        if (inventory.containsKey(item)) {
            // if we already have some of this item, add more
            inventory.put(item, inventory.get(item) + 1);
        } else {
            /* if we don't already have an inventory entry for this commodity,
             * create a new one
             */
            inventory.put(item, 1);
        }

        // update count of available cargo space
        updateUnusedCargoSpace();
    }

    public boolean canSellCargo(Commodity item) {
        if (inventory.containsKey(item)) {
            return inventory.get(item) >= 1;
        }
        return false;
    }

    public void sellCargo(Commodity item, Market buyer) {
        if (!canSellCargo(item)) {
            throw new IllegalArgumentException("Insufficient quantity to sell");
        }

        // add money to player
        Player playerCharacter = Game.getPlayerCharacter();
        playerCharacter.setMoney(playerCharacter.getMoney() + buyer.priceOfItem(item));

        // remove item from inventory
        inventory.put(item, inventory.get(item) - 1);
        // if there are now 0 items of this type in inventory, remove the entry from the map
        inventory.remove(item, 0);

        // update count of available cargo space
        updateUnusedCargoSpace();
    }

    public Set<Commodity> itemTypesInInventory() {
        return inventory.keySet();
    }
}
