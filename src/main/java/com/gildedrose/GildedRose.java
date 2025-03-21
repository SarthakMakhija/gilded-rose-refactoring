package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                item.dropQualityByOneIf((Item item1) -> !item1.name.equals("Sulfuras, Hand of Ragnaros"));
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

            item.dropSellInByOneIf((Item item1) -> !item1.name.equals("Sulfuras, Hand of Ragnaros"));

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        item.dropQualityByOneIf((Item item1) -> !item1.name.equals("Sulfuras, Hand of Ragnaros"));
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