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
    private final Map<String, Actions> actionsByItemName;

    ItemUpdateActions() {
        this.actionsByItemName = new HashMap<>();
        this.actionsByItemName.put("Sulfuras, Hand of Ragnaros",
                Actions.sensible().
                        addQualityUpdateAction(Action.nothing()).
                        addSellInUpdateAction(Action.nothing()).
                        addPostSellInQualityUpdateAction(Action.nothing()));
        this.actionsByItemName.put("Aged Brie",
                Actions.sensible().
                        addQualityUpdateAction(ImproveQualityActionProvider.provide()).
                        addSellInUpdateAction(ReduceSellInActionProvider.provide()).
                        addPostSellInQualityUpdateAction(ImproveQualityActionProvider.provide()));
        this.actionsByItemName.put("Backstage passes to a TAFKAL80ETC concert",
                Actions.sensible().
                        addQualityUpdateAction(BackstageBasedQualityUpdateActionProvider.provide()).
                        addSellInUpdateAction(ReduceSellInActionProvider.provide()).
                        addPostSellInQualityUpdateAction(ResetQualityActionProvider.provide()));
    }

    void update(Item item) {
        this.updateQualityFor(item);
        this.updateSellInFor(item);
        this.updateQualityPostSellInFor(item);
    }

    private void updateQualityFor(Item item) {
        this.actionsByItemName.getOrDefault(item.name(), Actions.sensible()).updateQualityFor(item);
    }

    private void updateSellInFor(Item item) {
        this.actionsByItemName.getOrDefault(item.name(), Actions.sensible()).updateSellInFor(item);
    }

    private void updateQualityPostSellInFor(Item item) {
        if (item.hasSellByPassed()) {
            this.actionsByItemName.getOrDefault(item.name(), Actions.sensible()).updateQualityPostSellInFor(item);
        }
    }

    static class Actions {

        enum ValueType {
            Quality,
            SellIn,
            PostSellInQuality
        }

        private final Map<ValueType, Action> actions = new HashMap<>();

        static Actions sensible() {
            Actions actions = new Actions();
            actions.actions.put(ValueType.Quality, DegradeQualityActionProvider.provide());
            actions.actions.put(ValueType.SellIn, ReduceSellInActionProvider.provide());
            actions.actions.put(ValueType.PostSellInQuality, DegradeQualityActionProvider.provide());
            return actions;
        }

        Actions addQualityUpdateAction(Action action) {
            this.actions.put(ValueType.Quality, action);
            return this;
        }

        Actions addSellInUpdateAction(Action action) {
            this.actions.put(ValueType.SellIn, action);
            return this;
        }

        Actions addPostSellInQualityUpdateAction(Action action) {
            this.actions.put(ValueType.PostSellInQuality, action);
            return this;
        }

        void updateQualityFor(Item item) {
            actions.get(ValueType.Quality).actOn(item);
        }

        void updateSellInFor(Item item) {
            actions.get(ValueType.SellIn).actOn(item);
        }

        void updateQualityPostSellInFor(Item item) {
            actions.get(ValueType.PostSellInQuality).actOn(item);
        }
    }

    static class Action {
        private final Consumer<Item> block;

        private Action(Consumer<Item> block) {
            this.block = block;
        }

        static Action nothing() {
            return new Action((Item item) -> {
            });
        }

        void actOn(Item item) {
            this.block.accept(item);
        }
    }

    static class ImproveQualityActionProvider {
        static Action provide() {
            return new Action(Item::improveQualityByOne);
        }
    }

    static class DegradeQualityActionProvider {
        static Action provide() {
            return new Action(Item::degradeQualityByOne);
        }
    }

    static class ReduceSellInActionProvider {
        static Action provide() {
            return new Action(Item::reduceSellInByOne);
        }
    }

    static class ResetQualityActionProvider {
        static Action provide() {
            return new Action(Item::resetQuality);
        }
    }

    static class BackstageBasedQualityUpdateActionProvider {
        static Action provide() {
            return new Action(BackstageBasedQualityUpdateActionProvider::updateQualityBasedOnDaysLeftToSell);
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