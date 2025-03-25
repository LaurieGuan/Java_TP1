package ca.tierslieux.iou.lib.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class ToolBar {
    public static HBox Generate() {
        Button addItem = new Button("Ajouter objet");
        Button removeItem = new Button("Retirer objet");
        ToggleButton gameFilter = new ToggleButton("Afficher jeux");
        ToggleButton bookFilter = new ToggleButton("Afficher livres");
        ToggleButton toolFilter = new ToggleButton("Afficher outils");
        Separator addFilterSeperator = new Separator();
        Label filter = new Label("Filtre:");
        ChoiceBox chooseFilter = new ChoiceBox();
        TextField description = new TextField();

        description.setPromptText("Recherche");

        HBox toolBar = new HBox(addItem, removeItem, addFilterSeperator, gameFilter, bookFilter, toolFilter, filter, chooseFilter, description);

        return toolBar;
    }
}
