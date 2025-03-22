package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImproveQualityActionProviderTest {

    @Test
    public void shouldImproveQuality() {
        ItemUpdateActions.Action action = ItemUpdateActions.ImproveQualityActionProvider.provide();
        Item item = new Item("Aged Brie", 10, 40);

        action.actOn(item);
        assertEquals(41, item.quality());
    }
}