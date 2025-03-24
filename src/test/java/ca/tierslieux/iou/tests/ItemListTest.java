package ca.tierslieux.iou.tests;

import ca.tierslieux.iou.lib.logic.exception.ItemNotFound;
import ca.tierslieux.iou.lib.logic.items.*;
import org.junit.jupiter.api.Test;
import ca.tierslieux.iou.lib.logic.list.ItemList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ItemListTest {

    @Test
    void add100() {
        ItemList list = new ItemList();
        for (int i = 0; i < 100; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }
        assertEquals(100, list.getArrayLength());
    }

    @Test
    void add1000() {
        ItemList list = new ItemList();
        for (int i = 0; i < 1000; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }
        assertEquals(1000, list.getArrayLength());
    }
    @Test
    void add10000() {
        ItemList list = new ItemList();
        for (int i = 0; i < 10000; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }
        assertEquals(10000, list.getArrayLength());
    }

    @Test
    void add100000() {
        ItemList list = new ItemList();
        for (int i = 0; i < 100000; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }
        assertEquals(100000, list.getArrayLength());
    }

    @Test
    void getItem() {
        ItemList list = new ItemList();
        Book book = new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");

        list.add(book);

        for (int i = 1; i < 100; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }
        assertEquals(book, list.getItem(0));
    }

    @Test
    void getItemEnd() {
        ItemList list = new ItemList();
        Book book = new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");

        for (int i = 0; i < 99; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }

        list.add(book);

        assertEquals(book, list.getItem(99));
    }

    @Test
    void getItemNegativeEnd() {
        ItemList list = new ItemList();
        Book book = new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");

        list.add(book);

        for (int i = 1; i < 100; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }
        assertEquals(book, list.getItem(-100));
    }

    @Test
    void getItemOutOfBounds() {
        ItemList list = new ItemList();
        for (int i = 0; i < 10; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }

        Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.getItem(-11);
        });
        e = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.getItem(10);
        });

    }

    @Test
    void getWholeList() {
        ItemList list = new ItemList();
        Book book1 = new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");
        list.add(book1);

        Game game = new Game("test", "test", 1000, LocalDate.parse("1900-01-30"), "C:\\Users\\Test",
                "Maison", State.STORAGE, "AlloGames", 10, 1, 8,
                "C:\\Users\\Allo\\Downloads\\test.png");
        list.add(game);

        Book book2 = new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");
        list.add(book2);

        Item[] array = new Item[3];
        array[0] = book1;
        array[1] = game;
        array[2] = book2;

        Item[] arrayList = list.getItemList();

        for (int i = 0; i < 3; i++) {
            assertEquals(array[i], arrayList[i]);
        }

    }

    @Test
    void getWholeBigList() {
        ItemList list = new ItemList();
        Item[] array = new Item[1000];

        for (int i = 0; i < 1000; i++) {
            Book book = new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users");
            list.add(book);
            array[i] = book;

        }

        Item[] arrayList = list.getItemList();

        for (int i = 0; i < 1000; i++) {
            assertEquals(array[i], arrayList[i]);
        }
    }

    @Test
    void addPosition() {
        ItemList list = new ItemList();
        for (int i = 0; i < 10; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }
        Tool tool = new Tool("test", "test", 1000, LocalDate.parse("1900-01-30"), "C:\\Users\\Test",
                "Maison", State.STORAGE, "AlloGames", "test");

        list.add(tool, 5);
        assertEquals(tool, list.getItem(5));
    }

    @Test
    void addNegativeOutOfBoundsPosition() {
        ItemList list = new ItemList();
        for (int i = 0; i < 10; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }
        Tool tool = new Tool("test", "test", 1000, LocalDate.parse("1900-01-30"), "C:\\Users\\Test",
                "Maison", State.STORAGE, "AlloGames", "test");

        list.add(tool, -100);
        assertEquals(tool, list.getItem(0));
    }

    @Test
    void addPositiveOutOfBoundsPosition() {
        ItemList list = new ItemList();
        for (int i = 0; i < 10; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }
        Tool tool = new Tool("test", "test", 1000, LocalDate.parse("1900-01-30"), "C:\\Users\\Test",
                "Maison", State.STORAGE, "AlloGames", "test");

        list.add(tool, 100);
        assertEquals(tool, list.getItem(-1));
    }

    @Test
    void addAll() {
        ItemList list = new ItemList();
        Book book1 = new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");
        list.add(book1);

        Game game = new Game("test", "test", 1000, LocalDate.parse("1900-01-30"), "C:\\Users\\Test",
                "Maison", State.STORAGE, "AlloGames", 10, 1, 8,
                "C:\\Users\\Allo\\Downloads\\test.png");
        list.add(game);

        Book book2 = new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");
        list.add(book2);

        Item[] array = new Item[3];
        array[0] = book1;
        array[1] = game;
        array[2] = book2;

        list.addAll(array);
    }

    @Test
    void bigAddAll() {
        ItemList list = new ItemList();
        Item[] array = new Item[1000];

        for (int i = 0; i < 1000; i++) {

           Tool tool = new Tool("test", "test", 1000, LocalDate.parse("1900-01-30"), "C:\\Users\\Test",
                    "Maison", State.STORAGE, "AlloGames", "test");
           array[i] = tool;
        }

        list.addAll(array);

        for (int i = 0; i < 1000; i++) {
            assertEquals(array[i], list.getItem(i));
        }
    }

    @Test
    void emptyAddAll() {
        ItemList list = new ItemList();
        Item[] array = new Item[0];

        list.addAll(array);

        Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.getItem(0);
        });
    }

    @Test
    void pop() {
        ItemList list = new ItemList();
        for (int i = 0; i < 49; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));

        }

        Book book =  new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");
        list.add(book);

        int sizeBeforePop = list.getArrayLength();
        assertEquals(book, list.pop(-1));
        assertEquals(sizeBeforePop - 1, list.getArrayLength());
    }

    @Test
    void popBeginning() {
        ItemList list = new ItemList();

        Book comparison =  new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");
        list.add(comparison);


        Book book1 = new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");
        list.add(book1);

        Game game = new Game("test", "test", 1000, LocalDate.parse("1900-01-30"), "C:\\Users\\Test",
                "Maison", State.STORAGE, "AlloGames", 10, 1, 8,
                "C:\\Users\\Allo\\Downloads\\test.png");
        list.add(game);

        Book book2 = new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");
        list.add(book2);

        Item[] array = new Item[3];
        array[0] = book1;
        array[1] = game;
        array[2] = book2;

        assertEquals(comparison, list.pop(0));

        for (int i = 0; i < 3; i++) {
            assertEquals(array[i], list.getItem(i));
        }
    }

    @Test
    void remove() {
        ItemList list = new ItemList();
        for (int i = 0; i < 49; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));
        }

        Book book =  new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");
        list.add(book);

        int sizeBeforeRem = list.getArrayLength();
        try {
            list.remove(book);
        } catch (ItemNotFound _) {
        }

        assertEquals(sizeBeforeRem - 1, list.getArrayLength());

    }

    void removeItemNotFound() {
        ItemList list = new ItemList();
        for (int i = 0; i < 49; i++) {
            list.add(new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                    "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                    "C:\\Users"));

        }

        Book book =  new Book("test", "test", 100, LocalDate.parse("2000-01-01"), "C:\\",
                "Char", State.STOLEN, "tolkien", "no", 1031, "12031-1313-13",
                "C:\\Users");

        Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(book);
        });
    }
}