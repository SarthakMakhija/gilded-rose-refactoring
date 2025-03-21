package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void dropQualityByOneIfTheGivenConditionHoldsTrue() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.dropQualityByOneIf((Item item1) -> item1.name.contains("Elixir"));

        assertEquals(6, item.quality);
    }

    @Test
    public void shouldNotDropQualityByOneGivenTheGivenConditionDoesNotHoldTrue() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.dropQualityByOneIf((Item item1) -> item1.name.contains("Mango"));

        assertEquals(7, item.quality);
    }

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
    public void dropSellInByOneIfTheGivenConditionHoldsTrue() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.dropSellInByOneIf((Item item1) -> item1.name.contains("Elixir"));

        assertEquals(4, item.sellIn);
    }

    @Test
    public void shouldNotDropSellInByOneGivenTheGivenConditionDoesNotHoldTrue() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.dropQualityByOneIf((Item item1) -> item1.name.contains("Mango"));

        assertEquals(5, item.sellIn);
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
    public void shouldNotIncreaseQualityGivenItIsAlreadyAtItsPeak() {
        Item item = new Item("Elixir of the Mongoose", 5, Item.PeakQuality);
        item.increaseQualityByOne();

        assertEquals(50, item.quality);
    }

    @Test
    public void increaseQualityByFive() {
        Item item = new Item("Elixir of the Mongoose", 5, 6);
        item.increaseQualityBy(5);

        assertEquals(11, item.quality);
    }

    @Test
    public void increaseQualityByFourOnlyGivenItReachesItsPeak() {
        Item item = new Item("Elixir of the Mongoose", 5, 46);
        item.increaseQualityBy(10);

        assertEquals(50, item.quality);
    }
}