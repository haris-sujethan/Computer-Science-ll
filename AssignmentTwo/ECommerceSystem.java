//Haris Sujethan 501088408

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.List;
import java.util.Collections;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem {
	ArrayList<Product> products = new ArrayList<Product>(); // The File I/O part of the code is still buggy, leaving
															// here so the code is still able to run
	HashMap<Product, Product> ProductMap = new HashMap<Product, Product>(); // from products.txt to here
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Product> OrderedItems = new ArrayList<Product>();

	ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
	ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();
	ArrayList<ProductOrder> cart = new ArrayList<ProductOrder>(); // Ignore this, was used for testing
	ArrayList<ProductOrder> OrderedCartItems = new ArrayList<ProductOrder>(); // Holds ordered items
	HashMap<String, Product> StatsMap = new HashMap<String, Product>(); // Holds ordered items for stats
	ArrayList<String> StatsCounter = new ArrayList<String>(); // Ignore this, was used for testing

	// These variables are used to generate order numbers, customer id's, product
	// id's
	int orderNumber = 500;
	int customerId = 900;
	int productId = 700;

	// General variable used to store an error message when something is invalid
	// (e.g. customer id does not exist)
	String errMsg = null;

	// Random number generator
	Random random = new Random();

	public ECommerceSystem() {
		// NOTE: do not modify or add to these objects!! - the TAs will use for testing
		// If you do the class Shoes bonus, you may add shoe products

		// Create some products
		products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
		products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
		products.add(
				new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney", 2021));
		products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
		products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
		products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
		products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast", 1997));
		products.add(
				new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive", 1963));
		products.add(
				new Book("Book", generateProductId(), 45.0, 4, 2, "How to Teach Programming", "T. McInerney", 2001));
		products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));
		products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn More", "T. McInerney",
				2022));
		int[][] stockCounts = { { 4, 2 }, { 3, 5 }, { 1, 4, }, { 2, 3 }, { 4, 2 } };
		products.add(new Shoes("Prada", generateProductId(), 595.0, stockCounts));

		// Create some customers
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

	public void printAllBooks() {
		for (Product p : products) {
			if (p.getCategory() == Product.Category.BOOKS)
				p.print();
		}
	}

	public ArrayList<Book> booksByAuthor(String author) {
		ArrayList<Book> books = new ArrayList<Book>();
		for (Product p : products) {
			if (p.getCategory() == Product.Category.BOOKS) {
				Book book = (Book) p;
				if (book.getAuthor().equals(author))
					books.add(book);
			}
		}
		return books;
	}

	public void printAllOrders() {
		for (ProductOrder o : orders)
			o.print();
	}

	public void printAllShippedOrders() {
		for (ProductOrder o : shippedOrders)
			o.print();
	}

	public void printCustomers() {
		for (Customer c : customers)
			c.print();
	}

	/*
	 * Given a customer id, print all the current orders and shipped orders for them
	 * (if any)
	 */
	public void printOrderHistory(String customerId) throws UnknownCustomerException {
		// Make sure customer exists
		int index = customers.indexOf(new Customer(customerId));
		if (index == -1) {
			throw new UnknownCustomerException();
		}

		System.out.println("Current Orders of Customer " + customerId);
		for (ProductOrder order : orders) {
			if (order.getCustomer().getId().equals(customerId))
				order.print();
		}
		System.out.println("\nShipped Orders of Customer " + customerId);
		for (ProductOrder order : shippedOrders) {
			if (order.getCustomer().getId().equals(customerId))
				order.print();
		}

	}

	public String orderProduct(String productId, String customerId, String productOptions)
			throws UnknownCustomerException, UnknownProductException, InvalidProductOptionException,
			InvalidStockCountException {
		// Get customer
		int index = customers.indexOf(new Customer(customerId));
		if (index == -1) {
			throw new UnknownCustomerException();
		}

		Customer customer = customers.get(index);

		// Get product
		index = products.indexOf(new Product(productId));

		if (index == -1) {
			throw new UnknownProductException();
		}

		Product product = products.get(index);

		// Check if the options are valid for this product (e.g. Paperback or Hardcover
		// or EBook for Book product)

		if (!product.validOptions(productOptions)) {
			throw new InvalidProductOptionException();
		}

		// Is it in stock?

		if (product.getStockCount(productOptions) == 0) {
			throw new InvalidStockCountException();
		}

		// Create a ProductOrder
		ProductOrder order = new ProductOrder(generateOrderNumber(), product, customer, productOptions);
		product.reduceStockCount(productOptions);

		// Add to orders and return
		orders.add(order);

		return order.getOrderNumber();
	}

	/*
	 * Create a new Customer object and add it to the list of customers
	 */

	public void createCustomer(String name, String address)
			throws InvalidCustomerNameException, InvalidCustomerAddressException {
		// Check to ensure name is valid

		if (name == null || name.equals("")) {
			throw new InvalidCustomerNameException();
		}

		// Check to ensure address is valid

		if (address == null || address.equals("")) {
			throw new InvalidCustomerAddressException();
		}

		Customer customer = new Customer(generateCustomerId(), name, address);
		customers.add(customer);

	}

	public ProductOrder shipOrder(String orderNumber) throws InvalidOrderNumberException {
		// Check if order number exists
		int index = orders.indexOf(new ProductOrder(orderNumber, null, null, ""));

		if (index == -1) {
			throw new InvalidOrderNumberException();
		}

		ProductOrder order = orders.get(index);
		orders.remove(index);
		shippedOrders.add(order);
		return order;
	}

	/*
	 * Cancel a specific order based on order number
	 */
	public void cancelOrder(String orderNumber) throws InvalidOrderNumberException {
		// Check if order number exists
		int index = orders.indexOf(new ProductOrder(orderNumber, null, null, ""));

		if (index == -1) {
			throw new InvalidOrderNumberException();
		}

		ProductOrder order = orders.get(index);
		orders.remove(index);
	}

	// Sort products by increasing price
	public void sortByPrice() {
		Collections.sort(products, new PriceComparator());
	}

	private class PriceComparator implements Comparator<Product> {
		public int compare(Product a, Product b) {
			if (a.getPrice() > b.getPrice())
				return 1;
			if (a.getPrice() < b.getPrice())
				return -1;
			return 0;
		}
	}

	// Sort products alphabetically by product name
	public void sortByName() {
		Collections.sort(products, new NameComparator());
	}

	private class NameComparator implements Comparator<Product> {
		public int compare(Product a, Product b) {
			return a.getName().compareTo(b.getName());
		}
	}

	// Sort products alphabetically by product name
	public void sortCustomersByName() {
		Collections.sort(customers);
	}

	// ------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------

	public void AddToCart(String productId, String customerId, String productOptions)
			throws UnknownCustomerException, UnknownProductException, InvalidProductOptionException,
			InvalidStockCountException {
		// Get customer
		int Count = 0;
		int index = customers.indexOf(new Customer(customerId));

		if (index == -1) {
			throw new UnknownCustomerException();
		}

		Customer customer = customers.get(index);

		// Get product
		index = products.indexOf(new Product(productId));

		if (index == -1) {
			throw new UnknownProductException();
		}

		Product product = products.get(index);

		// Check if the options are valid for this product (e.g. Paperback or Hardcover
		// or EBook for Book product)

		if (!product.validOptions(productOptions)) {
			throw new InvalidProductOptionException();
		}

		// Is it in stock?

		if (product.getStockCount(productOptions) == 0) {
			throw new InvalidStockCountException();
		}

		// Create a ProductOrder
		Cartitem order = new Cartitem(product, productOptions);
		product.reduceStockCount(productOptions);
		product.ProdsCount(productOptions);

		// if everything passses adds order to cart
		// Add to orders and return
		Cart myCart = customer.getCart();
		myCart.addProduct(order);

	}

	public void PrintCart(String customerId) throws UnknownCustomerException {
		int index = customers.indexOf(new Customer(customerId));

		// prints everything that is currently in the customers cart

		if (index == -1) {
			throw new UnknownCustomerException();
		}

		System.out.println("Current Orders of Customer " + customerId);

		Customer customer = customers.get(index);
		Cart myCart = customer.getCart();

		if (customer.getId().equals(customerId)) {
			for (Cartitem c : myCart.getCartitems()) {
				Product p = c.getProduct();
				System.out.println(p.getName());
			}
		}

	}

	public void RemoveCartItem(String customerId, String productId)
			throws UnknownCustomerException, UnknownProductException {
		// Check if order number exists
		int index = customers.indexOf(new Customer(customerId));

		if (index == -1) {
			throw new UnknownCustomerException();
		}

		index = products.indexOf(new Product(productId));

		Customer customer = customers.get(index);

		// Get product
		index = products.indexOf(new Product(productId));

		if (index == -1) {
			throw new UnknownProductException();
		}

		Product product = products.get(index);

		// Check if the options are valid for this product (e.g. Paperback or Hardcover
		// or EBook for Book product)

		// Is it in stock?

		// Create a ProductOrder
		Cartitem order = new Cartitem(product, customerId);

		// Add to orders and return

		// if everything passes removes item from cart
		Cart myCart = customer.getCart();
		myCart.remProduct(order);

	}

	public ProductOrder OrderedCartItems(String customerId, String productId, String productOptions)
			throws UnknownCustomerException, UnknownProductException {

		// Check if order number exists
		int index = customers.indexOf(new Customer(customerId));

		if (index == -1) {
			throw new UnknownCustomerException();
		}

		index = products.indexOf(new Product(productId));

		Customer customer = customers.get(index);

		// Get product
		index = products.indexOf(new Product(productId));

		if (index == -1) {
			throw new UnknownProductException();
		}

		Product product = products.get(index);

		// Check if the options are valid for this product (e.g. Paperback or Hardcover
		// or EBook for Book product)

		// Is it in stock?

		// Create a ProductOrder
		Cartitem order = new Cartitem(product, productOptions);

		// Add to orders and return
		// if everything passes reomves everything from cart
		Cart myCart = customer.getCart();
		myCart.remProduct(order);
		// starts the counter for how many times a spefic prodcut was orderd
		product.getProdsCount(productOptions);

		//
		// add the stats counter here
		//
		if (StatsMap.containsValue(order.getProduct())) { // if the product is already in the map do nothing
			System.out.println("");
		} else if (!StatsMap.containsValue(order.getProduct())) {
			StatsMap.put(product.getName(), product); // if the product is not in the map, add it to it
		}

		if (index == -1) {
			throw new InvalidOrderNumberException();
		}

		ProductOrder OrderedItems = new ProductOrder(generateOrderNumber(), null, null, "");
		System.out.println("Product Order #" + OrderedItems.getOrderNumber());
		// add the order to orderedCartItems
		OrderedCartItems.add(OrderedItems);

		return OrderedItems;

	}

	//
	// This code seems to be in the correct path to the solution, but to many bugs
	// were encountred, so I commented it out.

	//

	// public static void FileIo(String[] args) {
	// ArrayList<String> list = new ArrayList<>();
	// String[] Arr = new String[4];
	// String[] ArrBook = new String[7];
	// int i = 0;
	// int j = 0;

	// File file = new File("products.txt");
	// try {
	// Scanner scan = new Scanner(file);
	// scan.useDelimiter(":");
	// while (scan.hasNext()) {
	// String CurrentField = scan.next();
	// Arr[i] = CurrentField;
	// i++;
	// if (i == 4) {
	// if (Arr[0].equals("COMPUTERS")) {

	// String name = Arr[1];
	// String id = generateProductId();
	// String PriceOfProductString = Arr[2];
	// Double price = Double.parseDouble(PriceOfProductString);
	// String StockAmountString = Arr[3];
	// int stock = Integer.parseInt(StockAmountString);
	// Product.Category category = Product.Category.COMPUTERS;
	// Product p = new Product(name, id, price, stock, category);
	// ProductMap.put(p);

	// // list.add(p);
	// } else if (Arr[0].equals("FURNITURE")) {

	// String name = Arr[1];
	// String id = generateProductId();
	// String PriceOfProductString = Arr[2];
	// Double price = Double.parseDouble(PriceOfProductString);
	// String StockAmountString = Arr[3];
	// int stock = Integer.parseInt(StockAmountString);
	// Product.Category category = Product.Category.FURNITURE;
	// Product p = new Product(name, id, price, stock, category);
	// ProductMap.put(p);

	// } else if (Arr[0].equals("SHOES")) {
	// Product.Category properCategory = Product.Category.FURNITURE;
	// String name = Arr[1];
	// String id = generateProductId();
	// String PriceOfProductString = Arr[2];
	// Double price = Double.parseDouble(PriceOfProductString);
	// String StockAmountString = Arr[3];
	// int stock = Integer.parseInt(StockAmountString);
	// Product.Category category = Product.Category.FURNITURE;
	// Product p = new Product(name, id, price, stock, category);
	// ProductMap.put(p);
	// } else if (Arr[0].equals("CLOTHING")) {

	// String name = Arr[1];
	// String id = generateProductId();
	// String PriceOfProductString = Arr[2];
	// Double price = Double.parseDouble(PriceOfProductString);
	// String StockAmountString = Arr[3];
	// int stock = Integer.parseInt(StockAmountString);
	// Product.Category category = Product.Category.CLOTHING;
	// Product p = new Product(name, id, price, stock, category);
	// ProductMap.put(p);
	// } else if (Arr[0].equals("BOOKS")) {

	// ArrBook[0] = Arr[0];
	// ArrBook[1] = Arr[1];
	// ArrBook[2] = Arr[2];
	// ArrBook[3] = Arr[3];
	// ArrBook[4] = Arr[4];
	// ArrBook[5] = scan.next();
	// ArrBook[6] = scan.next();

	// String name = Arr[1];

	// String id = generateProductId();

	// String PriceOfProductString = Arr[2];
	// Double price = Double.parseDouble(PriceOfProductString);

	// String myString = Arr[3];
	// String[] splitString = myString.split(" ");

	// int paperbackStock = Integer.parseInt((splitString[0]));
	// int hardcoverStock = Integer.parseInt((splitString[1]));

	// String title = ArrBook[4];

	// String author = ArrBook[5];

	// String StringYear = Arr[6];
	// int year = Integer.parseInt(StringYear);

	// // Splits up the string to have int paperback and int hardcover

	// Product.Category category = Product.Category.BOOKS;
	// Product p = new Product(name, id, price, paperbackStock, hardcoverStock,
	// title, author, year);
	// ProductMap.put(p);
	// }
	// i = 0;
	// Arr[0] = null;
	// Arr[1] = null;
	// Arr[2] = null;
	// Arr[4] = null;

	// }

	// }
	// } catch (Exception e) {
	// System.out.println(e);
	// }

	// }

	public void ProductOrderStatisitcs(String productOptions) {

		// prints everything in the map, with details. *Not in order*

		for (String i : StatsMap.keySet()) {
			System.out.println("Product Name: " + i + " Product Id: " + StatsMap.get(i).getId() + " Times Ordered "
					+ StatsMap.get(i).getProdsCount(productOptions));

		}

	}

}

