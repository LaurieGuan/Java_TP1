package ca.tierslieux.iou.lib.logic.items;

import ca.tierslieux.iou.lib.Regex;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Book extends Item {
    private String author;
    private String publisher;
    private int publishedYear;
    private String isbn;
    private String pathToImage;
    private Image bookCover = null;
    private boolean imageFound;

    Book(String name, String description, int price, LocalDate purchaseDate,
         String pathToReceipt, String location, State status,
         String author, String publisher, int publishedYear, String isbn, String pathToImage) {

        super(name, description, price, purchaseDate, pathToReceipt, location, status);
        this.author = author;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
        this.pathToImage = pathToImage;

        FileInputStream rawImage = null;
        try {
            rawImage = new FileInputStream(pathToImage);
            this.bookCover = new Image(rawImage);
            this.imageFound = true;
        } catch (FileNotFoundException e) {
            this.imageFound = false;
        }
    }



    @Override
    public String toJson() {
        return "";
    }

    public static Book fromJson(String json) {
        String tempName = Regex.attributeMatch(json, "name", Regex.MODE.STRING);
        String tempDescription = Regex.attributeMatch(json, "description", Regex.MODE.STRING);

        String tempPriceString = Regex.attributeMatch(json, "price", Regex.MODE.NUMBER);
        int tempPrice = Integer.parseInt(tempPriceString);

        String tempPurchaseDateString = Regex.attributeMatch(json, "purchaseDate", Regex.MODE.STRING);
        LocalDate tempPurchaseDate = LocalDate.parse(tempPurchaseDateString, DateTimeFormatter.ISO_LOCAL_DATE);
        String tempReceipt = Regex.attributeMatch(json, "receipt", Regex.MODE.STRING);
        String tempLocation = Regex.attributeMatch(json, "location", Regex.MODE.STRING);

        String tempStatusString = Regex.attributeMatch(json, "status", Regex.MODE.STRING);
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

        String tempAuthor = Regex.attributeMatch(json, "author", Regex.MODE.STRING);
        String tempPublisher = Regex.attributeMatch(json, "publisher", Regex.MODE.STRING);
        String tempPublishedYearString = Regex.attributeMatch(json, "publishedYear", Regex.MODE.NUMBER);
        int tempPublishedYear = Integer.parseInt(tempPublishedYearString);
        String tempIsbn = Regex.attributeMatch(json, "isbn", Regex.MODE.STRING);
        String tempPathToImage = Regex.attributeMatch(json, "pathToImage", Regex.MODE.STRING);

        return new Book(tempName, tempDescription, tempPrice, tempPurchaseDate, tempReceipt, tempLocation,
                tempStatus, tempAuthor, tempPublisher, tempPublishedYear, tempIsbn, tempPathToImage);
    }
}