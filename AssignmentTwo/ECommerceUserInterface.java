
//Haris Sujethan 501088408
import java.util.ArrayList;
import java.util.Collections;
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
			} else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) // ship an order to a customer
			{
				String author = "";

				System.out.print("Author: ");
				if (scanner.hasNextLine())
					author = scanner.nextLine();

				ArrayList<Book> books = amazon.booksByAuthor(author);
				Collections.sort(books);
				for (Book book : books)
					book.print();
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
				boolean error = false;
				do {

					String name = "";
					String address = "";

					System.out.print("Name: ");

					if (scanner.hasNextLine())
						name = scanner.nextLine();

					System.out.print("\nAddress: ");
					if (scanner.hasNextLine())
						address = scanner.nextLine();
					try {
						amazon.createCustomer(name, address);
						error = false;
					} catch (InvalidCustomerNameException e) {
						System.out.println(e.getMessage());
						error = true;

					} catch (InvalidCustomerAddressException e) {
						System.out.println(e.getMessage());
						error = true;

					}
				} while (error == true);

			} else if (action.equalsIgnoreCase("SHIP")) // ship an order to a customer
			{
				Boolean error = false;
				do {

					String orderNumber = "";

					System.out.print("Order Number: ");
					if (scanner.hasNextLine())
						orderNumber = scanner.nextLine();

					try {
						ProductOrder order = amazon.shipOrder(orderNumber);
						if (order != null) {
							order.print();
							error = false;
						}
					} catch (InvalidOrderNumberException e) {
						System.out.println(e.getMessage());
						error = true;
					}

				} while (error == true);

			} else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this
																// customer
			{
				Boolean error = false;
				do {

					String customerId = "";

					System.out.print("Customer Id: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					// Prints all current orders and all shipped orders for this customer
					try {
						amazon.printOrderHistory(customerId);
						error = false;
					} catch (UnknownCustomerException e) {
						System.out.println(e.getMessage());
						error = true;
					}
				} while (error == true);

			} else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				boolean error = false;
				do {

					String productId = "";
					String customerId = "";

					System.out.print("Product Id: ");
					if (scanner.hasNextLine())
						productId = scanner.nextLine();

					System.out.print("\nCustomer Id: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					try {

						String orderNumber = amazon.orderProduct(productId, customerId, "");

						if (orderNumber != null) {
							System.out.println("Order #" + orderNumber);
							error = false;
						}

					} catch (UnknownCustomerException e) {
						System.out.println(e.getMessage());
						error = true;
					} catch (UnknownProductException e) {
						System.out.println(e.getMessage());
						error = true;
					} catch (InvalidProductOptionException e) {
						System.out.println(e.getMessage());
						error = true;
					} catch (InvalidStockCountException e) {
						System.out.println(e.getMessage());
						error = true;
					}
				} while (error == true);
			}

			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format
															// (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String format = "";

				System.out.print("Product Id: ");
				if (scanner.hasNextLine())
					productId = scanner.nextLine();

				System.out.print("\nCustomer Id: ");
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();

				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				if (scanner.hasNextLine())
					format = scanner.nextLine();

				String orderNumber = amazon.orderProduct(productId, customerId, format);
				if (orderNumber != null) {
					System.out.println("Order #" + orderNumber);
				} else {
					System.out.println(amazon.getErrorMessage());
				}
			} else if (action.equalsIgnoreCase("ORDERSHOES")) // order a book for a customer, provide a format
																// (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String sizeColor = "";

				System.out.print("Product Id: ");
				if (scanner.hasNextLine())
					productId = scanner.nextLine();

				System.out.print("\nCustomer Id: ");
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();

				System.out.print("\nSize (6, 7, 8, 9, 10) and Color (Black or Brown): ");
				if (scanner.hasNextLine())
					sizeColor = scanner.nextLine();

				String orderNumber = amazon.orderProduct(productId, customerId, sizeColor);
				if (orderNumber != null) {
					System.out.println("Order #" + orderNumber);
				} else {
					System.out.println(amazon.getErrorMessage());
				}
			} else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				Boolean error = false;
				do {

					String orderNumber = "";

					System.out.print("Order Number: ");
					if (scanner.hasNextLine())
						orderNumber = scanner.nextLine();
					try {
						amazon.cancelOrder(orderNumber);
						error = false;
					} catch (InvalidOrderNumberException e) {
						System.out.println(e.getMessage());
						error = true;
					}
				} while (error == true);

			}
			// -------------------------------------------------------------------------------------------------------
			// -------------------------------------------------------------------------------------------------------
			// -------------------------------------------------------------------------------------------------------
			// -------------------------------------------------------------------------------------------------------

			else if (action.equalsIgnoreCase("ADDTOCART")) // **HK CODE**
			{
				Boolean error = false;
				do {

					String productId = "";
					String customerId = "";

					System.out.print("Product Id: ");
					if (scanner.hasNextLine())
						productId = scanner.nextLine();

					System.out.print("\nCustomer Id: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					try {
						amazon.AddToCart(productId, customerId, "");

					} catch (UnknownCustomerException e) {
						System.out.println(e.getMessage());
						error = true;
					} catch (UnknownProductException e) {
						System.out.println(e.getMessage());
						error = true;
					} catch (InvalidProductOptionException e) {
						System.out.println(e.getMessage());
						error = true;
					} catch (InvalidStockCountException e) {
						System.out.println(e.getMessage());
						error = true;
					}
				} while (error == true);

			}

			else if (action.equalsIgnoreCase("PRINTCART")) {
				Boolean error = false;
				do {

					String customerId = "";

					System.out.print("Customer Id: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					// Prints all current orders and all shipped orders for this customer
					try {
						amazon.PrintCart(customerId);
						error = false;
					} catch (UnknownCustomerException e) {
						System.out.println(e.getMessage());
						error = true;
					}
				} while (error == true);

			}

			else if (action.equalsIgnoreCase("REMCARTITEM")) {
				Boolean error = false;

				do {

					String customerId = "";
					String productId = "";

					System.out.print("customerId: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					System.out.print("productId: ");
					if (scanner.hasNextLine())
						productId = scanner.nextLine();

					try {
						amazon.RemoveCartItem(customerId, productId);
						error = false;
					} catch (InvalidOrderNumberException e) {
						System.out.println(e.getMessage());
						error = true;
					} catch (UnknownProductException e) {
						System.out.println(e.getMessage());
						error = true;
					}
				} while (error == true);

			} else if (action.equalsIgnoreCase("ORDERITEMS")) {
				Boolean error = false;
				do {
					String productId = "";
					String customerId = "";

					System.out.print("customerId: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					System.out.print("productId: ");
					if (scanner.hasNextLine())
						productId = scanner.nextLine();

					try {
						amazon.OrderedCartItems(customerId, productId, " ");

					} catch (UnknownProductException e) {
						System.out.println(e.getMessage());
						error = true;
					} catch (InvalidProductOptionException e) {
						System.out.println(e.getMessage());
						error = true;
					} catch (UnknownCustomerException e) {
						System.out.println(e.getMessage());
						error = true;
					}
				} while (error == true);

			} else if (action.equalsIgnoreCase("STATS")) {

				amazon.ProductOrderStatisitcs("");
			}

			// -------------------------------------------------------------------------------------------------------
			// -------------------------------------------------------------------------------------------------------
			// -------------------------------------------------------------------------------------------------------
			// -------------------------------------------------------------------------------------------------------

			else if (action.equalsIgnoreCase("SORTBYPRICE")) // sort products by price
			{
				amazon.sortByPrice();
			} else if (action.equalsIgnoreCase("SORTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.sortByName();
			} else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			System.out.print("\n>");
		}
	}
}
