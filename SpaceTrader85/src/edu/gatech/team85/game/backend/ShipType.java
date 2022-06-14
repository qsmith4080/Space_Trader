package edu.gatech.team85.game.backend;

/*
 * Defines types of ships with different stats
 */
public enum ShipType {
    // type name, max cargo, max fuel, max health
    Standard("Falcon", 125, 100, 100),
    Light("Ladybug", 200, 150, 30),
    Heavy("Ladybug", 200, 150, 30);


    ShipType(String name, int maxCargo, int maxFuel, int maxHealth) {
        this.name = name;
        this.maxCargo = maxCargo;
        this.maxFuel = maxFuel;
        this.maxHealth = maxHealth;
    }

    private String name;
    private int maxCargo;
    private int maxHealth;
    private int maxFuel;

    public String getName() {
        return name;
    }

    public int getMaxCargo() {
        return maxCargo;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxFuel() {
        return maxFuel;
    }


}
