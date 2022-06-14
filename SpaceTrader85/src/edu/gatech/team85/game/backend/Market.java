package edu.gatech.team85.game.backend;

import java.util.Set;

/*
 * A market is any entity that the player can trade from
 * e.g. a region, but we may add traveling merchants later
 */
public interface Market {
    public Set<Commodity> itemsForSale();

    public int priceOfItem(Commodity item);
}
