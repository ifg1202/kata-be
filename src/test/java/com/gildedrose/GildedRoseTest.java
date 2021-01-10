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
        assertEquals(5, app.items[0].quality);
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
        assertEquals(0, app.items[0].quality);
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
        assertEquals(2, app.items[0].quality);
    }

}
