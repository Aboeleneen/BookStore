package model;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserCredintial s = new UserCredintial();
		Manager user = (Manager) s.login("shar_2", "20211998");

//		user.setUserName("sharshar2");
//		user.setEmail("mahlasdjfioashdiov.com");
		try {
			Book book = user.getBooksByCategory("science").get(0);
//			book.setTitle("ana aho");
//			user.addToshoppingCart(user.getBooksByCategory("science").get(0));
			System.out.println(user.topSellingBooks().size());
			user.addNewBook(928,"demo 3",1,"2020",254,2,7,4);
			
//			System.out.println(user.buyShoppingCart());
//			user.addCreditCard("1234237893754567", "sdlf", "253", "2008-07-04");
			
//			user.Logout();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
