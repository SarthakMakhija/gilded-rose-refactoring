package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseFunctionalTest {

    @Test
    public void allItemsStateAfterUpdate() {
        GildedRose app = createGildedRose();
        app.update();
        app.update();

        Item[] expected = new Item[]{
                new Item("+5 Dexterity Vest", 8, 18),
                new Item("Aged Brie", 0, 2),
                new Item("Aged Brie", -1, 3),
                new Item("Aged Brie", -2, 4),
                new Item("Elixir of the Mongoose", 3, 5),
                new Item("Elixir of the Mongoose", -2, 3),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 13, 22),
                new Item("Backstage passes to a TAFKAL80ETC concert", 8, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 3, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", -2, 0)};

        for (int index = 0; index < expected.length; index++) {
            assertEquals(expected[index].name(), app.items()[index].name());
            assertEquals(expected[index].sellIn(), app.items()[index].sellIn());
            assertEquals(expected[index].quality(), app.items()[index].quality());
        }
    }

    private static GildedRose createGildedRose() {
        return new GildedRose(new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Aged Brie", 1, 0),
                new Item("Aged Brie", 0, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Elixir of the Mongoose", 0, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10)}
        );
    }
}