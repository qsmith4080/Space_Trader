package edu.gatech.team85.game.backend;

public enum Commodity {

    // pre-agrarian tech
    Stone_Tools(TechLevel.PreAgrarian, TechLevel.PreAgrarian, TechLevel.Agrarian, 10, 4),
    Animal_Skins(TechLevel.PreAgrarian, TechLevel.PreAgrarian, TechLevel.Renaissance, 12, 2),
    Fruit(TechLevel.PreAgrarian, TechLevel.PreAgrarian, TechLevel.Futuristic, 20, 1),
    Animal_Meat(TechLevel.PreAgrarian, TechLevel.PreAgrarian, TechLevel.Futuristic, 30, 3),
    Cloth(TechLevel.PreAgrarian, TechLevel.PreAgrarian, TechLevel.Modern, 50, 2),
    Woven_Baskets(TechLevel.PreAgrarian, TechLevel.PreAgrarian, TechLevel.Industrial, 12, 3),
    Wooden_Spear(TechLevel.PreAgrarian, TechLevel.PreAgrarian, TechLevel.Medieval, 10, 5),
    Bow_And_Arrows(TechLevel.PreAgrarian, TechLevel.PreAgrarian, TechLevel.Renaissance, 15, 5),
    Primitive_Clothes(TechLevel.PreAgrarian, TechLevel.PreAgrarian, TechLevel.Medieval, 10, 3),
    Clay_Pot(TechLevel.PreAgrarian, TechLevel.PreAgrarian, TechLevel.Medieval, 8, 3),
    // agrarian tech
    Grains(TechLevel.Agrarian, TechLevel.PreAgrarian, TechLevel.Futuristic, 16, 1),
    Alcohol(TechLevel.Agrarian, TechLevel.PreAgrarian, TechLevel.Modern, 32, 1),
    Livestock(TechLevel.Agrarian, TechLevel.Agrarian, TechLevel.Futuristic, 15, 20),
    Gold(TechLevel.Agrarian, TechLevel.Agrarian, TechLevel.Modern, 100, 10),
    Metal_Spear(TechLevel.Agrarian, TechLevel.PreAgrarian, TechLevel.Medieval, 10, 5),
    Papyrus(TechLevel.Agrarian, TechLevel.Agrarian, TechLevel.Medieval, 8, 1),
    Plow(TechLevel.Agrarian, TechLevel.Agrarian, TechLevel.Renaissance, 20, 3),
    Sickle(TechLevel.Agrarian, TechLevel.Agrarian, TechLevel.Renaissance, 10, 3),
    Astrolabe(TechLevel.Agrarian, TechLevel.Agrarian, TechLevel.Renaissance, 12, 1),
    Ballista(TechLevel.Agrarian, TechLevel.Agrarian, TechLevel.Medieval, 30, 12),
    // medieval tech
    Iron_Ore(TechLevel.Medieval, TechLevel.PreAgrarian, TechLevel.Futuristic, 10, 10),
    Trebuchet(TechLevel.Medieval, TechLevel.Agrarian, TechLevel.Medieval, 20, 30),
    Carruca_Plow(TechLevel.Medieval, TechLevel.Agrarian, TechLevel.Renaissance, 15, 12),
    Horse_Shoes(TechLevel.Medieval, TechLevel.Agrarian, TechLevel.Industrial, 12, 2),
    Oil_Paint(TechLevel.Medieval, TechLevel.Medieval, TechLevel.Modern, 10, 1),
    Mechanical_Clock(TechLevel.Medieval, TechLevel.Agrarian, TechLevel.Industrial, 15, 3),
    Chain_Mail(TechLevel.Medieval, TechLevel.Agrarian, TechLevel.Renaissance, 12, 4),
    Cannon(TechLevel.Medieval, TechLevel.Agrarian, TechLevel.Industrial, 25, 25),
    Spinning_Wheel(TechLevel.Medieval, TechLevel.Agrarian, TechLevel.Industrial, 15, 10),
    Silk_Clothes(TechLevel.Medieval, TechLevel.Agrarian, TechLevel.Renaissance, 30, 2),
    // renaissance tech
    Firearms(TechLevel.Renaissance, TechLevel.Agrarian, TechLevel.Modern, 20, 2),
    Steel_Tools(TechLevel.Renaissance, TechLevel.Agrarian, TechLevel.Industrial, 8, 4),
    Steel_Weapons(TechLevel.Renaissance, TechLevel.Agrarian, TechLevel.Industrial, 10, 5),
    Fine_Clothes(TechLevel.Renaissance, TechLevel.Agrarian, TechLevel.Industrial, 5, 2),
    Distilled_Spirits(TechLevel.Renaissance, TechLevel.PreAgrarian, TechLevel.Modern, 20, 1),
    Tea(TechLevel.Renaissance, TechLevel.Agrarian, TechLevel.Futuristic, 15, 1),
    Compass(TechLevel.Renaissance, TechLevel.PreAgrarian, TechLevel.Industrial, 8, 1),
    Printing_Press(TechLevel.Renaissance, TechLevel.Medieval, TechLevel.Industrial, 30, 10),
    Blast_Furnace(TechLevel.Renaissance, TechLevel.Medieval, TechLevel.Industrial, 35, 20),
    Stained_Glass(TechLevel.Renaissance, TechLevel.Agrarian, TechLevel.Renaissance, 5, 5),
    // industrial tech
    Steam_Engine(TechLevel.Industrial, TechLevel.Renaissance, TechLevel.Modern, 50, 40),
    Rifles(TechLevel.Industrial, TechLevel.Medieval, TechLevel.Modern, 20, 2),
    Electric_Generator(TechLevel.Industrial, TechLevel.Industrial, TechLevel.Modern, 50, 20),
    Telegraph(TechLevel.Industrial, TechLevel.Renaissance, TechLevel.Industrial, 10, 2),
    Spinning_Jenny(TechLevel.Industrial, TechLevel.Agrarian, TechLevel.Industrial, 12, 4),
    Mass_Produced_Clothing(TechLevel.Industrial, TechLevel.PreAgrarian, TechLevel.Futuristic, 5, 3),
    Tractor(TechLevel.Industrial, TechLevel.Renaissance, TechLevel.Modern, 25, 30),
    Phonograph(TechLevel.Industrial, TechLevel.Renaissance, TechLevel.Modern, 20, 5),
    Fertilizer(TechLevel.Industrial, TechLevel.Agrarian, TechLevel.Modern, 8, 5),
    Gas_Lighting(TechLevel.Industrial, TechLevel.Agrarian, TechLevel.Industrial, 5, 4),
    // modern tech
    Antibiotics(TechLevel.Modern, TechLevel.PreAgrarian, TechLevel.Futuristic, 5, 1),
    Automobile(TechLevel.Modern, TechLevel.Renaissance, TechLevel.Modern, 50, 15),
    Radio(TechLevel.Modern, TechLevel.Renaissance, TechLevel.Modern, 10, 2),
    Computer(TechLevel.Modern, TechLevel.Industrial, TechLevel.Futuristic, 15, 2),
    Compound_Bow(TechLevel.Modern, TechLevel.PreAgrarian, TechLevel.Modern, 8, 2),
    Microwave_Oven(TechLevel.Modern, TechLevel.Industrial, TechLevel.Futuristic, 10, 4),
    Refrigerator(TechLevel.Modern, TechLevel.Industrial, TechLevel.Futuristic, 15, 15),
    Synthetic_Fabric(TechLevel.Modern, TechLevel.PreAgrarian, TechLevel.Futuristic, 2, 1),
    Soda(TechLevel.Modern, TechLevel.PreAgrarian, TechLevel.Futuristic, 3, 2),
    Portable_Water_Purifier(TechLevel.Modern, TechLevel.PreAgrarian, TechLevel.Futuristic, 15, 2),
    // futuristic tech
    Universal_Translator(TechLevel.Futuristic, TechLevel.Industrial, TechLevel.Futuristic, 15, 1),
    Cancer_Cure(TechLevel.Futuristic, TechLevel.Agrarian, TechLevel.Futuristic, 10, 1),
    Virtual_Reality_System(TechLevel.Futuristic, TechLevel.Modern, TechLevel.Futuristic, 12, 4),
    Nanotechnology_Assembler(TechLevel.Futuristic, TechLevel.Modern, TechLevel.Futuristic, 100, 10),
    Bio_Printer(TechLevel.Futuristic, TechLevel.Modern, TechLevel.Futuristic, 90, 12),
    Flying_Car(TechLevel.Futuristic, TechLevel.Industrial, TechLevel.Futuristic, 60, 15),
    Jet_Pack(TechLevel.Futuristic, TechLevel.Renaissance, TechLevel.Futuristic, 50, 5),
    Personal_Robot(TechLevel.Futuristic, TechLevel.Modern, TechLevel.Futuristic, 30, 5),
    Fusion_Reactor(TechLevel.Futuristic, TechLevel.Industrial, TechLevel.Futuristic, 500, 50),
    Laser_Gun(TechLevel.Futuristic, TechLevel.Industrial, TechLevel.Futuristic, 10, 1);



