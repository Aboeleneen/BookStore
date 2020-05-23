package model;

import java.util.ArrayList;

public class Book {

	private int ISBN;
	private String title;
	private ArrayList<String> authors;
	private int publisher_id;
	private String publicationYear;
	private double sellingPrice;
	private int category_id;
	private int quantity;
	private int minimum_quantity;
	private boolean updated;

	public Book(int ISBN, String title, int publisher_id, String publicationYear, double sellingPrice, int category_id,
			int quantity, int minimum_quantity) {
		this.ISBN = ISBN;
		this.title = title;
		this.publisher_id = publisher_id;
		this.publicationYear = publicationYear;
		this.sellingPrice = sellingPrice;
		this.category_id = category_id;
		this.quantity = quantity;
		this.minimum_quantity = minimum_quantity;
		this.updated = false;
	}

	public void insert(String userName, String password) {
		try {
			String query = "call addNewBook(\"" + userName + "\",\"" + password + "\"," + this.ISBN + ",\"" + this.title
					+ "\"," + this.publisher_id + ",\"" + this.publicationYear + "\",\"" + this.sellingPrice + "\","
					+ this.category_id + "," + this.minimum_quantity + "," + this.quantity + ")";
			DataBaseInteraction.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public void update(String userName, String password) {
		if (this.updated) {
			try {
				String query = "call modifyBook(\"" + userName + "\",\"" + password + "\"," + this.ISBN + ",\""
						+ this.title + "\"," + this.publisher_id + ",\"" + this.publicationYear.substring(0, 4) + "\",\""
						+ this.sellingPrice + "\"," + this.category_id + "," + this.minimum_quantity + ","
						+ this.quantity + ")";
				DataBaseInteraction.executeQuery(query);
				this.updated = false;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
		}
	}

	public void delete() {

	}


	// getters and setters
	public int getISBN() {
		return ISBN;
	}

//	public void setISBN(int iSBN) {
//		this.updated = true;
//		ISBN = iSBN;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.updated = true;
		this.title = title;
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String publicationYear) {
		this.updated = true;
		this.publicationYear = publicationYear;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.updated = true;
		this.sellingPrice = sellingPrice;
	}

	public int getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(int publisher_id) {
		this.updated = true;
		this.publisher_id = publisher_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.updated = true;
		this.category_id = category_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.updated = true;
		this.quantity = quantity;
	}

	public int getMinimum_quantity() {
		return minimum_quantity;
	}

	public void setMinimum_quantity(int minimum_quantity) {
		this.updated = true;
		this.minimum_quantity = minimum_quantity;
	}

}
