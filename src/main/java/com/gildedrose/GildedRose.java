package com.gildedrose;

import java.util.function.Predicate;

class GildedRose {
    Item[] items;
    Predicate<Item> nonLegendaryItemMatch;

    public GildedRose(Item[] items) {
        this.items = items;
        this.nonLegendaryItemMatch = ((Predicate<Item>) (Item item) -> item.matchesName("Sulfuras, Hand of Ragnaros")).negate();
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                item.dropQualityByOneIf(this.nonLegendaryItemMatch);
            } else {
                if (item.quality < 50) { //TODO: combine this condition and increaseQualityByOne
                    item.increaseQualityByOne();

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn < 11) {
                            item.increaseQualityByOne();
                        }
                        if (item.sellIn < 6) {
                            item.increaseQualityByOne();
                        }
                    }
                }
            }

            item.dropSellInByOneIf(this.nonLegendaryItemMatch);

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        item.dropQualityByOneIf(this.nonLegendaryItemMatch);
                    } else {
                        item.quality = 0;
                    }
                } else {
                    item.increaseQualityByOne();
                }
            }
        }
    }
}