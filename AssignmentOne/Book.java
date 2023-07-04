//Haris Sujethan 501088408

import java.util.Objects;

/* A book IS A product that has additional information - e.g. title, author

      A book also comes in different formats ("Paperback", "Hardcover", "EBook")

      The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product {
    // Stock related information NOTE: inherited stockCount variable is used for
    // EBooks
    int paperbackStock;
    int hardcoverStock;
    private String author;
    private String title;
    private int year;

    public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title,
            String author, int year) {
        // Make use of the constructor in the super class Product. Initialize additional
        // Book instance variables.
        // Set category to BOOKS

        super(name, id, price, 100000, Product.Category.BOOKS);
        this.title = title;
        this.author = author;
        this.paperbackStock = paperbackStock;
        this.hardcoverStock = hardcoverStock;
        this.year = year;

    }

    // Check if a valid format
    public boolean validOptions(String productOptions) {
        // check productOptions for "Paperback" or "Hardcover" or "EBook"
        // if it is one of these, return true, else return false

        return Objects.equals(productOptions, "Paperback") || Objects.equals(productOptions, "Hardcover")
                || Objects.equals(productOptions, "EBook");

    }

    // Override getStockCount() in super class.
    @Override
    public int getStockCount(String productOptions) {
        // Use the productOptions to check for (and return) the number of stock for
        // "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", use the inherited stockCount variable.
        if (Objects.equals(productOptions, "Paperback")) { // if paper back sets paper back stock
            return this.paperbackStock;

        } else if (Objects.equals(productOptions, "Hardcover")) { // if hard cover sets hard cover stock
            return this.hardcoverStock;
        } else {
            return this.getStockCount(productOptions);
        }
    }

    public void setStockCount(int stockCount, String productOptions) {
        // Use the productOptions to check for (and set) the number of stock for
        // "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", set the inherited stockCount variable.
        if (Objects.equals(productOptions, "Paperback")) { // sets paper back stock
            this.paperbackStock = stockCount;

        } else if (Objects.equals(productOptions, "Hardcover")) { // sets hard cover stock
            this.hardcoverStock = stockCount;
        } else {
            this.setStockCount(stockCount, productOptions);
        }
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    /*
     * When a book is ordered, reduce the stock count for the specific stock type
     */
    public void reduceStockCount(String productOptions) {
        // Use the productOptions to check for (and reduce) the number of stock for
        // "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", set the inherited stockCount variable.
        if (Objects.equals(productOptions, "Paperback")) { // if product is paper back reduces stock by one
            this.paperbackStock = this.paperbackStock - 1;

        } else if (Objects.equals(productOptions, "Hardcover")) { // if hardcover reduces stock by one
            this.hardcoverStock = this.hardcoverStock - 1;
        } else {
            this.reduceStockCount(productOptions);
        }
    }

    /*
     * Print product information in super class and append Book specific information
     * title and author
     */
    public void print() {
        // Replace the line below.
        // Make use of the super class print() method and append the title and author
        // info. See the video
        super.print();
        System.out.printf(" Book Title: %-20s Author: %-50s", this.title, this.author);

    }
}