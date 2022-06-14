package edu.gatech.team85.game.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {
    private static Difficulty difficulty;
    private static Player playerCharacter;
    private static Ship playerShip;

    // List of names used to generate regions
    private static List<String> regionNames = new ArrayList<>(
            Arrays.asList("Aerelon", "Aquaria", "Canceron", "Caprica", "Gemenon", "Leonis",
                    "Libris", "Picon", "Sagittaron", "Scorpia", "Tauron", "Virgon"));

    /* Generates a Universe with random region locations and tech levels.
     * Initializes player character with given skill points.
     *
     */
    public static void startGame(Difficulty difficultySetting, String playerName, int pilotPoints,
                                 int fighterPoints, int merchantPoints, int engineerPoints) {
        difficulty = difficultySetting;
        Universe.createUniverse(regionNames);

        // Initialize player character
        playerCharacter = new Player(playerName, pilotPoints, fighterPoints,
        merchantPoints, engineerPoints);

        // Add starting money
        int startingMoney = Constants.STARTING_MONEY.get(difficultySetting);
        playerCharacter.setMoney(startingMoney);

        // pick a region at random as the starting location
        List<Region> allRegions = Universe.getInstance().getRegions();
        int regionIndex = new Random().nextInt(allRegions.size());
        playerCharacter.setCurrentLocationRegion(allRegions.get(regionIndex));

        playerShip = new Ship(ShipType.Standard);
    }

    public static Player getPlayerCharacter() {
        return playerCharacter;
    }
    public static Difficulty getDifficulty() {
        return difficulty;
    }
    public static Ship getPlayerShip() {
        return playerShip;
    }


}
