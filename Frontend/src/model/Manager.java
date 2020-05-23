package model;

public class Manager extends User{

	public Manager(String userName, String password, String firstName, String lastName, String email,
			String phoneNumber, String shippingAddress) {
		super(userName, password, firstName, lastName, email, phoneNumber, shippingAddress);
		this.setManager(true);
	}
	
	
}
