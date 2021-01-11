package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    private static final Integer SELL_IN_DECREASE_RATIO = 1;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public void update() {
        sellIn = sellIn == 0 ? sellIn : sellIn - SELL_IN_DECREASE_RATIO;
        quality = 0;
    }
}
