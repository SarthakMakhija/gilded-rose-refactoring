package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void degradeQualityByOneIfTheGivenConditionHoldsTrue() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.degradeQualityByOneIf((Item item1) -> item1.name.contains("Elixir"));

        assertEquals(6, item.quality);
    }

    @Test
    public void shouldNotDegradeQualityByOneGivenTheGivenConditionDoesNotHoldTrue() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.degradeQualityByOneIf((Item item1) -> item1.name.contains("Mango"));

        assertEquals(7, item.quality);
    }

    @Test
    public void degradeQualityByOne() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.degradeQualityByOne();

        assertEquals(6, item.quality);
    }

    @Test
    public void degradeQualityByOneAndBringTheQualityToZero() {
        Item item = new Item("Elixir of the Mongoose", 5, 1);
        item.degradeQualityByOne();

        assertEquals(0, item.quality);
    }

    @Test
    public void shouldNotDegradeQualityByOne() {
        Item item = new Item("Elixir of the Mongoose", 5, 0);
        item.degradeQualityByOne();

        assertEquals(0, item.quality);
    }

    @Test
    public void shouldNotDropSellInByOneGivenTheGivenConditionDoesNotHoldTrue() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.degradeQualityByOneIf((Item item1) -> item1.name.contains("Mango"));

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
    public void improveQualityByOne() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        item.improveQualityByOne();

        assertEquals(8, item.quality);
    }

    @Test
    public void shouldNotIncreaseQualityGivenItIsAlreadyAtItsPeak() {
        Item item = new Item("Elixir of the Mongoose", 5, Item.PeakQuality);
        item.improveQualityByOne();

        assertEquals(50, item.quality);
    }

    @Test
    public void improveQualityByFive() {
        Item item = new Item("Elixir of the Mongoose", 5, 6);
        item.improveQualityBy(5);

        assertEquals(11, item.quality);
    }

    @Test
    public void improveQualityByFourOnlyGivenItReachesItsPeak() {
        Item item = new Item("Elixir of the Mongoose", 5, 46);
        item.improveQualityBy(10);

        assertEquals(50, item.quality);
    }

    @Test
    public void sellByHasPassed() {
        Item item = new Item("Elixir of the Mongoose", -1, 46);
        assertTrue(item.hasSellByPassed());
    }

    @Test
    public void sellByHasNotPassedGivenSellInZero() {
        Item item = new Item("Elixir of the Mongoose", 0, 46);
        assertFalse(item.hasSellByPassed());
    }

    @Test
    public void sellByHasNotPassedGivenNonZeroSellIn() {
        Item item = new Item("Elixir of the Mongoose", 1, 46);
        assertFalse(item.hasSellByPassed());
    }
}