package com.gildedrose;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

class GildedRose {
    Item[] items;
    private final Predicate<Item> nonLegendaryItemMatch;
    private final QualityUpdateActions qualityUpdateActions;
    private final SellInUpdateActions sellInUpdateActions;

    //TODO:
    //1. Magic numbers "item names", 6, 3, 11, 2 ...
    //2. Duplication in magic numbers, Sulfuras...
    //3. Visibility specifiers for fields and methods
    public GildedRose(Item[] items) {
        this.items = items;
        this.nonLegendaryItemMatch = ((Predicate<Item>) (Item item) -> item.matchesName("Sulfuras, Hand of Ragnaros")).negate();
        this.qualityUpdateActions = new QualityUpdateActions();
        this.sellInUpdateActions = new SellInUpdateActions();
    }

    public void updateQuality() {
        for (Item item : items) {
            this.qualityUpdateActions.updateQualityFor(item);
            this.sellInUpdateActions.updateSellInFor(item);

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

class QualityUpdateActions {

    private final Map<String, Consumer<Item>> qualityUpdateActions;

    QualityUpdateActions() {
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

    void updateQualityFor(Item item) {
        this.qualityUpdateActions.getOrDefault(item.name, Item::dropQualityByOne).accept(item);
    }
}

class SellInUpdateActions {
    private final Map<String, Consumer<Item>> sellInUpdateActions;

    SellInUpdateActions() {
        this.sellInUpdateActions = new HashMap<>();
        this.sellInUpdateActions.put("Sulfuras, Hand of Ragnaros", (Item item) -> {});
    }

    void updateSellInFor(Item item) {
        this.sellInUpdateActions.getOrDefault(item.name, Item::dropSellInByOne).accept(item);
    }
}