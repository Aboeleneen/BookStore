package model;

public class Main {

	public static void main(String[] args) {
				Manager new_c ;
                UserCredintial user = new UserCredintial();
                new_c = (Manager) user.signUp("karim" , "karim" , "karim" , "karim" , "karim" , "karim" , "karim");
                System.err.println(new_c.getEmail());
                new_c.addNewBook(1, "title", 1, "2012", 12.5,1,15, 10);
                new_c.addNewBook(10, "title", 1, "2012", 12.5,1,15, 10);
                new_c.addNewBook(100, "title", 1, "2012", 12.5,1,15, 10);
                new_c.addNewBook(1000, "title", 1, "2012", 12.5,1,15, 10);
	}

}
