package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BackstageBasedActionProviderTest {

    @Test
    public void shouldImproveQuality() {
        ItemUpdateActions.Action action = ItemUpdateActions.BackstageBasedActionProvider.provide();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 40);
        action.qualityUpdateAction.accept(item);

        assertEquals(41, item.quality());
    }

    @Test
    public void shouldImproveQualityByTwoGivenLessThanTenDaysLeftToSell() {
        ItemUpdateActions.Action action = ItemUpdateActions.BackstageBasedActionProvider.provide();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 9, 40);
        action.qualityUpdateAction.accept(item);

        assertEquals(42, item.quality());
    }

    @Test
    public void shouldImproveQualityByThreeGivenFiveDaysLeftToSell() {
        ItemUpdateActions.Action action = ItemUpdateActions.BackstageBasedActionProvider.provide();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40);
        action.qualityUpdateAction.accept(item);

        assertEquals(43, item.quality());
    }

    @Test
    public void shouldResetQualityPostSellIn() {
        ItemUpdateActions.Action action = ItemUpdateActions.BackstageBasedActionProvider.provide();
        Item item = new Item("Aged Brie", -1, 40);

        action.postSellInQualityUpdateAction.accept(item);
        assertEquals(0, item.quality());
    }

    @Test
    public void shouldReduceTimeLeftToSell() {
        ItemUpdateActions.Action action = ItemUpdateActions.BackstageBasedActionProvider.provide();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40);

        action.sellInUpdateAction.accept(item);
        assertEquals(4, item.sellIn());
    }
}