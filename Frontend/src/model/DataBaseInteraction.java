package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseInteraction {
	public static String DATABASE_URL = "jdbc:mysql://localhost:3306/bookstore";
	public static String DATABASE_USER_NAME = "root";
	public static String DATABASE_PASSWORD = "20211998";
	
	public static void executeQuery(String query) throws SQLException {
		System.out.println(query);
		
		try {
			Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD);
			Statement stmt = con.createStatement();
			 stmt.executeQuery(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}
	
	public static ArrayList<Book> executeQueryBooks(String query) throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		System.out.println(query);
		try {
			Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				books.add(new Book(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5),
						rs.getInt(6), rs.getInt(8), rs.getInt(7)));
			}
			con.close();
		} catch (Exception e) {
			throw new SQLException();
		}
		return books;
	}
	
	
}
