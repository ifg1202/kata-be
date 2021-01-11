package com.gildedrose;

class GildedRose {

    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    private static final int DEGRADE_RANGE = 2;
    public static final int MAX_QUALITY = 50;
    public static final int DROP_QUALITY_LIMIT = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateItem() {
        for (Item item : items) {
            updateSellIn(item);
            updateQuality(item);
        }
    }

    private void updateQuality(Item item) {
        if (isDegradable(item)) {
            degradeQuality(item);
        } else {
            increaseQuality(item);
        }
    }

    private void updateSellIn(Item item) {
        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
            if (item.name.equals(BACKSTAGE)) {
                backstageQualitySecondIncrease(item, 11);
                backstageQualitySecondIncrease(item, 6);
                backstageQualitySecondIncrease(item, 0);
            }
        }

    }

    private void backstageQualitySecondIncrease(Item item, int limit) {
        if (item.sellIn < limit) {
            if (isDropable(limit)) {
                item.quality = 0;
            } else if (item.quality < MAX_QUALITY) {
                item.quality = item.quality + 1;
            }
        }
    }

    private boolean isDropable(int limit) {
        return limit == DROP_QUALITY_LIMIT;
    }

    private boolean isDegradable(Item item) {
        return !item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE);
    }

    private void degradeQuality(Item item) {
        if (item.quality > 0 && !item.name.equals(SULFURAS)) {
            if(item.quality == 1) {
                item.quality = 0;
            } else {
                item.quality = item.quality - DEGRADE_RANGE;
            }
        }
    }
}