package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class User {

	private String oldUserName;
	private String oldPassword;
	protected String userName;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String phoneNumber;
	protected String shippingAddress;
	protected boolean isManager;
	private boolean Updated;


	public User(String userName, String password, String firstName, String lastName, String email, String phoneNumber,
			String shippingAddress) {
		this.oldPassword = password;
		this.oldUserName = userName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.shippingAddress = shippingAddress;
		this.isManager = false;
//		this.shoppingCart = new ArrayList<Book>();
	}

	public void insert() throws SQLException {
		try {
			String query = "call signUp(\"" + this.userName + "\",\"" + this.password + "\",\"" + this.lastName
					+ "\",\"" + this.firstName + "\",\"" + this.email + "\",\"" + this.phoneNumber + "\",\""
					+ this.shippingAddress + "\")";
			DataBaseInteraction.executeQuery(query);
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	public void update() throws SQLException {
		if (this.Updated) {
			String query = "call editUserInfo(\"" + this.oldUserName + "\",\"" + this.oldPassword + "\",\""
					+ this.userName + "\",\"" + this.password + "\",\"" + this.lastName + "\",\"" + this.firstName
					+ "\",\"" + this.email + "\",\"" + this.phoneNumber + "\",\"" + this.shippingAddress + "\")";
			try {
				DataBaseInteraction.executeQuery(query);
			} catch (Exception e) {
				throw new SQLException();
			}

			this.oldPassword = this.password;
			this.oldUserName = this.userName;
			this.Updated = false;
		}
	}

	public ArrayList<Book> getBooksByCategory(String category) throws SQLException {
		String query = "call getBooksByCategory(\"" + this.userName + "\",\"" + this.password + "\",\"" + category
				+ "\")";
		return DataBaseInteraction.executeQueryBooks(query);
	}

	public ArrayList<Book> searchForBooks(String searchTerm) throws SQLException {
		String query = "call searchForbooks(\"" + this.userName + "\",\"" + this.password + "\",\"" + searchTerm
				+ "\")";
		return DataBaseInteraction.executeQueryBooks(query);
	}

	// shopping cart operations
	public void addToshoppingCart(Book book) throws SQLException {
		try {
			String query = "call addBookToShoppingCart(\"" + this.userName + "\",\"" + this.password + "\","
					+ book.getISBN() + ")";
			DataBaseInteraction.executeQuery(query);
		} catch (Exception e) {
			throw new SQLException();
		}
//		shoppingCart.add(book);
	}

	public ArrayList<Book> viewShoppingCart() throws SQLException {
		String query = "call viewCartItems(\"" + this.userName + "\",\"" + this.password + "\")";
		return DataBaseInteraction.executeQueryBooks(query);
	}

	public void removeBookFromShoppingCart(int book_isbn) throws SQLException {
		String query = "call removeItemFromCart(\"" + this.userName + "\",\"" + this.password + "\"," + book_isbn + ")";
		DataBaseInteraction.executeQuery(query);
	}

	public double getTotalCartPrice() throws SQLException {
		String query = "call viewCartPrice(\"" + this.userName + "\",\"" + this.password + "\")";
		double price = 0.0;
		try {
			Connection con = DriverManager.getConnection(DataBaseInteraction.DATABASE_URL,
					DataBaseInteraction.DATABASE_USER_NAME, DataBaseInteraction.DATABASE_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				price = rs.getDouble(1);
			}
			con.close();
		} catch (Exception e) {
			throw new SQLException();
		}
		return price;
	}

	public double getBookPriceInCart(int book_isbn) throws SQLException {
		String query = "call viewBookCartPrice(\"" + this.userName + "\",\"" + this.password + "\"," + book_isbn + ")";
		double price = 0.0;
		try {
			Connection con = DriverManager.getConnection(DataBaseInteraction.DATABASE_URL,
					DataBaseInteraction.DATABASE_USER_NAME, DataBaseInteraction.DATABASE_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				price = rs.getDouble(1);
			}
			con.close();
		} catch (Exception e) {
			throw new SQLException();
		}
		return price;
	}

	public void addCreditCard(String credit_num, String ownerName, String CSV, String expiry_date) throws SQLException {
		String query = "call addCreditCard(\"" + this.userName + "\",\"" + this.password + "\",\"" + credit_num
				+ "\",\"" + ownerName + "\",\"" + CSV + "\",\"" + expiry_date + "\")";
		DataBaseInteraction.executeQuery(query);
	}

	public boolean hasCreditCard() throws SQLException {
		String query = "SELECT hasCreditCard(\"" + this.userName + "\",\"" + this.password
				+ "\") FROM credit_card where customer_user_name=\"" + this.userName + "\"";
		boolean status = false;
		try {
			Connection con = DriverManager.getConnection(DataBaseInteraction.DATABASE_URL,
					DataBaseInteraction.DATABASE_USER_NAME, DataBaseInteraction.DATABASE_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				status = rs.getBoolean(1);
			}
			con.close();
		} catch (Exception e) {
			throw new SQLException();
		}
		return status;
	}

	public String buyShoppingCart() throws SQLException {
		if (this.hasCreditCard()) {
			ArrayList<Book> books = this.viewShoppingCart();
			for (Book book : books) {
				DataBaseInteraction.executeQuery("call buyBook(\"" + this.userName + "\",\"" + this.password + "\"," + book.getISBN()
						+ "," + book.getSellingPrice() + ")");
			}
			return "success";
		} else
			return "Credit card isn't available";
	}

	public void Logout() throws SQLException {
		String query = "call Logout(\"" + this.userName + "\",\"" + this.password + "\")";
		DataBaseInteraction.executeQuery(query);
	}


	// getters and setters

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.Updated = true;
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.Updated = true;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.Updated = true;
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.Updated = true;
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.Updated = true;
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.Updated = true;
		this.phoneNumber = phoneNumber;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.Updated = true;
		this.shippingAddress = shippingAddress;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.Updated = true;
		this.isManager = isManager;
	}

//	public ArrayList<Book> getShoppingCart() {
//		return shoppingCart;
//	}
//
//	public void setShoppingCart(ArrayList<Book> shoppingCart) {
//		this.shoppingCart = shoppingCart;
//	}

}
