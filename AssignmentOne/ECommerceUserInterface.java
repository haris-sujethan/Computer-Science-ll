//Haris Sujethan 501088408

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface {
    public static void main(String[] args) {
        // Create the system
        ECommerceSystem amazon = new ECommerceSystem();

        Scanner scanner = new Scanner(System.in);
        System.out.print(">");

        // Process keyboard actions
        while (scanner.hasNextLine()) {
            String action = scanner.nextLine();

            if (action == null || action.equals("")) {
                System.out.print("\n>");
                continue;
            } else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
                return;

            else if (action.equalsIgnoreCase("PRODS")) // List all products for sale
            {
                amazon.printAllProducts();
            } else if (action.equalsIgnoreCase("BOOKS")) // List all books for sale
            {
                amazon.printAllBooks();
            } else if (action.equalsIgnoreCase("CUSTS")) // List all registered customers
            {
                amazon.printCustomers();
            } else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
            {
                amazon.printAllOrders();
            } else if (action.equalsIgnoreCase("SHIPPED")) // List all orders that have been shipped
            {
                amazon.printAllShippedOrders();
            } else if (action.equalsIgnoreCase("NEWCUST")) // Create a new registered customer
            {
                String name = "";
                String address = "";

                System.out.print("Name: ");
                if (scanner.hasNextLine())
                    name = scanner.nextLine();

                System.out.print("\nAddress: ");
                if (scanner.hasNextLine())
                    address = scanner.nextLine();

                boolean success = amazon.createCustomer(name, address);
                if (!success) {
                    System.out.println(amazon.getErrorMessage());
                }
            } else if (action.equalsIgnoreCase("SHIP")) // ship an order to a customer
            {
                String orderNumber = "";

                System.out.print("Order Number: ");

                // Get order number from scanner
                if (scanner.hasNextLine())
                    orderNumber = scanner.nextLine();

                // Ship order to customer (see ECommerceSystem for the correct method to use
                ProductOrder success = amazon.shipOrder(orderNumber);
                if (success == null) {
                    System.out.println(amazon.getErrorMessage());
                }

            } else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this
                                                              // customer id
            {
                String customerId = "";

                System.out.print("Customer Id: ");
                if (scanner.hasNextLine())
                    customerId = scanner.nextLine();
                // Get customer Id from scanner

                // Print all current orders and all shipped orders for this customer
                amazon.printAllOrders();
                amazon.printOrderHistory(customerId);

            } else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
            {
                String productId = "";
                String customerId = "";

                System.out.print("Product Id: ");
                // Get product Id from scanner
                if (scanner.hasNextLine())
                    productId = scanner.nextLine();

                System.out.print("\nCustomer Id: ");
                if (scanner.hasNextLine())
                    customerId = scanner.nextLine();
                // Get customer Id from scanner

                // Order the product. Check for valid orderNumber string return and for error
                // message set in ECommerceSystem

                String success = amazon.orderProduct(productId, customerId, "");
                if (success == null) {
                    System.out.println(amazon.getErrorMessage());
                }
                // Print Order Number string returned from method in ECommerceSystem
                System.out.println("Order Number:" + success);

            } else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format
                                                             // (Paperback, Hardcover or EBook)
            {
                String productId = "";
                String customerId = "";
                String options = "";

                System.out.print("Product Id: ");
                // get product id
                if (scanner.hasNextLine())
                    productId = scanner.nextLine();

                System.out.print("\nCustomer Id: ");
                // get customer id
                if (scanner.hasNextLine())
                    customerId = scanner.nextLine();

                System.out.print("\nFormat [Paperback Hardcover EBook]: ");
                // get book forma and store in options string
                if (scanner.hasNextLine())
                    options = scanner.nextLine();

                String success = amazon.orderProduct(productId, customerId, options);
                if (success == null) {
                    System.out.println(amazon.getErrorMessage());
                }

                System.out.println("Order Number:" + success);

                // Order product. Check for error mesage set in ECommerceSystem
                // Print order number string if order number is not null
            } else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color
            {
                String productId = "";
                String customerId = "";
                String options = "";
                String options2 = "";

                System.out.print("Product Id: ");
                // get product id
                if (scanner.hasNextLine())
                    productId = scanner.nextLine();

                System.out.print("\nCustomer Id: ");
                // get customer id
                if (scanner.hasNextLine())
                    customerId = scanner.nextLine();

                System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
                // get shoe size and store in options
                if (scanner.hasNextLine())
                    options = scanner.nextLine();

                System.out.print("\nColor: \"Black\" \"Brown\": ");
                // get shoe color and append to options
                if (scanner.hasNextLine())
                    options2 = scanner.nextLine();

                options = options2 + options;

                // order shoes
                amazon.orderProduct(productId, customerId, options);
                String success = amazon.orderProduct(productId, customerId, options);
                if (success == null) {
                    System.out.println(amazon.getErrorMessage());
                }

                System.out.println("Order Number:" + success);
            }

            else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
            {
                String orderNumber = "";

                System.out.print("Order Number: ");
                // get order number from scanner
                if (scanner.hasNextLine())
                    orderNumber = scanner.nextLine();

                // cancel order. Check for error
                boolean success = amazon.cancelOrder(orderNumber);
                if (!success) {
                    System.out.println(amazon.getErrorMessage());
                }

                System.out.println("Order Number:" + success);
            } else if (action.equalsIgnoreCase("SORTBYPRICE")) // sort products by price
            {
                amazon.sortByPrice();
            } else if (action.equalsIgnoreCase("SORTBYNAME")) // sort products by name (alphabetic)
            {
                amazon.sortByName();
            } else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
            {
                amazon.sortCustomersByName();
            } else if (action.equalsIgnoreCase("BooksByAuthor")) {
                String author = "";
                System.out.print("Author's Name: ");
                // get order number from scanner
                if (scanner.hasNextLine())
                    author = scanner.nextLine();
                amazon.booksByAuthor(author);
            }
            System.out.print("\n>");
        }
    }
}
