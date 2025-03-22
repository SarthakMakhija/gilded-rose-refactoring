package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResetQualityActionProviderTest {

    @Test
    public void shouldResetQuality() {
        ItemUpdateActions.Action action = ItemUpdateActions.ResetQualityActionProvider.provide();
        Item item = new Item("Aged Brie", 10, 40);

        action.actOn(item);
        assertEquals(0, item.quality());
    }
}