package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActionsTest {

    @Test
    public void shouldDegradeQuality() {
        ItemUpdateActions.Actions actions = ItemUpdateActions.Actions.sensible().
                addQualityUpdateAction(ItemUpdateActions.DegradeQualityActionProvider.provide());

        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40);
        actions.updateQualityFor(item);

        assertEquals(39, item.quality());
    }

    @Test
    public void shouldReduceSellIn() {
        ItemUpdateActions.Actions actions = ItemUpdateActions.Actions.sensible().
                addSellInUpdateAction(ItemUpdateActions.ReduceSellInActionProvider.provide());

        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40);
        actions.updateSellInFor(item);

        assertEquals(4, item.sellIn());
    }

    @Test
    public void shouldDegradeQualityPostSellIn() {
        ItemUpdateActions.Actions actions = ItemUpdateActions.Actions.sensible().
                addPostSellInQualityUpdateAction(ItemUpdateActions.DegradeQualityActionProvider.provide());

        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 40);
        actions.updateQualityPostSellInFor(item);

        assertEquals(39, item.quality());
    }
}