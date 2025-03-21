package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.*;

public class QualityUpdateActionsTest {

    @Test
    public void shouldNotUpdateQualityForSulfuras() {
        QualityUpdateActions qualityUpdateActions = new QualityUpdateActions();
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 40);
        qualityUpdateActions.updateQualityFor(item);

        assertEquals(40, item.quality);
    }

    @Test
    public void shouldIncreaseQualityForAgedBrie() {
        QualityUpdateActions qualityUpdateActions = new QualityUpdateActions();
        Item item = new Item("Aged Brie", 10, 40);
        qualityUpdateActions.updateQualityFor(item);

        assertEquals(41, item.quality);
    }

    @Test
    public void shouldIncreaseQualityForBackstage() {
        QualityUpdateActions qualityUpdateActions = new QualityUpdateActions();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 40);
        qualityUpdateActions.updateQualityFor(item);

        assertEquals(41, item.quality);
    }

    @Test
    public void shouldIncreaseQualityByTwoForBackstageGivenLessThanTenDaysLeftToSell() {
        QualityUpdateActions qualityUpdateActions = new QualityUpdateActions();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 9, 40);
        qualityUpdateActions.updateQualityFor(item);

        assertEquals(42, item.quality);
    }

    @Test
    public void shouldIncreaseQualityByThreeForBackstageGivenFiveDaysLeftToSell() {
        QualityUpdateActions qualityUpdateActions = new QualityUpdateActions();
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40);
        qualityUpdateActions.updateQualityFor(item);

        assertEquals(43, item.quality);
    }

    @Test
    public void shouldDropQuality() {
        QualityUpdateActions qualityUpdateActions = new QualityUpdateActions();
        Item item = new Item("Elixir of the Mongoose", 10, 40);
        qualityUpdateActions.updateQualityFor(item);

        assertEquals(39, item.quality);
    }
}