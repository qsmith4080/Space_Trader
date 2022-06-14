package edu.gatech.team85.game.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Universe {

    // Universe is a singleton, so this holds the one instance of this class
    private static Universe instance = null;

    public static Universe getInstance() {
        return instance;
    }

    public static Universe createUniverse(List<String> regionNames) {
        // Check that the universe has not already been created
        if (instance == null) {
            instance = new Universe(regionNames);
            return instance;
        } else {
            throw new IllegalStateException("There can be only one instance of the Universe");
        }
    }

    private List<Region> regions;


    private Universe(List<String> regionNames) {
        // start a random number generator
        Random rng = new Random();

        this.regions = new ArrayList<>();

        for (String name : regionNames) {
            // pick a random tech level for the region
            int techIndex = rng.nextInt(TechLevel.values().length);
            TechLevel techLevel = TechLevel.values()[techIndex];

            int x = rng.nextInt(400) - 200;
            int y = rng.nextInt(400) - 200;

            /* If the x-coord (or y-coord) is too close to another region's X (or Y),
             * we randomly pick a new value until we find a new coordinate.
             * This is inefficient, but we only have to do it at startup; the delay is not
             * noticeable if the number of regions is reasonable (i.e. less than 50).
             *
             * There are more efficient but more complicated algorithms we can switch to if
             * requirements change to expect more than a couple dozen regions
             */
            while (hasNeighborWithin5X(x, this.regions)) {
                x = rng.nextInt(400) - 200;
            }
            while (hasNeighborWithin5Y(y, this.regions)) {
                y = rng.nextInt(400) - 200;
            }

            // inflation modifiers between 0.8 and 1.2
            double inflationMultiplier = 0.9 + (rng.nextDouble() * 0.2);

            regions.add(new Region(name, techLevel, x, y, inflationMultiplier));
        }
    }

    // returns true if the given X coord is within 5 units of the X coord of any existing region
    private static boolean hasNeighborWithin5X(int newX, List<Region> existingRegions) {
        for (Region neighbor : existingRegions) {
            int distance = Math.abs(newX - neighbor.getxCoordinate());
            if (distance <= 5) {
                return true;
            }
        }
        return false;
    }
    // returns true if the given X coord is within 5 units of the X coord of any existing region
    private static boolean hasNeighborWithin5Y(int newY, List<Region> existingRegions) {
        for (Region neighbor : existingRegions) {
            int distance = Math.abs(newY - neighbor.getxCoordinate());
            if (distance <= 5) {
                return true;
            }
        }
        return false;
    }

    public List<Region> getRegions() {
        return instance.regions;
    }

}
