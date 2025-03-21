package com.gildedrose;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

class GildedRose {
    Item[] items;
    Predicate<Item> nonLegendaryItemMatch;
    Map<String, Consumer<Item>> qualityUpdateActions;

    public GildedRose(Item[] items) {
        this.items = items;
        this.nonLegendaryItemMatch = ((Predicate<Item>) (Item item) -> item.matchesName("Sulfuras, Hand of Ragnaros")).negate();
        this.qualityUpdateActions = new HashMap<>();

        this.qualityUpdateActions.put("Sulfuras, Hand of Ragnaros", (Item item) -> {});
        this.qualityUpdateActions.put("Aged Brie", Item::increaseQualityByOne);
        this.qualityUpdateActions.put("Backstage passes to a TAFKAL80ETC concert", (Item item) -> {
            if (item.sellIn < 6)
                item.increaseQualityBy(3);
            else if (item.sellIn < 11)
                item.increaseQualityBy(2);
            else
                item.increaseQualityByOne();
        });
    }

    public void updateQuality() {
        for (Item item : items) {
            Consumer<Item> action = this.qualityUpdateActions.getOrDefault(item.name, Item::dropQualityByOne);
            action.accept(item);

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