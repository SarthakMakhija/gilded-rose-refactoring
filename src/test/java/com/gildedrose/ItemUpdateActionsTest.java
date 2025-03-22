package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemUpdateActionsTest {

    @Test
    public void shouldNotUpdateQualityForSulfuras() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 40);
        actions.update(item);

        assertEquals(40, item.quality());
    }

    @Test
    public void shouldNotUpdateQualityForSulfurasPostSellIn() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Sulfuras, Hand of Ragnaros", -1, 40);
        actions.update(item);

        assertEquals(40, item.quality());
    }

    @Test
    public void shouldImproveQualityForAgedBrie() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Aged Brie", 10, 40);
        actions.update(item);

        assertEquals(41, item.quality());
    }

    @Test
    public void shouldImproveQualityForAgedBriePostSellIn() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Aged Brie", -1, 40);
        actions.update(item);

        assertEquals(42, item.quality());
    }

    @Test
    public void shouldImproveQualityForBackstage() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 40);
        actions.update(item);

        assertEquals(41, item.quality());
    }

    @Test
    public void shouldImproveQualityByTwoForBackstageGivenLessThanTenDaysLeftToSell() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 9, 40);
        actions.update(item);

        assertEquals(42, item.quality());
    }

    @Test
    public void shouldImproveQualityByThreeForBackstageGivenFiveDaysLeftToSell() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40);
        actions.update(item);

        assertEquals(43, item.quality());
    }

    @Test
    public void shouldDegradeQualityWithPassingTimeQuality() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Elixir of the Mongoose", 10, 40);
        actions.update(item);

        assertEquals(39, item.quality());
    }

    @Test
    public void shouldResetQualityForBackstagePostSellIn() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 40);
        actions.update(item);

        assertEquals(0, item.quality());
    }

    @Test
    public void shouldUpdateSellIn() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 40);
        actions.update(item);

        assertEquals(9, item.sellIn());
    }

    @Test
    public void shouldUpdateSellInTakingTheItToNegative() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 40);
        actions.update(item);

        assertEquals(-1, item.sellIn());
    }

    @Test
    public void shouldNotUpdateSellInForSulphur() {
        ItemUpdateActions actions = new ItemUpdateActions();
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 40);
        actions.update(item);

        assertEquals(10, item.sellIn());
    }
}