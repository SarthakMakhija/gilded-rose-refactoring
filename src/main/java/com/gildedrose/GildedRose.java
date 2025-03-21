package com.gildedrose;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

class GildedRose {
    Item[] items;
    private final Predicate<Item> nonLegendaryItemMatch;
    private final ItemUpdateActions itemUpdateActions;

    //TODO:
    //1. Magic numbers "item names", 6, 3, 11, 2 ...
    //2. Duplication in magic numbers, Sulfuras...
    //3. Visibility specifiers for fields and methods
    public GildedRose(Item[] items) {
        this.items = items;
        this.nonLegendaryItemMatch = ((Predicate<Item>) (Item item) -> item.matchesName("Sulfuras, Hand of Ragnaros")).negate();
        this.itemUpdateActions = new ItemUpdateActions();
    }

    public void updateQuality() {
        for (Item item : items) {
            this.itemUpdateActions.updateQualityFor(item);
            this.itemUpdateActions.updateSellInFor(item);

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

class ItemUpdateActions {

    private final Map<String, Action> actions;

    ItemUpdateActions() {
        this.actions = new HashMap<>();
        this.actions.put("Sulfuras, Hand of Ragnaros", Action.empty());
        this.actions.put("Aged Brie", new Action(Item::increaseQualityByOne, Item::dropSellInByOne));
        this.actions.put("Backstage passes to a TAFKAL80ETC concert", new Action((Item item) -> {
            if (item.sellIn < 6)
                item.increaseQualityBy(3);
            else if (item.sellIn < 11)
                item.increaseQualityBy(2);
            else
                item.increaseQualityByOne();
        }, Item::dropSellInByOne));
    }

    void updateQualityFor(Item item) {
        this.actions.getOrDefault(item.name, Action.degrade()).qualityUpdateAction.accept(item);
    }

    void updateSellInFor(Item item) {
        this.actions.getOrDefault(item.name, Action.degrade()).sellInUpdateAction.accept(item);
    }

    static class Action {
        final Consumer<Item> qualityUpdateAction;
        final Consumer<Item> sellInUpdateAction;

        Action(Consumer<Item> qualityUpdateAction, Consumer<Item> sellInUpdateAction) {
            this.qualityUpdateAction = qualityUpdateAction;
            this.sellInUpdateAction = sellInUpdateAction;
        }

        static Action empty() {
            return new Action((Item item) -> {}, (Item item) -> {});
        }

        static Action degrade() {
            return new Action(Item::dropQualityByOne, Item::dropSellInByOne);
        }
    }
}