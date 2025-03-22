package com.gildedrose;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class GildedRose {
    private final Item[] items;
    private final ItemUpdateActions itemUpdateActions;

    public GildedRose(Item[] items) {
        this.items = items;
        this.itemUpdateActions = new ItemUpdateActions();
    }

    public void update() {
        for (Item item : items) {
            this.itemUpdateActions.update(item);
        }
    }

    Item[] items() {
        return this.items;
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

    void update(Item item) {
        this.updateQualityFor(item);
        this.updateSellInFor(item);
        this.updateQualityPostSellInFor(item);
    }

    private void updateQualityFor(Item item) {
        this.actions.
                getOrDefault(item.name(), DegradeQualityWithPassingTimeActionProvider.provide()).
                qualityUpdateAction.
                accept(item);
    }

    private void updateSellInFor(Item item) {
        this.actions.
                getOrDefault(item.name(), DegradeQualityWithPassingTimeActionProvider.provide()).
                sellInUpdateAction.
                accept(item);
    }

    private void updateQualityPostSellInFor(Item item) {
        if (item.hasSellByPassed()) {
            this.actions.
                    getOrDefault(item.name(), DegradeQualityWithPassingTimeActionProvider.provide()).
                    postSellInQualityUpdateAction.
                    accept(item);
        }
    }

    //TODO: revisit this (maybe separate out quality and sell update concepts) and the map as a data structure inside ItemUpdateActions
    static class Action {
        final Consumer<Item> qualityUpdateAction;
        final Consumer<Item> sellInUpdateAction;
        final Consumer<Item> postSellInQualityUpdateAction;

        private Action(Consumer<Item> qualityUpdateAction, Consumer<Item> sellInUpdateAction, Consumer<Item> postSellInQualityUpdateAction) {
            this.qualityUpdateAction = qualityUpdateAction;
            this.sellInUpdateAction = sellInUpdateAction;
            this.postSellInQualityUpdateAction = postSellInQualityUpdateAction;
        }

        static Action nothing() {
            return new Action((Item item) -> {}, (Item item) -> {}, (Item item) -> {});
        }
    }

    static class ImproveQualityWithPassingTimeActionProvider {
        static Action provide() {
            return new Action(Item::improveQualityByOne, Item::reduceSellInByOne, Item::improveQualityByOne);
        }
    }

    static class DegradeQualityWithPassingTimeActionProvider {
        static Action provide() {
            return new Action(Item::degradeQualityByOne, Item::reduceSellInByOne, Item::degradeQualityByOne);
        }
    }

    static class BackstageBasedActionProvider {
        static Action provide() {
            return new Action(BackstageBasedActionProvider::updateQualityBasedOnDaysLeftToSell, Item::reduceSellInByOne, Item::resetQuality);
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