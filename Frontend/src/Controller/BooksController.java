/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Book;
import model.Customer;
import model.Manager;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class BooksController extends MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button science_button ;
    @FXML private Button art_button ;
    @FXML private Button religion_button ;
    @FXML private Button history_button ;
    @FXML private Button geography_button ;
    @FXML private Button return_button ;
    @FXML private Button search_button ;
    @FXML private TextField search_bar ;
    @FXML private VBox library;
    
    
    final private String customer_path = "/View/CustomerHome.fxml" ;
    final private String manager_path = "/View/ManagerHome.fxml" ;
    
    private Customer current_customer;
    private Manager current_manager;
    private boolean is_customer;
    private ArrayList<Book> books;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    public void get_science_book(ActionEvent event){
        get_books_category("science");
    }
    
     @FXML
    public void get_art_book(ActionEvent event){
        get_books_category("art");
    }
    
     @FXML
    public void get_religion_book(ActionEvent event){
        get_books_category("religion");
    }
    
     @FXML
    public void get_history_book(ActionEvent event){
        get_books_category("history");
    }
    
     @FXML
    public void get_geography_book(ActionEvent event){
        get_books_category("geography");
    }
    
     @FXML
    public void return_home(ActionEvent event) throws IOException{
    	String return_path = is_customer ? customer_path : manager_path;
        change_scene(event , return_path) ;
    }
    
    @FXML
    public void search(ActionEvent event) throws SQLException {
        if(is_customer) books = current_customer.searchForBooks(search_bar.getText());
        else books = current_manager.searchForBooks(search_bar.getText());
        show_books();
    }
    
    public void get_books_category(String category) {
        try {
            if(is_customer) books = current_customer.getBooksByCategory(category);
            else books = current_manager.getBooksByCategory(category);
            show_books();
        } catch (SQLException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void show_books(){
    	library.getChildren().clear();
    	String cssLayout = "-fx-border-color: red;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n" + 
                "-fx-padding : 10 ; \n";
    	library.setStyle(cssLayout); 
    	for(Book book:books) {
    		HBox box = new HBox(20);
    		Text text = new Text("Title : " + book.getTitle());
    		Text price = new Text("price : " + book.getSellingPrice());
    		Button add = new Button("Add");
    		add.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
					if(is_customer) {
						current_customer.addToshoppingCart(book);
					}else {
						current_manager.addToshoppingCart(book);
					}
					}
					catch(SQLException e) {
						
					}
						
				}
    			
    		});
    		box.getChildren().add(text);
    		box.getChildren().add(price);
    		box.getChildren().add(add);
    		box.setAlignment(Pos.CENTER); 
    		box.setStyle(cssLayout);
    		library.getChildren().add(box);
    	}
        
    }

    public void initData_customer(Customer current_user, boolean is_customer) {
        this.current_customer = current_user;
        this.is_customer = is_customer;
        get_books_category("science");
    }
    
    public void initData_manager(Manager current_user , boolean is_customer){
        this.current_manager = current_user;
        this.is_customer = is_customer;
        get_books_category("science");
    }
    
    @Override
    public void init_controller(FXMLLoader loader){
        if(is_customer){
            CustomerHomeController controller = loader.getController();
            controller.initData(current_customer, is_customer);
        }else{
            ManagerHomeController controller = loader.getController();
            controller.initData(current_manager, is_customer);
        }
    }
    
}
