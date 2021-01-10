package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GildedRoseTest {

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
    }

    @Test
    @DisplayName("Add an item with quality more than 50 then set its quality 50")
    void qualityMoreThan50() {
        //given
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 5, 51) };
        // when
        GildedRose app = new GildedRose(items);
        // then
        assertQuality(50, app.items[0]);
    }

    private void assertQuality(int expected, Item item) {
        assertEquals(expected, item.quality);
    }
}
