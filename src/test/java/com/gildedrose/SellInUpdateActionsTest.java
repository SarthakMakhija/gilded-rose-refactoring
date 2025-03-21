package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class SellInUpdateActionsTest {

    @Test
    public void shouldUpdateSellIn() {
        SellInUpdateActions sellInUpdateActions = new SellInUpdateActions();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 40);
        sellInUpdateActions.updateSellInFor(item);

        assertEquals(9, item.sellIn);
    }

    @Test
    public void shouldNotUpdateSellInForSulphur() {
        SellInUpdateActions sellInUpdateActions = new SellInUpdateActions();
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 40);
        sellInUpdateActions.updateSellInFor(item);

        assertEquals(10, item.sellIn);
    }
}