package edu.gatech.team85.game.backend;

import java.util.HashMap;
import java.util.Map;


/**
 * Contains game balance constants
 *
 * Some constants may vary depending on difficulty level
 */
public class Constants {

    // This indentation looks kind of weird but checkstyle says this is the correct way
    public static final Map<Difficulty, Integer> STARTING_MONEY
            = new HashMap<Difficulty, Integer>() {{
                    put(Difficulty.Easy, 1000);
                    put(Difficulty.Medium, 500);
                    put(Difficulty.Hard, 100);
                }};

    public static final Map<Difficulty, Integer> MAX_SKILL_POINTS
            = new HashMap<Difficulty, Integer>() {{
                    put(Difficulty.Easy, 16);
                    put(Difficulty.Medium, 12);
                    put(Difficulty.Hard, 8);
                }};

    /* The cost of fuel depends on a region's tech level
     * Buying fuel from high-tech regions is cheaper than buying it from mid-level regions
     *
     * Low-tech regions not listed here cannot sell fuel at all
     *
     * These are the cost of one unit of fuel.
     */
    public static final Map<TechLevel, Double> FUEL_COST
            = new HashMap<TechLevel, Double>() {{
                    put(TechLevel.Futuristic, 0.5);
                    put(TechLevel.Modern, 1.0);
                    put(TechLevel.Industrial, 4.0);
                }};
}
