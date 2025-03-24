package ca.tierslieux.iou.lib.logic.items;

import ca.tierslieux.iou.lib.logic.Regex;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tool extends Item {
    private String model;
    private String brand;

    public Tool(String name, String description, int price, LocalDate purchaseDate,
                String pathToReceipt, String location, State status,
                String model, String brand) {
        super(name, description, price, purchaseDate, pathToReceipt, location, status);
        this.model = model;
        this.brand = brand;
    }

    @Override
    public String toJson() {
        String space = "        ";
        return String.format("      \"Tool\": {\n" +
                        space + "\"name\": \"%s\",\n" +
                        space + "\"description\": \"%s\",\n" +
                        space + "\"price\": %s,\n" +
                        space + "\"purchaseDate\": \"%s\",\n" +
                        space + "\"receipt\": \"%s\",\n" +
                        space + "\"location\": \"%s\",\n" +
                        space + "\"status\": \"%s\",\n" +
                        space + "\"model\": \"%s\",\n" +
                        space + "\"brand\": \"%s\"\n" +
                        "      }\n",
                name, description, price, purchaseDate, receipt, location, status, model, brand);
    }

    public String toCsv() {
        return String.format("Tool;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                name, description, getFormattedPrice(), purchaseDate.toString(), receipt, location, status.toString(), model, brand);
    }

    public static Tool fromJson(String json) {
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

        String tempModel = Regex.attributeMatch(json, "model", Regex.ATTRIBUTE_MODE.STRING);
        String tempBrand = Regex.attributeMatch(json, "brand", Regex.ATTRIBUTE_MODE.STRING);

        return new Tool(tempName, tempDescription, tempPrice, tempPurchaseDate, tempReceipt, tempLocation,
                tempStatus, tempModel, tempBrand);
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }
}
