package edu.gatech.team85.game.backend;

import java.util.HashSet;
import java.util.Set;

public class Region implements Market {
    private int xCoordinate;
    private int yCoordinate;
    private TechLevel techLevel;
    private String name;
    private double inflationMultiplier; // prices are multiplied by this amount

    public Region(String name, TechLevel techLevel, int x, int y, double inflation) {
        this.name = name;
        this.techLevel = techLevel;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.inflationMultiplier = inflation;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public String getName() {
        return name;
    }

    public double getInflationMultiplier() {
        return inflationMultiplier;
    }

    public double distanceFrom(Region otherRegion) {
        int xDist = Math.abs(this.xCoordinate - otherRegion.xCoordinate);
        int yDist = Math.abs(this.yCoordinate - otherRegion.yCoordinate);
        return Math.hypot(xDist, yDist);
    }

    @Override
    public Set<Commodity> itemsForSale() {
        Set<Commodity> goods = new HashSet<>();
        // initialize set of commodities
        for (Commodity commodity : Commodity.values()) {
            if (commodity.canBeSoldBy(this)) {
                goods.add(commodity);
            }
        }
        return goods;
    }

    @Override
    public int priceOfItem(Commodity item) {
        return item.marketPrice(this.techLevel, this.inflationMultiplier);
    }
}
