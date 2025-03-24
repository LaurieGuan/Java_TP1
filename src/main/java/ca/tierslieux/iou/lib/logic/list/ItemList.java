package ca.tierslieux.iou.lib.logic.list;

import ca.tierslieux.iou.lib.logic.exception.ItemNotFound;
import ca.tierslieux.iou.lib.logic.exception.ListNotSaved;
import ca.tierslieux.iou.lib.logic.items.Item;

public class ItemList {
    private Item[] array;
    private int arrayLength;

    public ItemList() {
        array = new Item[10];
        arrayLength = 0;
    }

    public Item[] getItemList() {
        Item[] tempArray = new Item[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            tempArray[i] = array[i];
        }
        return tempArray;
    }

    public Item getItem(int position) throws IndexOutOfBoundsException {
        Item returnedItem = null;
        boolean indexAllowed = (position < arrayLength) && (position >= -arrayLength);
        if (indexAllowed) {
            if (position < 0) {
                position += arrayLength;
            }
            returnedItem = array[position];
        } else {
            throw new IndexOutOfBoundsException();
        }
        return returnedItem;
    }

    public int getArrayLength() {
        return arrayLength;
    }

    public void add(Item item) {
        arrayLength++;
        if (((arrayLength - 1) % 10) == 0) {
            Item[] tempArray = new Item[arrayLength + 9];
            for (int i = 0; i < (arrayLength - 1); i++) {
                tempArray[i] = array[i];
            }
            array = tempArray;
        }
        array[arrayLength - 1] = item;
    }

    public void add(Item item, int position) {
        arrayLength++;
        if (((arrayLength - 1) % 10) == 0) {
            Item[] tempArray = new Item[arrayLength + 9];
            for (int i = 0; i < (arrayLength - 1); i++) {
                tempArray[i] = array[i];
            }
            array = tempArray;
        }

        if (position >= arrayLength) {
            array[arrayLength - 1] = item;
        } else {
            if (position < 0) { position = 0; }
            Item[] arrayTemp = new Item[array.length];
            for (int i = 0; i < position; i++) {
                arrayTemp[i] = array[i];
            }
            arrayTemp[position] = item;
            for (int i = position + 1; i < arrayLength; i++) {
                arrayTemp[i] = array[i - 1];
            }
            array = arrayTemp;
        }
    }

    public void addAll(Item[] items) {
        for (Item item : items) {
            add(item);
        }
    }

    public Item pop(int position) throws IndexOutOfBoundsException {
        boolean indexAllowed = (position < arrayLength) && (position >= -arrayLength);
        Item removedItem = null;
        if (indexAllowed) {
            if (position < 0) {
                position += arrayLength;
            }
            removedItem = array[position];
            for (int i = 0; i < arrayLength; i++) {
                if (i > position) {
                    array[i - 1] = array[i];
                    array[i] = null;
                }
            }
            arrayLength--;
            if (((arrayLength + 1) % 10) == 0) {
                Item[] tempArray = new Item[arrayLength];
                for (int i = 0; i < arrayLength; i++) {
                    tempArray[i] = array[i];
                }
                array = tempArray;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return removedItem;
    }
    public void remove(Item item) {
        boolean itemFound = false;
        for (int i = 0; i < arrayLength; i++) {
            if (itemFound) {
                array[i - 1] = array[i];
                array[i] = null;
            }
            if (!itemFound && item == array[i]) {
                itemFound = true;
            }
        }
        if (itemFound) {
            arrayLength--;
            if (((arrayLength + 1) % 10) == 0) {
                Item[] tempArray = new Item[arrayLength];
                for (int i = 0; i < arrayLength; i++) {
                    tempArray[i] = array[i];
                }
                array = tempArray;
            }
        } else {
            throw new ItemNotFound();
        }
    }
}
