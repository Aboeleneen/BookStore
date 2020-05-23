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
public class CartController extends MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button return_button;
    @FXML private Button checkout_button ;
    @FXML private TextField price_field;
    @FXML private VBox items;
    
    final private String return_path = "/View/CustomerHome.fxml" ;
    final private String checkout_path = "/View/Checkout.fxml" ;
    private String current_path;
    
    private Customer current_customer;
    private Manager current_manager;
    private boolean is_customer;
    private ArrayList<Book> shopping_cart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void checkout(ActionEvent event) throws IOException {
        current_path = checkout_path;
        change_scene(event , checkout_path) ;
    }
    
    @FXML
    public void return_home(ActionEvent event) throws IOException {
        current_path = return_path;
        change_scene(event , return_path) ;
    }

    public void initData_customer(Customer current_user, boolean is_customer) throws SQLException {
       this.current_customer = current_user;
       this.is_customer = is_customer;
       this.shopping_cart = current_user.viewShoppingCart();
    }
    
    public void initData_manager(Manager current_user , boolean is_customer) throws SQLException{
        this.current_manager = current_user;
        this.is_customer = is_customer;
        this.shopping_cart = current_user.viewShoppingCart();
    }
    
    
    public void show_shopping_cart(){
    	double pric = 0 ;
    	try {
    	if(is_customer) {
    		pric = current_customer.getTotalCartPrice();
    	}else {
    		pric = current_manager.getTotalCartPrice();
    	}
    	}catch(SQLException e) {
    		
    	}
    	
    	price_field.setText(Double.toString(pric));
    	price_field.setDisable(true);
    	
    	items.getChildren().clear();
    	String cssLayout = "-fx-border-color: red;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n" + 
                "-fx-padding : 10 ; \n";
    	items.setStyle(cssLayout); 
    	for(Book book:shopping_cart) {
    		HBox box = new HBox(20);
    		Text text = new Text("Title : " + book.getTitle());
    		Text price = new Text("price : " + book.getSellingPrice());
    		Button remove = new Button("Remove");
    		remove.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
					if(is_customer) {
						current_customer.removeBookFromShoppingCart(book.getISBN());
					}else {
						current_manager.removeBookFromShoppingCart(book.getISBN());
					}
					}
					catch(SQLException e) {
						
					}
						
				}
    			
    		});
    		box.getChildren().add(text);
    		box.getChildren().add(price);
    		box.getChildren().add(remove);
    		box.setAlignment(Pos.CENTER); 
    		box.setStyle(cssLayout);
    		items.getChildren().add(box);
    	}
    	
    }
    
    @Override
    public void init_controller(FXMLLoader loader){
        if(current_path.equals(checkout_path)){
            CheckoutController controller = loader.getController();
            if(is_customer)
                 controller.initData_customer(current_customer , is_customer);
            else 
                controller.initData_manager(current_manager , is_customer);
        }else{
            if(is_customer){
                  CustomerHomeController controller = loader.getController();
                 controller.initData(current_customer , is_customer);
            }    
            else 
            {
                 ManagerHomeController controller = loader.getController();
                 controller.initData(current_manager , is_customer);
            }
        }
    }
    
}
