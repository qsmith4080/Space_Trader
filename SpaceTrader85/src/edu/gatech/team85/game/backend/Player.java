package edu.gatech.team85.game.backend;

/**
 * Player character's info and stats
 */
public class Player {
    private String name;

    private int money;

    private int pilotSkill;
    private int fighterSkill;
    private int merchantSkill;
    private int engineerSkill;
    private Region currentLocationRegion;

    public Player(String playerName,
                  int pilotPoints, int fighterPoints,
                  int merchantPoints, int engineerPoints) {
        this.name = playerName;

        // The relationship between difficulty level and starting cash is
        // defined in the game's Constants
        this.money = 0;

        this.pilotSkill = pilotPoints;
        this.fighterSkill = fighterPoints;
        this.merchantSkill = merchantPoints;
        this.engineerSkill = engineerPoints;


    }

    public String getName() {
        return name;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int balance) {
        this.money = balance;
    }
    public void setCurrentLocationRegion(Region region) {
        this.currentLocationRegion = region;
    }
    public Region getCurrentLocationRegion() {
        return this.currentLocationRegion;
    }
    public int getPilotSkill() {
        return pilotSkill;
    }
    public int getFighterSkill() {
        return fighterSkill;
    }
    public int getMerchantSkill() {
        return merchantSkill;
    }
    public int getEngineerSkill() {
        return engineerSkill;
    }

    // These attributes should probably never change, but the M4 requirements say to include setters
    public void setName(String name) {
        this.name = name;
    }
    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }
    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }
    public void setMerchantSkill(int merchantSkill) {
        this.merchantSkill = merchantSkill;
    }
    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }

}
