package ca.tierslieux.iou.lib.logic.list;

import ca.tierslieux.iou.lib.logic.Regex;
import ca.tierslieux.iou.lib.logic.exception.FileAlreadyExists;
import ca.tierslieux.iou.lib.logic.exception.ItemNotFound;
import ca.tierslieux.iou.lib.logic.exception.ListNotSaved;
import ca.tierslieux.iou.lib.logic.exception.PathNotSpecified;
import ca.tierslieux.iou.lib.logic.file.CustomFile;
import ca.tierslieux.iou.lib.logic.file.FileType;
import ca.tierslieux.iou.lib.logic.file.Json;
import ca.tierslieux.iou.lib.logic.items.*;

import java.io.FileNotFoundException;

public class Inventory implements Json {
    private ItemList list;
    private ItemList restoreList;
    private String listName;
    private boolean modified;
    private String path = null;
    private CustomFile file;

    private static boolean isInstanciated = false;
    private static Inventory instance;

    private Inventory(String listName){
        this.list = new ItemList();
        this.listName = listName;
        this.restoreList = new ItemList();
        this.modified = true;
    }

    public static boolean createInstance(String listName){
        boolean returnCode = false;
        if (!isInstanciated) {
            instance = new Inventory(listName);
            isInstanciated = true;
            instance.modified = false;
            returnCode = true;
        }
        return returnCode;
    }

    public static int createInstanceFromFile(String path) throws FileNotFoundException {
        int returnCode = -1;
        CustomFile listFile = new CustomFile(path, true, FileType.JSON);
        if (!isInstanciated) {
            String json = listFile.read();
            String listName = Regex.listNameMatch(json);
            instance = new Inventory(listName);
            instance.path = path;

            for (String jsonObject : Regex.objectMatches(json)) {
                Item item = null;
                switch (Regex.objectTypeMatch(jsonObject)) {
                    case "Book":
                        item = Book.fromJson(jsonObject);
                        break;
                    case "Game":
                        item = Game.fromJson(jsonObject);
                        break;
                    case "Tool":
                        item = Tool.fromJson(jsonObject);
                        break;
                }
                instance.add(item);
            }
            isInstanciated = true;
            returnCode = 0;

        }
        return returnCode;
    }

    public static Inventory getInstance() {
        Inventory inventory = null;
        if (isInstanciated){
            inventory = instance;
        }
        return inventory;
    }

    public static boolean isInstanciated() {
        return isInstanciated;
    }

    public Item[] getItems() {
        ItemList tempList;
        return list.getItemList();
    }

    public Item[] getRestoreItems() {
        return restoreList.getItemList();
    }

    public Item getRestoreItem(int postion) {
        Item item = null;
        try {
            item = restoreList.getItem(postion);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("La position donnée est à l'extérieur des limites de la listes. La méthode ne retournera aucun item.");
        }
        return item;
    }

    public String getListName() {
        return listName;
    }

    public void add(Item item) {
        list.add(item);
        modified = true;
    }

    public void add(Item item, int position) {
        list.add(item, position);
        modified = true;
    }

    public void remove(Item item) {

        try {
            list.remove(item);
            restoreList.add(item);
            modified = true;
        } catch (ItemNotFound e) {
            System.err.println("L'élément donné ne fait pas partie de la liste. Il ne sera pas retiré de la liste.");
        }
    }

    public void addAll(Item[] items) {
        list.addAll(items);
        modified = true;
    }

    public void pop(int position) {
        try {
            Item removedItem = list.pop(position);
            restoreList.add(removedItem);
            modified = true;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("La position donnée est à l'extérieur des limites de la liste et ne pointe vers aucun élément. Aucun élément ne sera retiré de la liste.");
        }
    }

    public Item getItem(int position) {
        Item item = null;
        try {
            item = list.getItem(position);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("La position donnée est à l'extérieur des limites de la listes. La méthode ne retournera aucun item.");
        }

        return item;
    }

    public void close() throws ListNotSaved {
        if (modified) {
            throw new ListNotSaved();
        } else {
            instance = null;
            isInstanciated = false;
        }

    }

    public void saveAs(String name, String path, boolean force) throws FileAlreadyExists {
        if (name == "") {
            name = this.listName;
        }
        try {
            this.file = CustomFile.saveAtFromText(path, toJson(), FileType.JSON);
            this.path = path;
        } catch (FileAlreadyExists e) {
            if (force) {
                CustomFile.saveAtFromText(path, toJson(), true, FileType.JSON);
            } else {
                throw new FileAlreadyExists();
            }
        }
    }

    public void saveAs(String name, String path) throws FileAlreadyExists {
        if (name == "") {
            name = this.listName;
        }
        try {
            saveAs(name, path, false);
        } catch (FileAlreadyExists e) {
            throw new FileAlreadyExists();
        }
    }

    public void save() {
        if (path == null) {
            throw new PathNotSpecified();
        }
        if (modified) {
            String jsonString = toJson();
            file.write(jsonString, false);
        }
    }

    public void export(String path) {
        CustomFile csvFile;
        String csvString = toCsv();
        csvFile = CustomFile.saveAtFromText(path, csvString, true, FileType.CSV);
    }

    @Override
    public String toJson() {
        String jsonString = String.format("{\n  \"%s\": [\n", listName);
        for (Item item: list.getItemList()) {
            jsonString += "    {\n";
            jsonString += item.toJson();
            jsonString += "    },\n";
        }
        jsonString += "  ]\n}";
        return jsonString;
    }

    public String toCsv() {
        String csv = "";
        for (Item item: list.getItemList()) {
            csv += item.toCsv() + '\n';
        }
        return csv;
    }

}
