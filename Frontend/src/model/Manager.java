package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.util.Pair;

public class Manager extends User {

	public Manager(String userName, String password, String firstName, String lastName, String email,
			String phoneNumber, String shippingAddress) {
		super(userName, password, firstName, lastName, email, phoneNumber, shippingAddress);
		this.setManager(true);
	}

	public void addNewBook(int ISBN, String title, int publisher_id, String publicationYear, double sellingPrice,
			int category_id, int quantity, int minimum_quantity) {
		Book newBook = new Book(ISBN, title, publisher_id, publicationYear, sellingPrice, category_id, quantity,
				minimum_quantity);
		newBook.insert(this.userName, this.password);
	}

	public void modifyBook(Book book) {
		book.update(this.userName, this.password);
	}

	public void placeOrder(int book_isbn, int quantity) throws SQLException {
		String query = "call orderBooks(\"" + this.userName + "\",\"" + this.password + "\"," + book_isbn + ','
				+ quantity + ")";
		DataBaseInteraction.executeQuery(query);
	}

	public void confirmOrder(int order_id) throws SQLException {
		String query = "call confirm_order(\"" + this.userName + "\",\"" + this.password + "\"," + order_id + ")";
		DataBaseInteraction.executeQuery(query);
	}

	public void makeManager(String newManagerName) throws SQLException {
		String query = "call makeManager(\"" + this.userName + "\",\"" + this.password + "\",\"" + newManagerName
				+ "\")";
		DataBaseInteraction.executeQuery(query);
	}

	public ArrayList<Pair<String, Integer>> totalBooksSales() throws SQLException {
		String query = "call totalBooksSales(\"" + this.userName + "\",\"" + this.password + "\")";
		return bookSales(query);
	}

	public ArrayList<Pair<String, Double>> topPurchaceCustomers() throws SQLException {
		String query = "call topPurchaceCustomers(\"" + this.userName + "\",\"" + this.password + "\")";
		System.out.println(query);
		ArrayList<Pair<String, Double>> result = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(DataBaseInteraction.DATABASE_URL,
					DataBaseInteraction.DATABASE_USER_NAME, DataBaseInteraction.DATABASE_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				result.add(new Pair<String, Double>(rs.getString(1), rs.getDouble(2)));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}
		return result;
	}

	public ArrayList<Pair<String, Integer>> topSellingBooks() throws SQLException {
		String query = "call topSellingBooks(\"" + this.userName + "\",\"" + this.password + "\")";
		return bookSales(query);
	}

	private ArrayList<Pair<String, Integer>> bookSales(String query) throws SQLException {
		System.out.println(query);
		ArrayList<Pair<String, Integer>> result = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(DataBaseInteraction.DATABASE_URL,
					DataBaseInteraction.DATABASE_USER_NAME, DataBaseInteraction.DATABASE_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				result.add(new Pair<String, Integer>(rs.getString(1), rs.getInt(2)));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}
		return result;
	}
}
