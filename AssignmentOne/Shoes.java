//Haris Sujethan 501088408

import java.util.Objects;

/* A Shoe IS A product that has additional information - e.g. size, color


      The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Shoes extends Product {
    // shoes related information
    int size = 0;
    String color = null;
    int blackStock = 0;
    int brownStock = 0;

    public Shoes(String name, String id, double price, int size, String color) {
        super(name, id, price, 100000, Product.Category.SHOES);
        this.size = size;
        this.color = color;
    }

    // Check if a valid format
    public boolean validOptions(String productOptions) {
        // check shoes for color and size
        // if it is one of these, return true, else return false

        return Objects.equals(productOptions, "Black6") || Objects.equals(productOptions, "Brown6")
                || Objects.equals(productOptions, "Black7") || Objects.equals(productOptions, "Brown7")
                || Objects.equals(productOptions, "Black8") || Objects.equals(productOptions, "Brown8")
                || Objects.equals(productOptions, "Black9") || Objects.equals(productOptions, "Brown9")
                || Objects.equals(productOptions, "Black10") || Objects.equals(productOptions, "Brown10"); // all
                                                                                                           // possible
                                                                                                           // options

    }

    // Override getStockCount() in super class.
    @Override
    public int getStockCount(String productOptions) {
        // returns stock count
        if (Objects.equals(productOptions.substring(0, 5), "Black")) {
            return this.blackStock;

        } else if (Objects.equals(productOptions.substring(0, 5), "Brown")) {
            return this.brownStock;
        }
        return 0;
    }

    public void setStockCount(int stockCount, String productOptions) {
        // sets stock count
        if (Objects.equals(productOptions.substring(0, 5), "Black")) {
            this.blackStock = stockCount;

        } else if (Objects.equals(productOptions.substring(0, 5), "Brown")) {
            this.brownStock = stockCount;
        } else {
            this.setStockCount(stockCount, productOptions);
        }
    }

    /*
     * When a shoe is ordered, reduce the stock count for the specific shoe type
     */
    public void reduceStockCount(String productOptions) {
        // reduces shoe stock by one
        if (Objects.equals(productOptions.substring(0, 5), "Brown")) {
            this.brownStock = this.brownStock - 1;

        } else if (Objects.equals(productOptions.substring(0, 5), "Black")) {
            this.blackStock = this.blackStock - 1;
        } else {
            this.reduceStockCount(productOptions);
        }
    }

    /*
     * Print product information in super class and append shoe specific information
     * color and size
     */
    public void print() {

        super.print();
        System.out.printf(" Size: %-20s Color: %-50s", this.size, this.color);

    }
}