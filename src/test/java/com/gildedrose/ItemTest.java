package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    private void assertQuality(int expected, Item item) {
        assertEquals(expected, item.quality);
    }

    private void assertSale(int expected, Item item) {
        assertEquals(expected, item.sellIn);
    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("Elixir of the Mongoose", 0, 0);
        // when
        item.update();
        // then
        assertQuality(0, item);
        assertSale(0, item);
    }
}
