package ca.tierslieux.iou.lib.logic.items;

import ca.tierslieux.iou.lib.logic.Regex;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Game extends Item {
    private String publisher;
    private int minimumAge;
    private int minNumberPlayers;
    private int maxNumberPlayers;
    private String pathToImage;
    private Image gameCover = null;
    private boolean imageFound;


    public Game(String name, String description, int price, LocalDate purchaseDate,
                String pathToReceipt, String location, State status,
                String publisher, int minimumAge, int minNumberPlayers, int maxNumberPlayers, String pathToImage) {
        super(name, description, price, purchaseDate, pathToReceipt, location, status);
        this.publisher = publisher;
        this.minimumAge = minimumAge;
        this.minNumberPlayers = minNumberPlayers;
        this.maxNumberPlayers = maxNumberPlayers;
        this.pathToImage = pathToImage;

        FileInputStream rawImage = null;
        try {
            rawImage = new FileInputStream(pathToImage);
            this.gameCover = new Image(rawImage);
            this.imageFound = true;
        } catch (FileNotFoundException e) {
            this.imageFound = false;
        }
    }

    @Override
    public String toJson() {
        String space = "        ";
        return String.format("      \"Game\": {\n" +
                        space + "\"name\": \"%s\",\n" +
                        space + "\"description\": \"%s\",\n" +
                        space + "\"price\": %s,\n" +
                        space + "\"purchaseDate\": \"%s\",\n" +
                        space + "\"receipt\": \"%s\",\n" +
                        space + "\"location\": \"%s\",\n" +
                        space + "\"status\": \"%s\",\n" +
                        space + "\"publisher\": \"%s\",\n" +
                        space + "\"minimumAge\": %d,\n" +
                        space + "\"minNumberPlayers\": %d,\n" +
                        space + "\"maxNumberPlayers\": %d,\n" +
                        space + "\"pathToImage\": \"%s\"\n" +
                        "      }\n",
                name, description, price, purchaseDate, receipt, location, status, publisher, minimumAge, minNumberPlayers, maxNumberPlayers, pathToImage);
    }
    public String toCsv() {
        return String.format("Game;%s;%s;%s;%s;%s;%s;%s;%s;%d;%d;%d;%s",
                name, description, getFormattedPrice(), purchaseDate.toString(), receipt, location, status.toString(),
                publisher, minimumAge, minNumberPlayers, maxNumberPlayers, pathToImage);
    }

    public static Game fromJson(String json) {
        String tempName = Regex.attributeMatch(json, "name", Regex.ATTRIBUTE_MODE.STRING);
        String tempDescription = Regex.attributeMatch(json, "description", Regex.ATTRIBUTE_MODE.STRING);

        String tempPriceString = Regex.attributeMatch(json, "price", Regex.ATTRIBUTE_MODE.NUMBER);
        int tempPrice = Integer.parseInt(tempPriceString);

        String tempPurchaseDateString = Regex.attributeMatch(json, "purchaseDate", Regex.ATTRIBUTE_MODE.STRING);
        LocalDate tempPurchaseDate = LocalDate.parse(tempPurchaseDateString, DateTimeFormatter.ISO_LOCAL_DATE);
        String tempReceipt = Regex.attributeMatch(json, "receipt", Regex.ATTRIBUTE_MODE.STRING);
        String tempLocation = Regex.attributeMatch(json, "location", Regex.ATTRIBUTE_MODE.STRING);

        String tempStatusString = Regex.attributeMatch(json, "status", Regex.ATTRIBUTE_MODE.STRING);
        State tempStatus = null;
        switch (tempStatusString) {
            case "STORAGE":
                tempStatus = State.STORAGE;
                break;
            case "STOLEN":
                tempStatus = State.STOLEN;
                break;
            case "LENT":
                tempStatus = State.LENT;
                break;
            case "BROKEN":
                tempStatus = State.BROKEN;
                break;
        }

        String tempPublisher = Regex.attributeMatch(json, "publisher", Regex.ATTRIBUTE_MODE.STRING);
        String tempMinimumAgeString = Regex.attributeMatch(json, "minimumAge", Regex.ATTRIBUTE_MODE.NUMBER);
        int tempMinimumAge = Integer.parseInt(tempMinimumAgeString);
        String tempMinNumberPlayersString = Regex.attributeMatch(json, "minNumberPlayers", Regex.ATTRIBUTE_MODE.NUMBER);
        int tempMinNumberPlayers = Integer.parseInt(tempMinNumberPlayersString);
        String tempMaxNumberPlayersString = Regex.attributeMatch(json, "maxNumberPlayers", Regex.ATTRIBUTE_MODE.NUMBER);
        int tempMaxNumberPlayers = Integer.parseInt(tempMaxNumberPlayersString);
        String tempPathToImage = Regex.attributeMatch(json, "pathToImage", Regex.ATTRIBUTE_MODE.STRING);

        return new Game(tempName, tempDescription, tempPrice, tempPurchaseDate, tempReceipt, tempLocation,
                tempStatus, tempPublisher, tempMinimumAge, tempMinNumberPlayers, tempMaxNumberPlayers, tempPathToImage);
    }

    public String getPublisher() {
        return publisher;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public int getMinNumberPlayers() {
        return minNumberPlayers;
    }

    public int getMaxNumberPlayers() {
        return maxNumberPlayers;
    }

    public Type getType() {
        return Type.GAME;
    }


    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public void setMinNumberPlayers(int minNumberPlayers) {
        this.minNumberPlayers = minNumberPlayers;
    }

    public void setMaxNumberPlayers(int maxNumberPlayers) {
        this.maxNumberPlayers = maxNumberPlayers;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}
