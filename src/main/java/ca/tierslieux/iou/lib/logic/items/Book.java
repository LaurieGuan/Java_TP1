package ca.tierslieux.iou.lib.logic.items;

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

    private static String attributeMatch(String text, String attributeName) {
        String formattedString = String.format("(\"%s\": \"?)([0-9a-zA-ZÀ-ʯ ,.\\/:-]+)(\"?,?)", attributeName);
        Pattern p = Pattern.compile(formattedString);
        Matcher match = p.matcher(text);
        return match.group(2);
    }

    @Override
    public String toJson() {
        return "";
    }

    public static Book fromJson(String json) {
        String tempName = attributeMatch(json, "name");
        String tempDescription = attributeMatch(json, "description");

        String tempPriceString = attributeMatch(json, "price");
        int tempPrice = Integer.parseInt(tempPriceString);

        String tempPurchaseDateString = attributeMatch(json, "purchaseDate");
        LocalDate tempPurchaseDate = LocalDate.parse(tempPurchaseDateString, DateTimeFormatter.ISO_LOCAL_DATE);
        String tempReceipt = attributeMatch(json, "receipt");
        String tempLocation = attributeMatch(json, "location");

        String tempStatusString = attributeMatch(json, "status");
        State tempStatus = null;
        switch (tempStatusString) {
            case "STORAGE":
                tempStatus = State.STORAGE;
            case "STOLEN":
                tempStatus = State.STOLEN;
            case "LENT":
                tempStatus = State.LENT;
            case "BROKEN":
                tempStatus = State.BROKEN;
        }

        String tempAuthor = attributeMatch(json, "author");
        String tempPublisher = attributeMatch(json, "publisher");
        String tempPublishedYearString = attributeMatch(json, "publishedYear");
        int tempPublishedYear = Integer.parseInt(tempPublishedYearString);
        String tempIsbn = attributeMatch(json, "isbn");
        String tempPathToImage = attributeMatch(json, "pathToImage");

        return new Book(tempName, tempDescription, tempPrice, tempPurchaseDate, tempReceipt, tempLocation,
                tempStatus, tempAuthor, tempPublisher, tempPublishedYear, tempIsbn, tempPathToImage);
    }
}