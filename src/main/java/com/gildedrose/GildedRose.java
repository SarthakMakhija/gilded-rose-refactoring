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

            if (item.hasSellByPassed()) {
                if (item.name.equals("Aged Brie")) {
                    item.improveQualityByOne();
                } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    item.quality = 0;
                } else {
                    item.degradeQualityByOneIf(this.nonLegendaryItemMatch);
                }
            }
        }
    }
}

class ItemUpdateActions {
    private final Map<String, Action> actions;

    ItemUpdateActions() {
        this.actions = new HashMap<>();
        this.actions.put("Sulfuras, Hand of Ragnaros",
                Action.nothing());
        this.actions.put("Aged Brie",
                ImproveQualityWithPassingTimeActionProvider.provide());
        this.actions.put("Backstage passes to a TAFKAL80ETC concert",
                BackstageBasedActionProvider.provide());
    }

    void updateQualityFor(Item item) {
        this.actions.getOrDefault(item.name, DegradeQualityWithPassingTimeActionProvider.provide()).qualityUpdateAction.accept(item);
    }

    void updateSellInFor(Item item) {
        this.actions.getOrDefault(item.name, DegradeQualityWithPassingTimeActionProvider.provide()).sellInUpdateAction.accept(item);
    }

    static class Action {
        final Consumer<Item> qualityUpdateAction;
        final Consumer<Item> sellInUpdateAction;

        private Action(Consumer<Item> qualityUpdateAction, Consumer<Item> sellInUpdateAction) {
            this.qualityUpdateAction = qualityUpdateAction;
            this.sellInUpdateAction = sellInUpdateAction;
        }

        static Action nothing() {
            return new Action((Item item) -> {}, (Item item) -> {});
        }
    }

    static class ImproveQualityWithPassingTimeActionProvider {
        static Action provide() {
            return new Action(Item::improveQualityByOne, Item::reduceSellInByOne);
        }
    }

    static class DegradeQualityWithPassingTimeActionProvider {
        static Action provide() {
            return new Action(Item::degradeQualityByOne, Item::reduceSellInByOne);
        }
    }

    static class BackstageBasedActionProvider {
        static Action provide() {
            return new Action(BackstageBasedActionProvider::updateQualityBasedOnDaysLeftToSell, Item::reduceSellInByOne);
        }
        private static void updateQualityBasedOnDaysLeftToSell(Item item) {
            if (item.daysLeftToSell() < 6)
                item.improveQualityBy(3);
            else if (item.daysLeftToSell() < 11)
                item.improveQualityBy(2);
            else
                item.improveQualityByOne();
        }
    }
}