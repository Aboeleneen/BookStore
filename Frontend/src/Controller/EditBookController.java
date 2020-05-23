/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Book;
import model.Manager;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class EditBookController extends MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
	@FXML private TextField ISBN ;
	@FXML private TextField title ;
	@FXML private ChoiceBox publisher ;
	@FXML private TextField year ;
	@FXML private TextField price ;
	@FXML private ChoiceBox category ;
	@FXML private TextField quantity ;
	@FXML private TextField min_quantity ;
	@FXML private Button save_button;
	
	private final String path = "/View/ManagerHome.fxml" ;
	private Manager current_manager;
	private boolean is_customer;
	private Book current_book ;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(Manager current_manager , boolean is_customer , Book current_book) {
    	this.current_manager = current_manager;
    	this.is_customer = is_customer;
    	this.current_book = current_book;
    	this.ISBN.setText(current_book.getISBN());
    	this.title.setText(current_book.getTitle());
    	this.year.setText(current_book.getPublicationYear());
    	this.price.setText(current_book.getSellingPrice());
    	this.quantity.setText(current_book.getQuantity());
    	this.min_quantity.setText(current_book.getMinimum_quantity());
    	init_category();
    	init_publisher();
    	
    }
    
    private void init_publisher() {
		
	}
	
	private void init_category() {
		this.category.getItems().add("Science");
        this.category.getItems().add("Art");
        this.category.getItems().add("Religion");
        this.category.getItems().add("History");
        this.category.getItems().add("Geography");
	}
	
    @FXML
    public void save(ActionEvent event) {
    	try {
    		// update the book
			change_scene(event, path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Override
    public void init_controller(FXMLLoader loader) {
    	ManagerHomeController controller = loader.getController();
    	controller.initData(current_manager, is_customer);
    }
    
}
