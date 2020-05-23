package model;

public class Customer extends User {

	public Customer(String userName, String password, String firstName, String lastName, String email,
			String phoneNumber, String shippingAddress) {
		super(userName, password, firstName, lastName, email, phoneNumber, shippingAddress);
	}
}
