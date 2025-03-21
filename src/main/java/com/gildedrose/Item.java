package com.gildedrose;

import java.util.function.Predicate;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public static final int PeakQuality = 50;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    void degradeQualityByOneIf(Predicate<Item> predicate) {
        if (predicate.test(this)) {
            degradeQualityByOne();
        }
    }

    void degradeQualityByOne() {
        if (this.quality > 0) {
            this.quality = this.quality - 1;
        }
    }

    void improveQualityBy(int unit) {
        for (int count = 1; count <= unit; count++) this.improveQualityByOne();
    }

    void improveQualityByOne() {
        if (this.quality < PeakQuality) {
            this.quality = this.quality + 1;
        }
    }

    void dropSellInByOne() {
        this.sellIn = this.sellIn - 1;
    }

    boolean matchesName(String name) {
        return this.name.equals(name);
    }

    boolean hasSellByPassed() {
        return this.sellIn < 0;
    }

    int daysLeftToSell() {
        return this.sellIn;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}