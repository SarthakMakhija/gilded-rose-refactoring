package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    item.dropQualityByOne();
                }
            } else {
                if (item.quality < 50) {
                    item.increaseQualityByOne();

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn < 11) {
//                            if (item.quality < 50) {
                                item.increaseQualityByOne();
//                            }
                        }

                        if (item.sellIn < 6) {
//                            if (item.quality < 50) {
                                item.increaseQualityByOne();
//                            }
                        }
                    }
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.dropSellInByOne();
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.dropQualityByOne();
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
//                    if (item.quality < 50) {
                        item.increaseQualityByOne();
//                    }
                }
            }
        }
    }
}