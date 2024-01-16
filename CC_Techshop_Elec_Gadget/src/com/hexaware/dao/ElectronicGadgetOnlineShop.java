package com.hexaware.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hexaware.entity.CartItem;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Order;
import com.hexaware.entity.OrderDetails;
import com.hexaware.entity.Product;
//import com.hexaware.entity.User;
import com.hexaware.exception.AuthenticationException;
import com.hexaware.exception.AuthorizationException;
//import com.hexaware.exception.IncompleteOrderException;
import com.hexaware.exception.InsufficientStockException;
//import com.hexaware.exception.InvalidDataException;
import com.hexaware.exception.PaymentFailedException;
import com.hexaware.util.DBConnUtil;

public class ElectronicGadgetOnlineShop implements ElectronicGadgetOperations {

	private Scanner scanner;
	public Customer loggedInCustomer;
	private Connection connection;
//	private List<CartItem> cartItems;

	public ElectronicGadgetOnlineShop() {
		this.connection = DBConnUtil.getConnection();
		this.scanner = new Scanner(System.in);
	}

	public boolean isUserLoggedIn() {
		return loggedInCustomer != null;
	}

	public void login() {
		System.out.print("Enter email: ");
		String email = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		try {
			String query = "SELECT * FROM Customers WHERE Email = ? AND Password = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						Customer customer = new Customer();
						customer.setEmail(resultSet.getString("Email"));
						customer.setPassword(resultSet.getString("Password"));
						customer.setCustomerID(resultSet.getInt("customerid"));
						customer.setFirstName(resultSet.getString("firstName"));
						customer.setLastName(resultSet.getString("lastName"));
						loggedInCustomer = customer;
						System.out.println("Login successful!");
					} else {
						System.out.println("Incorrect email or password. Login failed.");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle SQL exception
		}
	}

