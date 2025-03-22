package com.gildedrose;

import java.util.HashMap;
import java.util.List;
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
    private final Map<String, List<Action>> actionsByItemName;

    ItemUpdateActions() {
        this.actionsByItemName = new HashMap<>();
        this.actionsByItemName.put("Sulfuras, Hand of Ragnaros",
                List.of(Action.nothing(), Action.nothing(), Action.nothing()));
        this.actionsByItemName.put("Aged Brie",
                List.of(ImproveQualityActionProvider.provide(), ReduceSellInActionProvider.provide(), ImproveQualityActionProvider.provide()));
        this.actionsByItemName.put("Backstage passes to a TAFKAL80ETC concert",
                List.of(BackstageBasedQualityUpdateActionProvider.provide(), ReduceSellInActionProvider.provide(), ResetQualityActionProvider.provide()));
    }

    void update(Item item) {
        this.updateQualityFor(item);
        this.updateSellInFor(item);
        this.updateQualityPostSellInFor(item);
    }

    private void updateQualityFor(Item item) {
        List<Action> actions = this.actionsByItemName.getOrDefault(item.name(), List.of());
        if (actions.isEmpty()) {
            DegradeQualityActionProvider.provide().actOn(item);
            return;
        }
        actions.get(0).actOn(item);
    }

    private void updateSellInFor(Item item) {
        List<Action> actions = this.actionsByItemName.getOrDefault(item.name(), List.of());
        if (actions.isEmpty()) {
            ReduceSellInActionProvider.provide().actOn(item);
            return;
        }
        actions.get(1).actOn(item);
    }

    private void updateQualityPostSellInFor(Item item) {
        if (item.hasSellByPassed()) {
            List<Action> actions = this.actionsByItemName.getOrDefault(item.name(), List.of());
            if (actions.isEmpty()) {
                DegradeQualityActionProvider.provide().actOn(item);
                return;
            }
            actions.get(2).actOn(item);
        }
    }

    //TODO: revisit this (maybe separate out quality and sell update concepts) and the map as a data structure inside ItemUpdateActions
    static class Action {
        final Consumer<Item> block;

        private Action(Consumer<Item> block) {
            this.block = block;
        }

        static Action nothing() {
            return new Action((Item item) -> {});
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