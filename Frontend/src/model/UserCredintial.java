package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserCredintial {
	private String DATABASE_URL = "jdbc:mysql://localhost:3306/bookstore";
	private String DATABASE_USER_NAME = "root";
	private String DATABASE_PASSWORD = "20211998";

	public User signUp(String userName, String password, String firstName, String lastName, String email,
			String phoneNumber, String shippingAddress) {
		User newUser = new Customer(userName, password, firstName, lastName, email, phoneNumber, shippingAddress);
		try {
			newUser.insert();
		} catch (Exception e) {
			return null;
		}
		return newUser;

	}

	public User login(String userName, String password) {
		boolean status = false;
		User user = null;
		try {
			Connection con = DriverManager.getConnection(this.DATABASE_URL, this.DATABASE_USER_NAME,
					this.DATABASE_PASSWORD);
			Statement stmt = con.createStatement();
			String query1 = "SELECT  login(\"" + userName + "\", \"" + password + "\") from user where user_name = \""
					+ userName + "\"";
//			System.out.println(query1);
			ResultSet rs = stmt.executeQuery(query1);
			if (rs.next())
				status = rs.getBoolean(1);
//			System.out.println(status);
			if (status) {
				String query2 = "SELECT  * from user where user_name = \"" + userName + "\"";
				rs = stmt.executeQuery(query2);
				if (rs.next()) {
					if (rs.getBoolean(8))
						user = new Manager(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3),
								rs.getString(5), rs.getString(6), rs.getString(7));
					else
						user = new Customer(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3),
								rs.getString(5), rs.getString(6), rs.getString(7));
				}

			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

}
