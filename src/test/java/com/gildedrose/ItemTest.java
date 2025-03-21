package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void dropQualityByOne() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.dropQualityByOne();

        assertEquals(6, item.quality);
    }

    @Test
    public void dropQualityByOneAndBringTheQualityToZero() {
        Item item = new Item("Elixir of the Mongoose", 5, 1);
        item.dropQualityByOne();

        assertEquals(0, item.quality);
    }

    @Test
    public void shouldNotDropQualityByOne() {
        Item item = new Item("Elixir of the Mongoose", 5, 0);
        item.dropQualityByOne();

        assertEquals(0, item.quality);
    }
}