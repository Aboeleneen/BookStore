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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ManagerHomeController extends MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button logout_button ;
    @FXML private Button book_button ;
    @FXML private Button edit_button ; 
    @FXML private Button cart_button ;
    @FXML private Button new_button ;
    @FXML private Button place_button ;
    @FXML private Button confirm_button ;
    @FXML private Button promote_button;

    private final String logout_path = "/View/Loading.fxml" ;
    private final String book_path = "/View/Books.fxml" ;
    private final String edit_path = "/View/EditProfile.fxml" ;
    private final String cart_path = "/View/Cart.fxml" ;
    private final String new_path = "/View/AddBook.fxml" ;
    private final String place_path = "/View/PlaceOrders.fxml" ;
    private final String confirm_path = "/View/ConfirmOrders.fxml" ;
    private final String promote_path = "/View/PromoteCustomers.fxml" ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    public void view_books(ActionEvent event) throws IOException {
        change_scene(event , book_path) ;
    }
    
     @FXML
    public void edit_profile(ActionEvent event) throws IOException {
        change_scene(event , edit_path) ;
    }
    
     @FXML
    public void show_cart(ActionEvent event) throws IOException {
        change_scene(event , cart_path) ;
    }
    
     @FXML
    public void add_book(ActionEvent event) throws IOException {
        change_scene(event , new_path) ;
    }
    
     @FXML
    public void place_orders(ActionEvent event) throws IOException {
        change_scene(event , place_path) ;
    }
     @FXML
    public void confirm_orders(ActionEvent event) throws IOException {
        change_scene(event , confirm_path) ;
    }
     @FXML
    public void promote_customers(ActionEvent event) throws IOException {
        change_scene(event , promote_path) ;
    }
     @FXML
    public void logout(ActionEvent event) throws IOException {
        change_scene(event , logout_path) ;
    }
    
            
    
    
    
}
