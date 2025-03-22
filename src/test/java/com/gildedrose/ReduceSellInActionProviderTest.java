package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReduceSellInActionProviderTest {

    @Test
    public void shouldReduceSellIn() {
        ItemUpdateActions.Action action = ItemUpdateActions.ReduceSellInActionProvider.provide();
        Item item = new Item("Aged Brie", 10, 40);

        action.actOn(item);
        assertEquals(9, item.sellIn());
    }
}