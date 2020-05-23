/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Manager;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AddBookController extends MainController implements Initializable {

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
	@FXML private Button add_button;
	
	private final String path = "/View/ManagerHome.fxml" ;
	
	private Manager current_manager ;
    private boolean is_customer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void add(ActionEvent event) {
    	current_manager.addNewBook(ISBN.getText(),
    								title.getText(),
    								publisher.getValue().toString(), 
    								year.getText(),
    								price.getText(), 
    								category.getValue(),
    								quantity.getText(),
    								min_quantity.getText());
    	change_scene(event , path);
    }
	public void initData(Manager current_user, boolean is_customer) {
		// TODO Auto-generated method stub
		this.current_manager = current_user;
		this.is_customer = is_customer;
		init_category();
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
	
	@Override
	public void init_controller(FXMLLoader loader) {
		ManagerHomeController controller = loader.getController();
		controller.initData(current_manager, is_customer);
	}
	
    
}
