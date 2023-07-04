//Haris Sujethan 501088408

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem {
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    private ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();

    // These variables are used to generate order numbers, customer id's, product
    // id's
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;

    // General variable used to store an error message when something is invalid
    // (e.g. customer id does not exist)
    String errMsg = null;

    // Random number generator
    Random random = new Random();

    public ECommerceSystem() {
        // NOTE: do not modify or add to these objects!! - the TAs will use for testing
        // If you do the class Shoes bonus, you may add shoe products

        // Create some products. Notice how generateProductId() method is used
        products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
        products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
        products.add(
                new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney", 2005));
        products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
        products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
        products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
        products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast", 2006));
        products.add(
                new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive", 2007));
        products.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Ahm Gonna Make You Learn More",
                "T. McInerney", 2008));
        products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));

        // Create some customers. Notice how generateCustomerId() method is used
        customers.add(new Customer(generateCustomerId(), "Inigo Montoya", "1 SwordMaker Lane, Florin"));
        customers.add(new Customer(generateCustomerId(), "Prince Humperdinck", "The Castle, Florin"));
        customers.add(new Customer(generateCustomerId(), "Andy Dufresne", "Shawshank Prison, Maine"));
        customers.add(new Customer(generateCustomerId(), "Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }

    private String generateOrderNumber() {
        return "" + orderNumber++;
    }

    private String generateCustomerId() {
        return "" + customerId++;
    }

    private String generateProductId() {
        return "" + productId++;
    }

    public String getErrorMessage() {
        return errMsg;
    }

    public void printAllProducts() {
        for (Product p : products)
            p.print();
    }

    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks() {
        for (Product p : products) {
            if (p.getCategory() == Product.Category.BOOKS) {
                p.print();
            }
        }
    }

    // Print all current orders
    public void printAllOrders() {
        for (int i = 0; i < orders.size(); i++) {
            ProductOrder c = orders.get(i);
            c.print();
        }

    }

    // Print all shipped orders
    public void printAllShippedOrders() {
        for (int i = 0; i < shippedOrders.size(); i++) {
            ProductOrder c = shippedOrders.get(i);
            c.print();
        }

    }

    // Print all customers
    public void printCustomers() {
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            c.print();
        }

    }

    public void booksByAuthor(String author) {
        Book product = null;
        ArrayList<Book> authorList = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equals(Product.Category.BOOKS)) {
                product = (Book) p;
                if (product.getAuthor().equals(author)) {
                    authorList.add(product);
                }
            }
        }
        boolean sorted = false;
        Product temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < authorList.size() - 1; i++) {
                if (authorList.get(i).getYear() > (authorList.get(i + 1).getYear())) {
                    temp = authorList.get(i);
                    authorList.set(i, authorList.get(i + 1));
                    authorList.set(i + 1, (Book) temp);
                    sorted = false;
                }
            }
        }

        for (int i = 0; i < authorList.size(); i++) {
            System.out.println(authorList.get(i).getTitle());
        }

    }

    /*
     * Given a customer id, print all the current orders and shipped orders for them
     * (if any)
     */
    public boolean printOrderHistory(String customerId) {
        // Make sure customer exists - check using customerId
        // If customer does not exist, set errMsg String and return false
        // see video for an appropriate error message string
        // ... code here

        Customer IdMatch = null;
        for (Customer c : customers) {
            if (c.getId().equals(customerId)) {
                IdMatch = c;
            }
        }

        if (IdMatch == null) {
            errMsg = "Customer" + customerId + "Not Found";
            return false;
        }

        // Print current orders of this customer
        System.out.println("Current Orders of Customer " + customerId);
        // enter code here
        for (ProductOrder po : orders) {
            if (po.getCustomer().getId().equals(IdMatch.getId())) {
                po.print();
            }
        }

        // Print shipped orders of this customer
        System.out.println("\nShipped Orders of Customer " + customerId);
        // enter code here

        for (ProductOrder po : shippedOrders) {
            if (po.getCustomer().getId().equals(IdMatch.getId())) {
                po.print();
            }
        }

        return true;
    }

    public String orderProduct(String productId, String customerId, String productOptions) {
        // First check to see if customer object with customerId exists in array list
        // customers
        // if it does not, set errMsg and return null (see video for appropriate error
        // message string)
        // else get the Customer object

        // Check to see if product object with productId exists in array list of
        // products
        // if it does not, set errMsg and return null (see video for appropriate error
        // message string)
        // else get the Product object

        // Check if the options are valid for this product (e.g. Paperback or Hardcover
        // or EBook for Book product)
        // See class Product and class Book for the method vaidOptions()
        // If options are not valid, set errMsg string and return null;

        // Check if the product has stock available (i.e. not 0)
        // See class Product and class Book for the method getStockCount()
        // If no stock available, set errMsg string and return null

        // Create a ProductOrder, (make use of generateOrderNumber() method above)
        // reduce stock count of product by 1 (see class Product and class Book)
        // Add to orders list and return order number string

        Customer customer = null; // if the id dose not exist null will stay the same
        for (Customer c : customers) {
            if (c.getId().equals(customerId)) { // checks if customer id exists
                customer = c; // changes null value
            }
        }

        if (customer == null) {
            errMsg = "Customer" + customerId + "Not Found"; // if not found runs this error and returns null
            return null;
        }

        Product product = null;
        for (Product p : products) {
            if (p.getId().equals(productId)) {// checks if product id exists
                product = p; // changes null value
            }
        }

        if (product == null) { // runs if the product id dose not exist
            errMsg = "Customer" + productId + "Not Found";
            return null;
        }

        if (!product.validOptions(productOptions)) { // checks if the options are valid
            errMsg = "Product" + product.getCategory() + " Product id" + productId + "Invalid Options: "
                    + productOptions; // if they are not valid the error message runs
            return null;
        }

        if (product.getStockCount(productOptions) <= 0) { // checks if stock exists
            errMsg = "Product" + productId + "0 in stock"; // if no stock is left runs error message
            return null;
        }

        String orderNumber = generateOrderNumber();// creates order number
        ProductOrder po = new ProductOrder(orderNumber, product, customer, productOptions);
        product.reduceStockCount(productOptions); // removes one to stock count
        orders.add(po); // adds order
        return po.getOrderNumber(); // returns order number
    }

    /*
     * Create a new Customer object and add it to the list of customers
     */

    public boolean createCustomer(String name, String address) {
        // Check name parameter to make sure it is not null or ""
        // If it is not a valid name, set errMsg (see video) and return false
        // Repeat this check for address parameter

        if (name == null || name.equals("")) { // if name is not valid
            errMsg = "Name is not valid";
            return false;
        }

        if (address == null || address.equals("")) { // if address is not valid
            errMsg = "Address is not valid";
            return false;
        }

        // Create a Customer object and add to array list
        String cID = generateCustomerId(); // makes a new customer id
        Customer c = new Customer(cID, name, address); // creates customer
        customers.add(c); // adds customer
        return true;
    }

    public ProductOrder shipOrder(String orderNumber) {
        // Check if order number exists first. If it doesn't, set errMsg to a message
        // (see video)
        // and return false
        // Retrieve the order from the orders array list, remove it, then add it to the
        // shippedOrders array list
        // return a reference to the order
        boolean check = false;
        ProductOrder currPO;
        for (ProductOrder po : orders) {
            if (po.getOrderNumber().equals(orderNumber)) { // checks if the order number exists
                check = true;
                currPO = po;
            }
        }
        if (!check) {
            errMsg = "Order Number does not exist"; // if order number dose not exist runs error
            return null;
        } else {
            for (ProductOrder o : orders) {
                if (o.getOrderNumber().equals(orderNumber)) {
                    currPO = o;
                    shippedOrders.add(currPO); // ships order
                    orders.remove(currPO); // removes it froms orders beacuse it is shipped now
                    return currPO;
                }
            }
            return null;
        }

    }

    /*
     * Cancel a specific order based on order number
     */
    public boolean cancelOrder(String orderNumber) {
        // Check if order number exists first. If it doesn't, set errMsg to a message
        // (see video)
        // and return false
        boolean check = false;
        ProductOrder currPO;
        for (ProductOrder po : orders) {
            if (po.getOrderNumber().equals(orderNumber)) { // checks if order number exists
                check = true;
                break;
            }
        }
        if (!check) {
            errMsg = "Order Number does not exist"; // if order number dose not exit error message
            return false;
        } else {
            for (ProductOrder o : orders) {
                if (o.getOrderNumber().equals(orderNumber)) {
                    currPO = o;
                    orders.remove(currPO);// removes order
                    return true;
                }
            }
        }
        return false;

    }

    // Sort products by increasing price
    public void sortByPrice() {
        boolean sorted = false;
        Product temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < products.size() - 1; i++) {
                if (products.get(i).getPrice() > products.get(i + 1).getPrice()) {
                    temp = products.get(i);
                    products.set(i, products.get(i + 1));
                    products.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }

    // Sort products alphabetically by product name
    public void sortByName() {
        boolean sorted = false;
        Product temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < products.size() - 1; i++) {
                if (products.get(i).getName().compareTo(products.get(i + 1).getName()) > 0) {
                    temp = products.get(i);
                    products.set(i, products.get(i + 1));
                    products.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }

    // Sort products alphabetically by product name
    public void sortCustomersByName() {
        boolean sorted = false;
        Customer temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < customers.size() - 1; i++) {
                if (customers.get(i).isDiffrentThan(customers.get(i + 1))) {
                    temp = customers.get(i);
                    customers.set(i, customers.get(i + 1));
                    customers.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }
}
