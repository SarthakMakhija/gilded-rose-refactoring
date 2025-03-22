package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class DegradeQualityActionProviderTest {

    @Test
    public void shouldDegradeQuality() {
        ItemUpdateActions.Action action = ItemUpdateActions.DegradeQualityActionProvider.provide();
        Item item = new Item("Aged Brie", 10, 40);

        action.actOn(item);
        assertEquals(39, item.quality());
    }
}