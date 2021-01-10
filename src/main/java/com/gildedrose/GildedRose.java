package com.gildedrose;

class GildedRose {

    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    private static final int DEGRADE_RANGE = 2;
    public static final int MAX_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (Item item : items) {
            if (isDegradable(item)) {
                degradeQuality(item);
            } else {
                increaseQuality(item);
            }
            decreaseSaleDate(item);
            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(BACKSTAGE)) {
                        degradeQuality(item);
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < MAX_QUALITY) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    private void decreaseSaleDate(Item item) {
        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
            if (item.name.equals(BACKSTAGE)) {
                if (item.sellIn < 11) {
                    if (item.quality < MAX_QUALITY) {
                        item.quality = item.quality + 1;
                    }
                }
                if (item.sellIn < 6) {
                    if (item.quality < MAX_QUALITY) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    private boolean isDegradable(Item item) {
        return !item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE);
    }

    private void degradeQuality(Item item) {
        if (item.quality > 0) {
            if (!item.name.equals(SULFURAS)) {
                if(item.quality == 1) {
                    item.quality = 0;
                } else {
                    item.quality = item.quality - DEGRADE_RANGE;
                }
            }
        }
    }
}