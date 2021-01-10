package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GildedRoseTest {

    private void assertQuality(int expected, Item item) {
        assertEquals(expected, item.quality);
    }

    private void assertSale(int expected, Item item) {
        assertEquals(expected, item.sellIn);
    }

    @Test
    @DisplayName("Once the sell by date has passed, Quality degrades twice as fast")
    void qualityDegrades() {
        //given
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 5, 7) };
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertQuality(5, app.items[0]);
        assertSale(4, app.items[0]);
    }

    @Test
    @DisplayName("Given quality 1, final quality must be 0")
    void qualityOneDegrades() {
        //given
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 5, 1) };
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertQuality(0, app.items[0]);
        assertSale(4, app.items[0]);
    }

    @Test
    @DisplayName("The Quality of an item is never negative")
    void qualityNeverNegative() {
        //given
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 5, 0) };
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertTrue(app.items[0].quality >= 0);
        assertSale(4, app.items[0]);
    }

    @Test
    @DisplayName("Aged Brie actually increases in Quality the older it gets")
    void agedBrieIncreasesQuality() {
        //given
        Item[] items = new Item[] { new Item("Aged Brie", 5, 1) };
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertQuality(2, app.items[0]);
        assertSale(4, app.items[0]);
    }

    @Test
    @DisplayName("When Aged Brie with quality 50 increases then stay 50")
    void agedBrie50IncreasesQuality() {
        //given
        Item[] items = new Item[] { new Item("Aged Brie", 5, 50) };
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertQuality(50, app.items[0]);
        assertSale(4, app.items[0]);
    }

    @Test
    @DisplayName("Sulfuras, being a legendary item, never decreases in Quality")
    void sulfurasNeverDecreasesQuality() {
        // given
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 1, 10) };
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertQuality(10, app.items[0]);
    }

    @Test
    @DisplayName("Sulfuras, being a legendary item, never decreases in Quality")
    void sulfurasNeverSold() {
        // given
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertQuality(80, app.items[0]);
        assertSale(10, app.items[0]);
    }

    @Test
    @DisplayName("Backstage passes, quality increases by 2 when there are 10 days or less")
    void backstageIncreasingTenDaysOrLess() {
        // given
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertQuality(12, app.items[0]);
        assertSale(9, app.items[0]);
    }

    @Test
    @DisplayName("Backstage passes, quality increases by 3 when there are 5 days")
    void backstageIncreasingFiveDaysOrLess() {
        // given
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertQuality(13, app.items[0]);
        assertSale(4, app.items[0]);
    }

    @Test
    @DisplayName("Backstage passes, quality drops to 0 when sellIn is 0")
    void backstageIncreasingZeroDays() {
        // given
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertQuality(0, app.items[0]);
    }
}
