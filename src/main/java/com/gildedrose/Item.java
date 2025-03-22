package com.gildedrose;

public class Item {

    private final String name;

    public int sellIn;

    public int quality;

    public static final int PeakQuality = 50;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
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

    void resetQuality() {
        this.quality = 0;
    }

    void reduceSellInByOne() {
        this.sellIn = this.sellIn - 1;
    }

    boolean hasSellByPassed() {
        return this.sellIn < 0;
    }

    int daysLeftToSell() {
        return this.sellIn;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}