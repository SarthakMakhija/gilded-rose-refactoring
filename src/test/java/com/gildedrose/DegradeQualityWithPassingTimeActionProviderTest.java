package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class DegradeQualityWithPassingTimeActionProviderTest {

    @Test
    public void shouldDegradeQuality() {
        ItemUpdateActions.Action action = ItemUpdateActions.DegradeQualityWithPassingTimeActionProvider.provide();
        Item item = new Item("Aged Brie", 10, 40);

        action.qualityUpdateAction.accept(item);
        assertEquals(39, item.quality);
    }

    @Test
    public void shouldReduceTimeLeftToSell() {
        ItemUpdateActions.Action action = ItemUpdateActions.DegradeQualityWithPassingTimeActionProvider.provide();
        Item item = new Item("Aged Brie", 10, 40);

        action.sellInUpdateAction.accept(item);
        assertEquals(9, item.sellIn);
    }
}