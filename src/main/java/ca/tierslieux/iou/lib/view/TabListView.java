package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.Item;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;

public class TabListView {

    public static TabPane getTabListView(Item[] items, Item[] restoreItems) {
        Tab mainListTab = new Tab("Liste des objets");
        Tab restoreListTab = new Tab("Liste de restoration");

        mainListTab.setClosable(false);
        restoreListTab.setClosable(false);

        TableView<Item> mainList = Table.getTableView(items, Table.ListType.MAIN_LIST);
        TableView<Item> restoreList = Table.getTableView(restoreItems, Table.ListType.RESTORE);

        mainListTab.setContent(mainList);
        restoreListTab.setContent(restoreList);


        TabPane panel = new TabPane();
        panel.getTabs().addAll(mainListTab,restoreListTab);

        return panel;
    }

}
