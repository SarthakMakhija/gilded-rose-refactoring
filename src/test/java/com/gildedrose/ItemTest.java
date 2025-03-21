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

    @Test
    public void dropSellInByOne() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.dropSellInByOne();

        assertEquals(4, item.sellIn);
    }

    @Test
    public void shouldDropSellInByOneMakingItNegative() {
        Item item = new Item("Elixir of the Mongoose", 0, 0);
        item.dropSellInByOne();

        assertEquals(-1, item.sellIn);
    }

    @Test
    public void increaseQualityByOne() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.increaseQualityByOne();

        assertEquals(8, item.quality);
    }

    @Test
    public void shouldNotIncreaseQualityGivenItItAlreadyAtItsPeak() {
        Item item = new Item("Elixir of the Mongoose", 5, 50);
        item.increaseQualityByOne();

        assertEquals(50, item.quality);
    }
}