    Commodity(TechLevel sellerMinTechLevel, TechLevel buyerMinTechLevel, TechLevel maxTechLevel,
              int basePrice, int size) {

        if (sellerMinTechLevel.ordinal() < buyerMinTechLevel.ordinal()) {
            throw new IllegalArgumentException(
                    "Tech level required to buy item "
                            + "cannot be higher than tech level required to sell it");
        }
        if (sellerMinTechLevel.ordinal() > maxTechLevel.ordinal()) {
            throw new IllegalArgumentException(
                    "Maximum tech level cannot be greater than minimum seller tech level");
        }

        this.sellerMinTechLevel = sellerMinTechLevel;
        this.buyerMinTechLevel = buyerMinTechLevel;
        this.maxTechLevel = maxTechLevel;
        this.basePrice = basePrice;
        this.size = size;

    }

    /*
     * Tech level required for a region to sell an item
     * e.g., a region must be at least Modern to sell computers
     */
    private TechLevel sellerMinTechLevel;
    /* Tech level required for a region to buy an item
     * e.g., a PreAgrarian society can't use computers and therefore will not pay for them.
     */
    private TechLevel buyerMinTechLevel;

    /* The maximum tech level to buy and sell this producy.
     * E.g. Futuristic regions will not buy stone tools
     */
    private TechLevel maxTechLevel;

