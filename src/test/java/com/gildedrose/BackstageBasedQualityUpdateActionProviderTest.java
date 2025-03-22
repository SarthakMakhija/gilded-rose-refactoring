package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BackstageBasedQualityUpdateActionProviderTest {

    @Test
    public void shouldImproveQuality() {
        ItemUpdateActions.Action action = ItemUpdateActions.BackstageBasedQualityUpdateActionProvider.provide();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 40);
        action.actOn(item);

        assertEquals(41, item.quality());
    }

    @Test
    public void shouldImproveQualityByTwoGivenLessThanTenDaysLeftToSell() {
        ItemUpdateActions.Action action = ItemUpdateActions.BackstageBasedQualityUpdateActionProvider.provide();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 9, 40);
        action.actOn(item);

        assertEquals(42, item.quality());
    }

    @Test
    public void shouldImproveQualityByThreeGivenFiveDaysLeftToSell() {
        ItemUpdateActions.Action action = ItemUpdateActions.BackstageBasedQualityUpdateActionProvider.provide();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40);
        action.actOn(item);

        assertEquals(43, item.quality());
    }
}