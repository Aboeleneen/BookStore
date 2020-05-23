package model;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserCredintial s = new UserCredintial();
		Customer user = (Customer) s.login("sharshar2", "25425845");

//		user.setUserName("sharshar2");
//		user.setEmail("mahlasdjfioashdiov.com");
		try {
			user.addToshoppingCart(user.getBooksByCategory("science").get(0));
			System.out.println(user.searchForBooks("ti").size());
			System.out.println(user.getTotalCartPrice());
//			user.addCreditCard("1234237893754567", "sdlf", "253", "2008-07-04");
			user.Logout();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