    // The base market price of the commodity in regions that are at the minimum price to produce it
    private int basePrice;

    // amount of cargo space needed to store one unit
    private int size;

    public int getSize() {
        return size;
    }

    public boolean canBeSoldBy(Region region) {
        int regionTech = region.getTechLevel().ordinal();
        int requiredTech = this.sellerMinTechLevel.ordinal();

        if (regionTech > this.maxTechLevel.ordinal()) {
            return false;
        }

        return regionTech >= requiredTech;
    }


    /*
     * Market prices depend on a market's tech level
     *
     * If a region's level is too low, it will not be willing to pay more than 0.
     * For example, computers are useless to pre-industrial societies because they do not have
     * electricity.
     *
     * If a region can buy a certain commodity but cannot produce it themselves, they will pay
     * a premium: they will pay double for technology that is one tech level beyond their own,
     * 4 times the base price for technology 2 levels beyond their own, and so on.
     * For example, pre-agrarian societies would be willing to pay a lot for antibiotics.
     *
     * If a region is at the same tech level as is required to produce a commodity, they will
     * buy and sell it for the base price.
     *
     * If a region is at a higher tech level than is required to produce a commodity, the market
     * price in that region will be halved for each additional tech level the region has.
     * However, it will always be at least 1.
     * For example, futuristic societies will sell food at low prices.
     *
     * All prices in each region are multiplied by the region's Inflation Multiplier,
     * which is selected randomly at universe creation
     */
    public int marketPrice(TechLevel marketTechLevel, double inflationMultiplier) {
        int techLevelDifference = marketTechLevel.ordinal() - sellerMinTechLevel.ordinal();

        if (marketTechLevel.ordinal() < buyerMinTechLevel.ordinal()) {
            return 0; //e.g., Pre-agrarian societies will not pay for computers
        }
        if (marketTechLevel.ordinal() > this.maxTechLevel.ordinal()) {
            return  0; //e.g. Futuristic regions will not pay for stone tools
        }
        double price = this.basePrice * inflationMultiplier;

        if (techLevelDifference != 0) {

            /* the player's main source of profit is to buy commodities from high-tech regions
             * and sell them to lower-tech regions at a higher price
             *
             * the bonus from tech-level differences increases with merchant skill
             */
            double merchantSkill = Game.getPlayerCharacter().getMerchantSkill();
            double differenceFactor = 2.0 + (merchantSkill * 0.25);
            double multiplier = Math.pow(differenceFactor, -1 * techLevelDifference);
            price = (price * multiplier);
        }
        if (price < 1.0) {
            price = 1.0;
        }
        return (int) price;
    }

    @Override
    public String toString() {
        // "Fusion Reactor" instead of "Fusion_Reactor"
        return this.name().replace('_', ' ');
    }
}