class UnknownCustomerException extends RuntimeException {
	public UnknownCustomerException() {
		super("Invalid Customer: Customer Unknown");
	}

	public UnknownCustomerException(String message) {
		super(message);
	}
}

class UnknownProductException extends RuntimeException {
	public UnknownProductException() {
		super("Invalid Product: Product Not Found");
	}

	public UnknownProductException(String message) {
		super(message);
	}
}

class InvalidProductOptionException extends RuntimeException {
	public InvalidProductOptionException() {
		super("Invalid Product Option: Product Option Unknown");
	}

	public InvalidProductOptionException(String message) {
		super(message);
	}
}

class InvalidStockCountException extends RuntimeException {
	public InvalidStockCountException() {
		super("Invalid Product Stock: Product has no Stock");
	}

	public InvalidStockCountException(String message) {
		super(message);
	}
}

class InvalidCustomerNameException extends RuntimeException {
	public InvalidCustomerNameException() {
		super("Invalid Customer Name");
	}

	public InvalidCustomerNameException(String message) {
		super(message);
	}
}

class InvalidCustomerAddressException extends RuntimeException {
	public InvalidCustomerAddressException() {
		super("Invalid Customer Name");
	}

	public InvalidCustomerAddressException(String message) {
		super(message);
	}
}

class InvalidOrderNumberException extends RuntimeException {
	public InvalidOrderNumberException() {
		super("Invalid Order Number");
	}

	public InvalidOrderNumberException(String message) {
		super(message);
	}
}