	public void register() {
		try {
			System.out.print("Enter first name: ");
			String firstname = scanner.nextLine();
			System.out.print("Enter last name: ");
			String lastname = scanner.nextLine();
			System.out.print("Enter email: ");
			String email = scanner.nextLine();
			System.out.print("Enter mobile number: ");
			String mobileNumber = scanner.nextLine();
			System.out.print("Create a password: ");
			String password = scanner.nextLine();

			// Create a PreparedStatement to execute SQL queries
			String query = "INSERT INTO Customers (FirstName, LastName, Email, phone, Password) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, firstname);
				preparedStatement.setString(2, lastname);
				preparedStatement.setString(3, email);
				preparedStatement.setString(4, mobileNumber);
				preparedStatement.setString(5, password);

				// Execute the query to insert a new customer
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Registration successful!");
				} else {
					System.out.println("Registration failed. Please try again.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle SQL exception
		}
	}

	@Override
	public void displayMenu() {
		System.out.println("\n\n========================================\n");
		System.out.println("=== Electronic Gadget Online Shop ===");
		System.out.println("1. View Product Details");
		System.out.println("2. Search for a Product");
		if (isUserLoggedIn()) {
			System.out.println("3. View Cart");
//			System.out.println("4. Place Order");
			System.out.println("4. View Order History");
			System.out.println("5. View Payment History");
			System.out.println("6. Update Profile");
			System.out.println("7. Logout");
		} else {
			System.out.println("4. Login");
			System.out.println("5. Register");
			System.out.println("6. Exit");
		}

		System.out.print("Enter your choice: ");
	}

	@Override
	public void viewOrderHistory() {
		if (isUserLoggedIn()) {
			System.out.println("Viewing Order History...");
			try {
				displayOrderHistory(loggedInCustomer.getCustomerID());
			} catch (SQLException e) {
				System.out.println("Failed to fetch order history. Please try again later.");
				e.printStackTrace();
			}
		} else {
			System.out.println("Please log in to view order history.");
			System.out.println("Press 1 to login or 0 to skip");
			int inp = scanner.nextInt();
			if (inp == 1) {
				login();
			} else {
				return;
			}
		}
	}

	private void displayOrderHistory(int customerId) throws SQLException {
		try {
			String sql = "SELECT OrderID, OrderDate, TotalAmount FROM Orders WHERE CustomerID = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, customerId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (!resultSet.isBeforeFirst()) {
						System.out.println("You have not made any orders.");
					} else {
						while (resultSet.next()) {
							int orderID = resultSet.getInt("OrderID");
							String orderDate = resultSet.getString("OrderDate");
							double totalAmount = resultSet.getDouble("TotalAmount");

							System.out.println("OrderID: " + orderID);
							System.out.println("OrderDate: " + orderDate);
							System.out.println("TotalAmount: " + totalAmount);
							System.out.println("Order Details:");

							// Fetch and display order details for the current order
							displayOrderDetails(orderID);

							System.out.println("--------------------");
						}
					}
				}
			}
		} catch (AuthorizationException e) {
			e.getMessage();
		}
	}

	private void displayOrderDetails(int orderID) throws SQLException {
		// Fetch and display order details based on the provided orderID
		String detailsSql = "SELECT ProductID, Quantity FROM OrderDetails WHERE OrderID = ?";
		try (PreparedStatement detailsStatement = connection.prepareStatement(detailsSql)) {
			detailsStatement.setInt(1, orderID);
			try (ResultSet detailsResultSet = detailsStatement.executeQuery()) {
				while (detailsResultSet.next()) {
					int productID = detailsResultSet.getInt("ProductID");
					int quantity = detailsResultSet.getInt("Quantity");

					System.out.println("ProductID: " + productID);
					System.out.println("Quantity: " + quantity);
					System.out.println("------------");
				}
			}
		}
	}

	@Override
	public void viewPaymentHistory() {
		if (isUserLoggedIn()) {
			System.out.println("Viewing Payment History...");
			try {
				displayPaymentHistory(loggedInCustomer.getCustomerID());
			} catch (SQLException e) {
				System.out.println("Failed to fetch payment history. Please try again later.");
				e.printStackTrace(); // Log the exception for debugging purposes
			}
		} else {
			System.out.println("Please log in to view payment history.");
		}
	}

	private void displayPaymentHistory(int customerId) throws SQLException {

		try {
			String sql = "SELECT PaymentID, OrderID, PaymentDate, Amount FROM PaymentHistory WHERE UserID = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, customerId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						while (resultSet.next()) {
							int paymentID = resultSet.getInt("PaymentID");
							int orderID = resultSet.getInt("OrderID");
							String paymentDate = resultSet.getString("PaymentDate");
							double amount = resultSet.getDouble("Amount");
							System.out.println("PaymentID: " + paymentID);
							System.out.println("OrderID: " + orderID);
							System.out.println("PaymentDate: " + paymentDate);
							System.out.println("Amount: " + amount);
							System.out.println("--------------------");
						}
					} else {
						System.out.println("You have not made any payments...");
					}
				}
			}
		} catch (AuthorizationException e) {
			e.getMessage();
		}
	}

	@Override
	public void updateProfile() {
		if (isUserLoggedIn()) {
			System.out.println("Updating Profile...");

			String newFirstName = getUserInput2("Enter your new first name: ");
			String newLastName = getUserInput2("Enter your new last name: ");
			String newEmail = getUserInput2("Enter your new email address: ");
			String newMobileNumber = getUserInput2("Enter your new mobile number: ");
			String newPassword = getUserInput2("Enter your new password: ");
			try {
				updateUserProfileInDatabase(newFirstName, newLastName, newEmail, newMobileNumber, newPassword,
						loggedInCustomer.getCustomerID());
				System.out.println("Profile updated successfully!");
			} catch (SQLException e) {
				System.out.println("Failed to update profile. Please try again later.");
				e.printStackTrace(); // Log the exception for debugging purposes
			}
		} else {
			System.out.println("Please log in to update your profile.");
		}
	}

	private String getUserInput2(String prompt) {
		System.out.print(prompt);
//		scanner.nextLine();
		return scanner.nextLine();
	}
	private void updateUserProfileInDatabase(String newFirstName, String newLastName, String newEmail,
			String newMobileNumber, String newPassword, int userID) throws SQLException {
		try {
			// Prepare SQL statement
			String sql = "UPDATE customers SET firstName = ?,lastname= ? , Email = ?, phone = ?, Password = ? WHERE customerid = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, newFirstName);
				preparedStatement.setString(2, newLastName);
				preparedStatement.setString(3, newEmail);
				preparedStatement.setString(4, newMobileNumber);
				preparedStatement.setString(5, newPassword);
				preparedStatement.setInt(6, userID);

				// Execute the update statement
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Your profile has been updated...!");
				} else {
					throw new AuthenticationException("No rows affected. Update failed.");
				}
			}
		} catch (AuthorizationException e) {
			e.getMessage();
		}
	}

	@Override
	public void exit() {
		System.out.println("Exiting the Electronic Gadget Online Shop. Thank you!");
		// Additional cleanup or exit logic if needed
		System.exit(0);
	}

	@Override
	public void viewProductDetails() {
		try {
			System.out.print("Enter the ID of the product you want to view (or enter 0 to go back): ");
			int selectedProductID = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			if (selectedProductID != 0) {
				// Create a PreparedStatement to execute SQL queries
				String query = "SELECT * FROM Products WHERE ProductID = ?";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setInt(1, selectedProductID);

					// Execute the query and retrieve the result set
					try (ResultSet resultSet = preparedStatement.executeQuery()) {
						if (resultSet.next()) {
							// Product found, display details
							System.out.println("Selected Product:");
							System.out.println("Product ID: " + resultSet.getInt("ProductID"));
							System.out.println("Product Name: " + resultSet.getString("ProductName"));
							System.out.println("Product Description: " + resultSet.getString("Description"));
							System.out.println("Price: " + resultSet.getDouble("Price"));

							// Prompt user to add the product to the cart
							System.out.print("Do you want to add this product to your cart? (yes/no): ");
							String addToCartChoice = scanner.nextLine().toLowerCase();

							if (addToCartChoice.equals("yes")) {
								if (isUserLoggedIn()) {
									addToCart(selectedProductID);
									System.out.println("Product added to the cart successfully!");
								} else {
									System.out.println(
											"You are not logged in...\n Please login or register to continue.!");
									System.out.println(" 1.Login\n 2.Register\n 3.Continue viewing products");
									int input = scanner.nextInt();
									if (input == 1) {
										login();
										addToCart(selectedProductID);
										System.out.println("Product added to the cart successfully!");
									} else if (input == 2) {
										register();
										addToCart(selectedProductID);
										System.out.println("Product added to the cart successfully!");
									} else {
										searchProduct();
									}
								}

							} else {
								System.out.println("Product not added to the cart.");
							}
						} else {
							System.out.println("Product not found.");
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle SQL exception
		}
	}

	public Product getProductDetails(int productID) throws SQLException {
		String sql = "SELECT ProductID, ProductName, Description, Price FROM Products WHERE ProductID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, productID);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int retrievedProductID = resultSet.getInt("ProductID");
					String productName = resultSet.getString("ProductName");
					String description = resultSet.getString("Description");
					double price = resultSet.getDouble("Price");

					// Create a Product object with retrieved details
					return new Product(retrievedProductID, productName, description, price);
				}
			}
		}
		return null;
	}

	private boolean isProductInCart(int customerId, int productId) {
		try {
			String sql = "SELECT COUNT(*) FROM cartItems WHERE customerId = ? AND productId = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, customerId);
				preparedStatement.setInt(2, productId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						int count = resultSet.getInt(1);
						return count > 0;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle or log the exception appropriately
		}
		return false;
	}

	public void addToCart(int productID) throws SQLException {
		if (isProductInCart(loggedInCustomer.getCustomerID(), productID)) {
			System.out.println("Product is already in the cart.");
			return;
		} else {
			Product product = getProductDetails(productID);
			if (product != null) {
				System.out.println("Adding product to the cart:");
				System.out.println("Product ID: " + product.getProductID());
				System.out.println("Product Name: " + product.getProductName());
				System.out.println("Description: " + product.getDescription());
				System.out.println("Price: " + product.getPrice());
				int quantity = getUserInputForQuantity();
				CartItem cartItem = new CartItem(product.getProductID(), product.getProductName(), quantity,
						product.getPrice(), loggedInCustomer.getCustomerID());
				insertCartItemIntoDatabase(cartItem);
//                 System.out.println("Product added to the cart successfully!");
			} else {
				System.out.println("Product details not found.");
			}
		}
	}

	private void insertCartItemIntoDatabase(CartItem cartItem) throws SQLException {
		String sql = "INSERT INTO cartItems (customerID,productId,ProductName, Quantity, TotalAmount) "
				+ "VALUES (?,?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, loggedInCustomer.getCustomerID());
			preparedStatement.setInt(2, cartItem.getProductId());
			preparedStatement.setString(3, cartItem.getProductName());
			preparedStatement.setInt(4, cartItem.getQuantity());
			preparedStatement.setDouble(5, cartItem.getTotalAmount());
			int success = preparedStatement.executeUpdate();
			if (success != 1) {
				System.out.println("Oops! Something went wrong. Product not added to cart...");
			} else {
				return;
			}
		}
	}

	private int getUserInputForQuantity() {
		System.out.println("Enter the number of products you want to add...");
		int quantity = scanner.nextInt();
		return quantity; // Placeholder, replace with actual user input logic
	}

	public void searchProduct() {
		try {
			System.out.print("Enter a keyword to search for products: ");
			String searchKeyword = scanner.nextLine();
			String query = "SELECT * FROM Products WHERE ProductName LIKE ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, "%" + searchKeyword + "%");
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					List<Product> searchResults = new ArrayList<>();
					while (resultSet.next()) {
						Product product = new Product();
						product.setProductID(resultSet.getInt("ProductID"));
						product.setProductName(resultSet.getString("ProductName"));
						product.setDescription(resultSet.getString("Description"));
						product.setPrice(resultSet.getDouble("Price"));
						searchResults.add(product);
					}
					if (!searchResults.isEmpty()) {
						// Display search results
						System.out.println("Search Results:");
						for (Product product : searchResults) {
							System.out.println("Product ID: " + product.getProductID());
							System.out.println("Product Name: " + product.getProductName());
							System.out.println("Product Description: " + product.getDescription());
							System.out.println("Price: " + product.getPrice());
							System.out.println("--------------------------------------");
						}

						// Prompt user to choose a product by ID
						System.out.print("Enter the ID of the product you want to view (or enter 0 to go back): ");
						int selectedProductID = scanner.nextInt();
						scanner.nextLine(); // Consume the newline character

						if (selectedProductID != 0) {
							// Assume you have a method to get a specific product by ID
							Product selectedProduct = getProductDetails(selectedProductID);

							if (selectedProduct != null) {
								System.out.println("Selected Product:");
								System.out.println("Product ID: " + selectedProduct.getProductID());
								System.out.println("Product Name: " + selectedProduct.getProductName());
								System.out.println("Product Description: " + selectedProduct.getDescription());
								System.out.println("Price: " + selectedProduct.getPrice());

								// Prompt user to add the product to the cart
								System.out.print("Do you want to add this product to your cart? (yes/no): ");
								String addToCartChoice = scanner.nextLine().toLowerCase();

								if (addToCartChoice.equals("yes")) {
									// Assume you have a method to add a product to the cart
									addToCart(selectedProduct.getProductID());
									System.out.println("Product added to the cart successfully!");
								} else {
									System.out.println("Product not added to the cart.");
								}
							} else {
								System.out.println("Product not found.");
							}
						}
					} else {
						System.out.println("No products found matching the search keyword.");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle SQL exception
		}
	}

	public List<CartItem> getCartItemsForUser(int customerId) throws SQLException {
		List<CartItem> cartItems = new ArrayList<>();

		String sql = "SELECT * FROM cartitems WHERE customerId = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, customerId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int cartItemId = resultSet.getInt("cartItemId");
					int productId = resultSet.getInt("productId");
					String productName = resultSet.getString("productName");
					int quantity = resultSet.getInt("quantity");
					double totalAmount = resultSet.getDouble("totalAmount");

					CartItem cartItem = new CartItem(productId, productName, quantity, totalAmount,
							loggedInCustomer.getCustomerID());
					cartItems.add(cartItem);
				}
			}
		}

		return cartItems;
	}

	public void viewCart() {
		try {
			int customerId = loggedInCustomer.getCustomerID(); // Assuming you have a method to get the customer ID
			List<CartItem> cartItems = getCartItemsForUser(customerId);

			if (cartItems.size() > 0) {
				System.out.println("Cart Contents:");
				for (CartItem cartItem : cartItems) {
					System.out.println("Product ID: " + cartItem.getProductId());
					System.out.println("Product Name: " + cartItem.getProductName());
					System.out.println("Quantity: " + cartItem.getQuantity());
					System.out.println("Total Amount: " + cartItem.getTotalAmount() * cartItem.getQuantity());
					System.out.println("-----------------------------");
				}

				System.out.println("Do you want to proceed with the order..?(yes/no):");
				String response = scanner.next();
				if (response.equalsIgnoreCase("yes")) {
					placeOrder(cartItems); // Pass the list of cart items to the placeOrder method
				} else {
					System.out.println("Order placement canceled.");
				}
			} else {
				System.out.println("Your cart is empty...!");
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle SQL exception
		}
	}

	private void placeOrder(List<CartItem> cartItems) {
		if (isUserLoggedIn()) {
			try {
				System.out.println("Placing Order...");
				Order order = createOrder(cartItems); // Pass the list of cart items to createOrder
				clearCartItems();
				processOrder(order);
			} catch (PaymentFailedException e) {
				System.out.println("Failed to place order: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("An unexpected error occurred while placing the order.");
				e.printStackTrace(); // Log the stack trace for debugging purposes
			}
		} else {
			System.out.println("Please log in to place an order.");
		}
	}

	private Order createOrder(List<CartItem> cartItems) throws SQLException {
		System.out.println("Creating Order...");
		LocalDate orderDate = LocalDate.now();
		Order order = new Order(loggedInCustomer.getCustomerID(), orderDate);
		double totalAmount = 0.0;

		try {
			// Insert into Orders table
			String sqlInsertOrder = "INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES (?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertOrder,
					Statement.RETURN_GENERATED_KEYS)) {
				// Set parameters for the prepared statement
				preparedStatement.setInt(1, loggedInCustomer.getCustomerID());
				preparedStatement.setDate(2, java.sql.Date.valueOf(order.getOrderDate()));
				preparedStatement.setDouble(3, order.getTotalAmount());

				// Execute the insert query
				int rowsAffected = preparedStatement.executeUpdate();

				// Check if the order was inserted successfully
				if (rowsAffected > 0) {
					// Retrieve the generated keys (in this case, just the OrderID)
					try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							// Set the generated OrderID in the Order object
							order.setOrderID(generatedKeys.getInt(1));
							System.out.println("Order placed successfully. OrderID: " + order.getOrderID());
						} else {
							System.out.println("Failed to retrieve OrderID.");
						}
					}
				} else {
					System.out.println("Failed to place the order.");
					return null;
				}
			}

			// Calculate total amount and insert into OrderDetails table
			for (CartItem cartItem : cartItems) {
				Product product = cartItem.getProduct();
				int quantity = cartItem.getQuantity();
				if (isProductAvailableInDatabase(product.getProductID(), quantity)) {
					throw new InsufficientStockException("Stock not available..");
				} else {
					double itemTotalAmount = quantity * product.getPrice();
					totalAmount += itemTotalAmount;

					// Insert into OrderDetails table
					String sqlInsertOrderDetails = "INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES (?, ?, ?)";
					try (PreparedStatement preparedStatementDetails = connection
							.prepareStatement(sqlInsertOrderDetails)) {
						preparedStatementDetails.setInt(1, order.getOrderID());
						preparedStatementDetails.setInt(2, product.getProductID());
						preparedStatementDetails.setInt(3, quantity);

						// Execute the insert query for OrderDetails
						int rowsAffectedDetails = preparedStatementDetails.executeUpdate();
						System.out.println(rowsAffectedDetails + " rows inserted into OrderDetails table.");
			            System.out.println("Order placed successfully!");
					}
				}
			}

			// Update the total amount in the Orders table
			String sqlUpdateTotalAmount = "UPDATE Orders SET TotalAmount = ? WHERE OrderID = ?";
			try (PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlUpdateTotalAmount)) {
				preparedStatementUpdate.setDouble(1, totalAmount);
				preparedStatementUpdate.setInt(2, order.getOrderID());
				preparedStatementUpdate.executeUpdate();
			}

		} catch (InsufficientStockException e) {
			System.out.println(e.getMessage());
//			return null;
		}

		return order;
	}

	public void clearCartItems() {
		try {
			String sql = "DELETE FROM cartItems where customerid = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, loggedInCustomer.getCustomerID());
				// Execute the delete query
				int rowsAffected = preparedStatement.executeUpdate();

				// Optionally, you can check the number of rows affected or log the operation
				System.out.println(rowsAffected + " items have been removed from the cart");
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle SQL exception
		}
	}

	private boolean isProductAvailableInDatabase(int productid, int orderedQuantity) throws SQLException {
		String sql = "SELECT QuantityInStock FROM Inventory WHERE ProductID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, productid);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int quantityInStock = resultSet.getInt("QuantityInStock");
					return quantityInStock >= orderedQuantity;
				}
			}
		}

		return false;
	}

	public void processOrder(Order order) throws PaymentFailedException {
	    try {
	        if (order == null) {
	            System.out.println("Order is null. Order processing failed.");
	            return;
	        }

	        if (loggedInCustomer.getAddress() == null) {
	            System.out.println("Enter your delivery address:");
	            scanner.nextLine();
	            String userAddress = scanner.nextLine();
	            loggedInCustomer.setAddress(userAddress);
	        } else {
	            System.out.println("Your saved addresses:");
	            System.out.println(loggedInCustomer.getAddress());
	        }

	        // Show available payment options (assuming only COD is available)
	        System.out.println("Available Payment Options:");
	        System.out.println("1. Cash on Delivery (COD)");
	        System.out.println("2. Credit/Debit card (Not available for your address)");
	        System.out.print("Choose a payment option (enter the corresponding number): ");
	        int paymentOption = scanner.nextInt();

	        if (paymentOption == 1) {
	            // Assume you have a method to get the current date and time
	            LocalDateTime paymentDate = LocalDateTime.now();

	            // Insert payment details into the paymentHistory table
	            insertPaymentHistory(order.getOrderID(), loggedInCustomer.getCustomerID(), paymentDate,
	                    order.getTotalAmount());

	            System.out.println("Order placed successfully!");
	        } else {
	            System.out.println("Invalid payment option selected. Order processing failed.");
	        }
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred during order processing.");
	        e.printStackTrace(); // Log the stack trace for debugging purposes
	    }
	}


	// Assume you have a method to insert payment details into the paymentHistory
	// table
	private void insertPaymentHistory(int orderID, int userID, LocalDateTime paymentDate, double amount)
			throws SQLException {
		String sql = "INSERT INTO PaymentHistory (OrderID, UserID, PaymentDate, Amount) VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, orderID);
			preparedStatement.setInt(2, userID);
			preparedStatement.setTimestamp(3, Timestamp.valueOf(paymentDate));
			preparedStatement.setDouble(4, amount);
			preparedStatement.executeUpdate();
		}
	}
}
